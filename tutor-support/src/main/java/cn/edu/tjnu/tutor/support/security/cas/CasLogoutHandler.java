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

import cn.edu.tjnu.tutor.common.core.domain.model.LoginUser;
import cn.edu.tjnu.tutor.common.provider.TokenProvider;
import cn.edu.tjnu.tutor.common.util.ServletUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * CAS 登出配置。
 *
 * @author 王帅
 * @since 2.0
 */
@Component
@RequiredArgsConstructor
public class CasLogoutHandler implements LogoutHandler {

    private final TokenProvider tokenProvider;
    @Value("${cas.server-url-prefix}")
    private String serverUrlPrefix;
    @Value("${cas.client-host-url}")
    private String clientHostUrl;

    @SneakyThrows
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        LoginUser loginUser = tokenProvider.getLoginUser(request);
        // 删除当前登录用户令牌和用户信息
        if (loginUser != null) {
            tokenProvider.deleteToken(loginUser.getUuid());
        }
        // 返回前端重定向地址或者直接重定向
        String potentialRedirectUrl = serverUrlPrefix + "/logout?service=" + clientHostUrl;
        if (ServletUtils.isAjaxRequest(request)) {
            response.getWriter().write(potentialRedirectUrl);
        } else {
            response.sendRedirect(potentialRedirectUrl);
        }
    }

}