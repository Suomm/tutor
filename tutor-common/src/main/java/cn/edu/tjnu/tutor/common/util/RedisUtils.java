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
import org.redisson.api.RBatch;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Redis 缓存操作的工具类。
 *
 * @author 王帅
 * @since 1.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RedisUtils {

    private static final RedissonClient DB = SpringUtils.getBean(RedissonClient.class);

    /**
     * 缓存基本的对象，Integer、String、实体类等。
     *
     * @param key   缓存的键值
     * @param value 缓存的值
     */
    public static <T> void setCacheObject(String key, T value) {
        DB.getBucket(key).set(value);
    }

    /**
     * 获得缓存的基本对象。
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public static <T> T getCacheObject(String key) {
        RBucket<T> rBucket = DB.getBucket(key);
        return rBucket.get();
    }

    /**
     * 删除单个对象。
     *
     * @param key 缓存键值
     */
    public static void deleteObject(String key) {
        DB.getBucket(key).delete();
    }

    /**
     * 删除集合对象。
     *
     * @param collection 多个对象
     */
    public static void deleteObject(Collection<?> collection) {
        RBatch batch = DB.createBatch();
        collection.forEach(t -> batch.getBucket(t.toString()).deleteAsync());
        batch.execute();
    }

    /**
     * 获得缓存的基本对象列表
     *
     * @param pattern 字符串前缀
     * @return 对象列表
     */
    public static Collection<String> keys(String pattern) {
        Iterable<String> iterable = DB.getKeys().getKeysByPattern(pattern);
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }

}