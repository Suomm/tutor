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
 * 导师小组信息。
 *
 * @author 王帅
 * @since 2.0
 */
@Data
public class GroupVO implements Serializable {

    private static final long serialVersionUID = -5606515644585402547L;

    /**
     * 小组主键。
     *
     * @mock 1
     */
    private Integer groupId;

    /**
     * 小组名称。
     *
     * @mock 学习小组1
     */
    private String groupName;

    /**
     * 小组可选人数。
     *
     * @mock 10
     */
    private Integer total;

    /**
     * 小组剩余可选人数。
     *
     * @mock 2
     */
    private Integer stock;

    /**
     * 小组介绍。
     *
     * @mock 小组介绍
     */
    private String introduce;

}