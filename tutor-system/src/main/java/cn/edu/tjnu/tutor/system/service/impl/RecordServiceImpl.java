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

package cn.edu.tjnu.tutor.system.service.impl;

import cn.easyes.core.conditions.interfaces.BaseEsMapper;
import cn.edu.tjnu.tutor.system.domain.model.Record;
import cn.edu.tjnu.tutor.system.repository.RecordRepository;
import cn.edu.tjnu.tutor.system.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 活动记录信息服务层实现。
 *
 * @author 王帅
 * @since 2.0
 */
@Service
@RequiredArgsConstructor
public class RecordServiceImpl implements RecordService {

    private final RecordRepository recordRepository;

    @Override
    public BaseEsMapper<Record> getBaseMapper() {
        return recordRepository;
    }

}