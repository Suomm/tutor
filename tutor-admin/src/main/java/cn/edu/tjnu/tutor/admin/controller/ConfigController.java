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
import cn.edu.tjnu.tutor.system.domain.entity.Config;
import cn.edu.tjnu.tutor.system.service.ConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.edu.tjnu.tutor.common.enums.Category.CONFIG;
import static cn.edu.tjnu.tutor.common.enums.OperType.*;

/**
 * 参数配置控制层。
 *
 * @author 王帅
 * @since 2.0
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/config")
public class ConfigController extends BaseController {

    private final ConfigService configService;

    /**
     * 分页查询参数配置。
     *
     * @param pageDTO 分页参数
     * @return 分页对象
     */
    @GetMapping("list")
    public AjaxResult<PageVO<Config>> list(PageDTO pageDTO) {
        return pageSuccess(configService.page(pageDTO.page()));
    }

    /**
     * 根据参数主键获取详细信息。
     *
     * @param configId 参数主键
     * @return 参数配置详情
     */
    @GetMapping("getInfo/{configId}")
    public AjaxResult<Config> getInfo(@PathVariable Integer configId) {
        return success(configService.getById(configId));
    }

    /**
     * 添加参数配置。
     *
     * @param config 参数配置
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    @Log(category = CONFIG, operType = INSERT)
    public AjaxResult<Void> save(@RequestBody Config config) {
        return toResult(configService.save(config));
    }

    /**
     * 更新参数配置。
     *
     * @param config 参数配置
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    @Log(category = CONFIG, operType = UPDATE)
    public AjaxResult<Void> update(@RequestBody Config config) {
        return toResult(configService.updateById(config));
    }

    /**
     * 根据参数主键删除参数配置。
     *
     * @param configId 参数主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{configId}")
    @Log(category = CONFIG, operType = DELETE)
    public AjaxResult<Void> remove(@PathVariable Integer configId) {
        return toResult(configService.removeById(configId));
    }

}