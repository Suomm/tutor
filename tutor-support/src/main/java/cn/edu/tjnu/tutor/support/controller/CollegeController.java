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
import cn.edu.tjnu.tutor.common.core.domain.query.PageQuery;
import cn.edu.tjnu.tutor.common.core.domain.view.PageVO;
import cn.edu.tjnu.tutor.common.util.ExcelUtils;
import cn.edu.tjnu.tutor.common.validation.groups.Insert;
import cn.edu.tjnu.tutor.common.validation.groups.Update;
import cn.edu.tjnu.tutor.system.domain.dto.CollegeDTO;
import cn.edu.tjnu.tutor.system.domain.entity.College;
import cn.edu.tjnu.tutor.system.domain.view.CollegeVO;
import cn.edu.tjnu.tutor.system.service.CollegeService;
import cn.edu.tjnu.tutor.system.structure.CollegeStruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static cn.edu.tjnu.tutor.common.constant.RoleConst.ROLE_ROOT;
import static cn.edu.tjnu.tutor.common.constant.RoleConst.ROLE_STUDENT;
import static cn.edu.tjnu.tutor.common.enums.Category.COLLEGE;
import static cn.edu.tjnu.tutor.common.enums.ExceptionType.COLLEGE_CODE_ALREADY_EXISTS;
import static cn.edu.tjnu.tutor.common.enums.ExceptionType.COLLEGE_NAME_ALREADY_EXISTS;
import static cn.edu.tjnu.tutor.common.enums.OperType.*;

/**
 * 学院信息控制层。
 *
 * @author 王帅
 * @since 1.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/college")
public class CollegeController extends BaseController {

    private final CollegeStruct collegeStruct;
    private final CollegeService collegeService;

    /**
     * 查询所有学院信息。
     *
     * @param pageQuery 分页参数
     * @return 分页对象
     */
    @Secured(ROLE_ROOT)
    @GetMapping("list")
    public AjaxResult<PageVO<CollegeVO>> page(@Validated PageQuery pageQuery) {
        return pageSuccess(collegeService.pageVO(pageQuery.page()));
    }

    /**
     * 学院信息的下拉列表。
     *
     * @return 所有学院主键和名称
     */
    @GetMapping("selectList")
    @Secured({ROLE_ROOT, ROLE_STUDENT})
    public AjaxResult<List<College>> selectList() {
        return success(collegeService.lambdaQuery()
                .select(College::getCollegeId, College::getCollegeName)
                .eq(College::getVisible, 0)
                .list());
    }

    /**
     * 添加学院信息。
     *
     * @param collegeDTO 学院信息
     * @return {@code code = 200} 添加成功，{@code code = 500} 添加失败
     */
    @PostMapping("save")
    @Log(category = COLLEGE, operType = INSERT)
    public AjaxResult<Void> save(@RequestBody @Validated(Insert.class) CollegeDTO collegeDTO) {
        if (collegeService.containsCode(collegeDTO.getCollegeCode())) {
            return error(COLLEGE_CODE_ALREADY_EXISTS, collegeDTO.getCollegeCode());
        }
        if (collegeService.containsName(collegeDTO.getCollegeName())) {
            return error(COLLEGE_NAME_ALREADY_EXISTS, collegeDTO.getCollegeName());
        }
        return toResult(collegeService.save(collegeStruct.toEntity(collegeDTO)));
    }

    /**
     * 更新学院信息。
     *
     * @param collegeDTO 学院信息
     * @return {@code code = 200} 更新成功，{@code code = 500} 更新失败
     */
    @PutMapping("update")
    @Log(category = COLLEGE, operType = UPDATE)
    public AjaxResult<Void> update(@RequestBody @Validated(Update.class) CollegeDTO collegeDTO) {
        return toResult(collegeService.updateById(collegeStruct.toEntity(collegeDTO)));
    }

    /**
     * 导入 Excel 文档中的数据到数据库。
     *
     * @param file Excel 文档
     * @return {@code code = 200} 导入成功，{@code code = 500} 导入失败
     */
    @PostMapping("importData")
    @Log(category = COLLEGE, operType = IMPORT)
    public AjaxResult<Void> importData(MultipartFile file) {
        return toResult(ExcelUtils.readExcel(file, collegeService));
    }

    /**
     * 导出数据到 Excel 文档。
     *
     * @param response 响应对象
     */
    @GetMapping("exportData")
    public void exportData(HttpServletResponse response) {
        ExcelUtils.writeExcel(response, "学院信息汇总", collegeService, new PageQuery());
    }

    /**
     * 导出 Excel 模板。
     *
     * @param response 响应对象
     */
    @GetMapping("exportTmpl")
    public void exportTmpl(HttpServletResponse response) {
        ExcelUtils.writeTemplate(response, "学院信息模板", collegeService);
    }

}