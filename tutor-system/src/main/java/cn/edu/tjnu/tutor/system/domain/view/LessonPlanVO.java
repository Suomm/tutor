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
 * 实习教案信息。
 *
 * @author 王帅
 * @since 2.0
 */
@Data
public class LessonPlanVO implements Serializable {

    private static final long serialVersionUID = -6791891856474716434L;

    /**
     * 实习教案主键。
     *
     * @mock 1
     */
    private Integer planId;

    /**
     * 课题名称。
     *
     * @mock 课题名称
     */
    private String subjectName;

    /**
     * 课程类型。
     *
     * @mock 课程类型
     */
    private String subjectType;

    /**
     * 教学过程。
     *
     * @mock 教学过程
     */
    private String teachingProcess;

    /**
     * 课程设计思路。
     *
     * @mock 课程设计思路
     */
    private String designIdea;

    /**
     * 个人反思与总结。
     *
     * @mock 个人反思与总结
     */
    private String personalSummary;

    /**
     * 小组评价。
     *
     * @mock 小组评价
     */
    private String groupEvaluation;

    /**
     * 教案撰写日期。
     *
     * @mock 2022-07-01
     */
    private LocalDate startDate;

    /**
     * 上传的文件链接地址。
     *
     * @mock https://localhost/files/208897
     */
    private String docLink;

}