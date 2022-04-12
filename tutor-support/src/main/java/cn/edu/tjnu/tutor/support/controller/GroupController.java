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
import cn.edu.tjnu.tutor.common.core.domain.PageQuery;
import cn.edu.tjnu.tutor.common.core.domain.Pagination;
import cn.edu.tjnu.tutor.common.util.PageUtils;
import cn.edu.tjnu.tutor.system.domain.entity.Group;
import cn.edu.tjnu.tutor.system.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.edu.tjnu.tutor.common.enums.Category.GROUP;
import static cn.edu.tjnu.tutor.common.enums.OperType.*;

/**
 * 导师小组信息控制层。
 *
 * @author 王帅
 * @since 2.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/group")
public class GroupController extends BaseController {

    private final GroupService groupService;

    /**
     * 分页查询导师小组信息。
     *
     * @param pageQuery 分页帮助
     * @return 分页对象
     */
    @GetMapping("list")
    public AjaxResult<Pagination<Group>> list(PageQuery pageQuery) {
        return success(PageUtils.convert(groupService.page(pageQuery.page())));
    }

    /**
     * 根据小组主键获取详细信息。
     *
     * @param groupId 小组主键
     * @return 导师小组信息详情
     */
    @GetMapping("getInfo/{groupId}")
    public AjaxResult<Group> getInfo(@PathVariable Integer groupId) {
        return success(groupService.getById(groupId));
    }

    /**
     * 添加导师小组信息。
     *
     * @param group 导师小组信息
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    @Log(category = GROUP, operType = INSERT)
    public AjaxResult<Void> save(@Validated @RequestBody Group group) {
        return toResult(groupService.save(group));
    }

    /**
     * 更新导师小组信息。
     *
     * @param group 导师小组信息
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    @Log(category = GROUP, operType = UPDATE)
    public AjaxResult<Void> update(@Validated @RequestBody Group group) {
        return toResult(groupService.updateById(group));
    }

    /**
     * 根据小组主键删除导师小组信息。
     *
     * @param groupId 小组主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{groupId}")
    @Log(category = GROUP, operType = DELETE)
    public AjaxResult<Void> remove(@PathVariable Integer groupId) {
        return toResult(groupService.removeById(groupId));
    }

}