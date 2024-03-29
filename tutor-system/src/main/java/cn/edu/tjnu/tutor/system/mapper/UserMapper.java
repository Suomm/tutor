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
import cn.edu.tjnu.tutor.common.core.domain.model.LoginUser;
import cn.edu.tjnu.tutor.system.domain.entity.User;
import cn.edu.tjnu.tutor.system.domain.query.UserQuery;
import cn.edu.tjnu.tutor.system.domain.view.ProfileVO;
import cn.edu.tjnu.tutor.system.domain.view.UserVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

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
     * 根据用户主键查询用户信息。
     *
     * @param userId 用户主键
     * @return 用户信息
     */
    ProfileVO selectUserProfile(Integer userId);

    /**
     * 根据用户编号查询用户信息。
     *
     * @param userCode 用户编号
     * @return 登录用户信息
     */
    LoginUser selectByUserCode(String userCode);

    /**
     * 根据用户主键查询角色信息。
     *
     * @param userId 用户主键
     * @return 角色信息
     */
    List<String> selectRoleKeysByUserId(Integer userId);

    /**
     * 分页查询用户信息。
     *
     * @param page  分页参数
     * @param query 查询信息
     * @param <P>   分页对象类型
     * @return 分页对象
     */
    <P extends IPage<UserVO>> P selectPageVO(P page, @Param("query") UserQuery query);

}