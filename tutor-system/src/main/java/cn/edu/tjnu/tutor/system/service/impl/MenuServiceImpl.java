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

import cn.edu.tjnu.tutor.common.util.TreeUtils;
import cn.edu.tjnu.tutor.system.domain.entity.Menu;
import cn.edu.tjnu.tutor.system.domain.view.RouterVO;
import cn.edu.tjnu.tutor.system.mapper.MenuMapper;
import cn.edu.tjnu.tutor.system.service.MenuService;
import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单信息服务层实现。
 *
 * @author 王帅
 * @since 1.0
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

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
    public boolean hasMenuName(String menuName) {
        return lambdaQuery().eq(Menu::getMenuName, menuName).count() != 0L;
    }

    @Override
    public boolean hasChildMenu(Integer menuId) {
        return lambdaQuery().eq(Menu::getParentId, menuId).count() != 0L;
    }

    @Override
    public boolean isMenuBindRole(Integer menuId) {
        return baseMapper.selectRoleCountByMenuId(menuId) != 0L;
    }

}