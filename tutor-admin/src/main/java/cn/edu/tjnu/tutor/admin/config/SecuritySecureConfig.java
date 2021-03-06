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

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.UUID;

/**
 * <a href="https://codecentric.github.io/spring-boot-admin/current/#_securing_spring_boot_admin_server">
 * SpringBoot Admin 安全配置。
 * </a>
 *
 * @author 王帅
 * @since 2.0
 */
@RequiredArgsConstructor
@Configuration(proxyBeanMethods = false)
public class SecuritySecureConfig extends WebSecurityConfigurerAdapter {

    private final SecurityProperties security;

    private final AdminServerProperties adminServer;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter("redirectTo");
        successHandler.setDefaultTargetUrl(this.adminServer.path("/"));

        http.authorizeRequests(
                        authorizeRequests -> authorizeRequests.antMatchers(this.adminServer.path("/assets/**")).permitAll()
                                .antMatchers(this.adminServer.path("/actuator/info")).permitAll()
                                .antMatchers(this.adminServer.path("/actuator/health")).permitAll()
                                .antMatchers(this.adminServer.path("/login")).permitAll()
                                .antMatchers("/auth/*").permitAll()
                                .anyRequest().hasRole("ROOT")
                ).formLogin(
                        formLogin -> formLogin.loginPage(this.adminServer.path("/login")).successHandler(successHandler).and()
                ).logout(logout -> logout.logoutUrl(this.adminServer.path("/logout"))).httpBasic(Customizer.withDefaults())
                .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        .ignoringRequestMatchers(
                                new AntPathRequestMatcher(this.adminServer.path("/instances"),
                                        HttpMethod.POST.toString()),
                                new AntPathRequestMatcher(this.adminServer.path("/instances/*"),
                                        HttpMethod.DELETE.toString()),
                                new AntPathRequestMatcher(this.adminServer.path("/actuator/**"))
                        ))
                .rememberMe(rememberMe -> rememberMe.key(UUID.randomUUID().toString()).tokenValiditySeconds(1209600));
    }

    /**
     * Required to provide UserDetailsService for "remember functionality".
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        SecurityProperties.User user = security.getUser();
        auth.inMemoryAuthentication().withUser(user.getName())
                .password("{noop}" + user.getPassword())
                .authorities(user.getRoles().stream().map(e -> "ROLE_" + e).toArray(String[]::new));
    }

}