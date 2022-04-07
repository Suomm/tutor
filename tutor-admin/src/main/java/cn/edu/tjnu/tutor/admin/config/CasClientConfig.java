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

package cn.edu.tjnu.tutor.admin.config;

import cn.edu.tjnu.tutor.admin.security.CasAuthenticationFailureHandler;
import cn.edu.tjnu.tutor.admin.security.CasAuthenticationSuccessHandler;
import cn.edu.tjnu.tutor.admin.security.CasLogoutSuccessHandler;
import cn.edu.tjnu.tutor.common.provider.TokenProperties;
import cn.edu.tjnu.tutor.common.provider.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
import org.jasig.cas.client.validation.TicketValidator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;

/**
 * <p><a href="https://docs.spring.io/spring-security/reference/servlet/authentication/cas.html">
 * Spring Security CAS Client 授权配置。
 * </a>
 *
 * <p>前后端分离分布式微服务组件
 *
 * <ul>
 *     <li>前端地址：https://localhost/</li>
 *     <li>网关地址：http://localhost:5735/</li>
 *     <li>授权地址：http://localhost:9377/</li>
 *     <li>认证地址：http://localhost:8443/cas</li>
 * </ul>
 *
 * <p><b>CAS 登录流程：</b>
 *
 * <ul>
 *     <li>前端登录请求：https://localhost/api/login</li>
 *     <li>Nginx 反向代理到网关：http://localhost:5735/login</li>
 *     <li>网关将请求转发到授权服务器：http://localhost:9377/auth/login</li>
 *     <li>用户没有被授权时，授权服务器返回 401 UNAUTHORIZED 状态码并将认证地址返回给前端</li>
 *     <li>前端访问认证地址：https://localhost:8443/cas/login?service=URLEncode(https://localhost/auth/login)</li>
 *     <li>认证成功之后重定向到：https://localhost/auth/login?ticket=...</li>
 *     <li>Nginx 反向代理到网关：http://localhost:5735/login?ticket=...</li>
 *     <li>网关将请求转发到授权服务器：http://localhost:9377/auth/login?ticket=...</li>
 *     <li>授权服务器根据 Ticket 进行授权，从数据库查询用户并生成生成 Token 令牌</li>
 *     <li>授权服务器重定向到前端：https://localhost/login?token=...</li>
 *     <li>前端根据 URL 参数进行保存 Token 操作，携带令牌即可访问相关接口</li>
 * </ul>
 *
 * <p><b>CAS 登出流程：</b>
 *
 * <ul>
 *     <li>前端登出请求：https://localhost/api/logout</li>
 *     <li>Nginx 反向代理到网关：http://localhost:5735/logout</li>
 *     <li>网关将请求转发到授权服务器：http://localhost:9377/auth/logout</li>
 *     <li>授权服务器返回重定向地址，前端接收到地址后可以进行前端数据清理等操作</li>
 *     <li>前端进行重定向：https://localhost:8443/cas/logout?service=URLEncode(https://localhost/auth/login)</li>
 *     <li>登出成功之后重定向到：https://localhost/auth/login</li>
 *     <li>Nginx 反向代理到网关：http://localhost:5735/login</li>
 *     <li>网关将请求转发到授权服务器：http://localhost:9377/auth/login</li>
 *     <li>授权服务器返回 401 UNAUTHORIZED 状态码并将认证地址返回给前端</li>
 *     <li>前端访问认证地址：https://localhost:8443/cas/login?service=URLEncode(https://localhost/auth/login)</li>
 *     <li>其他可以用户在 CAS 认证登陆界面进行再次登录或者直接退出系统</li>
 * </ul>
 *
 * <p>参考：
 * <a href="https://docs.spring.io/spring-security/reference/servlet/configuration/java.html#_multiple_httpsecurity">
 * Spring Security 多个过滤链的优先级配置。
 * </a>
 *
 * @author 王帅
 * @since 2.0
 */
@Configuration
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
@EnableConfigurationProperties({CasClientProperties.class, TokenProperties.class})
@ConditionalOnProperty(name = {"cas.enable", "token.enable"}, havingValue = "true")
public class CasClientConfig extends WebSecurityConfigurerAdapter {

    /**
     * 令牌提供者，用于登录成功之后创建令牌。
     */
    private final TokenProvider tokenProvider;

    /**
     * CAS 授权服务相关配置，用于设置 CAS 过滤器等。
     */
    private final CasClientProperties properties;

    /**
     * 用户服务，用于登录成功之后创建用户对象保存在上下文中。
     */
    private final AuthenticationUserDetailsService<CasAssertionAuthenticationToken>
            authenticationUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/auth/*")
                .logout()
                .logoutUrl("/auth/logout")
                .invalidateHttpSession(true)
                .logoutSuccessHandler(
                        new CasLogoutSuccessHandler(tokenProvider, properties.getLogoutUrl())
                ).and().csrf().disable()
                .addFilter(casAuthenticationFilter());
    }

    @Bean
    public CasAuthenticationFilter casAuthenticationFilter() throws Exception {
        CasAuthenticationFilter filter = new CasAuthenticationFilter();
        filter.setServiceProperties(properties);
        filter.setFilterProcessesUrl("/auth/login");
        filter.setAuthenticationManager(authenticationManager());
        filter.setSessionAuthenticationStrategy(new NullAuthenticatedSessionStrategy());
        CasAuthenticationSuccessHandler successHandler = new CasAuthenticationSuccessHandler();
        successHandler.setTokenProvider(tokenProvider);
        successHandler.setCallbackUrl(properties.getCallbackUrl());
        filter.setAuthenticationSuccessHandler(successHandler);
        CasAuthenticationFailureHandler failureHandler = new CasAuthenticationFailureHandler();
        failureHandler.setLoginUrl(properties.getLoginUrl());
        filter.setAuthenticationFailureHandler(failureHandler);
        return filter;
    }

    @Bean
    public CasAuthenticationProvider casAuthenticationProvider() {
        CasAuthenticationProvider provider = new CasAuthenticationProvider();
        provider.setServiceProperties(properties);
        provider.setTicketValidator(ticketValidator());
        provider.setKey("casAuthenticationProviderKey");
        provider.setAuthenticationUserDetailsService(authenticationUserDetailsService);
        return provider;
    }

    @Bean
    public TicketValidator ticketValidator() {
        return new Cas20ServiceTicketValidator(properties.getServer());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(casAuthenticationProvider());
    }

}
