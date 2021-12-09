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

package cn.edu.tjnu.tutor.framework.web.filter;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.ReUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 防止XSS攻击的过滤器。
 *
 * @author 王帅
 * @since 2.0
 */
public class XssFilter implements Filter {

    /**
     * 排除链接。
     */
    private final List<String> excludes = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) {
        String tempExcludes = filterConfig.getInitParameter("excludes");
        if (!CharSequenceUtil.isEmpty(tempExcludes)) {
            String[] urls = tempExcludes.split(",");
            Collections.addAll(excludes, urls);
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (handleExcludeUrl((HttpServletRequest) request)) {
            chain.doFilter(request, response);
            return;
        }
        XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper(request);
        chain.doFilter(xssRequest, response);
    }

    /**
     * 处理排除过滤的连接。
     */
    private boolean handleExcludeUrl(HttpServletRequest request) {
        String url = request.getServletPath();
        String method = request.getMethod();
        // GET DELETE 不过滤
        if (method == null ||
                method.matches("GET") ||
                method.matches("DELETE")) {
            return true;
        }
        return matches(url, excludes);
    }

    @Override
    public void destroy() {
        // unimplemented method.
    }

    /**
     * 查找指定字符串是否匹配指定字符串列表中的任意一个字符串。
     *
     * @param str  指定字符串
     * @param patterns 需要检查的字符串数组
     * @return 是否匹配
     */
    private static boolean matches(String str, List<String> patterns) {
        if (CharSequenceUtil.isEmpty(str) ||
                CollUtil.isEmpty(patterns)) {
            return false;
        }
        for (String pattern : patterns) {
            if (ReUtil.isMatch(pattern, str)) {
                return true;
            }
        }
        return false;
    }

}