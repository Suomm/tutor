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
import cn.edu.tjnu.tutor.system.domain.entity.LeaderWork;
import cn.edu.tjnu.tutor.system.domain.view.ScoreVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * 班主任实习工作信息数据层。
 *
 * @author 王帅
 * @since 2.0
 */
@CacheNamespace(implementation = MybatisRedisCache.class, eviction = MybatisRedisCache.class)
public interface LeaderWorkMapper extends BaseMapper<LeaderWork> {

    /**
     * 选择分数签证官
     *
     * @param userId 用户id
     * @return {@link ScoreVO}
     */
    ScoreVO selectScoreVO(Integer userId);

}