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

package cn.edu.tjnu.tutor.admin.security;

import cn.edu.tjnu.tutor.common.util.ServletUtils;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * CAS 授权失败处理器。
 *
 * @author 王帅
 * @since 2.0
 */
@NoArgsConstructor
public class CasAuthenticationFailureHandler implements AuthenticationFailureHandler {

    /**
     * 登录认证 URL 地址。
     */
    @Setter
    private String loginUrl;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        if (ServletUtils.isAjaxRequest(request)) {
            // 异步请求：返回 401 状态码和重定向地址
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(loginUrl);
        } else {
            // 非异步请求，直接重定向到 CAS 登录地址
            response.sendRedirect(loginUrl);
        }
    }

}