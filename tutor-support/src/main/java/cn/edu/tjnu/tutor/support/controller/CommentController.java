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
import cn.edu.tjnu.tutor.system.domain.model.Comment;
import cn.edu.tjnu.tutor.system.domain.view.CommentVO;
import cn.edu.tjnu.tutor.system.repository.CommentRepository;
import cn.edu.tjnu.tutor.system.structure.CommentStruct;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static cn.edu.tjnu.tutor.common.enums.Category.COMMENT;
import static cn.edu.tjnu.tutor.common.enums.OperType.DELETE;
import static cn.edu.tjnu.tutor.common.enums.OperType.INSERT;

/**
 * 文章评论信息控制层。
 *
 * @author 王帅
 * @since 2.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController extends BaseController {

    private final CommentRepository commentRepository;

    /**
     * 根据文章主键查询对应的评论信息。
     *
     * @param articleId 文章主键
     * @return 文章的所有评论信息
     */
    @GetMapping("list/{articleId}")
    public AjaxResult<List<CommentVO>> list(@PathVariable Integer articleId) {
        return success(TreeUtils.build(commentRepository.findAllByArticleId(articleId)
                .stream()
                .map(CommentStruct.INSTANCE::toVO)
                .collect(Collectors.toList())));
    }

    /**
     * 添加评论信息。
     *
     * @param comment 评论信息
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    @Log(category = COMMENT, operType = INSERT)
    public AjaxResult<Void> save(@Validated @RequestBody Comment comment) {
        return toResult(commentRepository.save(comment).getCommentId() != null);
    }

    /**
     * 根据评论主键删除评论信息。
     *
     * @param commentId 评论主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{commentId}")
    @Log(category = COMMENT, operType = DELETE)
    public AjaxResult<Void> remove(@PathVariable Integer commentId) {
        commentRepository.deleteById(commentId);
        return AjaxResult.SUCCESS;
    }

}