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

import cn.edu.tjnu.tutor.system.domain.entity.LessonPlan;
import cn.edu.tjnu.tutor.system.domain.view.LessonPlanVO;
import cn.edu.tjnu.tutor.system.mapper.LessonPlanMapper;
import cn.edu.tjnu.tutor.system.service.LessonPlanService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 实习教案信息服务层实现。
 *
 * @author 王帅
 * @since 2.0
 */
@Service
public class LessonPlanServiceImpl extends ServiceImpl<LessonPlanMapper, LessonPlan> implements LessonPlanService {

    @Override
    public <E extends IPage<LessonPlanVO>> E pageVO(Integer userId, E page) {
        return baseMapper.selectPageVO(userId, page);
    }

}