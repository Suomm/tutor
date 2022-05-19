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

import java.util.regex.Pattern;

/**
 * 正则预编译常量。
 *
 * @author 王帅
 * @since 2.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RegexConst {

    /**
     * 手机号正则，用于校验手机号。
     */
    public static final Pattern PHONE = Pattern.compile("1[3456789]\\d{9}");

    /**
     * 用户编号正则，教师六位数字，学生十位数字。
     */
    public static final Pattern USER_CODE = Pattern.compile("\\d{6}|\\d{10}");

    /**
     * 班级名称正则表达式，用于提取年级信息。
     */
    public static final Pattern CLASS_NAME = Pattern.compile(".+\\d{4}");

}