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

import cn.edu.tjnu.tutor.system.domain.entity.Major;
import cn.edu.tjnu.tutor.system.domain.entity.TheClass;
import cn.edu.tjnu.tutor.system.domain.query.ClassQuery;
import cn.edu.tjnu.tutor.system.domain.view.ClassVO;
import cn.edu.tjnu.tutor.system.mapper.ClassMapper;
import cn.edu.tjnu.tutor.system.mapper.MajorMapper;
import cn.edu.tjnu.tutor.system.service.ClassService;
import cn.edu.tjnu.tutor.system.structure.ClassStruct;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 班级信息服务层实现。
 *
 * @author 王帅
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class ClassServiceImpl extends ServiceImpl<ClassMapper, TheClass> implements ClassService {

    private final ClassStruct classStruct;
    private final MajorMapper majorMapper;

    @Override
    public IPage<ClassVO> pageVO(ClassQuery query) {
        return baseMapper.selectPageVO(query.page(), query);
    }

    @Override
    public Class<ClassVO> getExcelHead() {
        return ClassVO.class;
    }

    @Override
    public List<ClassVO> getExcelData() {
        return baseMapper.selectExcelDataList()
                .stream()
                .peek(vo -> vo.setGrade("20" + vo.getGrade() + "级"))
                .collect(Collectors.toList());
    }

    @Override
    public boolean saveExcelData(ClassVO vo, Map<Object, Object> cachedMap) {
        Integer majorId = (Integer) cachedMap.computeIfAbsent(vo.getMajorName(), key ->
                // 查询并且缓存专业主键，没有结果则返回 -1
                ChainWrappers.lambdaQueryChain(majorMapper)
                        .select(Major::getMajorId)
                        .eq(Major::getMajorName, key)
                        .oneOpt()
                        .map(Major::getMajorId)
                        .orElse(-1)
        );
        if (majorId == -1) {
            return false;
        }
        TheClass theClass = classStruct.toEntity(vo);
        theClass.setMajorId(majorId);
        return save(theClass);
    }

}