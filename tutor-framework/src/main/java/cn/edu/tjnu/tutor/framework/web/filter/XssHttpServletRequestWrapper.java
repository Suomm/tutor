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

package cn.edu.tjnu.tutor.framework.web.filter;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.http.HtmlUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * XSS 过滤处理。
 *
 * @author 王帅
 * @since 2.0
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    public XssHttpServletRequestWrapper(ServletRequest request) {
        super((HttpServletRequest) request);
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        if (values != null) {
            int length = values.length;
            String[] escapeValues = new String[length];
            for (int i = 0; i < length; i++) {
                // 防 XSS 攻击和过滤前后空格
                escapeValues[i] = HtmlUtil.filter(values[i]).trim();
            }
            return escapeValues;
        }
        return super.getParameterValues(name);
    }

    /**
     * {@code @RequestBody} 注解读取参数的方法是 {@code getInputStream()}。
     */
    @Override
    public ServletInputStream getInputStream() throws IOException {
        // 非 JSON 类型，直接返回
        if (!isJsonType()) {
            return super.getInputStream();
        }

        // 为空，直接返回
        String value = IoUtil.read(super.getInputStream(), StandardCharsets.UTF_8);
        if (CharSequenceUtil.isEmpty(value)) {
            return super.getInputStream();
        }

        // XSS 过滤 script 标签
        value = HtmlUtil.filter(value).trim();

        byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
        ByteArrayInputStream bis = IoUtil.toStream(bytes);

        return new ServletInputStream() {

            @Override
            public boolean isFinished() {
                return true;
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public int available() {
                return bytes.length;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
                // unimplemented method.
            }

            @Override
            public int read() {
                return bis.read();
            }

        };
    }

    /**
     * 请求内容是否为 JSON 类型。
     */
    public boolean isJsonType() {
        String header = super.getHeader(HttpHeaders.CONTENT_TYPE);
        return StringUtils.startsWithIgnoreCase(header, MediaType.APPLICATION_JSON_VALUE);
    }

}