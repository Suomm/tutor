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
import cn.edu.tjnu.tutor.system.domain.entity.Menu;
import cn.edu.tjnu.tutor.system.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static cn.edu.tjnu.tutor.common.enums.Category.MENU;
import static cn.edu.tjnu.tutor.common.enums.OperType.*;

/**
 * 菜单信息控制层。
 *
 * @author 王帅
 * @since 2.0
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class MenuController extends BaseController {

    private final MenuService menuService;

    /**
     * 分页查询菜单信息。
     *
     * @param pageDTO 分页参数
     * @return 分页对象
     */
    @GetMapping("list")
    public AjaxResult<PageVO<Menu>> list(PageDTO pageDTO) {
        return pageSuccess(menuService.page(pageDTO.page()));
    }

    /**
     * 根据菜单主键获取详细信息。
     *
     * @param menuId 菜单主键
     * @return 菜单信息详情
     */
    @GetMapping("getInfo/{menuId}")
    public AjaxResult<Menu> getInfo(@PathVariable Integer menuId) {
        return success(menuService.getById(menuId));
    }

    /**
     * 添加菜单信息。
     *
     * @param menu 菜单信息
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    @Log(category = MENU, operType = INSERT)
    public AjaxResult<Void> save(@RequestBody Menu menu) {
        return toResult(menuService.save(menu));
    }

    /**
     * 更新菜单信息。
     *
     * @param menu 菜单信息
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    @Log(category = MENU, operType = UPDATE)
    public AjaxResult<Void> update(@RequestBody Menu menu) {
        return toResult(menuService.updateById(menu));
    }

    /**
     * 根据菜单主键删除菜单信息。
     *
     * @param menuId 菜单主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{menuId}")
    @Log(category = MENU, operType = DELETE)
    public AjaxResult<Void> remove(@PathVariable Integer menuId) {
        return toResult(menuService.removeById(menuId));
    }

}