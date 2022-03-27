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

package cn.edu.tjnu.tutor.common.annotation;

import cn.edu.tjnu.tutor.common.enums.Category;
import cn.edu.tjnu.tutor.common.enums.OperType;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 自定义操作日志记录注解。
 *
 * @author 王帅
 * @since 2.0
 */
@Documented
@Target(METHOD)
@Retention(RUNTIME)
public @interface Log {

    /**
     * 日志类别。
     */
    Category category();

    /**
     * 业务操作。
     */
    OperType operType() default OperType.OTHER;

    /**
     * 是否保存请求的参数。
     */
    boolean isSaveRequestData() default true;

    /**
     * 是否保存响应的参数。
     */
    boolean isSaveResponseData() default true;

}
