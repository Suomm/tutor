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
import cn.edu.tjnu.tutor.system.domain.model.Problem;
import cn.edu.tjnu.tutor.system.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.edu.tjnu.tutor.common.enums.Category.PROBLEM;
import static cn.edu.tjnu.tutor.common.enums.OperType.DELETE;
import static cn.edu.tjnu.tutor.common.enums.OperType.INSERT;

/**
 * 问题信息控制层。
 *
 * @author 王帅
 * @since 1.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/problem")
public class ProblemController extends BaseController {

    private final ProblemRepository problemRepository;

    /**
     * 分页查询问题信息。
     *
     * @param pageDTO 分页参数
     * @return 分页对象
     */
    @GetMapping("list}")
    public AjaxResult<PageVO<Problem>> list(@Validated PageDTO pageDTO) {
        return pageSuccess(problemRepository.findAll(pageDTO.pageable()));
    }

    /**
     * 根据问题主键获取详细信息。
     *
     * @param problemId 问题主键
     * @return 问题信息详情
     */
    @GetMapping("getInfo/{problemId}")
    public AjaxResult<Problem> getInfo(@PathVariable Integer problemId) {
        return success(problemRepository.findById(problemId).orElse(null));
    }

    /**
     * 添加问题信息。
     *
     * @param problem 问题信息
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    @Log(category = PROBLEM, operType = INSERT)
    public AjaxResult<Void> save(@RequestBody @Validated(Insert.class) Problem problem) {
        return toResult(problemRepository.save(problem).getProblemId() != null);
    }

    /**
     * 根据问题主键删除问题信息。
     *
     * @param problemId 问题主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{problemId}")
    @Log(category = PROBLEM, operType = DELETE)
    public AjaxResult<Void> remove(@PathVariable Integer problemId) {
        problemRepository.deleteById(problemId);
        return AjaxResult.SUCCESS;
    }

}