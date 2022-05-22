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
import cn.edu.tjnu.tutor.common.util.TreeUtils;
import cn.edu.tjnu.tutor.common.validation.groups.Insert;
import cn.edu.tjnu.tutor.system.domain.model.Answer;
import cn.edu.tjnu.tutor.system.domain.view.AnswerVO;
import cn.edu.tjnu.tutor.system.repository.AnswerRepository;
import cn.edu.tjnu.tutor.system.structure.AnswerStruct;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static cn.edu.tjnu.tutor.common.enums.Category.ANSWER;
import static cn.edu.tjnu.tutor.common.enums.OperType.DELETE;
import static cn.edu.tjnu.tutor.common.enums.OperType.INSERT;

/**
 * 问题答复信息控制层。
 *
 * @author 王帅
 * @since 2.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController extends BaseController {

    private final AnswerStruct answerStruct;
    private final AnswerRepository answerRepository;

    /**
     * 查询问题对应的答复信息。
     *
     * @param problemId 问题主键|1
     * @return 问题的所有答复信息
     */
    @GetMapping("list/{problemId}")
    public AjaxResult<List<AnswerVO>> list(@PathVariable Integer problemId) {
        return success(TreeUtils.build(answerRepository.findAllByProblemId(problemId)
                .stream()
                .map(answerStruct::toVO)
                .collect(Collectors.toList())));
    }

    /**
     * 添加答复信息。
     *
     * @param answer 答复信息
     * @return {@code code = 200} 添加成功，{@code code = 500} 添加失败
     */
    @PostMapping("save")
    @Log(category = ANSWER, operType = INSERT)
    public AjaxResult<Void> save(@RequestBody @Validated(Insert.class) Answer answer) {
        return toResult(answerRepository.save(answer).getAnswerId() != null);
    }

    /**
     * 删除答复信息。
     *
     * @param commentId 答复主键|1
     * @return {@code code = 200} 删除成功，{@code code = 500} 删除失败
     */
    @DeleteMapping("remove/{commentId}")
    @Log(category = ANSWER, operType = DELETE)
    public AjaxResult<Void> remove(@PathVariable String commentId) {
        answerRepository.deleteById(commentId);
        return AjaxResult.SUCCESS;
    }

}