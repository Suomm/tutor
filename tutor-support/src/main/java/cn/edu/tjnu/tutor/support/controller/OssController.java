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
import cn.edu.tjnu.tutor.system.domain.entity.Oss;
import cn.edu.tjnu.tutor.system.service.OssService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
     * 分页查询对象存储信息。
     *
     * @param pageHelper 分页帮助
     * @return 分页对象
     */
    @GetMapping("list")
    public AjaxResult<Page<Oss>> list(PageHelper pageHelper) {
        return success(ossService.page(pageHelper.mybatisPlus()));
    }

    /**
     * 根据对象存储主键获取详细信息。
     *
     * @param ossId 对象存储主键
     * @return 对象存储信息详情
     */
    @GetMapping("getInfo/{ossId}")
    public AjaxResult<Oss> getInfo(@PathVariable Integer ossId) {
        return success(ossService.getById(ossId));
    }

    /**
     * 添加对象存储信息。
     *
     * @param oss 对象存储信息
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    @Log(category = OSS, operType = INSERT)
    public AjaxResult<Void> save(@Validated @RequestBody Oss oss) {
        return toResult(ossService.save(oss));
    }

    /**
     * 更新对象存储信息。
     *
     * @param oss 对象存储信息
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    @Log(category = OSS, operType = UPDATE)
    public AjaxResult<Void> update(@Validated @RequestBody Oss oss) {
        return toResult(ossService.updateById(oss));
    }

    /**
     * 根据对象存储主键删除对象存储信息。
     *
     * @param ossId 对象存储主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{ossId}")
    @Log(category = OSS, operType = DELETE)
    public AjaxResult<Void> remove(@PathVariable Integer ossId) {
        return toResult(ossService.removeById(ossId));
    }

}