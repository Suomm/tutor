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

package cn.edu.tjnu.tutor.support.config;

import cn.edu.tjnu.tutor.support.security.cas.CasLogoutHandler;
import cn.edu.tjnu.tutor.support.security.filter.JwtAuthenticationTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 后端服务安全配置。
 *
 * @author 王帅
 * @since 1.0
 */
@EnableWebSecurity
@RequiredArgsConstructor
@Configuration(proxyBeanMethods = false)
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CasLogoutHandler casLogoutHandler;

    private final JwtAuthenticationTokenFilter authenticationTokenFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 设置监控端点需要认证，并开启 HTTP Basic，用于监控端点的授权
        http.httpBasic().init(http.requestMatcher(EndpointRequest.toAnyEndpoint()));
        // 关闭 CSRF 防护功能，因为不使用 Session
        http.csrf().disable();
        // CAS 过滤器拦截所有请求
        http.authorizeRequests()
                .anyRequest().authenticated();
        // Cas 登出配置
        http.logout()
                .logoutUrl("/logout")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .addLogoutHandler(casLogoutHandler);
        // 添加 JWT Filter
        http.addFilterAfter(authenticationTokenFilter, CasAuthenticationFilter.class);
    }

}
