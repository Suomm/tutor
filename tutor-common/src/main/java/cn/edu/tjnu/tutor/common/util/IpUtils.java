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

package cn.edu.tjnu.tutor.common.util;

import cn.edu.tjnu.tutor.common.constant.GlobalConst;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletRequest;

/**
 * IP 工具类。
 *
 * @author 王帅
 * @since 2.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class IpUtils {

    private static final String URL = "http://whois.pconline.com.cn/ipJson.jsp?ip=";
    private static final String ADDR = "addr";
    private static final String QUERY = "&json=true";
    private static final String UNKNOWN = "UNKNOWN";

    /**
     * 获取客户端 IP 地址。
     *
     * @param request 当前请求
     * @return IP 地址
     */
    public static String getClientIp(HttpServletRequest request) {
        return ServletUtil.getClientIP(request);
    }

    /**
     * 获取 IP 位置信息。
     *
     * @param ip IP 地址
     * @return 位置信息
     */
    public static String getIpAddress(String ip) {
        // 调用 IP 地址查询地址
        String result = HttpUtil.createGet(URL + ip + QUERY)
                .contentType(GlobalConst.GBK)
                .execute()
                .body();
        // 解析返回结果，返回地理位置和运营商信息
        if (CharSequenceUtil.isNotEmpty(result)) {
            Object addr = JSONUtil.parseObj(result).get(ADDR);
            if (addr != null) {
                return addr.toString();
            }
        }
        return UNKNOWN;
    }

}