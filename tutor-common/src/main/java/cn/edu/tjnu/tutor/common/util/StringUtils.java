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

import java.util.regex.Pattern;

/**
 * 字符串工具类。
 *
 * @author 王帅
 * @since 2.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StringUtils {

    /**
     * 根据班级名称截取年级信息。
     *
     * @param className 班级名称
     * @return 年级信息
     */
    public static String trimGrade(String className) {
        int length = className.length();
        return className.substring(length - 4, length - 2);
    }

    /**
     * <p>判断所给定的字符串是否满足正则表达式，如果所给需要匹配的内容为
     * {@code null} 的话，匹配结果返回 {@code true} 最为结果，用于
     * JSR-303 规范（Bean Validation 规范）的空值跳过。
     *
     * @param pattern 正则表达式
     * @param text    需要匹配的内容
     * @return {@code true} 匹配正则，{@code false} 不匹配正则
     */
    public static boolean match(Pattern pattern, CharSequence text) {
        return text == null || pattern.matcher(text).matches();
    }

}