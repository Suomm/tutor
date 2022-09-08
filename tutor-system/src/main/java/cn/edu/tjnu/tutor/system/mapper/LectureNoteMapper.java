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
import cn.edu.tjnu.tutor.system.domain.entity.LectureNote;
import cn.edu.tjnu.tutor.system.domain.view.LectureNoteVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

/**
 * 实习听课记录信息数据层。
 *
 * @author 王帅
 * @since 2.0
 */
@CacheNamespace(implementation = MybatisRedisCache.class, eviction = MybatisRedisCache.class)
public interface LectureNoteMapper extends BaseMapper<LectureNote> {

    /**
     * 分页查询实习听课记录信息。
     *
     * @param userId 实习生主键
     * @param page   分页对象
     * @param <P>    分页对象类型
     * @return 分页对象
     */
    <P extends IPage<LectureNoteVO>> P selectPageVO(@Param("userId") Integer userId, P page);

}