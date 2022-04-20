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
 * 文章信息。
 *
 * @author 王帅
 * @since 1.0
 */
@Data
@Document(indexName = "article")
public class Article implements Serializable {

    private static final long serialVersionUID = 3195804457014661912L;

    /**
     * 文章主键。
     */
    @Id
    private Integer articleId;

    /**
     * 发布文章的用户主键。
     *
     * @see User#getUserId()
     */
    private Integer userId;

    /**
     * 文章作者。
     *
     * @see User#getUserName()
     */
    @Field(type = FieldType.Keyword)
    private String author;

    /**
     * 发布文章者所属学院名称。
     */
    @Field(type = FieldType.Keyword)
    private String collegeName;

    /**
     * 文章标题。
     */
    @Field(analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String title;

    /**
     * 文章封面图片。
     */
    @Field(type = FieldType.Keyword)
    private String cover;

    /**
     * 文章介绍（引言）。
     */
    @Field(analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String introduce;

    /**
     * 文章内容。
     */
    private String content;

    /**
     * 发布时间。
     */
    @Field(format = DateFormat.date_time_no_millis)
    private LocalDateTime createTime;

    /**
     * 更新时间。
     */
    @Field(format = DateFormat.date_time_no_millis)
    private LocalDateTime updateTime;

}
