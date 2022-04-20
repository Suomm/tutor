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

package cn.edu.tjnu.tutor.gateway.request;

import io.netty.buffer.ByteBufAllocator;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.lang.NonNull;
import reactor.core.publisher.Flux;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * XSS 过滤请求。
 *
 * @author 王帅
 * @since 2.0
 */
public class XssServerHttpRequest extends ServerHttpRequestDecorator {

    public XssServerHttpRequest(ServerHttpRequest delegate) {
        super(delegate);
    }

    @NonNull
    @Override
    public Flux<DataBuffer> getBody() {
        return super.getBody()
                .flatMap(dataBuffer -> {
                    byte[] bytes = Jsoup.clean(dataBuffer.toString(UTF_8), Safelist.basic())
                            .getBytes(UTF_8);
                    DataBuffer buffer = new NettyDataBufferFactory(ByteBufAllocator.DEFAULT)
                            .allocateBuffer(bytes.length);
                    buffer.write(bytes);
                    return Flux.just(buffer);
                });
    }

    @NonNull
    @Override
    public HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.putAll(super.getHeaders());
        headers.remove(HttpHeaders.CONTENT_LENGTH);
        return headers;
    }

}