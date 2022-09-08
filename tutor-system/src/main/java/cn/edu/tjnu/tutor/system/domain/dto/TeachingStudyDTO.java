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
 * 教研活动记录信息数据传输对象。
 *
 * <p>插入教研活动记录时：
 * <blockquote><pre>
 *     {
 *         "userId": 1,
 *         "title": "教研活动1",
 *         "content": "教研内容",
 *         "type": "教研方式",
 *         "teachingProcess": "教学过程",
 *         "designIdea": "课程设计思路",
 *         "personalSummary": "个人反思及总结",
 *         "startDate": "2022-10-01",
 *         "docLink": "https://localhost/files/407968"
 *     }
 * </pre></blockquote>
 *
 * <p>更新教研活动记录时：
 * <blockquote><pre>
 *     {
 *         "studyId": 1,
 *         "title": "教研活动1",
 *         "content": "教研内容",
 *         "type": "教研方式",
 *         "teachingProcess": "教学过程",
 *         "designIdea": "课程设计思路",
 *         "personalSummary": "个人反思及总结",
 *         "startDate": "2022-10-01",
 *         "docLink": "https://localhost/files/407968"
 *     }
 * </pre></blockquote>
 *
 * @author 王帅
 * @since 2.0
 */
@Data
public class TeachingStudyDTO implements Serializable {

    private static final long serialVersionUID = 7732899970167696267L;

    /**
     * 教研活动主键。
     *
     * @mock 1
     */
    @Null(groups = Insert.class)
    @NotNull(groups = Update.class)
    private Integer studyId;

    /**
     * 教研内容。
     *
     * @mock 教研内容
     */
    private String content;

    /**
     * 教研方式。
     *
     * @mock 教研方式
     */
    @Length(max = 50)
    private String type;

    /**
     * 教研过程。
     *
     * @mock 教研过程
     */
    private String teachingProcess;

    /**
     * 教研思路。
     *
     * @mock 教研思路
     */
    private String designIdea;

    /**
     * 个人反思与总结。
     *
     * @mock 个人反思与总结
     */
    private String personalSummary;

    /**
     * 教研开始日期。
     *
     * @mock 2022-10-01
     */
    @NotNull(groups = Insert.class)
    private LocalDate startDate;

    /**
     * 上传的文件链接地址。
     *
     * @mock https://localhost/files/407968
     */
    private String docLink;

}