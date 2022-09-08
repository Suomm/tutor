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
import cn.edu.tjnu.tutor.system.domain.dto.TeachingStudyDTO;
import cn.edu.tjnu.tutor.system.domain.entity.TeachingStudy;
import cn.edu.tjnu.tutor.system.domain.view.TeachingStudyVO;
import cn.edu.tjnu.tutor.system.service.GroupService;
import cn.edu.tjnu.tutor.system.service.TeachingStudyService;
import cn.edu.tjnu.tutor.system.structure.TeachingStudyStruct;
import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Insert;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

import static cn.edu.tjnu.tutor.common.constant.RoleConst.*;
import static cn.edu.tjnu.tutor.common.enums.Category.TEACHING_STUDY;
import static cn.edu.tjnu.tutor.common.enums.ExceptionType.USERS_ARE_NOT_IN_THE_SAME_GROUP;
import static cn.edu.tjnu.tutor.common.enums.OperType.*;

/**
 * 教研活动信息记录控制层。
 *
 * @author 王帅
 * @since 2.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/teachingStudy")
public class TeachingStudyController extends BaseController {

    private final GroupService groupService;
    private final TeachingStudyStruct teachingStudyStruct;
    private final TeachingStudyService teachingStudyService;

    /**
     * 查询教研活动信息。
     *
     * @param query 查询参数
     * @return 分页对象
     * @apiNote 实习生查询本人的教研活动信息。
     */
    @Secured(ROLE_INTERN)
    @GetMapping("page")
    public AjaxResult<PageVO<TeachingStudyVO>> page(@Validated PageQuery query) {
        return pageSuccess(teachingStudyService.pageVO(getUserId(), query.page()));
    }

    /**
     * 查询教研活动信息。
     *
     * @param userId 实习生主键|1
     * @param query  查询参数
     * @return 分页对象
     * @apiNote 小组长评价与导师打分时查询小组内实习生的教研活动信息。
     */
    @GetMapping("page/{userId}")
    @Secured({ROLE_HEADMAN, ROLE_INSTRUCTOR})
    public AjaxResult<PageVO<TeachingStudyVO>> page(@PathVariable Integer userId,
                                                    @Validated PageQuery query) {
        if (groupService.notInSameGroup(getUserId(), userId)) {
            return error(USERS_ARE_NOT_IN_THE_SAME_GROUP);
        }
        return pageSuccess(teachingStudyService.pageVO(userId, query.page()));
    }

    /**
     * 添加教研活动信息。
     *
     * @param teachingStudyDTO 教研活动信息
     * @return {@code code = 200} 添加成功，{@code code = 500} 添加失败
     */
    @Secured(ROLE_INTERN)
    @PostMapping("save")
    @Log(category = TEACHING_STUDY, operType = INSERT)
    public AjaxResult<Void> save(@RequestBody @Validated(Insert.class) TeachingStudyDTO teachingStudyDTO) {
        return toResult(teachingStudyService.save(teachingStudyStruct.toEntity(teachingStudyDTO)));
    }

    /**
     * 更新教研活动信息。
     *
     * @param teachingStudyDTO 教研活动信息
     * @return {@code code = 200} 更新成功，{@code code = 500} 更新失败
     */
    @Secured(ROLE_INTERN)
    @PutMapping("update")
    @Log(category = TEACHING_STUDY, operType = UPDATE)
    public AjaxResult<Void> update(@RequestBody @Validated(Update.class) TeachingStudyDTO teachingStudyDTO) {
        TeachingStudy teachingStudy = teachingStudyStruct.toEntity(teachingStudyDTO);
        return toResult(teachingStudyService.lambdaUpdate()
                .eq(TeachingStudy::getStudyId, teachingStudy.getStudyId())
                .eq(TeachingStudy::getUserId, getUserId())
                .update(teachingStudy));
    }

    /**
     * 删除教研活动信息。
     *
     * @param studyId 教研活动主键|1
     * @return {@code code = 200} 删除成功，{@code code = 500} 删除失败
     */
    @Secured(ROLE_INTERN)
    @DeleteMapping("remove/{studyId}")
    @Log(category = TEACHING_STUDY, operType = DELETE)
    public AjaxResult<Void> remove(@PathVariable Integer studyId) {
        Wrapper<TeachingStudy> wrapper = teachingStudyService.lambdaQuery()
                .eq(TeachingStudy::getStudyId, studyId)
                .eq(TeachingStudy::getUserId, getUserId());
        return toResult(teachingStudyService.remove(wrapper));
    }

    /**
     * 实习小组组长评价小组成员的教研活动。
     *
     * @param userId  实习生主键|1
     * @param studyId 教研活动主键|1
     * @param content 评价内容|继续加油！
     * @return {@code code = 200} 评价成功，{@code code = 500} 评价失败
     */
    @Secured(ROLE_HEADMAN)
    @PutMapping("setRemark/{userId}/{studyId}")
    @Log(category = TEACHING_STUDY, operType = UPDATE)
    public AjaxResult<Void> setRemark(@PathVariable Integer userId,
                                      @PathVariable Integer studyId,
                                      @RequestBody @NotBlank String content) {
        if (groupService.notInSameGroup(getUserId(), userId)) {
            return error(USERS_ARE_NOT_IN_THE_SAME_GROUP);
        }
        return toResult(teachingStudyService.lambdaUpdate()
                .set(TeachingStudy::getGroupEvaluation, content)
                .eq(TeachingStudy::getUserId, userId)
                .eq(TeachingStudy::getStudyId, studyId)
                .update());
    }

    /**
     * 实习小组组长获取小组成员的教研活动评价内容。
     *
     * @param userId  实习生主键|1
     * @param studyId 教研活动主键|1
     * @return 教研活动小组评价
     */
    @Secured(ROLE_HEADMAN)
    @GetMapping("getRemark/{userId}/{studyId}")
    public AjaxResult<String> getRemark(@PathVariable Integer userId,
                                        @PathVariable Integer studyId) {
        if (groupService.notInSameGroup(getUserId(), userId)) {
            return error(USERS_ARE_NOT_IN_THE_SAME_GROUP);
        }
        return success(teachingStudyService.lambdaQuery()
                .select(TeachingStudy::getGroupEvaluation)
                .eq(TeachingStudy::getUserId, userId)
                .eq(TeachingStudy::getStudyId, studyId)
                .oneOpt()
                .map(TeachingStudy::getGroupEvaluation)
                .orElse(CharSequenceUtil.EMPTY));
    }

}