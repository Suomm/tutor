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

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.util.ArrayList;
import java.util.List;

/**
 * XSS 跨站脚本配置。
 *
 * @author 王帅
 * @since 2.0
 */
@Data
@RefreshScope
@ConfigurationProperties(prefix = "security.xss")
public class XssProperties {

    /**
     * XSS 脚本过滤开关。
     */
    private boolean enable;

    /**
     * 过滤器排除路径。
     */
    private List<String> excludeUrls = new ArrayList<>();

}