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
import cn.edu.tjnu.tutor.common.validation.groups.Update;
import cn.edu.tjnu.tutor.system.domain.entity.TheClass;
import cn.edu.tjnu.tutor.system.domain.query.ClassQuery;
import cn.edu.tjnu.tutor.system.domain.view.ClassVO;
import cn.edu.tjnu.tutor.system.service.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

import static cn.edu.tjnu.tutor.common.constant.RoleConst.ROLE_ADMIN;
import static cn.edu.tjnu.tutor.common.constant.RoleConst.ROLE_ROOT;
import static cn.edu.tjnu.tutor.common.enums.Category.CLASS;
import static cn.edu.tjnu.tutor.common.enums.Category.COLLEGE;
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

    private final ClassService classService;

    /**
     * 分页查询班级信息。
     *
     * @param query 分页参数
     * @return 分页对象
     */
    @Secured(ROLE_ROOT)
    @GetMapping("page")
    public AjaxResult<PageVO<ClassVO>> page(@Validated ClassQuery query) {
        return pageSuccess(classService.pageVO(query));
    }

    /**
     * 分页查询班级信息。
     *
     * @param query 分页参数
     * @return 分页对象
     */
    @Secured(ROLE_ADMIN)
    @GetMapping("list")
    public AjaxResult<PageVO<ClassVO>> list(@Validated ClassQuery query) {
        return page(query.setCollegeId(getCollegeId()));
    }

    /**
     * 添加班级信息。
     *
     * @param theClass 班级信息
     * @return {@code code = 200} 添加成功，{@code code = 500} 添加失败
     */
    @PostMapping("save")
    @Secured({ROLE_ROOT, ROLE_ADMIN})
    @Log(category = CLASS, operType = INSERT)
    public AjaxResult<Void> save(@RequestBody @Validated(Insert.class) TheClass theClass) {
        return toResult(classService.save(theClass));
    }

    /**
     * 更新班级信息。
     *
     * @param theClass 班级信息
     * @return {@code code = 200} 更新成功，{@code code = 500} 更新失败
     */
    @PutMapping("update")
    @Secured({ROLE_ROOT, ROLE_ADMIN})
    @Log(category = CLASS, operType = UPDATE)
    public AjaxResult<Void> update(@RequestBody @Validated(Update.class) TheClass theClass) {
        return toResult(classService.updateById(theClass));
    }

    /**
     * 根据班级主键删除班级信息。
     *
     * @param classId 班级主键
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