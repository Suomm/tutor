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

package cn.edu.tjnu.tutor.system.domain.query;

import cn.edu.tjnu.tutor.common.core.domain.dto.PageDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 专业信息查询对象。
 *
 * @author 王帅
 * @since 2.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MajorQuery extends PageDTO {

    private static final long serialVersionUID = 3729660645629823779L;

    /**
     * 学院主键。
     */
    private Integer collegeId;

}