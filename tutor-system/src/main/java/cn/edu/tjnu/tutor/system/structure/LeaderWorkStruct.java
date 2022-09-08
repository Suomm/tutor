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

import cn.edu.tjnu.tutor.system.domain.dto.LeaderWorkDTO;
import cn.edu.tjnu.tutor.system.domain.entity.LeaderWork;
import cn.edu.tjnu.tutor.system.domain.view.LeaderWorkVO;
import cn.edu.tjnu.tutor.system.settings.MapstructSettings;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * 班主任实习工作实体类结构映射接口。
 *
 * @author 王帅
 * @since 2.0
 */
@Mapper(config = MapstructSettings.class)
public interface LeaderWorkStruct {

    /**
     * 转换为视图对象。
     *
     * @param entity 实体类对象
     * @return 视图对象
     */
    LeaderWorkVO toVO(LeaderWork entity);

    /**
     * 转换到实体类对象。
     *
     * @param dto 数据传输对象
     * @return 实体类对象
     */
    @Mapping(target = "workId", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "markSchool", ignore = true)
    @Mapping(target = "markCollege", ignore = true)
    LeaderWork toEntity(LeaderWorkDTO dto);

}