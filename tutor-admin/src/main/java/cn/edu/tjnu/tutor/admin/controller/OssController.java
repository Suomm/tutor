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

package cn.edu.tjnu.tutor.admin.controller;

import cn.edu.tjnu.tutor.common.annotation.Log;
import cn.edu.tjnu.tutor.common.core.controller.BaseController;
import cn.edu.tjnu.tutor.common.core.domain.AjaxResult;
import cn.edu.tjnu.tutor.common.core.domain.query.PageQuery;
import cn.edu.tjnu.tutor.common.core.domain.view.PageVO;
import cn.edu.tjnu.tutor.common.validation.groups.Insert;
import cn.edu.tjnu.tutor.common.validation.groups.Update;
import cn.edu.tjnu.tutor.system.domain.entity.Oss;
import cn.edu.tjnu.tutor.system.service.OssService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.edu.tjnu.tutor.common.enums.Category.OSS;
import static cn.edu.tjnu.tutor.common.enums.OperType.*;

/**
 * 对象存储信息控制层。
 *
 * @author 王帅
 * @since 2.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/oss")
public class OssController extends BaseController {

    private final OssService ossService;

    /**
     * 查询对象存储信息。
     *
     * @param pageQuery 分页参数
     * @return 分页对象
     */
    @GetMapping("list")
    public AjaxResult<PageVO<Oss>> list(@Validated PageQuery pageQuery) {
        return pageSuccess(ossService.page(pageQuery.page()));
    }

    /**
     * 添加对象存储信息。
     *
     * @param oss 对象存储信息
     * @return {@code code = 200} 添加成功，{@code code = 500} 添加失败
     */
    @PostMapping("save")
    @Log(category = OSS, operType = INSERT)
    public AjaxResult<Void> save(@RequestBody @Validated(Insert.class) Oss oss) {
        return toResult(ossService.save(oss));
    }

    /**
     * 更新对象存储信息。
     *
     * @param oss 对象存储信息
     * @return {@code code = 200} 更新成功，{@code code = 500} 更新失败
     */
    @PutMapping("update")
    @Log(category = OSS, operType = UPDATE)
    public AjaxResult<Void> update(@RequestBody @Validated(Update.class) Oss oss) {
        return toResult(ossService.updateById(oss));
    }

    /**
     * 删除对象存储信息。
     *
     * @param ossId 对象存储主键|1
     * @return {@code code = 200} 删除成功，{@code code = 500} 删除失败
     */
    @DeleteMapping("remove/{ossId}")
    @Log(category = OSS, operType = DELETE)
    public AjaxResult<Void> remove(@PathVariable Integer ossId) {
        return toResult(ossService.removeById(ossId));
    }

}