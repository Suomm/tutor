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

import cn.easyes.core.conditions.LambdaEsQueryWrapper;
import cn.easyes.core.conditions.LambdaEsUpdateWrapper;
import cn.edu.tjnu.tutor.common.annotation.Log;
import cn.edu.tjnu.tutor.common.core.controller.BaseController;
import cn.edu.tjnu.tutor.common.core.domain.AjaxResult;
import cn.edu.tjnu.tutor.common.core.domain.query.PageQuery;
import cn.edu.tjnu.tutor.common.core.domain.view.PageVO;
import cn.edu.tjnu.tutor.system.domain.dto.ActivityDTO;
import cn.edu.tjnu.tutor.system.domain.entity.Group;
import cn.edu.tjnu.tutor.system.domain.model.Activity;
import cn.edu.tjnu.tutor.system.domain.model.Record;
import cn.edu.tjnu.tutor.system.domain.query.ActivityQuery;
import cn.edu.tjnu.tutor.system.domain.view.ActivityVO;
import cn.edu.tjnu.tutor.system.service.ActivityService;
import cn.edu.tjnu.tutor.system.service.GroupService;
import cn.edu.tjnu.tutor.system.service.RecordService;
import cn.edu.tjnu.tutor.system.structure.ActivityStruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static cn.edu.tjnu.tutor.common.constant.RoleConst.ROLE_STUDENT;
import static cn.edu.tjnu.tutor.common.constant.RoleConst.ROLE_TUTOR;
import static cn.edu.tjnu.tutor.common.enums.Category.ACTIVITY;
import static cn.edu.tjnu.tutor.common.enums.ExceptionType.USER_ARE_NOT_OWNED_GROUP;
import static cn.edu.tjnu.tutor.common.enums.OperType.*;

/**
 * 活动信息控制层。
 *
 * @author 王帅
 * @since 1.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/activity")
public class ActivityController extends BaseController {

    private final RecordService recordService;
    private final ActivityStruct activityStruct;
    private final ActivityService activityService;
    private final GroupService groupService;

    /**
     * 查询已完成的活动。
     *
     * @param pageQuery 分页参数
     * @return 所有关联活动
     */
    @GetMapping("list")
    public AjaxResult<PageVO<Record>> list(@Validated PageQuery pageQuery) {
        LambdaEsQueryWrapper<Record> wrapper = recordService.lambdaQuery()
                .eq(Record::getUserId, getUserId());
        return pageSuccess(recordService.page(wrapper, pageQuery));
    }

    /**
     * 查询活动信息。
     *
     * @param query 查询参数
     * @return 分页对象
     */
    @Secured(ROLE_TUTOR)
    @GetMapping("page")
    public AjaxResult<PageVO<ActivityVO>> page(@Validated ActivityQuery query) {
        LambdaEsQueryWrapper<Activity> wrapper = activityService.lambdaQuery()
                .eq(Activity::getUserId, getUserId());
        return pageSuccess(activityService.page(wrapper, query), activityStruct::toVO);
    }

    /**
     * 查询小组内的活动信息。
     *
     * @param groupId 小组主键
     * @param query   查询参数
     * @return 分页对象
     */
    @Secured(ROLE_STUDENT)
    @GetMapping("page/{groupId}")
    public AjaxResult<PageVO<ActivityVO>> page(@PathVariable Integer groupId,
                                               @Validated ActivityQuery query) {
        LambdaEsQueryWrapper<Record> idWrapper = recordService.lambdaQuery()
                .select(Record::getActivityId)
                .eq(Record::getUserId, getUserId());
        List<String> ids = recordService.selectList(idWrapper)
                .stream()
                .map(Record::getActivityId)
                .collect(Collectors.toList());
        // 查询活动时排除已经完成的活动
        LambdaEsQueryWrapper<Activity> wrapper = activityService.lambdaQuery()
                .eq(Activity::getGroupId, groupId)
                .notIn(Activity::getActivityId, ids);
        return pageSuccess(activityService.page(wrapper, query), activityStruct::toVO);
    }

    /**
     * 添加活动信息。
     *
     * @param activityDTO 活动信息
     * @return {@code code = 200} 添加成功，{@code code = 500} 添加失败
     */
    @Secured(ROLE_TUTOR)
    @PostMapping("save")
    @Log(category = ACTIVITY, operType = INSERT)
    public AjaxResult<Void> save(@RequestBody @Validated ActivityDTO activityDTO) {
        Activity activity = activityStruct.toEntity(activityDTO, getLoginUser());
        // 非公开的活动查询并设置小组名称
        if (activity.getGroupId() != 0) {
            String groupName = groupService.lambdaQuery()
                    .select(Group::getGroupName)
                    .eq(Group::getGroupId, activity.getGroupId())
                    .eq(Group::getUserId, activity.getUserId())
                    .oneOpt()
                    .map(Group::getGroupName)
                    .orElse(null);
            // 用户没有拥有这个小组
            if (groupName == null) {
                return error(USER_ARE_NOT_OWNED_GROUP);
            }
            activity.setGroupName(groupName);
        }
        return toResult(activityService.save(activity));
    }

    /**
     * 更新活动信息。
     *
     * @param activityDTO 活动信息
     * @return {@code code = 200} 更新成功，{@code code = 500} 更新失败
     */
    @Secured(ROLE_TUTOR)
    @PostMapping("update")
    @Log(category = ACTIVITY, operType = UPDATE)
    public AjaxResult<Void> update(@RequestBody @Validated ActivityDTO activityDTO) {
        Activity activity = activityStruct.toEntity(activityDTO);
        LambdaEsUpdateWrapper<Activity> wrapper = activityService.lambdaUpdate()
                .eq(Activity::getActivityId, activity.getActivityId())
                .eq(Activity::getUserId, getUserId());
        return toResult(activityService.update(activity, wrapper));
    }

    /**
     * 删除活动信息。
     *
     * @param activityId 活动主键|oeLbA4MBq8gxIEWz6
     * @return 总是返回 {@code code = 200} 删除成功
     */
    @Secured(ROLE_TUTOR)
    @DeleteMapping("remove/{activityId}")
    @Log(category = ACTIVITY, operType = DELETE)
    public AjaxResult<Void> remove(@PathVariable String activityId) {
        // 删除活动记录信息
        recordService.delete(recordService.lambdaQuery()
                .eq(Record::getActivityId, activityId));
        // 删除活动信息
        LambdaEsQueryWrapper<Activity> wrapper = activityService.lambdaQuery()
                .eq(Activity::getActivityId, activityId)
                .eq(Activity::getUserId, getUserId());
        return toResult(activityService.delete(wrapper));
    }

}