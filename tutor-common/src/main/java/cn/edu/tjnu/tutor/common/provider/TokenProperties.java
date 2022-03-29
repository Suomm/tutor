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

package cn.edu.tjnu.tutor.common.provider;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * JWT 令牌校验相关属性绑定类。
 *
 * @author 王帅
 * @since 2.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "token")
public class TokenProperties {

    /**
     * 令牌自定义标识头。
     */
    private String header = "Authorization";

    /**
     * 令牌自定义前缀。
     */
    private String prefix = "Bearer ";

    /**
     * 令牌秘钥（必填项）。
     */
    private String secret;

    /**
     * 令牌有效期（默认30分钟）。
     */
    private int expireTime = 30;

}