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

package cn.edu.tjnu.tutor.system.mapper;

import cn.edu.tjnu.tutor.common.cache.MybatisRedisCache;
import cn.edu.tjnu.tutor.system.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;

import java.util.List;

/**
 * 用户信息数据层。
 *
 * @author 王帅
 * @since 1.0
 */
@CacheNamespace(implementation = MybatisRedisCache.class, eviction = MybatisRedisCache.class)
public interface UserMapper extends BaseMapper<User> {

    /**
     * 为用户绑定角色。
     *
     * @param userId 用户主键
     * @param roleKey 角色键值
     * @return {@code 1} 绑定成功，否则失败
     */
    int bindRoleForUser(Integer userId, String roleKey);

    /**
     * 根据用户主键查询角色信息。
     *
     * @param userId 用户主键
     * @return 角色信息
     */
    List<String> selectRoleKeysByUserId(Integer userId);

}