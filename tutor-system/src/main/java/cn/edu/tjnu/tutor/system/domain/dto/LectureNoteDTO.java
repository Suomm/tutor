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
 * 实习听课记录信息数据传输对象。
 *
 * <p>插入实习听课记录信息时：
 * <blockquote><pre>
 *     {
 *         "userId": 1,
 *         "lectureContent": "听课内容",
 *         "lectureType": "课程类型",
 *         "teacherName": "任课教师",
 *         "className": "听课班级",
 *         "teachingProcess": "教学过程",
 *         "experience": "听课体会及建议",
 *         "personalSummary": "个人反思与总结",
 *         "docLink": "https://localhost/files/011001",
 *         "startDate": "2022-08-01"
 *     }
 * </pre></blockquote>
 *
 * <p>更新实习听课记录信息时：
 * <blockquote><pre>
 *     {
 *         "noteId": 1,
 *         "lectureContent": "听课内容",
 *         "lectureType": "课程类型",
 *         "teacherName": "任课教师",
 *         "className": "听课班级",
 *         "teachingProcess": "教学过程",
 *         "experience": "听课体会及建议",
 *         "personalSummary": "个人反思与总结",
 *         "docLink": "https://localhost/files/011001",
 *         "startDate": "2022-08-01"
 *     }
 * </pre></blockquote>
 *
 * @author 王帅
 * @since 2.0
 */
@Data
public class LectureNoteDTO implements Serializable {

    private static final long serialVersionUID = 6635308102793243303L;

    /**
     * 实习听课记录主键。
     *
     * @mock 1
     */
    @Null(groups = Insert.class)
    @NotNull(groups = Update.class)
    private Integer noteId;

    /**
     * 听课内容。
     *
     * @mock 听课内容
     */
    @Length(max = 100)
    @NotNull(groups = Insert.class)
    private String lectureContent;

    /**
     * 课程类型。
     *
     * @mock 课程类型
     */
    @Length(max = 50)
    private String lectureType;

    /**
     * 任课教师。
     *
     * @mock 任课教师
     */
    @Length(max = 50)
    private String teacherName;

    /**
     * 听课班级。
     *
     * @mock 听课班级
     */
    @Length(max = 50)
    private String className;

    /**
     * 教学过程。
     *
     * @mock 教学过程
     */
    private String teachingProcess;

    /**
     * 听课体会及建议。
     *
     * @mock 听课体会及建议
     */
    private String experience;

    /**
     * 个人反思与总结。
     *
     * @mock 个人反思与总结
     */
    private String personalSummary;

    /**
     * 听课开始日期。
     *
     * @mock 2022-08-01
     */
    @NotNull(groups = Insert.class)
    private LocalDate startDate;

    /**
     * 上传的文件链接地址。
     *
     * @mock https://localhost/files/011001
     */
    private String docLink;

}