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

package cn.edu.tjnu.tutor.common.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 统一类型常量。
 *
 * @author 王帅
 * @since 2.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GlobalConst {

    /**
     * GBK 字符集。
     */
    public static final String GBK = "GBK";

    /**
     * UTF-8 字符集。
     */
    public static final String UTF_8 = "UTF-8";

    /**
     * 空字符串数组。
     */
    public static final String[] EMPTY_STRING_ARRAY = {};

    /**
     * 日期时间格式化字符串。
     */
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

}