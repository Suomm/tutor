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

package cn.edu.tjnu.tutor.common.locale;

import org.springframework.context.i18n.LocaleContextHolder;

import java.io.Serializable;
import java.util.Locale;

/**
 * 可获取国际化字符串消息的接口。
 *
 * @author 王帅
 * @since 2.0
 */
public interface Localizable extends Serializable {

    /**
     * 通过 {@link LocaleContextHolder#getLocale()} 方法获得区域信息，
     * 解析返回消息。
     *
     * @param args 格式化参数
     * @return 默认消息
     * @implSpec 对于国际化接口，该默认实现为：
     * <pre>{@code
     * return getLocalizedMessage(LocaleContextHolder.getLocale(), args);
     * }</pre>
     */
    default String getMessage(Object... args) {
        return getLocalizedMessage(LocaleContextHolder.getLocale(), args);
    }

    /**
     * 通过指定的区域信息解析返回消息。
     *
     * @param locale 区域信息
     * @param args   格式化参数
     * @return 默认消息
     */
    String getLocalizedMessage(Locale locale, Object... args);

}