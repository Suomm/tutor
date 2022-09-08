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
import cn.edu.tjnu.tutor.system.domain.dto.MarkDTO;
import cn.edu.tjnu.tutor.system.domain.dto.TeachingRecordDTO;
import cn.edu.tjnu.tutor.system.domain.entity.TeachingRecord;
import cn.edu.tjnu.tutor.system.domain.view.ScoreVO;
import cn.edu.tjnu.tutor.system.domain.view.TeachingRecordVO;
import cn.edu.tjnu.tutor.system.service.GroupService;
import cn.edu.tjnu.tutor.system.service.TeachingRecordService;
import cn.edu.tjnu.tutor.system.structure.TeachingRecordStruct;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Insert;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.edu.tjnu.tutor.common.constant.RoleConst.ROLE_INSTRUCTOR;
import static cn.edu.tjnu.tutor.common.constant.RoleConst.ROLE_INTERN;
import static cn.edu.tjnu.tutor.common.enums.Category.TEACHING_RECORD;
import static cn.edu.tjnu.tutor.common.enums.ExceptionType.RECORD_ALREADY_SCORED;
import static cn.edu.tjnu.tutor.common.enums.ExceptionType.USERS_ARE_NOT_IN_THE_SAME_GROUP;
import static cn.edu.tjnu.tutor.common.enums.OperType.*;

/**
 * 课堂教学记录信息控制层。
 *
 * @author 王帅
 * @since 2.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/teachingRecord")
public class TeachingRecordController extends BaseController {

    private final GroupService groupService;
    private final TeachingRecordStruct teachingRecordStruct;
    private final TeachingRecordService teachingRecordService;

    /**
     * 查询课堂教学信息。
     *
     * @param query 查询参数
     * @return 分页对象
     * @apiNote 实习生查询本人的课堂教学信息。
     */
    @Secured(ROLE_INTERN)
    @GetMapping("page")
    public AjaxResult<PageVO<TeachingRecordVO>> page(@Validated PageQuery query) {
        return pageSuccess(teachingRecordService.pageVO(getUserId(), query.page()));
    }

    /**
     * 查询课堂教学信息。
     *
     * @param userId 实习生主键|1
     * @param query  查询参数
     * @return 分页对象
     * @apiNote 小组长评价与导师打分时查询小组内实习生的课堂教学信息。
     */
    @Secured(ROLE_INSTRUCTOR)
    @GetMapping("page/{userId}")
    public AjaxResult<PageVO<TeachingRecordVO>> page(@PathVariable Integer userId,
                                                     @Validated PageQuery query) {
        if (groupService.notInSameGroup(getUserId(), userId)) {
            return error(USERS_ARE_NOT_IN_THE_SAME_GROUP);
        }
        return pageSuccess(teachingRecordService.pageVO(userId, query.page()));
    }

    /**
     * 添加课堂教学信息。
     *
     * @param teachingRecordDTO 课堂教学信息
     * @return {@code code = 200} 添加成功，{@code code = 500} 添加失败
     */
    @Secured(ROLE_INTERN)
    @PostMapping("save")
    @Log(category = TEACHING_RECORD, operType = INSERT)
    public AjaxResult<Void> save(@RequestBody @Validated(Insert.class) TeachingRecordDTO teachingRecordDTO) {
        TeachingRecord teachingRecord = teachingRecordStruct.toEntity(teachingRecordDTO);
        teachingRecord.setUserId(getUserId());
        return toResult(teachingRecordService.save(teachingRecord));
    }

    /**
     * 删除课堂教学信息。
     *
     * @param recordId 课堂教学主键|1
     * @return {@code code = 200} 删除成功，{@code code = 500} 删除失败
     */
    @Secured(ROLE_INTERN)
    @DeleteMapping("remove/{recordId}")
    @Log(category = TEACHING_RECORD, operType = DELETE)
    public AjaxResult<Void> remove(@PathVariable Integer recordId) {
        TeachingRecord teachingRecord = new TeachingRecord();
        teachingRecord.setRecordId(recordId);
        teachingRecord.setUserId(getUserId());
        if (teachingRecordService.isMarked(teachingRecord)) {
            return error(RECORD_ALREADY_SCORED);
        }
        return toResult(teachingRecordService.removeById(teachingRecord));
    }

    /**
     * 导师为实习生的课堂教学记录进行打分。
     *
     * @param userId  实习生主键|1
     * @param markDTO 导师打分信息
     * @return {@code code = 200} 打分成功，{@code code = 500} 打分失败
     */
    @Secured(ROLE_INSTRUCTOR)
    @PutMapping("mark/{userId}")
    @Log(category = TEACHING_RECORD, operType = UPDATE)
    public AjaxResult<Void> mark(@PathVariable Integer userId,
                                 @RequestBody @Validated MarkDTO markDTO) {
        if (groupService.notInSameGroup(getUserId(), userId)) {
            return error(USERS_ARE_NOT_IN_THE_SAME_GROUP);
        }
        return toResult(teachingRecordService.lambdaUpdate()
                .set(TeachingRecord::getMarkSchool, markDTO.getHighSchool())
                .set(TeachingRecord::getMarkCollege, markDTO.getUniversity())
                .eq(TeachingRecord::getUserId, userId)
                .update());
    }

    /**
     * 导师获取实习生的课堂教学记录进行打分。
     *
     * @param userId 实习生主键|1
     * @return 课堂教学成绩
     */
    @Secured(ROLE_INSTRUCTOR)
    @GetMapping("score/{userId}")
    public AjaxResult<ScoreVO> score(@PathVariable Integer userId) {
        if (groupService.notInSameGroup(getUserId(), userId)) {
            return error(USERS_ARE_NOT_IN_THE_SAME_GROUP);
        }
        return success(teachingRecordService.getScore(userId));
    }

}