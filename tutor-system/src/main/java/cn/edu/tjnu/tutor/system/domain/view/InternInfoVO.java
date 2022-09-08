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
 * 教育实习基本信息。
 *
 * @author 王帅
 * @since 2.0
 */
@Data
public class InternInfoVO implements Serializable {

    private static final long serialVersionUID = 4359684666769880221L;

    /**
     * 实习类型：0 集中实习 1 自主实习。
     *
     * @mock 1
     */
    private Integer type;

    /**
     * 实习学科。
     *
     * @mock 化学
     */
    private String subject;

    /**
     * 实习基地名称。
     *
     * @mock 静海区第一中学
     */
    private String schoolName;

    /**
     * 基地实习导师姓名。
     *
     * @mock 张三
     */
    private String teacherName;

    /**
     * 实习年级。
     *
     * @mock 高三年级
     */
    private String grade;

    /**
     * 实习开始时间。
     *
     * @mock 2022-09-01
     */
    private LocalDate startDate;

}