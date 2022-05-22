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

package cn.edu.tjnu.tutor.system.domain.dto;

import cn.edu.tjnu.tutor.common.validation.constraints.ClassName;
import cn.edu.tjnu.tutor.common.validation.groups.Insert;
import cn.edu.tjnu.tutor.common.validation.groups.Update;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

/**
 * <p>班级信息数据传输对象，用于超级管理员（ROLE_ROOT）与学院管理员（ROLE_ADMIN）操作班级信息。
 *
 * <p>插入班级信息时：
 * <blockquote><pre>
 *     {
 *         "majorId": 1,
 *         "className": "化学2001"
 *     }
 * </pre></blockquote>
 *
 * <p>更新班级信息时：
 * <blockquote><pre>
 *     {
 *         "classId": 1,
 *         "className": "化学2001"
 *     }
 * </pre></blockquote>
 *
 * @author 王帅
 * @since 2.0
 */
@Data
public class ClassDTO implements Serializable {

    private static final long serialVersionUID = 1617141803307959986L;

    /**
     * 班级主键。
     *
     * @mock 1
     */
    @Null(groups = Insert.class)
    @NotNull(groups = Update.class)
    private Integer classId;

    /**
     * 所属专业的主键。
     *
     * @mock 1
     */
    @Null(groups = Update.class)
    @NotNull(groups = Insert.class)
    private Integer majorId;

    /**
     * 班级名称。
     *
     * @mock 化学2001
     */
    @ClassName
    @NotNull(groups = Insert.class)
    private String className;

}