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

import cn.edu.tjnu.tutor.common.core.domain.model.LoginUser;
import cn.edu.tjnu.tutor.system.domain.dto.ActivityDTO;
import cn.edu.tjnu.tutor.system.domain.model.Activity;
import cn.edu.tjnu.tutor.system.domain.view.ActivityVO;
import cn.edu.tjnu.tutor.system.settings.MapstructSettings;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

/**
 * 活动实体类结构映射接口。
 *
 * @author 王帅
 * @since 2.0
 */
@Mapper(config = MapstructSettings.class, imports = LocalDateTime.class)
public interface ActivityStruct {

    /**
     * 转换为视图对象。
     *
     * @param activity 实体类对象
     * @return 视图对象
     */
    ActivityVO toVO(Activity activity);

    /**
     * 转换到实体类对象。
     *
     * @param dto DTO 对象
     * @return 实体类对象
     */
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "publisher", ignore = true)
    @Mapping(target = "groupName", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "collegeName", ignore = true)
    Activity toEntity(ActivityDTO dto);

    /**
     * 转换到实体类对象。
     *
     * @param dto  DTO 对象
     * @param user 用户对象
     * @return 实体类对象
     */
    @Mapping(target = "groupName", ignore = true)
    @Mapping(target = "userId", source = "user.userId")
    @Mapping(target = "publisher", source = "user.username")
    @Mapping(target = "collegeName", source = "user.collegeName")
    @Mapping(target = "createTime", expression = "java(LocalDateTime.now())")
    Activity toEntity(ActivityDTO dto, LoginUser user);

}