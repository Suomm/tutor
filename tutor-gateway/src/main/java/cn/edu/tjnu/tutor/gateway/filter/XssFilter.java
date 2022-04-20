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

package cn.edu.tjnu.tutor.gateway.filter;

import cn.edu.tjnu.tutor.gateway.request.XssServerHttpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * XSS 攻击防御过滤器。
 *
 * @author 王帅
 * @since 2.0
 */
@Component
@RequiredArgsConstructor
@EnableConfigurationProperties(XssProperties.class)
@ConditionalOnProperty(prefix = "security.xss", value = "enabled", havingValue = "true")
public class XssFilter implements GlobalFilter, Ordered {

    private final XssProperties  properties;
    private final AntPathMatcher antMatcher = new AntPathMatcher();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        // GET DELETE 请求不过滤
        HttpMethod method = request.getMethod();
        if (method == null ||
                method.matches(HttpMethod.GET.name()) ||
                method.matches(HttpMethod.DELETE.name())) {
            return chain.filter(exchange);
        }
        // 非 JSON 类型，不过滤
        if (!isJsonRequest(request)) {
            return chain.filter(exchange);
        }
        // excludeUrls 不过滤
        String path = request.getURI().getPath();
        if (properties.getExcludeUrls()
                .stream()
                .anyMatch(pattern -> antMatcher.match(pattern, path))) {
            return chain.filter(exchange);
        }
        return chain.filter(exchange.mutate().request(new XssServerHttpRequest(request)).build());
    }

    /**
     * 是否是Json请求
     *
     * @param request HTTP请求
     */
    private boolean isJsonRequest(ServerHttpRequest request) {
        String header = request.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE);
        return StringUtils.startsWithIgnoreCase(header, MediaType.APPLICATION_JSON_VALUE);
    }

    @Override
    public int getOrder() {
        return -100;
    }

}