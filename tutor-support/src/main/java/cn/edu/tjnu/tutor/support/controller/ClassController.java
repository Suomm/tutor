/*
 * Copyright 2021-2022 the original author and authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.edu.tjnu.tutor.support.controller;

import cn.edu.tjnu.tutor.common.annotation.Log;
import cn.edu.tjnu.tutor.common.core.controller.BaseController;
import cn.edu.tjnu.tutor.common.core.domain.AjaxResult;
import cn.edu.tjnu.tutor.common.core.domain.view.PageVO;
import cn.edu.tjnu.tutor.common.util.ExcelUtils;
import cn.edu.tjnu.tutor.common.validation.groups.Insert;
import cn.edu.tjnu.tutor.common.validation.groups.Select;
import cn.edu.tjnu.tutor.common.validation.groups.Update;
import cn.edu.tjnu.tutor.system.domain.dto.ClassDTO;
import cn.edu.tjnu.tutor.system.domain.entity.Major;
import cn.edu.tjnu.tutor.system.domain.entity.TheClass;
import cn.edu.tjnu.tutor.system.domain.query.ClassQuery;
import cn.edu.tjnu.tutor.system.domain.view.ClassVO;
import cn.edu.tjnu.tutor.system.service.ClassService;
import cn.edu.tjnu.tutor.system.service.MajorService;
import cn.edu.tjnu.tutor.system.structure.ClassStruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static cn.edu.tjnu.tutor.common.constant.RoleConst.*;
import static cn.edu.tjnu.tutor.common.enums.Category.CLASS;
import static cn.edu.tjnu.tutor.common.enums.Category.COLLEGE;
import static cn.edu.tjnu.tutor.common.enums.ExceptionType.CLASS_NAME_ALREADY_EXISTS;
import static cn.edu.tjnu.tutor.common.enums.ExceptionType.MAJOR_NOT_EXISTS;
import static cn.edu.tjnu.tutor.common.enums.OperType.*;

/**
 * 班级信息控制层。
 *
 * @author 王帅
 * @since 1.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/theClass")
public class ClassController extends BaseController {

    private final ClassStruct classStruct;
    private final ClassService classService;
    private final MajorService majorService;

    /**
     * 查询班级信息。
     *
     * @param classQuery 班级信息查询参数
     * @return 分页对象
     */
    @Secured(ROLE_ROOT)
    @GetMapping("page")
    public AjaxResult<PageVO<ClassVO>> page(@Validated ClassQuery classQuery) {
        return pageSuccess(classService.pageVO(classQuery));
    }

    /**
     * 查询所在学院的班级信息。
     *
     * @param classQuery 班级信息查询参数
     * @return 分页对象
     */
    @Secured(ROLE_ADMIN)
    @GetMapping("list")
    public AjaxResult<PageVO<ClassVO>> list(@Validated(Select.class) ClassQuery classQuery) {
        classQuery.setCollegeId(getCollegeId());
        return page(classQuery);
    }

    /**
     * 班级信息的下拉列表。
     *
     * @param majorId 所属专业主键|1
     * @param grade   所属年级信息|20
     * @return 班级主键和名称
     */
    @Secured(ROLE_STUDENT)
    @GetMapping("selectList/{majorId}/{grade}")
    public AjaxResult<List<TheClass>> selectList(@PathVariable String grade,
                                                 @PathVariable Integer majorId) {
        return success(classService.lambdaQuery()
                .select(TheClass::getClassId, TheClass::getClassName)
                .eq(TheClass::getMajorId, majorId)
                .eq(TheClass::getGrade, grade)
                .list());
    }

    /**
     * 添加班级信息。
     *
     * @param classDTO 班级信息
     * @return {@code code = 200} 添加成功，{@code code = 500} 添加失败
     */
    @PostMapping("save")
    @Secured({ROLE_ROOT, ROLE_ADMIN})
    @Log(category = CLASS, operType = INSERT)
    public AjaxResult<Void> save(@RequestBody @Validated(Insert.class) ClassDTO classDTO) {
        if (!majorService.lambdaQuery()
                .eq(Major::getMajorId, classDTO.getMajorId())
                .exists()) {
            return error(MAJOR_NOT_EXISTS);
        }
        if (classService.containsName(classDTO.getClassName())) {
            return error(CLASS_NAME_ALREADY_EXISTS, classDTO.getClassName());
        }
        return toResult(classService.save(classStruct.toEntity(classDTO)));
    }

    /**
     * 更新班级信息。
     *
     * @param classDTO 班级信息
     * @return {@code code = 200} 更新成功，{@code code = 500} 更新失败
     */
    @PutMapping("update")
    @Secured({ROLE_ROOT, ROLE_ADMIN})
    @Log(category = CLASS, operType = UPDATE)
    public AjaxResult<Void> update(@RequestBody @Validated(Update.class) ClassDTO classDTO) {
        return toResult(classService.updateById(classStruct.toEntity(classDTO)));
    }

    /**
     * 删除班级信息。
     *
     * @param classId 班级主键|1
     * @return {@code code = 200} 删除成功，{@code code = 500} 删除失败
     */
    @Secured({ROLE_ROOT, ROLE_ADMIN})
    @DeleteMapping("remove/{classId}")
    @Log(category = CLASS, operType = DELETE)
    public AjaxResult<Void> remove(@PathVariable Integer classId) {
        return toResult(classService.removeById(classId));
    }

    /**
     * 导入 Excel 文档中的数据到数据库。
     *
     * @param file Excel 文档
     * @return {@code code = 200} 导入成功，{@code code = 500} 导入失败
     */
    @PostMapping("importData")
    @Secured({ROLE_ROOT, ROLE_ADMIN})
    @Log(category = COLLEGE, operType = IMPORT)
    public AjaxResult<Void> importData(MultipartFile file) {
        return toResult(ExcelUtils.readExcel(file, classService));
    }

    /**
     * 导出数据到 Excel 文档。
     *
     * @param response 响应对象
     */
    @Secured(ROLE_ROOT)
    @GetMapping("exportData")
    public void exportData(HttpServletResponse response) {
        ExcelUtils.writeExcel(response, "班级信息汇总", classService);
    }

    /**
     * 导出 Excel 模板。
     *
     * @param response 响应对象
     */
    @GetMapping("exportTmpl")
    @Secured({ROLE_ROOT, ROLE_ADMIN})
    public void exportTmpl(HttpServletResponse response) {
        ExcelUtils.writeTemplate(response, "班级信息模板", classService);
    }

}