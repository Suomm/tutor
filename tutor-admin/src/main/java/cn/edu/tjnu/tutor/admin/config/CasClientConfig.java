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

import cn.edu.tjnu.tutor.admin.strategy.CasAuthenticationRedirectStrategy;
import cn.edu.tjnu.tutor.common.provider.TokenProvider;
import org.jasig.cas.client.boot.configuration.CasClientConfigurationProperties;
import org.jasig.cas.client.boot.configuration.CasClientConfigurer;
import org.jasig.cas.client.boot.configuration.EnableCasClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * CAS 单点登录的相关配置。
 *
 * @author 王帅
 * @since 1.0
 */
@EnableCasClient
@Configuration(proxyBeanMethods = false)
@ConditionalOnBean(TokenProvider.class)
@ConditionalOnProperty(prefix = "spring.cas", name = "enable", havingValue = "true")
public class CasClientConfig implements CasClientConfigurer {

    /**
     * 由于 {@link CasClientConfigurationProperties} 设置了 {@code ignoreUnknownFields = false}，
     * 导致不能绑定其他属性。为了配置的统一与可读性，还有代码的可读性，这里将 CAS 的所有配置都绑定在前缀为
     * {@code spring.cas} 的配置文件中。
     */
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.cas")
    public CasClientConfigurationProperties configProps() {
        return new CasClientConfigurationProperties();
    }

    @Override
    public void configureAuthenticationFilter(FilterRegistrationBean authenticationFilter) {
        // 配置 CAS 自定义重定向策略
        authenticationFilter.addInitParameter("authenticationRedirectStrategyClass",
                CasAuthenticationRedirectStrategy.class.getName());
    }

}