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

package cn.edu.tjnu.tutor.system.domain.view;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 课堂教学成绩信息。
 *
 * @author 王帅
 * @since 2.0
 */
@Data
public class TeachingRecordVO implements Serializable {

    private static final long serialVersionUID = -7100704523535425797L;

    /**
     * 课堂教学成绩主键。
     *
     * @mock 1
     */
    private Integer recordId;

    /**
     * 试讲时间。
     *
     * @mock 2022-06-01
     */
    private LocalDate lessonDate;

    /**
     * 试讲地点。
     *
     * @mock 试讲地点
     */
    private String place;

    /**
     * 中学指导教师评分。
     *
     * @mock 80
     */
    private Integer markSchool;

    /**
     * 高校指导教师评分。
     *
     * @mock 85
     */
    private Integer markCollege;

}