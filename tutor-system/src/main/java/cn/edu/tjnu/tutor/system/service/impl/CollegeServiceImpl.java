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
import cn.edu.tjnu.tutor.system.domain.view.CollegeVO;
import cn.edu.tjnu.tutor.system.mapper.CollegeMapper;
import cn.edu.tjnu.tutor.system.service.CollegeService;
import cn.edu.tjnu.tutor.system.structure.CollegeStruct;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 学院信息服务层实现。
 *
 * @author 王帅
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class CollegeServiceImpl extends ServiceImpl<CollegeMapper, College> implements CollegeService {

    private final CollegeStruct collegeStruct;

    @Override
    public Class<CollegeVO> getExcelHead() {
        return CollegeVO.class;
    }

    @Override
    public List<CollegeVO> getExcelData() {
        return lambdaQuery()
                .eq(College::getVisible, 0)
                .list()
                .stream()
                .map(collegeStruct::toVO)
                .collect(Collectors.toList());
    }

    @Override
    public boolean saveExcelData(CollegeVO vo, Map<Object, Object> cachedMap) {
        return save(collegeStruct.toEntity(vo));
    }

    @Override
    public <P extends IPage<CollegeVO>> P pageVO(P page) {
        return baseMapper.selectPageVO(page);
    }

}