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

import cn.edu.tjnu.tutor.system.domain.entity.LeaderWork;
import cn.edu.tjnu.tutor.system.domain.view.ScoreVO;
import cn.edu.tjnu.tutor.system.mapper.LeaderWorkMapper;
import cn.edu.tjnu.tutor.system.service.LeaderWorkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 班主任实习工作信息服务层实现。
 *
 * @author 王帅
 * @since 2.0
 */
@Service
public class LeaderWorkServiceImpl extends ServiceImpl<LeaderWorkMapper, LeaderWork> implements LeaderWorkService {

    @Override
    public ScoreVO getWorkScore(Integer userId) {
        return baseMapper.selectScoreVO(userId);
    }

}