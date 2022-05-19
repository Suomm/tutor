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

package cn.edu.tjnu.tutor.system.service;

import cn.edu.tjnu.tutor.system.domain.entity.Menu;
import cn.edu.tjnu.tutor.system.domain.view.RouterVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Objects;

/**
 * 菜单信息服务层。
 *
 * @author 王帅
 * @since 1.0
 */
public interface MenuService extends IService<Menu> {

    /**
     * 保存菜单信息，并绑定菜单对应的角色。
     *
     * @param menu    菜单信息
     * @param roleIds 角色信息
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    boolean save(Menu menu, Integer[] roleIds);

    /**
     * 更新菜单信息，并更新菜单对应的角色（如果需要的话）。
     *
     * @param menu    菜单信息
     * @param roleIds 角色信息（可以为 {@code null}）
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    boolean update(Menu menu, Integer[] roleIds);

    /**
     * 根据用户主键查询路由权限信息。
     *
     * @param userId 用户主键
     * @return 路由信息
     */
    List<RouterVO> getRouters(Integer userId);

    /**
     * 查询菜单所对应的角色主键。
     *
     * @param menuId 菜单主键
     * @return 角色主键
     */
    List<Integer> roleIdList(Integer menuId);

    /**
     * 判断是否包含给定的菜单信息。
     *
     * @param menu 菜单信息
     * @return {@code true} 包含给定的菜单信息，{@code false} 不包含给定的菜单信息
     * @implSpec 对于菜单信息服务，该默认实现为：
     * <pre>{@code
     * return lambdaQuery()
     *         .eq(Menu::getMenuName, menu.getMenuName())
     *         .eq(Objects.nonNull(menu.getParentId()), Menu::getParentId, menu.getParentId())
     *         .ne(Objects.nonNull(menu.getMenuId()), Menu::getMenuId, menu.getMenuId())
     *         .exists();
     * }</pre>
     * @apiNote 该方法会根据菜单的名称来进行判断是否包含给定的菜单信息。要求同属于一个父菜单的子菜单名称不能重复，
     * 不指定父菜单的情况下全部菜单名称不能重复。
     */
    default boolean containsMenu(Menu menu) {
        return lambdaQuery()
                .eq(Menu::getMenuName, menu.getMenuName())
                .eq(Objects.nonNull(menu.getParentId()), Menu::getParentId, menu.getParentId())
                .ne(Objects.nonNull(menu.getMenuId()), Menu::getMenuId, menu.getMenuId())
                .exists();
    }

    /**
     * 判断菜单是否含有子菜单。
     *
     * @param menuId 菜单主键
     * @return {@code true} 存在子菜单，{@code false} 没有子菜单
     * @implSpec 对于菜单信息服务，该默认实现为：
     * <pre>{@code
     * return lambdaQuery()
     *         .eq(Menu::getParentId, menuId)
     *         .exists();
     * }</pre>
     */
    default boolean hasChildMenu(Integer menuId) {
        return lambdaQuery()
                .eq(Menu::getParentId, menuId)
                .exists();
    }

    /**
     * 判断菜单是否绑定了角色。
     *
     * @param menuId 菜单主键
     * @return {@code true} 已绑定角色，{@code false} 未绑定角色
     */
    boolean isBindingRole(Integer menuId);

}