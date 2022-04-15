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

package cn.edu.tjnu.tutor.common.core.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 树节点实体类。
 *
 * @author 王帅
 * @since 2.0
 * @param <T> 数据类型
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public abstract class TreeNode<T extends TreeNode<T>> {

    /**
     * 树主键。
     */
    protected Integer id;

    /**
     * 父主键。
     */
    protected Integer parentId;

    /**
     * 子节点。
     */
    protected List<T> children;

}