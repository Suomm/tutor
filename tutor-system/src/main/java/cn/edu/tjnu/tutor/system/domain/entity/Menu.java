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

package cn.edu.tjnu.tutor.system.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 菜单信息。
 *
 * @author 王帅
 * @since 1.0
 */
@Data
@TableName("sys_menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单主键。
     */
    @TableId(type = IdType.AUTO)
    private Integer menuId;

    /**
     * 菜单名称。
     */
    private String menuName;

    /**
     * 父菜单主键。
     */
    private Integer parentId;

    /**
     * 菜单权重。
     */
    private Integer weight;

    /**
     * 路由地址。
     */
    private String path;

    /**
     * 组件路径。
     */
    private String component;

    /**
     * 菜单图标。
     */
    private String icon;

}