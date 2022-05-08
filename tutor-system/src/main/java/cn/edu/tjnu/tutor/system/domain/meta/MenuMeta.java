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

package cn.edu.tjnu.tutor.system.domain.meta;

import cn.edu.tjnu.tutor.common.validation.groups.Insert;
import cn.edu.tjnu.tutor.common.validation.groups.Update;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * <p>菜单元数据信息，用于后台操作数据。
 *
 * <p>插入菜单数据时：<br>
 * {@code
 *     {
 *         "menuName": "系统管理",
 *         "path": "system",
 *         "roleIds": [],
 *         ... // 其他可选数据插入项
 *     }
 * }
 *
 * <p>更新菜单数据时：<br>
 * {@code
 *     {
 *         "menuId": 1,
 *         "menuName": "系统管理",
 *         ... // 需要更新的数据项
 *     }
 * }
 *
 * @author 王帅
 * @since 2.0
 */
@Data
public class MenuMeta implements Serializable {

    private static final long serialVersionUID = 3659034788828271077L;

    /**
     * 菜单主键。
     */
    @Null(groups = Insert.class)
    @NotNull(groups = Update.class)
    private Integer menuId;

    /**
     * 菜单名称。
     */
    @NotNull
    @Size(max = 50)
    private String menuName;

    /**
     * 父菜单主键。
     */
    @Min(0)
    private Integer parentId;

    /**
     * 菜单权重。
     */
    @Min(1)
    private Integer weight;

    /**
     * 路由地址。
     */
    @Size(max = 200)
    @NotNull(groups = Insert.class)
    private String path;

    /**
     * 组件路径。
     */
    @Size(max = 255)
    private String component;

    /**
     * 菜单图标。
     */
    @Size(max = 100)
    private String icon;

    /**
     * 角色绑定。
     */
    @NotNull(groups = Insert.class)
    private Integer[] roleIds;

}