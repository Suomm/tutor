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

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.aop.framework.AopContext;

/**
 * Aop 代理工具类。
 *
 * @author 王帅
 * @since 2.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AopUtils {

    /**
     * 获取 Aop 代理对象。
     *
     * @param invoker 代理调用者
     * @param <T>     代理对象类型
     * @return {@code AopContext.currentProxy();}
     */
    public static <T> T getProxy(Class<T> invoker) {
        return invoker.cast(AopContext.currentProxy());
    }

}