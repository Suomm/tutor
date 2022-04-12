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
import cn.edu.tjnu.tutor.common.helper.PageHelper;
import cn.edu.tjnu.tutor.system.domain.model.Article;
import cn.edu.tjnu.tutor.system.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.edu.tjnu.tutor.common.enums.Category.ARTICLE;
import static cn.edu.tjnu.tutor.common.enums.OperType.DELETE;
import static cn.edu.tjnu.tutor.common.enums.OperType.INSERT;

/**
 * 文章信息控制层。
 *
 * @author 王帅
 * @since 1.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/article")
public class ArticleController extends BaseController {

    private final ArticleRepository articleRepository;

    /**
     * 分页查询文章信息。
     *
     * @param pageHelper 分页帮助
     * @return 分页对象
     */
    @GetMapping("list}")
    public AjaxResult<Page<Article>> list(PageHelper pageHelper) {
        return success(articleRepository.findAll(pageHelper.elasticsearch()));
    }

    /**
     * 根据文章主键获取详细信息。
     *
     * @param articleId 文章主键
     * @return 文章信息详情
     */
    @GetMapping("getInfo/{articleId}")
    public AjaxResult<Article> getInfo(@PathVariable Integer articleId) {
        return success(articleRepository.findById(articleId).orElse(null));
    }

    /**
     * 添加文章信息。
     *
     * @param article 文章信息
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    @Log(category = ARTICLE, operType = INSERT)
    public AjaxResult<Void> save(@Validated @RequestBody Article article) {
        return toResult(articleRepository.save(article).getArticleId() != null);
    }

    /**
     * 根据文章主键删除文章信息。
     *
     * @param articleId 文章主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{articleId}")
    @Log(category = ARTICLE, operType = DELETE)
    public AjaxResult<Void> remove(@PathVariable Integer articleId) {
        articleRepository.deleteById(articleId);
        return success();
    }

}