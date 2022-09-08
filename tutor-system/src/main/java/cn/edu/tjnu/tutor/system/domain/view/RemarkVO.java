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
 * 教育实习评价信息。
 *
 * @author 王帅
 * @since 2.0
 */
@Data
public class RemarkVO implements Serializable {

    private static final long serialVersionUID = 6159375361272815836L;

    /**
     * 教育实习个人总结。
     *
     * @mock 个人总结
     */
    private String personalSummary;

    /**
     * 教育实习小组评价。
     *
     * @mock 小组评价
     */
    private String groupEvaluation;

    /**
     * 教育实习中学教师评语。
     *
     * @mock 中学教师评语
     */
    private String highSchool;

    /**
     * 教育实习高校教师评语。
     *
     * @mock 高校教师评语
     */
    private String university;

}