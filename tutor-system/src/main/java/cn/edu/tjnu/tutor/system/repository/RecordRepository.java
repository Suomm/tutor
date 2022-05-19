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

package cn.edu.tjnu.tutor.system.repository;

import cn.edu.tjnu.tutor.system.domain.model.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 活动记录信息数据层。
 *
 * @author 王帅
 * @since 2.0
 */
public interface RecordRepository extends ElasticsearchRepository<Record, Integer> {

    /**
     * 根据用户主键查询参加的的活动。
     *
     * @param userId   用户主键
     * @param pageable 分页对象
     * @return 所有关联活动
     */
    Page<Record> findAllByUserId(Integer userId, Pageable pageable);

}