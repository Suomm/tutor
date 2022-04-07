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

package cn.edu.tjnu.tutor.support.filter;

import cn.edu.tjnu.tutor.common.core.domain.model.LoginUser;
import cn.edu.tjnu.tutor.common.provider.TokenProvider;
import cn.edu.tjnu.tutor.common.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT Token 过滤器。
 *
 * @author 王帅
 * @since 2.0
 */
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "token", name = "enable", havingValue = "true")
public class JwtTokenFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;

    @Override
    public void doFilterInternal(@NonNull HttpServletRequest request,
                                 @NonNull HttpServletResponse response,
                                 @NonNull FilterChain chain)
            throws ServletException, IOException {
        // 根据 Token 请求头获取当前登录用户
        LoginUser loginUser = tokenProvider.getLoginUser(request);
        // loginUser == null 用户没有令牌或者令牌不正确，拒绝访问任何资源
        // SecurityUtils.getAuthentication() == null 用户未登录到系统
        if (loginUser != null && SecurityUtils.getAuthentication() == null) {
            // 校验令牌是否过期并增加可用时间
            tokenProvider.verifyToken(loginUser);
            // 为持有令牌的用户创建登录信息
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        // 继续下一个过滤器
        chain.doFilter(request, response);
    }

}