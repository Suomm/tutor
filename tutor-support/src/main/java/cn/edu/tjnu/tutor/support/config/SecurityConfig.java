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

import cn.edu.tjnu.tutor.support.filter.JwtTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring Security 配置。
 *
 * @author 王帅
 * @since 1.0
 */
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Order(1)
    @Configuration(proxyBeanMethods = false)
    public static class HttpBasicConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/actuator/**")
                    .authorizeRequests(authorize -> authorize.anyRequest().hasRole("MONITOR"))
                    .httpBasic();
        }

    }

    @RequiredArgsConstructor
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnBean(JwtTokenFilter.class)
    @EnableGlobalMethodSecurity(securedEnabled = true)
    public static class JwtTokenConfig extends WebSecurityConfigurerAdapter {

        private final JwtTokenFilter jwtTokenFilter;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests(authorize -> authorize.anyRequest().authenticated())
                    .exceptionHandling()
                    .authenticationEntryPoint(new Http403ForbiddenEntryPoint()).and()
                    .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                    .csrf().disable();
        }

    }

}