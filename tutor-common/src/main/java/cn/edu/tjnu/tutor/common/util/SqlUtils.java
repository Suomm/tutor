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

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * SQL 结果处理类。
 *
 * @author 王帅
 * @since 2.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SqlUtils {

    /**
     * 将 {@code count} 查询结果转为 {@code boolean}。
     *
     * @param count 数据数量
     * @return {@code true} 存在数据，{@code false} 不存在数据
     */
    public static boolean toBool(Long count) {
        return count != null && count > 0;
    }

    /**
     * 将数据库受影响行数转换为 {@code boolean}。
     *
     * @param rows 受影响行数
     * @return {@code true} 操作成功，{@code false} 操作失败
     */
    public static boolean toBool(int rows) {
        return rows > 0;
    }

}