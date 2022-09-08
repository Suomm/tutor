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
import cn.edu.tjnu.tutor.system.domain.model.Activity;
import cn.edu.tjnu.tutor.system.domain.view.ActivityVO;
import cn.edu.tjnu.tutor.system.repository.ActivityRepository;
import cn.edu.tjnu.tutor.system.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 活动信息服务层实现。
 *
 * @author 王帅
 * @since 2.0
 */
@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;

    @Override
    public BaseEsMapper<Activity> getBaseMapper() {
        return activityRepository;
    }

    @Override
    public Class<ActivityVO> getExcelHead() {
        return ActivityVO.class;
    }

    @Override
    public boolean saveExcelData(ActivityVO vo, Map<Object, Object> cachedMap) {
        return false;
    }

}