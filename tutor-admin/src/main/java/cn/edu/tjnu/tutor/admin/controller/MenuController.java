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
import cn.edu.tjnu.tutor.common.util.TreeUtils;
import cn.edu.tjnu.tutor.common.validation.groups.Insert;
import cn.edu.tjnu.tutor.common.validation.groups.Update;
import cn.edu.tjnu.tutor.system.domain.entity.Menu;
import cn.edu.tjnu.tutor.system.domain.meta.MenuMeta;
import cn.edu.tjnu.tutor.system.domain.view.MenuVO;
import cn.edu.tjnu.tutor.system.service.MenuService;
import cn.edu.tjnu.tutor.system.structure.MenuStruct;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static cn.edu.tjnu.tutor.common.enums.Category.MENU;
import static cn.edu.tjnu.tutor.common.enums.ExceptionType.*;
import static cn.edu.tjnu.tutor.common.enums.OperType.*;

/**
 * 菜单信息控制层。
 *
 * @author 王帅
 * @since 2.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class MenuController extends BaseController {

    private final MenuStruct menuStruct;
    private final MenuService menuService;

    /**
     * 查询菜单树信息。
     *
     * @return 菜单信息
     */
    @GetMapping("treeList")
    public AjaxResult<List<MenuVO>> treeList() {
        return success(TreeUtils.build(menuService.list()
                .stream()
                .map(menuStruct::toVO)
                .sorted()
                .collect(Collectors.toList())));
    }

    /**
     * 查询菜单绑定的角色主键。
     *
     * @param menuId 菜单主键
     * @return 角色主键
     */
    @GetMapping("roleIdList/{menuId}")
    public AjaxResult<List<Integer>> roleIdList(@PathVariable Integer menuId) {
        return success(menuService.roleIdList(menuId));
    }

    /**
     * 添加菜单信息。
     *
     * @param menuMeta 菜单信息
     * @return {@code code = 200} 添加成功，{@code code = 500} 添加失败
     */
    @PostMapping("save")
    @Log(category = MENU, operType = INSERT)
    public AjaxResult<Void> save(@RequestBody @Validated(Insert.class) MenuMeta menuMeta) {
        Menu menu = menuStruct.toEntity(menuMeta);
        if (menuService.containsMenu(menu)) {
            return error(MENU_NAME_ALREADY_EXISTS, menu.getMenuName());
        }
        return toResult(menuService.save(menu, menuMeta.getRoleIds()));
    }

    /**
     * 更新菜单信息。
     *
     * @param menuMeta 菜单信息
     * @return {@code code = 200} 更新成功，{@code code = 500} 更新失败
     */
    @PutMapping("update")
    @Log(category = MENU, operType = UPDATE)
    public AjaxResult<Void> update(@RequestBody @Validated(Update.class) MenuMeta menuMeta) {
        Menu menu = menuStruct.toEntity(menuMeta);
        if (menuService.containsMenu(menu)) {
            return error(MENU_NAME_ALREADY_EXISTS, menu.getMenuName());
        }
        return toResult(menuService.update(menu, menuMeta.getRoleIds()));
    }

    /**
     * 根据菜单主键删除菜单信息。
     *
     * @param menuId 菜单主键
     * @return {@code code = 200} 删除成功，{@code code = 500} 删除失败
     */
    @DeleteMapping("remove/{menuId}")
    @Log(category = MENU, operType = DELETE)
    public AjaxResult<Void> remove(@PathVariable Integer menuId) {
        if (menuService.hasChildMenu(menuId)) {
            return error(SUBMENU_ALREADY_EXISTS);
        }
        if (menuService.isBindingRole(menuId)) {
            return error(MENU_ALREADY_BIND_ROLE);
        }
        return toResult(menuService.removeById(menuId));
    }

}