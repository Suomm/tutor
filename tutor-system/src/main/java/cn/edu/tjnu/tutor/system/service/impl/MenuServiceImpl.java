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

package cn.edu.tjnu.tutor.system.service.impl;

import cn.edu.tjnu.tutor.common.util.SqlUtils;
import cn.edu.tjnu.tutor.common.util.TreeUtils;
import cn.edu.tjnu.tutor.system.domain.entity.Menu;
import cn.edu.tjnu.tutor.system.domain.extra.RoleMenu;
import cn.edu.tjnu.tutor.system.domain.view.RouterVO;
import cn.edu.tjnu.tutor.system.mapper.MenuMapper;
import cn.edu.tjnu.tutor.system.mapper.RoleMenuMapper;
import cn.edu.tjnu.tutor.system.service.MenuService;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单信息服务层实现。
 *
 * @author 王帅
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    private final RoleMenuMapper roleMenuMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveAndBind(Menu menu) {
        return save(menu) && insertRoleMenu(menu.getMenuId(), menu.getRoleIds());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateAndBind(Menu menu) {
        boolean inserted = true;
        if (ArrayUtil.isNotEmpty(menu.getRoleIds())) {
            roleMenuMapper.deleteById(menu.getMenuId());
            inserted = insertRoleMenu(menu.getMenuId(), menu.getRoleIds());
        }
        return inserted && super.updateById(menu);
    }

    /**
     * 插入角色与菜单的绑定到数据库。
     *
     * @param menuId  菜单主键
     * @param roleIds 角色主键
     */
    private boolean insertRoleMenu(Integer menuId, Integer[] roleIds) {
        List<RoleMenu> entities = Arrays.stream(roleIds)
                .map(roleId -> new RoleMenu(roleId, menuId))
                .collect(Collectors.toList());
        return SqlUtils.toBool(roleMenuMapper.insertBatch(entities));
    }

    @Override
    public List<RouterVO> getRouters(Integer userId) {
        return TreeUtils.build(baseMapper.findByUserId(userId)
                .stream()
                .map(this::generate)
                .collect(Collectors.toList()));
    }

    /**
     * 生成未进行映射的属性。
     */
    private RouterVO generate(RouterVO routerVO) {
        // 设置路由的 name 属性，默认 path 属性首字母大写
        routerVO.setName(CharSequenceUtil.upperFirst(routerVO.getPath()));
        // 如果路由对应的组件为空，则为父组件，path 前面要加 /
        if (routerVO.getComponent() == null) {
            routerVO.setComponent("LAYOUT");
            routerVO.setPath("/" + routerVO.getPath());
        }
        return routerVO;
    }

    @Override
    public List<Integer> roleIdList(Integer menuId) {
        return ChainWrappers.lambdaQueryChain(roleMenuMapper)
                .select(RoleMenu::getRoleId)
                .eq(RoleMenu::getMenuId, menuId)
                .list()
                .stream()
                .map(RoleMenu::getRoleId)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isBindingRole(Integer menuId) {
        return ChainWrappers.lambdaQueryChain(roleMenuMapper)
                .eq(RoleMenu::getMenuId, menuId)
                .exists();
    }

}