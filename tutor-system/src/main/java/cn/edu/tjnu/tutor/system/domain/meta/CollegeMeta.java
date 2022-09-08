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

package cn.edu.tjnu.tutor.system.domain.meta;

import cn.edu.tjnu.tutor.common.validation.groups.Insert;
import cn.edu.tjnu.tutor.common.validation.groups.Update;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

/**
 * <p>学院元数据信息。
 *
 * <p>插入学院信息时：
 * <blockquote><pre>
 *     {
 *         "collegeCode": 413,
 *         "collegeName": "化学学院",
 *         "visible": 0
 *     }
 * </pre></blockquote>
 *
 * <p>更新学院信息时：
 * <blockquote><pre>
 *     {
 *         "collegeId": 1,
 *         "visible": 0
 *     }
 * </pre></blockquote>
 *
 * @author 王帅
 * @since 2.0
 */
@Data
public class CollegeMeta implements Serializable {

    private static final long serialVersionUID = -4032764523470316917L;

    /**
     * 学院主键。
     *
     * @mock 1
     */
    @Null(groups = Insert.class)
    @NotNull(groups = Update.class)
    private Integer collegeId;

    /**
     * 学院编码。
     *
     * @mock 413
     */
    @NotNull(groups = Insert.class)
    private Integer collegeCode;

    /**
     * 学院名称。
     *
     * @mock 化学学院
     */
    @Length(max = 50)
    @NotNull(groups = Insert.class)
    private String collegeName;

    /**
     * 可见性（0可见，1不可见）。
     *
     * @mock 0
     */
    @Range(max = 1)
    private Integer visible;

}