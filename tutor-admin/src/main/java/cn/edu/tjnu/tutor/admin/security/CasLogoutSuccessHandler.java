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

import cn.edu.tjnu.tutor.common.core.domain.model.LoginUser;
import cn.edu.tjnu.tutor.common.provider.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * CAS 登出成功处理器。
 *
 * @author 王帅
 * @since 2.0
 */
@RequiredArgsConstructor
public class CasLogoutSuccessHandler implements LogoutSuccessHandler {

    /**
     * 令牌的提供者。
     */
    private final TokenProvider tokenProvider;

    /**
     * 认证服务器登出地址。
     */
    private final String logoutUrl;

    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication) throws IOException {
        // 获取当前登录用户
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 删除当前登录用户的令牌
        tokenProvider.deleteToken(loginUser.getUserCode());
        // 处理完成之后返回注销地址
        response.getWriter().write(logoutUrl);
    }

}