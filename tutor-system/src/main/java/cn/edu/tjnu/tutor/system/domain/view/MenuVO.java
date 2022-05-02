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

package cn.edu.tjnu.tutor.system.domain.view;

import cn.edu.tjnu.tutor.common.core.domain.TreeNode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 菜单信息展示。
 *
 * @author 王帅
 * @since 2.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MenuVO extends TreeNode<MenuVO> implements Comparable<MenuVO>, Serializable {

    private static final long serialVersionUID = 2467683013639503307L;

    /**
     * 菜单名称。
     */
    private String menuName;

    /**
     * 菜单排序。
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

    // 忽略路由主键和父主键的序列化

    @Override
    @JsonProperty("key")
    public Integer getId() {
        return super.getId();
    }

    @Override
    @JsonIgnore
    public Integer getParentId() {
        return super.getParentId();
    }

    @Override
    public int compareTo(MenuVO o) {
        return this.weight.compareTo(o.weight);
    }

}