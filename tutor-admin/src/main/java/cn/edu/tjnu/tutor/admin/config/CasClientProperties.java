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

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.SneakyThrows;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.cas.ServiceProperties;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * CAS 授权服务相关参数。
 *
 * @author 王帅
 * @since 2.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ConfigurationProperties(prefix = "cas")
public class CasClientProperties extends ServiceProperties {

    /**
     * 是否启用 CAS 授权服务器功能。
     */
    private boolean enable;

    /**
     * （CAS）认证服务器地址。
     */
    private String server;

    /**
     * （CAS）认证服务器登录地址。
     */
    private String serverLoginUrl;

    /**
     * （CAS）认证服务器登出地址。
     */
    private String serverLogoutUrl;

    /**
     * 授权成功时的重定向地址。
     */
    private String callbackUrl;

    /**
     * <p>CAS 登录认证 URL 地址。
     *
     * @return 登录 URL
     */
    public String getLoginUrl() {
        return buildUrl(serverLoginUrl, getService());
    }

    /**
     * CAS 登出认证 URL 地址。
     *
     * @return 登出 URL
     */
    public String getLogoutUrl() {
        return buildUrl(serverLogoutUrl, getService());
    }

    private static final String UTF_8 = "UTF-8";

    /**
     * 拼接 URL 地址。
     */
    @SneakyThrows(UnsupportedEncodingException.class)
    private String buildUrl(String serverUrl, String clientUrl) {
        return serverUrl + "?" + getServiceParameter() + "=" + URLEncoder.encode(clientUrl, UTF_8);
    }

}