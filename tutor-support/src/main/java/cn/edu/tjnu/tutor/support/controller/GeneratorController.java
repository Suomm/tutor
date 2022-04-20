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

import cn.edu.tjnu.tutor.common.core.controller.BaseController;
import cn.edu.tjnu.tutor.common.core.domain.AjaxResult;
import cn.edu.tjnu.tutor.common.validation.groups.Insert;
import cn.edu.tjnu.tutor.system.domain.entity.Document;
import cn.edu.tjnu.tutor.system.domain.entity.Template;
import cn.edu.tjnu.tutor.system.service.DocumentService;
import cn.edu.tjnu.tutor.system.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static cn.edu.tjnu.tutor.common.constant.RoleConst.ROLE_INSTRUCTOR;
import static cn.edu.tjnu.tutor.common.constant.RoleConst.ROLE_INTERN;

/**
 * 教育实践手册生成控制器。
 *
 * @author 王帅
 * @since 2.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/generator")
public class GeneratorController extends BaseController {

    private final DocumentService documentService;

    private final TemplateService templateService;

    /**
     * 添加实践手册样例文档。
     *
     * @param document 文档信息
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @Secured(ROLE_INSTRUCTOR)
    @PostMapping("/upload/doc")
    public AjaxResult<Void> uploadDoc(@RequestBody @Validated(Insert.class) Document document) {
        return toResult(documentService.save(document));
    }

    /**
     * 添加实践手册模板。
     *
     * @param template 模板信息
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @Secured(ROLE_INTERN)
    @PostMapping("/upload/tmpl")
    public AjaxResult<Void> uploadTmpl(@RequestBody @Validated(Insert.class) Template template) {
        return toResult(templateService.save(template));
    }


}