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
import cn.edu.tjnu.tutor.system.domain.entity.TheClass;
import cn.edu.tjnu.tutor.system.domain.query.ClassQuery;
import cn.edu.tjnu.tutor.system.domain.view.ClassVO;
import cn.edu.tjnu.tutor.system.mapper.ClassMapper;
import cn.edu.tjnu.tutor.system.service.ClassService;
import cn.edu.tjnu.tutor.system.service.MajorService;
import cn.edu.tjnu.tutor.system.structure.ClassStruct;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    private final MajorService majorService;

    @Override
    public IPage<ClassVO> pageVO(ClassQuery query) {
        return baseMapper.selectPageVO(query.page(), query);
    }

    @Override
    public Class<ClassVO> getExcelHead() {
        return ClassVO.class;
    }

    @Override
    public PageVO<ClassVO> getRealData(ClassQuery query) {
        PageVO<ClassVO> pageVO = PageUtils.convert(pageVO(query));
        pageVO.setContent(pageVO.getContent()
                .stream()
                .peek(e -> e.setGrade("20" + e.getGrade() + "级"))
                .collect(Collectors.toList()));
        return pageVO;
    }

    @Override
    public boolean saveExcelData(ClassVO vo, Map<Object, Object> cachedMap) {
        Object majorId = cachedMap.computeIfAbsent(vo.getMajorName(), majorService::getMajorId);
        if (majorId != null) {
            TheClass theClass = classStruct.toEntity(vo);
            theClass.setMajorId((Integer) majorId);
            return save(theClass);
        }
        return false;
    }

}