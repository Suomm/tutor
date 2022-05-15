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
import cn.edu.tjnu.tutor.system.domain.entity.Major;
import cn.edu.tjnu.tutor.system.domain.query.MajorQuery;
import cn.edu.tjnu.tutor.system.domain.view.MajorVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 专业信息数据层。
 *
 * @author 王帅
 * @since 2.0
 */
@CacheNamespace(implementation = MybatisRedisCache.class, eviction = MybatisRedisCache.class)
public interface MajorMapper extends BaseMapper<Major> {

    /**
     * 查询所有专业数据。
     *
     * @return 专业信息
     */
    List<MajorVO> selectExcelDataList();

    /**
     * 分页查询专业信息。
     *
     * @param <P>   分页对象类型
     * @param page  分页参数
     * @param query 查询信息
     * @return 分页对象
     */
    <P extends IPage<MajorVO>> P selectPageVO(P page, @Param("query") MajorQuery query);

}