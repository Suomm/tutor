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

import cn.edu.tjnu.tutor.common.provider.TokenProvider;
import cn.edu.tjnu.tutor.common.util.SecurityUtils;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * CAS 授权成功处理器。
 *
 * @author 王帅
 * @since 2.0
 */
@Setter
@NoArgsConstructor
public class CasAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    /**
     * Token 连接参数。
     */
    private static final String TOKEN_VARIABLE = "?token=";

    /**
     * 授权成功的重定向地址。
     */
    private String callbackUrl;

    /**
     * 令牌的提供者。
     */
    private TokenProvider tokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        // 为当前用户创建令牌
        String token = tokenProvider.createToken(SecurityUtils.getLoginUser());
        // 携带令牌信息重定向
        response.sendRedirect(callbackUrl + TOKEN_VARIABLE + token);
    }

}