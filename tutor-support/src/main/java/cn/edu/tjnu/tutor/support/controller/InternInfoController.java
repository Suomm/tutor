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
import cn.edu.tjnu.tutor.system.domain.dto.GroupMarkDTO;
import cn.edu.tjnu.tutor.system.domain.dto.InternInfoDTO;
import cn.edu.tjnu.tutor.system.domain.dto.MarkDTO;
import cn.edu.tjnu.tutor.system.domain.entity.InternInfo;
import cn.edu.tjnu.tutor.system.domain.view.*;
import cn.edu.tjnu.tutor.system.service.GroupService;
import cn.edu.tjnu.tutor.system.service.InternInfoService;
import cn.edu.tjnu.tutor.system.structure.InternInfoStruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

import static cn.edu.tjnu.tutor.common.constant.RoleConst.*;
import static cn.edu.tjnu.tutor.common.enums.Category.INTERN_INFO;
import static cn.edu.tjnu.tutor.common.enums.ExceptionType.USERS_ARE_NOT_IN_THE_SAME_GROUP;
import static cn.edu.tjnu.tutor.common.enums.OperType.UPDATE;

/**
 * 教育实习基本信息控制层。
 *
 * @author 王帅
 * @since 2.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/internInfo")
public class InternInfoController extends BaseController {

    private final GroupService groupService;
    private final InternInfoStruct internInfoStruct;
    private final InternInfoService internInfoService;

    /**
     * 获取教育实习基本信息。
     *
     * @return 教育实习基本信息
     * @apiNote 实习生查询本人的教育实习基本信息。
     */
    @Secured(ROLE_INTERN)
    @GetMapping("getInfo")
    public AjaxResult<InternInfoVO> getInfo() {
        return success(internInfoService.getInfo(getUserId()));
    }

    /**
     * 根据实习生主键主键获取教育实习基本信息。
     *
     * @param userId 实习生主键|1
     * @return 教育实习基本信息
     * @apiNote 教育实习指导教师查询指定实习生的教育实习基本信息。
     */
    @Secured(ROLE_INSTRUCTOR)
    @GetMapping("getInfo/{userId}")
    public AjaxResult<InternInfoVO> getInfo(@PathVariable Integer userId) {
        if (groupService.notInSameGroup(getUserId(), userId)) {
            return error(USERS_ARE_NOT_IN_THE_SAME_GROUP);
        }
        return success(internInfoService.getInfo(userId));
    }

    /**
     * 获取教育实习成绩信息。
     *
     * @return 教育实习成绩
     * @apiNote 实习生查询本人的教育实习成绩单。
     */
    @Secured(ROLE_INTERN)
    @GetMapping("getReport")
    public AjaxResult<ReportVO> getReport() {
        return success(internInfoService.getReport(getUserId()));
    }

    /**
     * 根据实习生主键获取教育实习成绩信息。
     *
     * @param userId 实习生主键|1
     * @return 教育实习成绩
     * @apiNote 教育实习指导教师查询指定实习生的成绩单。
     */
    @Secured(ROLE_INSTRUCTOR)
    @GetMapping("getReport/{userId}")
    public AjaxResult<ReportVO> getReport(@PathVariable Integer userId) {
        if (groupService.notInSameGroup(getUserId(), userId)) {
            return error(USERS_ARE_NOT_IN_THE_SAME_GROUP);
        }
        return success(internInfoService.getReport(userId));
    }

    /**
     * 获取教育实习评价信息。
     *
     * @return 教育实习评价
     * @apiNote 实习生查询本人的教育实习评价信息。
     */
    @Secured(ROLE_INTERN)
    @GetMapping("getRemark")
    public AjaxResult<RemarkVO> getRemark() {
        return success(internInfoService.getRemark(getUserId()));
    }

    /**
     * 根据实习生主键获取教育实习评价信息。
     *
     * @param userId 实习生主键|1
     * @return 教育实习评价
     * @apiNote 教育实习指导教师查询指定实习生的教育实习评价信息。
     */
    @Secured(ROLE_INSTRUCTOR)
    @GetMapping("getRemark/{userId}")
    public AjaxResult<RemarkVO> getRemark(@PathVariable Integer userId) {
        if (groupService.notInSameGroup(getUserId(), userId)) {
            return error(USERS_ARE_NOT_IN_THE_SAME_GROUP);
        }
        return success(internInfoService.getRemark(userId));
    }

    /**
     * 添加或者更新教育实习基本信息。
     *
     * @param internInfoDTO 教育实习基本信息
     * @return {@code code = 200} 操作成功，{@code code = 500} 操作失败
     */
    @Secured(ROLE_INTERN)
    @Log(category = INTERN_INFO)
    @PostMapping("saveOrUpdate")
    public AjaxResult<Void> saveOrUpdate(@Validated @RequestBody InternInfoDTO internInfoDTO) {
        InternInfo internInfo = internInfoStruct.toEntity(internInfoDTO);
        if (internInfoService.lambdaQuery()
                .eq(InternInfo::getUserId, getUserId())
                .exists()) {
            return toResult(internInfoService.lambdaUpdate()
                    .eq(InternInfo::getUserId, getUserId())
                    .update(internInfo));
        }
        return toResult(internInfoService.save(internInfo));
    }

    /**
     * 更新教育实习个人总结。
     *
     * @param content 个人总结内容|我已出舱，感觉良好！
     * @return {@code code = 200} 操作成功，{@code code = 500} 操作失败
     */
    @Secured(ROLE_INTERN)
    @PutMapping("setSummary")
    public AjaxResult<Void> setSummary(@RequestBody @NotBlank String content) {
        return toResult(internInfoService.setComment(getUserId(), InternInfoService.GR, content));
    }

    /**
     * 更新教育实习中学教师评语。
     *
     * @param userId  实习生主键|1
     * @param content 中学教师评语|挺好的！
     * @return {@code code = 200} 操作成功，{@code code = 500} 操作失败
     */
    @Secured(ROLE_INSTRUCTOR)
    @PutMapping("remark/zx/{userId}")
    public AjaxResult<Void> remarkZx(@PathVariable Integer userId,
                                     @RequestBody @NotBlank String content) {
        if (groupService.notInSameGroup(getUserId(), userId)) {
            return error(USERS_ARE_NOT_IN_THE_SAME_GROUP);
        }
        return toResult(internInfoService.setComment(userId, InternInfoService.ZX, content));
    }

    /**
     * 更新教育实习高校教师评语。
     *
     * @param userId  实习生主键|1
     * @param content 高校教师评语|挺好的！
     * @return {@code code = 200} 操作成功，{@code code = 500} 操作失败
     */
    @Secured(ROLE_INSTRUCTOR)
    @PutMapping("remark/gx/{userId}")
    public AjaxResult<Void> remarkGx(@PathVariable Integer userId,
                                     @RequestBody @NotBlank String content) {
        if (groupService.notInSameGroup(getUserId(), userId)) {
            return error(USERS_ARE_NOT_IN_THE_SAME_GROUP);
        }
        return toResult(internInfoService.setComment(userId, InternInfoService.GX, content));
    }

    /**
     * 通用打分方法。
     */
    private AjaxResult<Void> mark(Integer userId, String type, MarkDTO markDTO) {
        if (groupService.notInSameGroup(getUserId(), userId)) {
            return error(USERS_ARE_NOT_IN_THE_SAME_GROUP);
        }
        return toResult(internInfoService.setScore(userId, type, markDTO));
    }

    /**
     * 导师为实习生的听课记录进行打分。
     *
     * @param userId  实习生主键|1
     * @param markDTO 导师打分信息
     * @return {@code code = 200} 打分成功，{@code code = 500} 打分失败
     */
    @Secured(ROLE_INSTRUCTOR)
    @PutMapping("mark/tk/{userId}")
    @Log(category = INTERN_INFO, operType = UPDATE)
    public AjaxResult<Void> markTk(@PathVariable Integer userId,
                                   @RequestBody @Validated MarkDTO markDTO) {
        return mark(userId, InternInfoService.TK, markDTO);
    }

    /**
     * 导师为实习生的教案记录进行打分。
     *
     * @param userId  实习生主键|1
     * @param markDTO 导师打分信息
     * @return {@code code = 200} 打分成功，{@code code = 500} 打分失败
     */
    @Secured(ROLE_INSTRUCTOR)
    @PutMapping("mark/ja/{userId}")
    @Log(category = INTERN_INFO, operType = UPDATE)
    public AjaxResult<Void> markJa(@PathVariable Integer userId,
                                   @RequestBody @Validated MarkDTO markDTO) {
        return mark(userId, InternInfoService.JA, markDTO);
    }

    /**
     * 导师为实习生的教研记录进行打分。
     *
     * @param userId  实习生主键|1
     * @param markDTO 导师打分信息
     * @return {@code code = 200} 打分成功，{@code code = 500} 打分失败
     */
    @Secured(ROLE_INSTRUCTOR)
    @PutMapping("mark/jy/{userId}")
    @Log(category = INTERN_INFO, operType = UPDATE)
    public AjaxResult<Void> markJy(@PathVariable Integer userId,
                                   @RequestBody @Validated MarkDTO markDTO) {
        return mark(userId, InternInfoService.JY, markDTO);
    }

    /**
     * 导师为实习生的师德表现进行打分。
     *
     * @param userId  实习生主键|1
     * @param markDTO 导师打分信息
     * @return {@code code = 200} 打分成功，{@code code = 500} 打分失败
     */
    @Secured(ROLE_INSTRUCTOR)
    @PutMapping("mark/sd/{userId}")
    @Log(category = INTERN_INFO, operType = UPDATE)
    public AjaxResult<Void> markSd(@PathVariable Integer userId,
                                   @RequestBody @Validated MarkDTO markDTO) {
        return mark(userId, InternInfoService.SD, markDTO);
    }

    /**
     * 实习小组组长为实习生小组表现打分。
     *
     * @param userId       实习生主键|1
     * @param groupMarkDTO 组长打分信息
     * @return {@code code = 200} 打分成功，{@code code = 500} 打分失败
     */
    @Secured(ROLE_HEADMAN)
    @GetMapping("mark/xz/{userId}")
    @Log(category = INTERN_INFO, operType = UPDATE)
    public AjaxResult<Void> markXz(@PathVariable Integer userId,
                                   @RequestBody GroupMarkDTO groupMarkDTO) {
        if (groupService.notInSameGroup(getUserId(), userId)) {
            return error(USERS_ARE_NOT_IN_THE_SAME_GROUP);
        }
        return toResult(internInfoService.lambdaUpdate()
                .set(InternInfo::getGroupScore, groupMarkDTO.getScore())
                .set(InternInfo::getGroupEvaluation, groupMarkDTO.getRemark())
                .eq(InternInfo::getUserId, userId)
                .update());
    }

    /**
     * 通用查分方法。
     */
    private AjaxResult<ScoreVO> score(Integer userId, String type) {
        if (groupService.notInSameGroup(getUserId(), userId)) {
            return error(USERS_ARE_NOT_IN_THE_SAME_GROUP);
        }
        return success(internInfoService.getScore(userId, type));
    }

    /**
     * 导师获取实习生听课记录成绩信息。
     *
     * @param userId 实习生主键|1
     * @return 听课记录成绩
     */
    @Secured(ROLE_INSTRUCTOR)
    @GetMapping("score/tk/{userId}")
    public AjaxResult<ScoreVO> scoreTk(@PathVariable Integer userId) {
        return score(userId, InternInfoService.TK);
    }

    /**
     * 导师获取实习生教案记录成绩信息。
     *
     * @param userId 实习生主键|1
     * @return 教案记录成绩
     */
    @Secured(ROLE_INSTRUCTOR)
    @GetMapping("score/ja/{userId}")
    public AjaxResult<ScoreVO> scoreJa(@PathVariable Integer userId) {
        return score(userId, InternInfoService.JA);
    }

    /**
     * 导师获取实习生教研活动信息。
     *
     * @param userId 实习生主键|1
     * @return 教研活动成绩
     */
    @Secured(ROLE_INSTRUCTOR)
    @GetMapping("score/jy/{userId}")
    public AjaxResult<ScoreVO> scoreJy(@PathVariable Integer userId) {
        return score(userId, InternInfoService.JY);
    }

    /**
     * 导师获取实习生师德表现成绩信息。
     *
     * @param userId 实习生主键|1
     * @return 师德表现成绩
     */
    @Secured(ROLE_INSTRUCTOR)
    @GetMapping("score/sd/{userId}")
    public AjaxResult<ScoreVO> scoreSd(@PathVariable Integer userId) {
        return score(userId, InternInfoService.SD);
    }

    /**
     * 实习小组组长获取实习生小组成绩信息。
     *
     * @param userId 实习生主键|1
     * @return 小组成绩
     */
    @Secured(ROLE_HEADMAN)
    @GetMapping("score/xz/{userId}")
    public AjaxResult<GroupScoreVO> scoreXz(@PathVariable Integer userId) {
        if (groupService.notInSameGroup(getUserId(), userId)) {
            return error(USERS_ARE_NOT_IN_THE_SAME_GROUP);
        }
        return success(internInfoService.getGroupScore(userId));
    }

}