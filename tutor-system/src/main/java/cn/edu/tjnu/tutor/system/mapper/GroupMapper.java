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
import cn.edu.tjnu.tutor.system.domain.entity.Group;
import cn.edu.tjnu.tutor.system.domain.view.GroupVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * 导师小组信息数据层。
 *
 * @author 王帅
 * @since 2.0
 */
@CacheNamespace(implementation = MybatisRedisCache.class, eviction = MybatisRedisCache.class)
public interface GroupMapper extends BaseMapper<Group> {

    /**
     * 分页查询导师小组信息。
     *
     * @param <P>    分页对象类型
     * @param userId 导师用户主键
     * @param page   分页参数
     * @return 分页对象
     */
    <P extends IPage<GroupVO>> P selectPageVO(Integer userId, P page);

    /**
     * 选择用户数量
     *
     * @param userId      用户id
     * @param otherUserId 其他用户id
     * @return {@link Long}
     */
    Long selectUserCount(Integer userId, Integer otherUserId);

}