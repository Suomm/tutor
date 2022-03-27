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
import cn.edu.tjnu.tutor.system.domain.Major;
import cn.edu.tjnu.tutor.system.service.MajorService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.edu.tjnu.tutor.common.enums.Category.MAJOR;
import static cn.edu.tjnu.tutor.common.enums.OperType.*;

/**
 * 专业信息控制层。
 *
 * @author 王帅
 * @since 2.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/major")
public class MajorController extends BaseController {

    private final MajorService majorService;

    /**
     * 分页查询专业信息。
     *
     * @param pageHelper 分页帮助
     * @return 分页对象
     */
    @GetMapping("list")
    public AjaxResult<Page<Major>> list(PageHelper pageHelper) {
        return AjaxResult.success(majorService.page(pageHelper.mybatisPlus()));
    }

    /**
     * 根据专业主键获取详细信息。
     *
     * @param majorId 专业主键
     * @return 专业信息详情
     */
    @GetMapping("getInfo/{majorId}")
    public AjaxResult<Major> getInfo(@PathVariable Integer majorId) {
        return AjaxResult.success(majorService.getById(majorId));
    }

    /**
     * 添加专业信息。
     *
     * @param major 专业信息
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    @Log(category = MAJOR, operType = INSERT)
    public AjaxResult<Void> save(@Validated @RequestBody Major major) {
        return toResult(majorService.save(major));
    }

    /**
     * 更新专业信息。
     *
     * @param major 专业信息
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    @Log(category = MAJOR, operType = UPDATE)
    public AjaxResult<Void> update(@Validated @RequestBody Major major) {
        return toResult(majorService.updateById(major));
    }

    /**
     * 根据专业主键删除专业信息。
     *
     * @param majorId 专业主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{majorId}")
    @Log(category = MAJOR, operType = DELETE)
    public AjaxResult<Void> remove(@PathVariable Integer majorId) {
        return toResult(majorService.removeById(majorId));
    }

}