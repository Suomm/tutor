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

package cn.edu.tjnu.tutor.framework.config;

import cn.edu.tjnu.tutor.framework.web.filter.XssFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import javax.servlet.DispatcherType;
import java.util.HashMap;
import java.util.Map;

/**
 * Xss 防护的配置。
 *
 * @author 王帅
 * @since 2.0
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(prefix = "xss", value = "enable", havingValue = "true")
public class XssConfig {

    @Value("${xss.excludes:#{null}}")
    private String excludes;

    @Value("${xss.urlPatterns:/*}")
    private String urlPatterns;

    @Bean
    public FilterRegistrationBean<XssFilter> xssFilterRegistration() {
        FilterRegistrationBean<XssFilter> registration = new FilterRegistrationBean<>();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new XssFilter());
        registration.addUrlPatterns(urlPatterns.split(","));
        registration.setName("xssFilter");
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
        Map<String, String> initParameters = new HashMap<>(1);
        initParameters.put("excludes", excludes);
        registration.setInitParameters(initParameters);
        return registration;
    }

}
