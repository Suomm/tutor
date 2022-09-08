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

import java.io.Serializable;

/**
 * 班主任实习工作信息数据传输对象。
 *
 * <p>插入班主任实习工作信息时：
 * <blockquote><pre>
 *     {
 *         "userId": 1,
 *         "className": "高三（16）班",
 *         "studentAmount": 54,
 *         "baseInfo": "基本情况",
 *         "dailyWork": "日常工作",
 *         "workPlan": "班主任工作计划",
 *         "educationPlan": "班级综合育人课外活动设计实施方案",
 *         "classActivity": "独立开展班级活动记录",
 *         "docLink": "https://localhost/files/011001"
 *     }
 * </pre></blockquote>
 *
 * <p>更新班主任实习工作信息时：
 * <blockquote><pre>
 *     {
 *         "userId": 1,
 *         "className": "高三（16）班",
 *         "studentAmount": 54,
 *         "baseInfo": "基本情况",
 *         "dailyWork": "日常工作",
 *         "workPlan": "班主任工作计划",
 *         "educationPlan": "班级综合育人课外活动设计实施方案",
 *         "classActivity": "独立开展班级活动记录",
 *         "docLink": "https://localhost/files/011001"
 *     }
 * </pre></blockquote>
 *
 * @author 王帅
 * @since 2.0
 */
@Data
public class LeaderWorkDTO implements Serializable {

    private static final long serialVersionUID = -4799029322262769396L;

    /**
     * 实习班级。
     *
     * @mock 高三（16）班
     */
    @Length(max = 50)
    private String className;

    /**
     * 班级学生人数。
     *
     * @mock 54
     */
    private Integer studentAmount;

    /**
     * 基本情况。
     *
     * @mock 基本情况
     */
    private String baseInfo;

    /**
     * 日常工作。
     *
     * @mock 日常工作
     */
    private String dailyWork;

    /**
     * 班主任工作计划。
     *
     * @mock 班主任工作计划
     */
    private String workPlan;

    /**
     * 班级综合育人课外活动设计实施方案。
     *
     * @mock 班级综合育人课外活动设计实施方案
     */
    private String educationPlan;

    /**
     * 独立开展班级活动记录。
     *
     * @mock 独立开展班级活动记录
     */
    private String classActivity;

    /**
     * 上传的文件链接地址。
     *
     * @mock https://localhost/files/775238
     */
    private String docLink;

}