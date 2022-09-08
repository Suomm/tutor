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

package cn.edu.tjnu.tutor.system.service;

import cn.edu.tjnu.tutor.system.domain.entity.TeachingRecord;
import cn.edu.tjnu.tutor.system.domain.view.ScoreVO;
import cn.edu.tjnu.tutor.system.domain.view.TeachingRecordVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 课堂教学记录信息服务层。
 *
 * @author 王帅
 * @since 2.0
 */
public interface TeachingRecordService extends IService<TeachingRecord> {

    /**
     * 分页查询课堂教学信息。
     *
     * @param userId 实习生主键
     * @param page   分页查询参数
     * @return 分页对象
     */
    <E extends IPage<TeachingRecordVO>> E pageVO(Integer userId, E page);

    /**
     * 判断所给课堂教学记录是否被指导教师打分。
     *
     * @param teachingRecord 课堂教学成绩信息
     * @return {@code true} 课堂教学记录已经被打分，{@code false} 课堂教学记录没有被打分
     * @implSpec 对于课堂教学成绩信息服务，该默认实现为：
     * <pre>{@code
     * return lambdaQuery()
     *         .select(TeachingScore::getMarkSchool, TeachingScore::getMarkCollege)
     *         .eq(TeachingScore::getUserId, teachingScore.getUserId())
     *         .eq(TeachingScore::getRecordIdId, teachingScore.getRecordIdId())
     *         .oneOpt()
     *         .map(e -> e.getMarkSchool() != null || e.getMarkCollege() != null)
     *         .orElse(Boolean.FALSE);
     * }</pre>
     */
    default boolean isMarked(TeachingRecord teachingRecord) {
        return lambdaQuery()
                .select(TeachingRecord::getMarkSchool, TeachingRecord::getMarkCollege)
                .eq(TeachingRecord::getUserId, teachingRecord.getUserId())
                .eq(TeachingRecord::getRecordId, teachingRecord.getRecordId())
                .oneOpt()
                .map(e -> e.getMarkSchool() != null || e.getMarkCollege() != null)
                .orElse(Boolean.FALSE);
    }

    /**
     * 得到分数
     *
     * @param userId 用户id
     * @return {@link ScoreVO}
     */
    ScoreVO getScore(Integer userId);

}