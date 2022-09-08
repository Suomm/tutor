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
import cn.edu.tjnu.tutor.common.validation.groups.Update;
import cn.edu.tjnu.tutor.system.domain.dto.LessonPlanDTO;
import cn.edu.tjnu.tutor.system.domain.entity.LessonPlan;
import cn.edu.tjnu.tutor.system.domain.view.LessonPlanVO;
import cn.edu.tjnu.tutor.system.service.GroupService;
import cn.edu.tjnu.tutor.system.service.LessonPlanService;
import cn.edu.tjnu.tutor.system.structure.LessonPlanStruct;
import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Insert;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

import static cn.edu.tjnu.tutor.common.constant.RoleConst.*;
import static cn.edu.tjnu.tutor.common.enums.Category.LESSON_PLAN;
import static cn.edu.tjnu.tutor.common.enums.ExceptionType.USERS_ARE_NOT_IN_THE_SAME_GROUP;
import static cn.edu.tjnu.tutor.common.enums.OperType.*;

/**
 * 实习教案信息控制层。
 *
 * @author 王帅
 * @since 2.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/lessonPlan")
public class LessonPlanController extends BaseController {

    private final GroupService groupService;
    private final LessonPlanStruct lessonPlanStruct;
    private final LessonPlanService lessonPlanService;

    /**
     * 查询实习教案信息。
     *
     * @param query 查询参数
     * @return 分页对象
     * @apiNote 实习生查询本人的实习教案信息。
     */
    @Secured(ROLE_INTERN)
    @GetMapping("page")
    public AjaxResult<PageVO<LessonPlanVO>> page(@Validated PageQuery query) {
        return pageSuccess(lessonPlanService.pageVO(getUserId(), query.page()));
    }

    /**
     * 查询实习教案信息。
     *
     * @param userId 实习生主键|1
     * @param query  查询参数
     * @return 分页对象
     * @apiNote 小组长评价与导师打分时查询小组内实习生的实习教案信息。
     */
    @GetMapping("page/{userId}")
    @Secured({ROLE_HEADMAN, ROLE_INSTRUCTOR})
    public AjaxResult<PageVO<LessonPlanVO>> list(@PathVariable Integer userId,
                                                 @Validated PageQuery query) {
        if (groupService.notInSameGroup(getUserId(), userId)) {
            return error(USERS_ARE_NOT_IN_THE_SAME_GROUP);
        }
        return pageSuccess(lessonPlanService.pageVO(userId, query.page()));
    }

    /**
     * 添加实习教案信息。
     *
     * @param lessonPlanDTO 实习教案信息
     * @return {@code code = 200} 添加成功，{@code code = 500} 添加失败
     */
    @Secured(ROLE_INTERN)
    @PostMapping("save")
    @Log(category = LESSON_PLAN, operType = INSERT)
    public AjaxResult<Void> save(@RequestBody @Validated(Insert.class) LessonPlanDTO lessonPlanDTO) {
        return toResult(lessonPlanService.save(lessonPlanStruct.toEntity(lessonPlanDTO)));
    }

    /**
     * 更新实习教案信息。
     *
     * @param lessonPlanDTO 实习教案信息
     * @return {@code code = 200} 更新成功，{@code code = 500} 更新失败
     */
    @Secured(ROLE_INTERN)
    @PutMapping("update")
    @Log(category = LESSON_PLAN, operType = UPDATE)
    public AjaxResult<Void> update(@RequestBody @Validated(Update.class) LessonPlanDTO lessonPlanDTO) {
        LessonPlan lessonPlan = lessonPlanStruct.toEntity(lessonPlanDTO);
        return toResult(lessonPlanService.lambdaUpdate()
                .eq(LessonPlan::getPlanId, lessonPlan.getPlanId())
                .eq(LessonPlan::getUserId, getUserId())
                .update(lessonPlan));
    }

    /**
     * 删除实习教案信息。
     *
     * @param planId 实习教案主键|1
     * @return {@code code = 200} 删除成功，{@code code = 500} 删除失败
     */
    @Secured(ROLE_INTERN)
    @DeleteMapping("remove/{planId}")
    @Log(category = LESSON_PLAN, operType = DELETE)
    public AjaxResult<Void> remove(@PathVariable Integer planId) {
        Wrapper<LessonPlan> wrapper = lessonPlanService.lambdaQuery()
                .eq(LessonPlan::getPlanId, planId)
                .eq(LessonPlan::getUserId, getUserId());
        return toResult(lessonPlanService.remove(wrapper));
    }

    /**
     * 实习小组组长评价小组成员的实习教案。
     *
     * @param userId  被评价实习生主键|1
     * @param planId  被评价教案主键|1
     * @param content 评价内容|继续加油！
     * @return {@code code = 200} 评价成功，{@code code = 500} 评价失败
     */
    @Secured(ROLE_HEADMAN)
    @PutMapping("setRemark/{userId}/{planId}")
    @Log(category = LESSON_PLAN, operType = UPDATE)
    public AjaxResult<Void> setRemark(@PathVariable Integer userId,
                                      @PathVariable Integer planId,
                                      @RequestBody @NotBlank String content) {
        if (groupService.notInSameGroup(getUserId(), userId)) {
            return error(USERS_ARE_NOT_IN_THE_SAME_GROUP);
        }
        return toResult(lessonPlanService.lambdaUpdate()
                .set(LessonPlan::getGroupEvaluation, content)
                .eq(LessonPlan::getUserId, userId)
                .eq(LessonPlan::getPlanId, planId)
                .update());
    }

    /**
     * 实习小组组长获取小组成员的实习教案评语内容。
     *
     * @param userId 实习生主键|1
     * @param noteId 教案记录主键|1
     * @return 实习教案小组评价
     */
    @Secured(ROLE_HEADMAN)
    @GetMapping("getRemark/{userId}/{noteId}")
    public AjaxResult<String> getRemark(@PathVariable Integer userId,
                                        @PathVariable Integer noteId) {
        if (groupService.notInSameGroup(getUserId(), userId)) {
            return error(USERS_ARE_NOT_IN_THE_SAME_GROUP);
        }
        return success(lessonPlanService.lambdaQuery()
                .select(LessonPlan::getGroupEvaluation)
                .eq(LessonPlan::getUserId, userId)
                .eq(LessonPlan::getPlanId, noteId)
                .oneOpt()
                .map(LessonPlan::getGroupEvaluation)
                .orElse(CharSequenceUtil.EMPTY));
    }

}