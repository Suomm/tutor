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

import cn.edu.tjnu.tutor.common.core.domain.Pagination;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 分页工具类。
 *
 * @author 王帅
 * @since 2.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PageUtils {

    /**
     * 将 Elasticsearch 分页对象转换为 {@link Pagination} 对象。
     *
     * @param page 分页对象
     * @param <T> 数据类型
     * @return 分页数据
     */
    public static <T> Pagination<T> convert(
            org.springframework.data.domain.Page<T> page
    ) {
        return new Pagination<>(page.getTotalElements(), page.getContent());
    }

    /**
     * 将 MyBatis Plus 分页对象转换为 {@link Pagination} 对象。
     *
     * @param page 分页对象
     * @param <T> 数据类型
     * @return 分页数据
     */
    public static <T> Pagination<T> convert(
            com.baomidou.mybatisplus.extension.plugins.pagination.Page<T> page
    ) {
        return new Pagination<>(page.getTotal(), page.getRecords());
    }

}