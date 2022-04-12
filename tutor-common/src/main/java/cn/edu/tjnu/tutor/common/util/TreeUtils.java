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

package cn.edu.tjnu.tutor.common.util;

import cn.edu.tjnu.tutor.common.core.domain.TreeNode;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 树结构工具类。
 *
 * @author 王帅
 * @since 2.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TreeUtils {

    /**
     * 构建树结构（默认父节点主键为 0）。
     *
     * @param nodes 所有节点数据集合
     * @param <T> 数据类型
     * @return 完整的树结构信息
     */
    public static <T extends TreeNode<T>> List<T> build(Collection<T> nodes) {
        return build(nodes, 0);
    }

    /**
     * 构建树结构。
     *
     * @param nodes 所有节点数据集合
     * @param parentId 父节点主键
     * @param <T> 数据类型
     * @return 完整的树结构信息
     */
    public static <T extends TreeNode<T>> List<T> build(Collection<T> nodes, Integer parentId) {
        List<T> list = nodes.stream()
                .filter(e -> e.getParentId().equals(parentId))
                .collect(Collectors.toList());
        list.forEach(e -> e.setChildren(build(nodes, e.getId())));
        return list;
    }

}