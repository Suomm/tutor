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

package cn.edu.tjnu.tutor.system.domain.model;

import cn.edu.tjnu.tutor.system.domain.entity.User;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 文章评论信息。
 *
 * @author 王帅
 * @since 2.0
 */
@Data
@Document(indexName = "comment")
public class Comment implements Serializable {

    private static final long serialVersionUID = -1473775313525466489L;

    /**
     * 评论主键。
     */
    @Id
    private String commentId;

    /**
     * 评论文章的主键。
     *
     * @see Article#getArticleId()
     */
    private Integer articleId;

    /**
     * 评论者姓名。
     *
     * @see User#getUserName()
     */
    @Field(type = FieldType.Keyword)
    private String reviewer;

    /**
     * 回复评论的主键（0表示直接对文章进行评论）。
     *
     * @see #commentId
     */
    private Integer replyId;

    /**
     * 评论内容。
     */
    @Field(analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String content;

    /**
     * 创建时间。
     */
    @Field(format = DateFormat.date_time_no_millis)
    private LocalDateTime createTime;

}
