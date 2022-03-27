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

package cn.edu.tjnu.tutor.system.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 问题答复信息。
 *
 * @author 王帅
 * @since 2.0
 */
@Data
@Document(indexName = "txt_answer")
public class Answer implements Serializable {

    private static final long serialVersionUID = -5407130919934194838L;

    /**
     * 答复主键。
     */
    @Id
    private Integer answerId;

    /**
     * 答复问题的主键。
     *
     * @see Problem#getProblemId()
     */
    private Integer problemId;

    /**
     * 答复者姓名。
     *
     * @see User#getUserName()
     */
    @Field(type = FieldType.Keyword)
    private String reviewer;

    /**
     * 追问答复的主键（0表示直接对问题进行答复）。
     *
     * @see #answerId
     */
    private Integer replyId;

    /**
     * 答复内容。
     */
    @Field(analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String content;

    /**
     * 答复点赞数。
     */
    private Integer star;

    /**
     * 创建时间。
     */
    private LocalDateTime createTime;

}
