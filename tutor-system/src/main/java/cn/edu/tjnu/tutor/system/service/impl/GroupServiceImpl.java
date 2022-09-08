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

import cn.edu.tjnu.tutor.common.util.SqlUtils;
import cn.edu.tjnu.tutor.system.domain.entity.Group;
import cn.edu.tjnu.tutor.system.domain.view.GroupVO;
import cn.edu.tjnu.tutor.system.mapper.GroupMapper;
import cn.edu.tjnu.tutor.system.service.GroupService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 导师小组信息服务层实现。
 *
 * @author 王帅
 * @since 2.0
 */
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Group> implements GroupService {

    @Override
    public <E extends IPage<GroupVO>> E getUserInfo(Integer userId, String role, E page) {
        return null;
    }

    @Override
    public <E extends IPage<GroupVO>> E pageVO(Integer userId, E page) {
        return baseMapper.selectPageVO(userId, page);
    }

    @Override
    public boolean notInSameGroup(Integer userId, Integer otherUserId) {
        return !SqlUtils.toBool(baseMapper.selectUserCount(userId, otherUserId));
    }

}