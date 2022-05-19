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

import cn.edu.tjnu.tutor.common.validation.groups.Insert;
import cn.edu.tjnu.tutor.common.validation.groups.Update;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

/**
 * <p>专业信息数据传输对象，用于超级管理员（ROLE_ROOT）与学院管理员（ROLE_ADMIN）操作专业信息。
 *
 * <p>插入专业信息时：
 * <blockquote><pre>
 *     {
 *         "collegeId": 1,
 *         "majorName": "化学（师范）",
 *         "majorAbbr": "化学"
 *     }
 * </pre></blockquote>
 *
 * <p>更新专业信息时：
 * <blockquote><pre>
 *     {
 *         "majorId": 1,
 *         "majorName": "化学（师范）",
 *         "majorAbbr": "化学"
 *     }
 * </pre></blockquote>
 *
 * @author 王帅
 * @since 2.0
 */
@Data
public class MajorDTO implements Serializable {

    private static final long serialVersionUID = -8588941613994070798L;

    /**
     * 专业主键。
     */
    @Null(groups = Insert.class)
    @NotNull(groups = Update.class)
    private Integer majorId;

    /**
     * 所属学院的主键。
     */
    @Null(groups = Update.class)
    @NotNull(groups = Insert.class)
    private Integer collegeId;

    /**
     * 专业名称。
     */
    @Length(max = 50)
    @NotNull(groups = Insert.class)
    private String majorName;

    /**
     * 专业简称。
     */
    @Length(max = 10)
    @NotNull(groups = Insert.class)
    private String majorAbbr;

}