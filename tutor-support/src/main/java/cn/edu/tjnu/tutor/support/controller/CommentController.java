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

import cn.easyes.core.conditions.LambdaEsQueryWrapper;
import cn.edu.tjnu.tutor.common.annotation.Log;
import cn.edu.tjnu.tutor.common.core.controller.BaseController;
import cn.edu.tjnu.tutor.common.core.domain.AjaxResult;
import cn.edu.tjnu.tutor.common.util.TreeUtils;
import cn.edu.tjnu.tutor.common.validation.groups.Insert;
import cn.edu.tjnu.tutor.system.domain.model.Comment;
import cn.edu.tjnu.tutor.system.domain.view.CommentVO;
import cn.edu.tjnu.tutor.system.service.CommentService;
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

    private final CommentStruct commentStruct;
    private final CommentService commentService;

    /**
     * 查询文章对应的评论信息。
     *
     * @param articleId 文章主键|1
     * @return 文章的所有评论信息
     */
    @GetMapping("list/{articleId}")
    public AjaxResult<List<CommentVO>> list(@PathVariable Integer articleId) {
        LambdaEsQueryWrapper<Comment> wrapper = commentService.lambdaQuery()
                .eq(Comment::getArticleId, articleId);
        return success(TreeUtils.build(commentService.selectList(wrapper)
                .stream()
                .map(commentStruct::toVO)
                .collect(Collectors.toList())));
    }

    /**
     * 添加评论信息。
     *
     * @param comment 评论信息
     * @return {@code code = 200} 添加成功，{@code code = 500} 添加失败
     */
    @PostMapping("save")
    @Log(category = COMMENT, operType = INSERT)
    public AjaxResult<Void> save(@RequestBody @Validated(Insert.class) Comment comment) {
        return toResult(commentService.save(comment));
    }

    /**
     * 删除评论信息。
     *
     * @param commentId 评论主键|1
     * @return {@code code = 200} 删除成功，{@code code = 500} 删除失败
     */
    @DeleteMapping("remove/{commentId}")
    @Log(category = COMMENT, operType = DELETE)
    public AjaxResult<Void> remove(@PathVariable String commentId) {
        return toResult(commentService.deleteById(commentId));
    }

}