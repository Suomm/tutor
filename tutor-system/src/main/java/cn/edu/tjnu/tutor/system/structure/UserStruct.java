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

import cn.edu.tjnu.tutor.system.domain.entity.User;
import cn.edu.tjnu.tutor.system.domain.meta.UserMeta;
import cn.edu.tjnu.tutor.system.domain.view.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * 用户实体类结构映射接口。
 *
 * @author 王帅
 * @since 2.0
 */
@Mapper(componentModel = "spring")
public interface UserStruct {

    /**
     * 转换到实体类对象。
     *
     * @param vo 视图对象
     * @return 实体类对象
     */
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "phone", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "gender", ignore = true)
    @Mapping(target = "avatar", ignore = true)
    @Mapping(target = "roleIds", ignore = true)
    @Mapping(target = "introduce", ignore = true)
    User toEntity(UserVO vo);

    /**
     * 转换到实体类对象。
     *
     * @param meta 元数据对象
     * @return 实体类对象
     */
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "phone", ignore = true)
    @Mapping(target = "avatar", ignore = true)
    @Mapping(target = "gender", ignore = true)
    @Mapping(target = "introduce", ignore = true)
    User toEntity(UserMeta meta);

}