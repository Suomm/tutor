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

package cn.edu.tjnu.tutor.gateway.config;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Sentinel 配置类。
 *
 * @author 王帅
 * @since 2.0
 */
@Configuration(proxyBeanMethods = false)
public class SentinelConfig {

    @PostConstruct
    public void doInit() {
        Map<String, Object> result = new HashMap<>(3);
        result.put("code", 200);
        result.put("data", null);
        result.put("message", "业务繁忙请稍后重试！");
        GatewayCallbackManager.setBlockHandler((exchange, t) ->
                ServerResponse.ok().body(BodyInserters.fromValue(result)));
    }

}