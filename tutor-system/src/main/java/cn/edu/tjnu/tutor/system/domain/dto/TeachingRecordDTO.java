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

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * 课堂教学记录信息数据传输对象。
 *
 * <p>插入课堂教学记录信息时：
 * <blockquote><pre>
 *     {
 *         "userId": 1,
 *         "place": "试讲地点",
 *         "lessonDate": "2022-06-01"
 *     }
 * </pre></blockquote>
 *
 * <p>更新课堂教学记录信息时：
 * <blockquote><pre>
 *     {
 *         "recordId": 1,
 *         "place": "试讲地点",
 *         "lessonDate": "2022-06-01"
 *     }
 * </pre></blockquote>
 *
 * @author 王帅
 * @since 2.0
 */
@Data
public class TeachingRecordDTO implements Serializable {

    private static final long serialVersionUID = 7091066147256329786L;

    /**
     * 课堂教学记录主键。
     *
     * @mock 1
     */
    @Null
    private Integer recordId;

    /**
     * 实习生主键。
     *
     * @mock 1
     */
    @Null
    private Integer userId;

    /**
     * 试讲时间。
     *
     * @mock 2022-06-01
     */
    @NotNull
    @Length(max = 50)
    private LocalDate lessonDate;

    /**
     * 试讲地点。
     *
     * @mock 试讲地点
     */
    @NotNull
    @Length(max = 50)
    private String place;

}