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

import cn.edu.tjnu.tutor.system.domain.entity.College;
import cn.edu.tjnu.tutor.system.domain.entity.Major;
import cn.edu.tjnu.tutor.system.domain.query.MajorQuery;
import cn.edu.tjnu.tutor.system.domain.view.MajorVO;
import cn.edu.tjnu.tutor.system.mapper.CollegeMapper;
import cn.edu.tjnu.tutor.system.mapper.MajorMapper;
import cn.edu.tjnu.tutor.system.service.MajorService;
import cn.edu.tjnu.tutor.system.structure.MajorStruct;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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
    private final CollegeMapper collegeMapper;

    @Override
    public IPage<MajorVO> pageVO(MajorQuery query) {
        return baseMapper.selectPageVO(query.page(), query);
    }

    @Override
    public Class<MajorVO> getExcelHead() {
        return MajorVO.class;
    }

    @Override
    public List<MajorVO> getExcelData() {
        return baseMapper.selectExcelDataList();
    }

    @Override
    public boolean saveExcelData(MajorVO vo, Map<Object, Object> cachedMap) {
        Integer collegeId = (Integer) cachedMap.computeIfAbsent(vo.getCollegeName(), key -> {
            // 查询并且缓存学院主键，没有结果返回 -1
            College college = ChainWrappers.lambdaQueryChain(collegeMapper)
                    .select(College::getCollegeId)
                    .eq(College::getCollegeName, key)
                    .one();
            if (college == null) {
                return -1;
            }
            return college.getCollegeId();
        });
        if (collegeId == -1) {
            return false;
        }
        Major major = majorStruct.toEntity(vo);
        major.setCollegeId(collegeId);
        return save(major);
    }

}