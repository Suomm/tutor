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

package cn.edu.tjnu.tutor.support.security.cas;

import cn.edu.tjnu.tutor.common.util.ServletUtils;
import org.jasig.cas.client.authentication.AuthenticationRedirectStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * CAS 单点登陆的重定向策略。
 *
 * @author 王帅
 * @since 1.0
 */
public class CasAuthenticationRedirectStrategy implements AuthenticationRedirectStrategy {

    @Override
    public void redirect(HttpServletRequest request, HttpServletResponse response,
                         String potentialRedirectUrl) throws IOException {
        if (ServletUtils.isAjaxRequest(request)) {
            // 异步请求：返回 401 状态码和重定向地址
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(potentialRedirectUrl);
        } else {
            // 非异步请求，直接重定向到 CAS 登录地址
            response.sendRedirect(potentialRedirectUrl);
        }
    }

}