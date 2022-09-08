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
import java.time.LocalDateTime;

/**
 * 问题信息。
 *
 * @author 王帅
 * @since 1.0
 */
@Data
public class ProblemVO implements Serializable {

    private static final long serialVersionUID = 7480166029983890431L;

    /**
     * 提问者姓名。
     *
     * @mock 王帅
     */
    private String questioner;

    /**
     * 发布问题者所属学院名称。
     *
     * @mock 化学学院
     */
    private String collegeName;

    /**
     * 问题标题。
     *
     * @mock 这是第一个问题
     */
    private String title;

    /**
     * 详细内容。
     *
     * @mock 问题的详细信息描述
     */
    private String content;

    /**
     * 创建时间。
     *
     * @mock 2020-10-28
     */
    private LocalDateTime createTime;

    /**
     * 当前状态（0 未解决，1 线上解决，2 线下解决）。
     *
     * @mock 0
     */
    private Integer status;

}
