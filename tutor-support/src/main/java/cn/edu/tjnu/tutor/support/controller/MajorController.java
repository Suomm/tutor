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
import cn.edu.tjnu.tutor.system.domain.dto.MajorDTO;
import cn.edu.tjnu.tutor.system.domain.entity.College;
import cn.edu.tjnu.tutor.system.domain.entity.Major;
import cn.edu.tjnu.tutor.system.domain.query.MajorQuery;
import cn.edu.tjnu.tutor.system.domain.view.MajorVO;
import cn.edu.tjnu.tutor.system.service.CollegeService;
import cn.edu.tjnu.tutor.system.service.MajorService;
import cn.edu.tjnu.tutor.system.structure.MajorStruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static cn.edu.tjnu.tutor.common.constant.RoleConst.ROLE_ADMIN;
import static cn.edu.tjnu.tutor.common.constant.RoleConst.ROLE_ROOT;
import static cn.edu.tjnu.tutor.common.enums.Category.COLLEGE;
import static cn.edu.tjnu.tutor.common.enums.Category.MAJOR;
import static cn.edu.tjnu.tutor.common.enums.ExceptionType.COLLEGE_NOT_EXISTS;
import static cn.edu.tjnu.tutor.common.enums.ExceptionType.MAJOR_NAME_ALREADY_EXISTS;
import static cn.edu.tjnu.tutor.common.enums.OperType.*;

/**
 * 专业信息控制层。
 *
 * @author 王帅
 * @since 2.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/major")
public class MajorController extends BaseController {

    private final MajorStruct majorStruct;
    private final MajorService majorService;
    private final CollegeService collegeService;

    /**
     * 分页查询所有的专业信息。
     *
     * @param majorQuery 专业信息查询参数
     * @return 分页对象
     */
    @Secured(ROLE_ROOT)
    @GetMapping("page")
    public AjaxResult<PageVO<MajorVO>> page(@Validated MajorQuery majorQuery) {
        return pageSuccess(majorService.pageVO(majorQuery));
    }

    /**
     * 分页查询所在学院内的所有专业信息。
     *
     * @return 专业信息
     */
    @Secured(ROLE_ADMIN)
    @GetMapping("list")
    public AjaxResult<PageVO<MajorVO>> list() {
        MajorQuery majorQuery = new MajorQuery();
        majorQuery.setCollegeId(getCollegeId());
        return page(majorQuery);
    }

    /**
     * 专业信息的下拉列表。
     *
     * @return 专业主键和名称
     */
    @Secured(ROLE_ADMIN)
    @GetMapping("selectList")
    public AjaxResult<List<Major>> selectList() {
        return selectList(getCollegeId());
    }

    /**
     * 指定学院下专业信息的下拉列表。
     *
     * @param collegeId 学院主键
     * @return 专业主键和名称
     */
    @Secured(ROLE_ROOT)
    @GetMapping("selectList/{collegeId}")
    public AjaxResult<List<Major>> selectList(@PathVariable Integer collegeId) {
        return success(majorService.lambdaQuery()
                .select(Major::getMajorId, Major::getMajorName)
                .eq(Major::getCollegeId, collegeId)
                .list());
    }

    /**
     * 添加专业信息。
     *
     * @param majorDTO 专业信息
     * @return {@code code = 200} 添加成功，{@code code = 500} 添加失败
     */
    @PostMapping("save")
    @Secured({ROLE_ROOT, ROLE_ADMIN})
    @Log(category = MAJOR, operType = INSERT)
    public AjaxResult<Void> save(@RequestBody @Validated(Insert.class) MajorDTO majorDTO) {
        if (!collegeService.lambdaQuery()
                .eq(College::getCollegeId, majorDTO.getCollegeId())
                .exists()) {
            return error(COLLEGE_NOT_EXISTS);
        }
        if (majorService.containsName(majorDTO.getMajorName())) {
            return error(MAJOR_NAME_ALREADY_EXISTS, majorDTO.getMajorName());
        }
        return toResult(majorService.save(majorStruct.toEntity(majorDTO)));
    }

    /**
     * 更新专业信息。
     *
     * @param majorDTO 专业信息
     * @return {@code code = 200} 更新成功，{@code code = 500} 更新失败
     */
    @PutMapping("update")
    @Secured({ROLE_ROOT, ROLE_ADMIN})
    @Log(category = MAJOR, operType = UPDATE)
    public AjaxResult<Void> update(@RequestBody @Validated(Update.class) MajorDTO majorDTO) {
        return toResult(majorService.updateById(majorStruct.toEntity(majorDTO)));
    }

    /**
     * 根据专业主键删除专业信息。
     *
     * @param majorId 专业主键
     * @return {@code code = 200} 删除成功，{@code code = 500} 删除失败
     */
    @Secured({ROLE_ROOT, ROLE_ADMIN})
    @DeleteMapping("remove/{majorId}")
    @Log(category = MAJOR, operType = DELETE)
    public AjaxResult<Void> remove(@PathVariable Integer majorId) {
        return toResult(majorService.removeById(majorId));
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
        return toResult(ExcelUtils.readExcel(file, majorService));
    }

    /**
     * 导出数据到 Excel 文档。
     *
     * @param response 响应对象
     */
    @Secured(ROLE_ROOT)
    @GetMapping("exportData")
    public void exportData(HttpServletResponse response) {
        ExcelUtils.writeExcel(response, "专业信息汇总", majorService);
    }

    /**
     * 导出 Excel 模板。
     *
     * @param response 响应对象
     */
    @GetMapping("exportTmpl")
    @Secured({ROLE_ROOT, ROLE_ADMIN})
    public void exportTmpl(HttpServletResponse response) {
        ExcelUtils.writeTemplate(response, "专业信息模板", majorService);
    }

}