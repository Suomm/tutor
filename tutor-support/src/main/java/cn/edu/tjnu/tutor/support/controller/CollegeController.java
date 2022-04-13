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
import cn.edu.tjnu.tutor.common.core.domain.dto.PageDTO;
import cn.edu.tjnu.tutor.common.core.domain.view.PageVO;
import cn.edu.tjnu.tutor.system.domain.entity.College;
import cn.edu.tjnu.tutor.system.service.CollegeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.edu.tjnu.tutor.common.constant.RoleConst.ROLE_ROOT;
import static cn.edu.tjnu.tutor.common.enums.Category.COLLEGE;
import static cn.edu.tjnu.tutor.common.enums.OperType.*;

/**
 * 学院信息控制层。
 *
 * @author 王帅
 * @since 1.0
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/college")
public class CollegeController extends BaseController {

    private final CollegeService collegeService;

    /**
     * 分页查询学院信息。
     *
     * @param pageDTO 分页参数
     * @return 分页对象
     */
    @Secured(ROLE_ROOT)
    @GetMapping("list")
    public AjaxResult<PageVO<College>> list(PageDTO pageDTO) {
        return pageSuccess(collegeService.page(pageDTO.page()));
    }

    /**
     * 根据学院主键获取详细信息。
     *
     * @param collegeId 学院主键
     * @return 学院信息详情
     */
    @GetMapping("getInfo/{collegeId}")
    public AjaxResult<College> getInfo(@PathVariable Integer collegeId) {
        return success(collegeService.getById(collegeId));
    }

    /**
     * 添加学院信息。
     *
     * @param college 学院信息
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    @Log(category = COLLEGE, operType = INSERT)
    public AjaxResult<Void> save(@RequestBody College college) {
        return toResult(collegeService.save(college));
    }

    /**
     * 更新学院信息。
     *
     * @param college 学院信息
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    @Log(category = COLLEGE, operType = UPDATE)
    public AjaxResult<Void> update(@RequestBody College college) {
        return toResult(collegeService.updateById(college));
    }

    /**
     * 根据学院主键删除学院信息。
     *
     * @param collegeId 学院主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{collegeId}")
    @Log(category = COLLEGE, operType = DELETE)
    public AjaxResult<Void> remove(@PathVariable Integer collegeId) {
        return toResult(collegeService.removeById(collegeId));
    }

}