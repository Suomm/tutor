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

import cn.edu.tjnu.tutor.common.core.domain.view.PageVO;
import cn.edu.tjnu.tutor.common.util.PageUtils;
import cn.edu.tjnu.tutor.system.domain.entity.Major;
import cn.edu.tjnu.tutor.system.domain.query.MajorQuery;
import cn.edu.tjnu.tutor.system.domain.view.MajorVO;
import cn.edu.tjnu.tutor.system.mapper.MajorMapper;
import cn.edu.tjnu.tutor.system.service.CollegeService;
import cn.edu.tjnu.tutor.system.service.MajorService;
import cn.edu.tjnu.tutor.system.structure.MajorStruct;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 专业信息服务层实现。
 *
 * @author 王帅
 * @since 2.0
 */
@Service
@RequiredArgsConstructor
public class MajorServiceImpl extends ServiceImpl<MajorMapper, Major> implements MajorService {

    private final MajorStruct majorStruct;
    private final CollegeService collegeService;

    @Override
    public IPage<MajorVO> pageVO(MajorQuery query) {
        return baseMapper.selectPageVO(query.page(), query);
    }

    @Override
    public Class<MajorVO> getExcelHead() {
        return MajorVO.class;
    }

    @Override
    public PageVO<MajorVO> getRealData(MajorQuery query) {
        return PageUtils.convert(pageVO(query));
    }

    @Override
    public boolean saveExcelData(MajorVO vo, Map<Object, Object> cachedMap) {
        Object collegeId = cachedMap.computeIfAbsent(vo.getCollegeName(), collegeService::getCollegeId);
        if (collegeId != null) {
            Major major = majorStruct.toEntity(vo);
            major.setCollegeId((Integer) collegeId);
            return save(major);
        }
        return false;
    }

}