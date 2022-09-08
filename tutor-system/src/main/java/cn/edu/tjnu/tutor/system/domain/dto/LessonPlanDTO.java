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
import java.time.LocalDate;

/**
 * 实习教案信息数据传输对象。
 *
 * <p>插入实习教案信息时：
 * <blockquote><pre>
 *     {
 *         "userId": 1,
 *         "subjectName": "课题名称",
 *         "subjectType": "课程类型",
 *         "teachingProcess": "教学过程",
 *         "designIdea": "课程设计思路",
 *         "personalSummary": "个人反思及总结",
 *         "startDate": "2022-07-01",
 *         "docLink": "https://localhost/files/208897"
 *     }
 * </pre></blockquote>
 *
 * <p>更新实习教案信息时：
 * <blockquote><pre>
 *     {
 *         "planId": 1,
 *         "subjectName": "课题名称",
 *         "subjectType": "课程类型",
 *         "teachingProcess": "教学过程",
 *         "designIdea": "课程设计思路",
 *         "personalSummary": "个人反思及总结",
 *         "startDate": "2022-07-01",
 *         "docLink": "https://localhost/files/208897"
 *     }
 * </pre></blockquote>
 *
 * @author 王帅
 * @since 2.0
 */
@Data
public class LessonPlanDTO implements Serializable {

    private static final long serialVersionUID = -4841341454620828588L;

    /**
     * 教育实习教案主键。
     *
     * @mock 1
     */
    @Null(groups = Insert.class)
    @NotNull(groups = Update.class)
    private Integer planId;

    /**
     * 课题名称。
     *
     * @mock 课题名称
     */
    @Length(max = 100)
    @NotNull(groups = Insert.class)
    private String subjectName;

    /**
     * 课程类型。
     *
     * @mock 课程类型
     */
    @Length(max = 50)
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
     * 教案撰写日期。
     *
     * @mock 2022-07-01
     */
    @NotNull(groups = Insert.class)
    private LocalDate startDate;

    /**
     * 上传的文件链接地址。
     *
     * @mock https://localhost/files/208897
     */
    private String docLink;

}