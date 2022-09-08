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
import cn.edu.tjnu.tutor.system.domain.dto.LectureNoteDTO;
import cn.edu.tjnu.tutor.system.domain.entity.LectureNote;
import cn.edu.tjnu.tutor.system.domain.view.LectureNoteVO;
import cn.edu.tjnu.tutor.system.service.GroupService;
import cn.edu.tjnu.tutor.system.service.LectureNoteService;
import cn.edu.tjnu.tutor.system.structure.LectureNoteStruct;
import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Insert;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

import static cn.edu.tjnu.tutor.common.constant.RoleConst.*;
import static cn.edu.tjnu.tutor.common.enums.Category.LECTURE_NOTE;
import static cn.edu.tjnu.tutor.common.enums.ExceptionType.USERS_ARE_NOT_IN_THE_SAME_GROUP;
import static cn.edu.tjnu.tutor.common.enums.OperType.*;

/**
 * 实习听课记录信息控制层。
 *
 * @author 王帅
 * @since 2.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/lectureNote")
public class LectureNoteController extends BaseController {

    private final GroupService groupService;
    private final LectureNoteStruct lectureNoteStruct;
    private final LectureNoteService lectureNoteService;

    /**
     * 查询实习听课记录信息。
     *
     * @param query 查询参数
     * @return 分页对象
     * @apiNote 实习生查询本人的听课记录信息。
     */
    @Secured(ROLE_INTERN)
    @GetMapping("page")
    public AjaxResult<PageVO<LectureNoteVO>> page(@Validated PageQuery query) {
        return pageSuccess(lectureNoteService.pageVO(getUserId(), query.page()));
    }

    /**
     * 查询实习听课记录信息。
     *
     * @param userId 实习生主键|1
     * @param query  查询参数
     * @return 分页对象
     * @apiNote 小组长评价与导师打分时查询小组内实习生的听课记录信息。
     */
    @GetMapping("page/{userId}")
    @Secured({ROLE_HEADMAN, ROLE_INSTRUCTOR})
    public AjaxResult<PageVO<LectureNoteVO>> list(@PathVariable Integer userId,
                                                  @Validated PageQuery query) {
        if (groupService.notInSameGroup(getUserId(), userId)) {
            return error(USERS_ARE_NOT_IN_THE_SAME_GROUP);
        }
        return pageSuccess(lectureNoteService.pageVO(userId, query.page()));
    }

    /**
     * 添加实习听课记录信息。
     *
     * @param lectureNoteDTO 实习听课记录信息
     * @return {@code code = 200} 添加成功，{@code code = 500} 添加失败
     */
    @Secured(ROLE_INTERN)
    @PostMapping("save")
    @Log(category = LECTURE_NOTE, operType = INSERT)
    public AjaxResult<Void> save(@RequestBody @Validated(Insert.class) LectureNoteDTO lectureNoteDTO) {
        return toResult(lectureNoteService.save(lectureNoteStruct.toEntity(lectureNoteDTO)));
    }

    /**
     * 更新实习听课记录信息。
     *
     * @param lectureNoteDTO 实习听课记录信息
     * @return {@code code = 200} 更新成功，{@code code = 500} 更新失败
     */
    @Secured(ROLE_INTERN)
    @PutMapping("update")
    @Log(category = LECTURE_NOTE, operType = UPDATE)
    public AjaxResult<Void> update(@RequestBody @Validated(Update.class) LectureNoteDTO lectureNoteDTO) {
        LectureNote lectureNote = lectureNoteStruct.toEntity(lectureNoteDTO);
        return toResult(lectureNoteService.lambdaUpdate()
                .eq(LectureNote::getNoteId, lectureNote.getNoteId())
                .eq(LectureNote::getUserId, getUserId())
                .update(lectureNote));
    }

    /**
     * 删除实习听课记录信息。
     *
     * @param noteId 实习听课记录主键|1
     * @return {@code code = 200} 删除成功，{@code code = 500} 删除失败
     */
    @Secured(ROLE_INTERN)
    @DeleteMapping("remove/{noteId}")
    @Log(category = LECTURE_NOTE, operType = DELETE)
    public AjaxResult<Void> remove(@PathVariable Integer noteId) {
        Wrapper<LectureNote> wrapper = lectureNoteService.lambdaQuery()
                .eq(LectureNote::getNoteId, noteId)
                .eq(LectureNote::getUserId, getUserId());
        return toResult(lectureNoteService.remove(wrapper));
    }

    /**
     * 实习小组组长评价小组成员的实习听课记录。
     *
     * @param userId  实习生主键|1
     * @param noteId  听课记录主键|1
     * @param content 评价内容|继续加油！
     * @return {@code code = 200} 评价成功，{@code code = 500} 评价失败
     */
    @Secured(ROLE_HEADMAN)
    @PutMapping("setRemark/{userId}/{noteId}")
    @Log(category = LECTURE_NOTE, operType = UPDATE)
    public AjaxResult<Void> setRemark(@PathVariable Integer userId,
                                      @PathVariable Integer noteId,
                                      @RequestBody @NotBlank String content) {
        if (groupService.notInSameGroup(getUserId(), userId)) {
            return error(USERS_ARE_NOT_IN_THE_SAME_GROUP);
        }
        return toResult(lectureNoteService.lambdaUpdate()
                .set(LectureNote::getGroupEvaluation, content)
                .eq(LectureNote::getUserId, userId)
                .eq(LectureNote::getNoteId, noteId)
                .update());
    }

    /**
     * 实习小组组长获取小组成员的实习听课记录评价内容。
     *
     * @param userId 实习生主键|1
     * @param noteId 听课记录主键|1
     * @return 听课记录小组评价
     */
    @Secured(ROLE_HEADMAN)
    @GetMapping("getRemark/{userId}/{noteId}")
    public AjaxResult<String> getRemark(@PathVariable Integer userId,
                                        @PathVariable Integer noteId) {
        if (groupService.notInSameGroup(getUserId(), userId)) {
            return error(USERS_ARE_NOT_IN_THE_SAME_GROUP);
        }
        return success(lectureNoteService.lambdaQuery()
                .select(LectureNote::getGroupEvaluation)
                .eq(LectureNote::getUserId, userId)
                .eq(LectureNote::getNoteId, noteId)
                .oneOpt()
                .map(LectureNote::getGroupEvaluation)
                .orElse(CharSequenceUtil.EMPTY));
    }

}