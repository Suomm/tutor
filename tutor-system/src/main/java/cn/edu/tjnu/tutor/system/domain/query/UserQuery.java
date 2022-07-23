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

import cn.edu.tjnu.tutor.common.core.domain.query.PageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户信息查询对象。
 *
 * @author 王帅
 * @since 2.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserQuery extends PageQuery {

    private static final long serialVersionUID = -583042903949935455L;

    /**
     * 用户编号。
     *
     * @mock 2040050143
     */
    private String userCode;

    /**
     * 用户名称。
     *
     * @mock 王帅
     */
    private String userName;

    /**
     * 所属学院主键。
     *
     * @mock 1
     */
    private Integer collegeId;

    /**
     * 用户类型（0 教师，1 学生）。
     *
     * @mock 1
     */
    private Integer userType;

}