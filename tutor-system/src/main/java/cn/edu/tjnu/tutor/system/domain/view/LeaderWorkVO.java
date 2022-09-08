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
 * 班主任实习工作信息。
 *
 * @author 王帅
 * @since 2.0
 */
@Data
public class LeaderWorkVO implements Serializable {

    private static final long serialVersionUID = 141551422958781030L;

    /**
     * 实习班级。
     *
     * @mock 高三（16）班
     */
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