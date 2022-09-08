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
import cn.edu.tjnu.tutor.common.validation.groups.Insert;
import cn.edu.tjnu.tutor.common.validation.groups.Update;
import cn.edu.tjnu.tutor.system.domain.dto.GroupDTO;
import cn.edu.tjnu.tutor.system.domain.entity.Group;
import cn.edu.tjnu.tutor.system.domain.view.GroupVO;
import cn.edu.tjnu.tutor.system.service.GroupService;
import cn.edu.tjnu.tutor.system.structure.GroupStruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.edu.tjnu.tutor.common.constant.RoleConst.*;
import static cn.edu.tjnu.tutor.common.enums.Category.GROUP;
import static cn.edu.tjnu.tutor.common.enums.ExceptionType.GROUP_NAME_ALREADY_EXISTS;
import static cn.edu.tjnu.tutor.common.enums.ExceptionType.USER_ARE_NOT_OWNED_GROUP;
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

    private final GroupStruct groupStruct;
    private final GroupService groupService;

    /**
     * 查询导师小组信息。
     *
     * @param pageQuery 分页参数
     * @return 分页对象
     */
    @GetMapping("page")
    @Secured({ROLE_TUTOR, ROLE_INSTRUCTOR})
    public AjaxResult<PageVO<GroupVO>> page(@Validated PageQuery pageQuery) {
        return pageSuccess(groupService.pageVO(getUserId(), pageQuery.page()));
    }

    /**
     * 查询导师小组成员信息。
     *
     * @param pageQuery 分页参数
     * @return 分页对象
     */
    @GetMapping("list/teacher/{groupId}")
    @Secured({ROLE_TUTOR, ROLE_INSTRUCTOR})
    public AjaxResult<PageVO<GroupVO>> page(@PathVariable String groupId,
                                            @Validated PageQuery pageQuery) {
        if (!groupService.lambdaQuery()
                .eq(Group::getGroupId, groupId)
                .eq(Group::getUserId, getUserId())
                .exists()) {
            return error(USER_ARE_NOT_OWNED_GROUP);
        }
        return pageSuccess(groupService.getUserInfo(getUserId(), ROLE_TUTOR, pageQuery.page()));
    }

    /**
     * 查询导师小组成员信息。
     *
     * @param pageQuery 分页参数
     * @return 分页对象
     */
    @GetMapping("list/leader/{groupId}")
    @Secured({ROLE_TUTOR, ROLE_INSTRUCTOR})
    public AjaxResult<PageVO<GroupVO>> list(@PathVariable String groupId,
                                            @Validated PageQuery pageQuery) {
        if (!groupService.lambdaQuery()
                .eq(Group::getGroupId, groupId)
                .eq(Group::getLeaderId, getUserId())
                .exists()) {
            return error(USER_ARE_NOT_OWNED_GROUP);
        }
        return pageSuccess(groupService.getUserInfo(getUserId(), ROLE_HEADMAN, pageQuery.page()));
    }

    /**
     * 添加导师小组信息。
     *
     * @param groupDTO 导师小组信息
     * @return {@code code = 200} 添加成功，{@code code = 500} 添加失败
     */
    @PostMapping("save")
    @Secured({ROLE_TUTOR, ROLE_INSTRUCTOR})
    @Log(category = GROUP, operType = INSERT)
    public AjaxResult<Void> save(@RequestBody @Validated(Insert.class) GroupDTO groupDTO) {
        Group group = groupStruct.toEntity(groupDTO);
        group.setUserId(getUserId());
        if (groupService.containsName(group)) {
            return error(GROUP_NAME_ALREADY_EXISTS, groupDTO.getGroupName());
        }
        return toResult(groupService.save(group));
    }

    /**
     * 更新导师小组信息。
     *
     * @param group 导师小组信息
     * @return {@code code = 200} 更新成功，{@code code = 500} 更新失败
     */
    @PutMapping("update")
    @Secured({ROLE_TUTOR, ROLE_INSTRUCTOR})
    @Log(category = GROUP, operType = UPDATE)
    public AjaxResult<Void> update(@RequestBody @Validated(Update.class) GroupDTO group) {
        return toResult(groupService.updateById(groupStruct.toEntity(group)));
    }

    /**
     * 删除导师小组信息。
     *
     * @param groupId 小组主键|1
     * @return {@code code = 200} 删除成功，{@code code = 500} 删除失败
     */
    @DeleteMapping("remove/{groupId}")
    @Secured({ROLE_TUTOR, ROLE_INSTRUCTOR})
    @Log(category = GROUP, operType = DELETE)
    public AjaxResult<Void> remove(@PathVariable Integer groupId) {
        return toResult(groupService.removeById(groupId));
    }

}