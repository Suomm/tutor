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

import cn.edu.tjnu.tutor.common.validation.groups.Insert;
import cn.edu.tjnu.tutor.common.validation.groups.Update;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
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
    @Null(groups = Insert.class)
    @NotNull(groups = Update.class)
    @TableId(type = IdType.AUTO)
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
    @TableField(exist = false)
    @NotNull(groups = Insert.class)
    private Integer[] roleIds;

}