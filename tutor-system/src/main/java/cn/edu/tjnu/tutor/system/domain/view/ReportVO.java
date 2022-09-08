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

/**
 * 教育实习成绩信息。
 *
 * @author 王帅
 * @since 2.0
 */
@Data
public class ReportVO implements Serializable {

    private static final long serialVersionUID = -4065862111624501872L;

    /**
     * 听课记录中学指导老师评分。
     */
    private Integer tkpfSchool;

    /**
     * 听课记录高校指导老师评分。
     */
    private Integer tkpfCollege;

    /**
     * 教案设计中学指导老师评分。
     */
    private Integer japfSchool;

    /**
     * 教案设计高校指导老师评分。
     */
    private Integer japfCollege;

    /**
     * 教研活动中学指导老师评分。
     */
    private Integer jypfSchool;

    /**
     * 教研活动高校指导老师评分。
     */
    private Integer jypfCollege;

    /**
     * 师德表现中学指导老师评分。
     */
    private Integer sdpfSchool;

    /**
     * 师德表现高校指导老师评分。
     */
    private Integer sdpfCollege;

    /**
     * 班主任实习工作中学指导教师评分。
     */
    private Integer bzrpfSchool;

    /**
     * 班主任实习工作高校指导教师评分。
     */
    private Integer bzrpfCollege;

    /**
     * 课堂教学中学指导教师评分。
     */
    private Integer jxpfSchool;

    /**
     * 课堂教学高校指导教师评分。
     */
    private Integer jxpfCollege;

    /**
     * 教育实习学生小组互评分数。
     */
    private Integer groupScore;

}