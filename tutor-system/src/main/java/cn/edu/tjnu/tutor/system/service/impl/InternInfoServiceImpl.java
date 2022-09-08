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
import cn.edu.tjnu.tutor.system.domain.dto.MarkDTO;
import cn.edu.tjnu.tutor.system.domain.entity.InternInfo;
import cn.edu.tjnu.tutor.system.domain.view.*;
import cn.edu.tjnu.tutor.system.mapper.InternInfoMapper;
import cn.edu.tjnu.tutor.system.service.InternInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 教育实习基本信息服务层实现。
 *
 * @author 王帅
 * @since 2.0
 */
@Service
public class InternInfoServiceImpl extends ServiceImpl<InternInfoMapper, InternInfo> implements InternInfoService {

    @Override
    public InternInfoVO getInfo(Integer userId) {
        return baseMapper.selectInternInfo(userId);
    }

    @Override
    public ReportVO getReport(Integer userId) {
        ReportVO result = baseMapper.selectReportInfo(userId);
        ReportVO report = baseMapper.selectTeachingRecordScore(userId);
        result.setJxpfSchool(report.getJxpfSchool());
        result.setJxpfCollege(report.getJxpfCollege());
        return result;
    }

    @Override
    public ScoreVO getScore(Integer userId, String type) {
        return baseMapper.selectScoreByType(userId, type);
    }

    @Override
    public boolean setScore(Integer userId, String type, MarkDTO markDTO) {
        return SqlUtils.toBool(baseMapper.updateScoreByType(userId, type, markDTO));
    }

    @Override
    public boolean setComment(Integer userId, String type, String content) {
        return SqlUtils.toBool(baseMapper.updateCommentByType(userId, type, content));
    }

    @Override
    public RemarkVO getRemark(Integer userId) {
        return baseMapper.selectRemarkVO(userId);
    }

    @Override
    public GroupScoreVO getGroupScore(Integer userId) {
        return baseMapper.selectGroupScoreVO(userId);
    }

}