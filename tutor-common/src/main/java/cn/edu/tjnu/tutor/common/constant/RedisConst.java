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

package cn.edu.tjnu.tutor.common.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Redis 缓存键值常量。
 *
 * @author 王帅
 * @since 1.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RedisConst {

    /**
     * 当前登录用户键名前缀（loginUsers:login_user_用户编号）。
     */
    public static final String PREFIX_LOGIN_USER = "loginUsers:login_user_";

    /**
     * 配置参数名称（{@link org.redisson.api.RMap}）。
     */
    public static final String CONFIG_MAP_NAME = "sys_config";

}
