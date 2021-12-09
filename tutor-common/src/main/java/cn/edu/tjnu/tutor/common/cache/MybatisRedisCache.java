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

package cn.edu.tjnu.tutor.common.cache;

import cn.edu.tjnu.tutor.common.util.RedisUtils;
import cn.edu.tjnu.tutor.common.util.SpringUtils;
import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.connection.RedisServerCommands;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * <p>配置 MyBatis-Plus 使用 Redis 作为二级缓存。
 *
 * <p>使用方法：
 * <ul>
 * <li>配置文件开启 Mybatis-Plus 二级缓存（默认开启）：{@code mybatis-plus.configuration.cache-enabled: true}
 * <li>在 Mapper 类上添加注解 {@code @CacheNamespace(implementation = MybatisRedisCache.class, eviction = MybatisRedisCache.class)}
 * </ul>
 *
 * @author 王帅
 * @since 2.0
 */
@Slf4j
public class MybatisRedisCache implements Cache {

	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);

	private final String id;

	public MybatisRedisCache(final String id) {
		if (id == null) {
			throw new IllegalArgumentException("Cache instances require an ID");
		}
		this.id = id;
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public void putObject(Object key, Object value) {
		if (value != null) {
			RedisUtils.setCacheObject(key.toString(), value);
		}
	}

	@Override
	public Object getObject(Object key) {
		try {
			if (key != null) {
				return RedisUtils.getCacheObject(key.toString());
			}
		} catch (Exception e) {
			log.error("获取缓存 {} 失败", key);
		}
		return null;
	}

	@Override
	public Object removeObject(Object key) {
		if (key != null) {
			RedisUtils.deleteObject(key.toString());
		}
		return null;
	}

	@Override
	public void clear() {
		Collection<String> keys = RedisUtils.keys("*:" + this.id + "*");
		if (!CollUtil.isEmpty(keys)) {
			RedisUtils.deleteObject(keys);
			log.debug("成功清空所有缓存");
		}
	}

	@Override
	public int getSize() {
		RedisTemplate<String, Object> redisTemplate = SpringUtils.getBean("redisTemplate");
		Long size = redisTemplate.execute(RedisServerCommands::dbSize);
		return Objects.requireNonNull(size).intValue();
	}

	@Override
	public ReadWriteLock getReadWriteLock() {
		return this.readWriteLock;
	}

}