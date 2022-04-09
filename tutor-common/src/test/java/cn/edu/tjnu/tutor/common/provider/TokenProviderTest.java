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

import cn.hutool.jwt.JWTUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;

/**
 * Token 测试类。
 *
 * @author 王帅
 * @since 2.0
 */
class TokenProviderTest {

    @Test
    void testJWTUtil() {
        // JWT 私钥
        byte[] key = "secret".getBytes();
        // payload 载荷
        Map<String, Object> payload = Collections.singletonMap("key", "value");
        // 生成 Token
        String token = JWTUtil.createToken(payload, key);
        // 获取载荷值
        Object obj = JWTUtil.parseToken(token).getPayload("key");
        // 测试
        Assertions.assertEquals("value", obj);
    }

}