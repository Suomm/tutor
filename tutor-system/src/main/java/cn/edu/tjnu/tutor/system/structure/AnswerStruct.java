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

import cn.edu.tjnu.tutor.system.domain.model.Answer;
import cn.edu.tjnu.tutor.system.domain.view.AnswerVO;
import cn.edu.tjnu.tutor.system.settings.MapstructSettings;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * 问题回复实体类结构映射接口。
 *
 * @author 王帅
 * @since 2.0
 */
@Mapper(config = MapstructSettings.class)
public interface AnswerStruct {

    /**
     * 转换到 VO 对象。
     *
     * @param answer 实体类
     * @return VO 对象
     */
    @Mapping(target = "children", ignore = true)
    @Mapping(source = "answerId", target = "id")
    @Mapping(source = "replyId", target = "parentId")
    AnswerVO toVO(Answer answer);

}