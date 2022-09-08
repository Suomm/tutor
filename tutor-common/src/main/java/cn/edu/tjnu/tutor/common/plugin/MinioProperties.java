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

package cn.edu.tjnu.tutor.common.plugin;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Minio 的相关属性绑定。
 *
 * @author 王帅
 * @since 1.0
 */
@Data
@ConfigurationProperties(prefix = "minio")
public class MinioProperties {

    /**
     * 是否启用 Minio 功能。
     */
    private boolean enable;

    /**
     * 连接地址。
     */
    private String endpoint;

    /**
     * 访问账户。
     */
    private String accessKey;

    /**
     * 访问密码。
     */
    private String secretKey;

    /**
     * 储存桶。
     */
    private String bucket;

    /**
     * 访问文件的根路径。
     */
    private String baseUrl;

}
