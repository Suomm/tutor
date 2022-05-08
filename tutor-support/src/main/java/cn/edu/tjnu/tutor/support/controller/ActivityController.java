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
import cn.edu.tjnu.tutor.common.validation.groups.Insert;
import cn.edu.tjnu.tutor.system.domain.model.Activity;
import cn.edu.tjnu.tutor.system.domain.model.Record;
import cn.edu.tjnu.tutor.system.repository.ActivityRepository;
import cn.edu.tjnu.tutor.system.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.edu.tjnu.tutor.common.constant.RoleConst.ROLE_TUTOR;
import static cn.edu.tjnu.tutor.common.enums.Category.ACTIVITY;
import static cn.edu.tjnu.tutor.common.enums.OperType.DELETE;
import static cn.edu.tjnu.tutor.common.enums.OperType.INSERT;

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

    private final RecordRepository   recordRepository;
    private final ActivityRepository activityRepository;

    /**
     * 根据用户主键查询参加的的活动。
     *
     * @param pageDTO 分页参数
     * @return 所有关联活动
     */
    @GetMapping("list")
    public AjaxResult<PageVO<Record>> list(@Validated PageDTO pageDTO) {
        return pageSuccess(recordRepository.findAllByUserId(getUserId(), pageDTO.pageable()));
    }

    /**
     * 根据活动主键获取详细信息。
     *
     * @param activityId 活动主键
     * @return 活动信息详情
     */
    @GetMapping("getInfo/{activityId}")
    public AjaxResult<Activity> getInfo(@PathVariable Integer activityId) {
        return success(activityRepository.findById(activityId).orElse(null));
    }

    /**
     * 添加活动信息。
     *
     * @param activity 活动信息
     * @return {@code code = 200} 添加成功，{@code code = 500} 添加失败
     */
    @Secured(ROLE_TUTOR)
    @PostMapping("save")
    @Log(category = ACTIVITY, operType = INSERT)
    public AjaxResult<Void> save(@RequestBody @Validated(Insert.class) Activity activity) {
        return toResult(activityRepository.save(activity).getActivityId() != null);
    }

    /**
     * 根据活动主键删除活动信息。
     *
     * @param activityId 活动主键
     * @return 总是返回 {@code code = 200} 删除成功
     */
    @Secured(ROLE_TUTOR)
    @DeleteMapping("remove/{activityId}")
    @Log(category = ACTIVITY, operType = DELETE)
    public AjaxResult<Void> remove(@PathVariable Integer activityId) {
        activityRepository.deleteById(activityId);
        return AjaxResult.SUCCESS;
    }

}