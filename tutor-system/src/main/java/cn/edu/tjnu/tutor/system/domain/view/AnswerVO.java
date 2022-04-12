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

import cn.edu.tjnu.tutor.common.core.domain.TreeNode;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 问题答复显示信息。
 *
 * @author 王帅
 * @since 2.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AnswerVO extends TreeNode<AnswerVO> implements Serializable {

    private static final long serialVersionUID = 7475794724822576239L;

    /**
     * 答复人姓名。
     */
    private String reviewer;

    /**
     * 答复内容。
     */
    private String content;

    /**
     * 答复点赞数。
     */
    private Integer star;

    /**
     * 回答时间。
     */
    private LocalDateTime createTime;

}