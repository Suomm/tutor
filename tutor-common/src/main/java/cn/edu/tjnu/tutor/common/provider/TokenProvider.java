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
import cn.hutool.jwt.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static cn.edu.tjnu.tutor.common.constant.RedisConst.PREFIX_LOGIN_USER;

/**
 * 提供令牌的相关操作。
 *
 * @author 王帅
 * @since 2.0
 */
@Component
@RequiredArgsConstructor
@EnableConfigurationProperties(TokenProperties.class)
public class TokenProvider {

    /**
     * 毫秒数，用于分钟转毫秒。
     */
    private static final long MILLIS_MINUTE = 60 * 1000L;

    /**
     * 二十分钟毫秒数。
     */
    private static final long TWENTY_MINUTES = 20 * 60 * 1000L;

    /**
     * Token 配置信息。
     */
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
        if (CharSequenceUtil.isNotEmpty(token)) {
            try {
                // 解析 Token 从 Redis 中取出用户信息
                String userCode = parseToken(token);
                String userKey  = getUserKey(userCode);
                return RedisUtils.getCacheObject(userKey);
            } catch (Exception e) {
                // Token 解析失败
            }
        }
        return null;
    }

    /**
     * 根据用户编号为当前登录用户创建 JWT 令牌。
     *
     * @param loginUser 当前登录用户
     * @return JWT Token
     */
    public String createToken(LoginUser loginUser) {
        // 刷新存储令牌
        refreshToken(loginUser);
        // 生成 JWT 令牌
        byte[] key = tokenProperties.getSecret().getBytes();
        return JWTUtil.createToken(Map.of(PREFIX_LOGIN_USER, loginUser.getUserCode()), key);
    }

    /**
     * 验证 Token 有效期，相差不足20分钟，自动刷新缓存。
     *
     * @param loginUser 当前登录用户
     */
    public void verifyToken(LoginUser loginUser) {
        long expireTime = loginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= TWENTY_MINUTES) {
            refreshToken(loginUser);
        }
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
     * 从令牌中获取用户编号。
     *
     * @param token 令牌
     * @return 用户编号
     */
    private String parseToken(String token) {
        return (String) JWTUtil.parseToken(token).getPayload(PREFIX_LOGIN_USER);
    }

    /**
     * 刷新当前登录用户存储信息。
     *
     * @param loginUser 当前登录用户
     */
    public void refreshToken(LoginUser loginUser) {
        // 设用户登陆过期时间
        loginUser.setExpireTime(System.currentTimeMillis() + tokenProperties.getExpireTime() * MILLIS_MINUTE);
        // 缓存当前登录用户的信息
        String userKey = getUserKey(loginUser.getUserCode());
        RedisUtils.setCacheObject(userKey, loginUser, tokenProperties.getExpireTime(), TimeUnit.MINUTES);
    }

    /**
     * 删除令牌信息和当前登录用户信息。
     *
     * @param userCode 用户编号
     */
    public void deleteToken(String userCode) {
        if (CharSequenceUtil.isNotEmpty(userCode)) {
            String userKey = getUserKey(userCode);
            RedisUtils.deleteObject(userKey);
        }
    }

    /**
     * 获取用户键，用于 Redis 存取。
     *
     * @param userCode 用户编号
     * @return 用户键
     */
    private String getUserKey(String userCode) {
        return PREFIX_LOGIN_USER.concat(userCode);
    }

}
