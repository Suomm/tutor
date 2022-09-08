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
import cn.edu.tjnu.tutor.system.domain.dto.MarkDTO;
import cn.edu.tjnu.tutor.system.domain.entity.InternInfo;
import cn.edu.tjnu.tutor.system.domain.view.*;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

/**
 * 教育实习基本信息数据层。
 *
 * @author 王帅
 * @since 2.0
 */
@CacheNamespace(implementation = MybatisRedisCache.class, eviction = MybatisRedisCache.class)
public interface InternInfoMapper extends BaseMapper<InternInfo> {

    /**
     * 根据实习生主键查询教育实习基本信息。
     *
     * @param userId 实习生主键
     * @return 教育实习基本信息
     */
    InternInfoVO selectInternInfo(Integer userId);

    /**
     * 选择报告信息
     *
     * @param userId 用户id
     * @return {@link ReportVO}
     */
    ReportVO selectReportInfo(Integer userId);

    /**
     * 选择教学记录分数
     *
     * @param userId 用户id
     * @return {@link ReportVO}
     */
    ReportVO selectTeachingRecordScore(Integer userId);

    /**
     * 根据类型选择分数
     *
     * @param userId 用户id
     * @param type   类型
     * @return {@link ScoreVO}
     */
    ScoreVO selectScoreByType(@Param("userId") Integer userId, @Param("type") String type);

    /**
     * 更新分数按类型
     *
     * @param userId  用户id
     * @param type    类型
     * @param markDTO 马克dto
     * @return int
     */
    int updateScoreByType(@Param("userId") Integer userId,
                          @Param("type") String type,
                          @Param("markDTO") MarkDTO markDTO);

    /**
     * 更新注释类型
     *
     * @param userId  用户id
     * @param type    类型
     * @param content 内容
     * @return int
     */
    int updateCommentByType(@Param("userId") Integer userId,
                            @Param("type") String type,
                            @Param("content") String content);

    /**
     * 选择话签证官
     *
     * @param userId 用户id
     * @return {@link RemarkVO}
     */
    RemarkVO selectRemarkVO(Integer userId);

    /**
     * 选择组分数签证官
     *
     * @param userId 用户id
     * @return {@link GroupScoreVO}
     */
    GroupScoreVO selectGroupScoreVO(Integer userId);

}