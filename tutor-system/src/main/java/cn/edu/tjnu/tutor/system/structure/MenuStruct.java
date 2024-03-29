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

package cn.edu.tjnu.tutor.system.structure;

import cn.edu.tjnu.tutor.system.domain.entity.Menu;
import cn.edu.tjnu.tutor.system.domain.meta.MenuMeta;
import cn.edu.tjnu.tutor.system.domain.view.MenuVO;
import cn.edu.tjnu.tutor.system.settings.MapstructSettings;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * 菜单实体类结构映射接口。
 *
 * @author 王帅
 * @since 2.0
 */
@Mapper(config = MapstructSettings.class)
public interface MenuStruct {

    /**
     * 转换到 VO 对象。
     *
     * @param menu 实体类
     * @return VO 对象
     */
    @Mapping(target = "id", source = "menuId")
    @Mapping(target = "children", ignore = true)
    MenuVO toVO(Menu menu);

    /**
     * 转换到实体类对象。
     *
     * @param meta 元数据对象
     * @return 实体类对象
     */
    Menu toEntity(MenuMeta meta);

}