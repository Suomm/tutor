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

import cn.edu.tjnu.tutor.system.domain.model.Answer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * 问题答复信息数据层。
 *
 * @author 王帅
 * @since 2.0
 */
public interface AnswerRepository extends ElasticsearchRepository<Answer, Integer> {

    /**
     * 根据问题主键查询对应的答复信息。
     *
     * @param problemId 问题主键
     * @return 问题的所有答复信息
     */
    List<Answer> findAllByProblemId(Integer problemId);

}