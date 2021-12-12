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

import cn.edu.tjnu.tutor.support.security.authentication.CasAuthenticationRedirectStrategy;
import org.jasig.cas.client.boot.configuration.CasClientConfigurer;
import org.jasig.cas.client.boot.configuration.EnableCasClient;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;

/**
 * CAS SSO 的相关配置。
 *
 * @author 王帅
 * @since 1.0
 */
@EnableCasClient
@Configuration(proxyBeanMethods = false)
public class CasConfig implements CasClientConfigurer {

    @Override
    public void configureAuthenticationFilter(FilterRegistrationBean authenticationFilter) {
        // 配置 CAS 自定义重定向策略
        authenticationFilter.addInitParameter("authenticationRedirectStrategyClass",
                CasAuthenticationRedirectStrategy.class.getName());
        // CAS 不要拦截 Actuator 监控端点
        authenticationFilter.addInitParameter("ignorePattern", "/actuator.*");
        authenticationFilter.addInitParameter("ignoreUrlPatternType",
                "org.jasig.cas.client.authentication.RegexUrlPatternMatcherStrategy");
    }

}