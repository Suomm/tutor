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
import cn.edu.tjnu.tutor.system.domain.dto.LeaderWorkDTO;
import cn.edu.tjnu.tutor.system.domain.dto.MarkDTO;
import cn.edu.tjnu.tutor.system.domain.entity.LeaderWork;
import cn.edu.tjnu.tutor.system.domain.view.LeaderWorkVO;
import cn.edu.tjnu.tutor.system.domain.view.ScoreVO;
import cn.edu.tjnu.tutor.system.service.GroupService;
import cn.edu.tjnu.tutor.system.service.LeaderWorkService;
import cn.edu.tjnu.tutor.system.structure.LeaderWorkStruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.edu.tjnu.tutor.common.constant.RoleConst.ROLE_INSTRUCTOR;
import static cn.edu.tjnu.tutor.common.constant.RoleConst.ROLE_INTERN;
import static cn.edu.tjnu.tutor.common.enums.Category.LEADER_WORK;
import static cn.edu.tjnu.tutor.common.enums.ExceptionType.USERS_ARE_NOT_IN_THE_SAME_GROUP;
import static cn.edu.tjnu.tutor.common.enums.OperType.UPDATE;

/**
 * 班主任实习工作信息控制层。
 *
 * @author 王帅
 * @since 2.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/leaderWork")
public class LeaderWorkController extends BaseController {

    private final GroupService groupService;
    private final LeaderWorkStruct leaderWorkStruct;
    private final LeaderWorkService leaderWorkService;

    /**
     * 获取班主任实习工作记录详细信息。
     *
     * @return 班主任实习工作信息
     * @apiNote 实习生查询本人的班主任工作记录信息。
     */
    @Secured(ROLE_INTERN)
    @GetMapping("getInfo")
    public AjaxResult<LeaderWorkVO> getInfo() {
        return getLeaderWorkVO(getUserId());
    }

    /**
     * 根据实习生主键主键获取班主任实习工作记录信息。
     *
     * @param userId 实习生主键|1
     * @return 班主任实习工作信息
     * @apiNote 教育实习指导教师查询指定实习生的班主任工作记录信息。
     */
    @Secured(ROLE_INSTRUCTOR)
    @GetMapping("getInfo/{userId}")
    public AjaxResult<LeaderWorkVO> getInfo(@PathVariable Integer userId) {
        if (groupService.notInSameGroup(getUserId(), userId)) {
            return error(USERS_ARE_NOT_IN_THE_SAME_GROUP);
        }
        return getLeaderWorkVO(userId);
    }

    /**
     * 获取班主任工作详情。
     */
    private AjaxResult<LeaderWorkVO> getLeaderWorkVO(Integer userId) {
        return success(leaderWorkService.lambdaQuery()
                .eq(LeaderWork::getUserId, userId)
                .oneOpt()
                .map(leaderWorkStruct::toVO)
                .orElse(new LeaderWorkVO()));
    }

    /**
     * 添加或者更新班主任实习工作信息。
     *
     * @param leaderWorkDTO 班主任实习工作信息
     * @return {@code code = 200} 操作成功，{@code code = 500} 操作失败
     */
    @Secured(ROLE_INTERN)
    @Log(category = LEADER_WORK)
    @PostMapping("saveOrUpdate")
    public AjaxResult<Void> saveOrUpdate(@Validated @RequestBody LeaderWorkDTO leaderWorkDTO) {
        LeaderWork leaderWork = leaderWorkStruct.toEntity(leaderWorkDTO);
        if (leaderWorkService.lambdaQuery()
                .eq(LeaderWork::getUserId, getUserId())
                .exists()) {
            return toResult(leaderWorkService.lambdaUpdate()
                    .eq(LeaderWork::getUserId, getUserId())
                    .update(leaderWork));
        }
        return toResult(leaderWorkService.save(leaderWork));
    }

    /**
     * 导师为实习生的班主任实习记录进行打分。
     *
     * @param userId  实习生主键|1
     * @param markDTO 导师打分信息
     * @return {@code code = 200} 打分成功，{@code code = 500} 打分失败
     */
    @Secured(ROLE_INSTRUCTOR)
    @PutMapping("mark/{userId}")
    @Log(category = LEADER_WORK, operType = UPDATE)
    public AjaxResult<Void> mark(@PathVariable Integer userId,
                                 @RequestBody @Validated MarkDTO markDTO) {
        if (groupService.notInSameGroup(getUserId(), userId)) {
            return error(USERS_ARE_NOT_IN_THE_SAME_GROUP);
        }
        return toResult(leaderWorkService.lambdaUpdate()
                .set(LeaderWork::getMarkSchool, markDTO.getHighSchool())
                .set(LeaderWork::getMarkCollege, markDTO.getUniversity())
                .eq(LeaderWork::getUserId, userId)
                .update());
    }

    /**
     * 导师获取班主任实习工作的成绩信息。
     *
     * @param userId 实习生主键|
     * @return 班主任实习工作成绩
     */
    @Secured(ROLE_INSTRUCTOR)
    @GetMapping("score/{userId}")
    public AjaxResult<ScoreVO> score(@PathVariable Integer userId) {
        if (groupService.notInSameGroup(getUserId(), userId)) {
            return error(USERS_ARE_NOT_IN_THE_SAME_GROUP);
        }
        return success(leaderWorkService.getWorkScore(userId));
    }

}