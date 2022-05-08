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
import cn.edu.tjnu.tutor.common.core.domain.dto.PageDTO;
import cn.edu.tjnu.tutor.common.core.domain.view.PageVO;
import cn.edu.tjnu.tutor.common.validation.groups.Insert;
import cn.edu.tjnu.tutor.common.validation.groups.Update;
import cn.edu.tjnu.tutor.system.domain.entity.Config;
import cn.edu.tjnu.tutor.system.service.ConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.edu.tjnu.tutor.common.enums.Category.CONFIG;
import static cn.edu.tjnu.tutor.common.enums.OperType.*;

/**
 * 参数配置信息控制层。
 *
 * @author 王帅
 * @since 2.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/config")
public class ConfigController extends BaseController {

    private final ConfigService configService;

    /**
     * 分页查询参数配置信息。
     *
     * @param pageDTO 分页参数
     * @return 分页对象
     */
    @GetMapping("list")
    public AjaxResult<PageVO<Config>> list(@Validated PageDTO pageDTO) {
        return pageSuccess(configService.page(pageDTO.page()));
    }

    /**
     * 添加参数配置信息。
     *
     * @param config 参数配置信息
     * @return {@code code = 200} 添加成功，{@code code = 500} 添加失败
     */
    @PostMapping("save")
    @Log(category = CONFIG, operType = INSERT)
    public AjaxResult<Void> save(@RequestBody @Validated(Insert.class) Config config) {
        return toResult(configService.save(config));
    }

    /**
     * 更新参数配置信息。
     *
     * @param config 参数配置信息
     * @return {@code code = 200} 更新成功，{@code code = 500} 更新失败
     */
    @PutMapping("update")
    @Log(category = CONFIG, operType = UPDATE)
    public AjaxResult<Void> update(@RequestBody @Validated(Update.class) Config config) {
        return toResult(configService.updateById(config));
    }

    /**
     * 根据参数配置主键删除参数配置信息。
     *
     * @param configId 参数配置主键
     * @return {@code code = 200} 删除成功，{@code code = 500} 删除失败
     */
    @DeleteMapping("remove/{configId}")
    @Log(category = CONFIG, operType = DELETE)
    public AjaxResult<Void> remove(@PathVariable Integer configId) {
        return toResult(configService.removeById(configId));
    }

    /**
     * 刷新参数配置缓存。
     *
     * @return 总是返回 {@code code = 200} 成功状态
     */
    @PostMapping("refreshCache")
    public AjaxResult<Void> refreshCache() {
        configService.resetConfigCache();
        return AjaxResult.SUCCESS;
    }

}