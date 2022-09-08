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

package cn.edu.tjnu.tutor.system.structure;

import cn.edu.tjnu.tutor.system.domain.dto.CollegeDTO;
import cn.edu.tjnu.tutor.system.domain.entity.College;
import cn.edu.tjnu.tutor.system.domain.meta.CollegeMeta;
import cn.edu.tjnu.tutor.system.domain.view.CollegeVO;
import cn.edu.tjnu.tutor.system.settings.MapstructSettings;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * 学院实体类结构映射接口。
 *
 * @author 王帅
 * @since 2.0
 */
@Mapper(config = MapstructSettings.class)
public interface CollegeStruct {

    /**
     * 转换到实体类对象。
     *
     * @param dto DTO 对象
     * @return 实体类对象
     */
    @Mapping(target = "visible", ignore = true)
    College toEntity(CollegeDTO dto);

    /**
     * 转换到实体类对象。
     *
     * @param meta 元数据对象
     * @return 实体类对象
     */
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "phone", ignore = true)
    @Mapping(target = "leader", ignore = true)
    College toEntity(CollegeMeta meta);

    /**
     * 转换到实体类对象。
     *
     * @param vo 视图对象
     * @return 实体类对象
     */
    @Mapping(target = "visible", ignore = true)
    @Mapping(target = "collegeId", ignore = true)
    College toEntity(CollegeVO vo);

    /**
     * 转换为视图对象。
     *
     * @param college 实体类对象
     * @return 视图对象
     */
    CollegeVO toVO(College college);

}