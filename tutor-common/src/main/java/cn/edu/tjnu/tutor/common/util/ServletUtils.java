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

import cn.hutool.extra.servlet.ServletUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 客户端工具类。
 *
 * @author 王帅
 * @since 2.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ServletUtils {

    /**
     * Ajax 异步请求请求头。
     */
    public static final String XML_HTTP_REQUEST = "XMLHttpRequest";

    /**
     * Ajax 异步请求请求头对应值。
     */
    public static final String X_REQUESTED_WITH = "X-Requested-With";

    /**
     * 判断请求是否为 Ajax 异步请求。
     *
     * @param request 请求
     * @return {@code true} 请求为异步请求，{@code false} 非异步请求
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        return XML_HTTP_REQUEST.equals(request.getHeader(X_REQUESTED_WITH));
    }

    /**
     * 获取 {@link HttpServletRequest} 类。
     *
     * @return 当前请求对象
     */
    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    /**
     * 获取 {@link ServletRequestAttributes} 类。
     */
    private static ServletRequestAttributes getRequestAttributes() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) Objects.requireNonNull(attributes);
    }

    /**
     * 获取客户端 IP 地址。
     *
     * @return IP 地址
     */
    public static String getClientIp() {
        return ServletUtil.getClientIP(getRequest());
    }

}