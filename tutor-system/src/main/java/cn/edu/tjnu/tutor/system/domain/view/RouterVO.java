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
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 路由信息。
 *
 * @author 王帅
 * @since 2.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RouterVO extends TreeNode<RouterVO> implements Serializable {

    private static final long serialVersionUID = 7776260582756952728L;

    /**
     * 路由地址。
     *
     * @mock /system
     */
    private String path;

    /**
     * 路由名称（不能重复）。
     *
     * @mock System
     */
    private String name;

    /**
     * 路由组件。
     *
     * @mock LAYOUT
     */
    private String component;

    /**
     * 其他元数据。
     */
    private MetaVO meta;

    // 忽略路由主键和父主键的序列化

    @Override
    @JsonIgnore
    public Integer getId() {
        return super.getId();
    }

    @Override
    @JsonIgnore
    public Integer getParentId() {
        return super.getParentId();
    }

}