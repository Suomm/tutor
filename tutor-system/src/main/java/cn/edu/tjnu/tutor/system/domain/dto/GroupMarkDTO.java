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

import java.io.Serializable;

/**
 * 小组组长打分信息数据传输对象。
 *
 * @author 王帅
 * @since 2.0
 */
@Data
public class GroupMarkDTO implements Serializable {

    private static final long serialVersionUID = -2190319347340576693L;

    /**
     * 打分成绩。
     *
     * @mock 100
     */
    private Integer score;

    /**
     * 小组评价。
     *
     * @mock 表现优秀！
     */
    private String remark;

}