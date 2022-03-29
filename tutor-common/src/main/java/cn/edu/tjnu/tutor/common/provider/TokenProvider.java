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

import cn.edu.tjnu.tutor.common.core.domain.model.LoginUser;
import cn.edu.tjnu.tutor.common.util.RedisUtils;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.jwt.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static cn.edu.tjnu.tutor.common.constant.RedisConst.LOGIN_USER_KEY;

/**
 * 提供令牌的相关操作。
 *
 * @author 王帅
 * @since 2.0
 */
@Component
@RequiredArgsConstructor
public class TokenProvider {

    private final TokenProperties tokenProperties;

    /**
     * 根据 JWT 请求头获取当前登录用户信息。
     *
     * @param request 认证请求
     * @return 当前登录用户，如果为 {@code null} 则用户未登录
     */
    public LoginUser getLoginUser(HttpServletRequest request) {
        // 获取请求头中的 Token
        String token = getToken(request);
        // 没有 Token 直接返回空
        if (CharSequenceUtil.isEmpty(token)) {
            return null;
        }
        // 解析 Token 并从 Redis 中取出用户信息
        String uuid = parseToken(token);
        String userKey = getUserKey(uuid);
        return RedisUtils.getCacheObject(userKey);
    }

    /**
     * 为当前登录用户创建 JWT 令牌。
     *
     * @param loginUser 当前登录用户
     * @return JWT Token
     */
    public String createToken(LoginUser loginUser) {
        // 创建并设置 UUID
        String uuid = IdUtil.fastUUID();
        loginUser.setUuid(uuid);
        // 刷新存储令牌
        refreshToken(loginUser);
        // 生成 JWT 令牌
        byte[] key = tokenProperties.getSecret().getBytes();
        return JWTUtil.createToken(Map.of(LOGIN_USER_KEY, uuid), key);
    }

    /**
     * 获取请求头中的 Token 信息。
     *
     * @param request 请求
     * @return token 令牌
     */
    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(tokenProperties.getHeader());
        // 处理 Token 前缀信息
        String prefix = tokenProperties.getPrefix();
        if (CharSequenceUtil.isNotEmpty(token) && token.startsWith(prefix)) {
            token = token.replace(prefix, CharSequenceUtil.EMPTY);
        }
        return token;
    }

    /**
     * 从令牌中获取用户唯一标识（UUID）。
     *
     * @param token 令牌
     * @return 用户唯一标识（UUID）
     */
    private String parseToken(String token) {
        return (String) JWTUtil.parseToken(token).getPayload(LOGIN_USER_KEY);
    }

    /**
     * 刷新当前登录用户存储信息。
     *
     * @param loginUser 当前登录用户
     */
    public void refreshToken(LoginUser loginUser) {
        String userKey = getUserKey(loginUser.getUuid());
        RedisUtils.setCacheObject(userKey, loginUser, tokenProperties.getExpireTime(), TimeUnit.MINUTES);
    }

    /**
     * 删除令牌信息和当前登录用户信息。
     *
     * @param uuid 用户唯一标识
     */
    public void deleteToken(String uuid) {
        if (CharSequenceUtil.isNotEmpty(uuid)) {
            String userKey = getUserKey(uuid);
            RedisUtils.deleteObject(userKey);
        }
    }

    /**
     * 获取用户键，用于 Redis 存取。
     *
     * @param uuid 用户唯一标识
     * @return 用户键
     */
    private String getUserKey(String uuid) {
        return LOGIN_USER_KEY.concat(uuid);
    }

}
