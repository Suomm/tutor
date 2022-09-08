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

import cn.easyes.annotation.IndexField;
import cn.easyes.annotation.IndexId;
import cn.easyes.annotation.IndexName;
import cn.easyes.common.constants.Analyzer;
import cn.easyes.common.enums.FieldType;
import cn.edu.tjnu.tutor.common.constant.GlobalConst;
import cn.edu.tjnu.tutor.system.domain.entity.User;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 文章信息。
 *
 * @author 王帅
 * @since 1.0
 */
@Data
@IndexName
public class Article implements Serializable {

    private static final long serialVersionUID = 3195804457014661912L;

    /**
     * 文章主键。
     */
    @IndexId
    private String articleId;

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
    @IndexField(fieldType = FieldType.KEYWORD)
    private String author;

    /**
     * 发布文章者所属学院名称。
     */
    @IndexField(fieldType = FieldType.KEYWORD)
    private String collegeName;

    /**
     * 文章标题。
     */
    @IndexField(fieldType = FieldType.TEXT, analyzer = Analyzer.IK_SMART, searchAnalyzer = Analyzer.IK_MAX_WORD)
    private String title;

    /**
     * 文章封面图片。
     */
    @IndexField(fieldType = FieldType.KEYWORD)
    private String cover;

    /**
     * 文章介绍（引言）。
     */
    @IndexField(fieldType = FieldType.TEXT, analyzer = Analyzer.IK_SMART, searchAnalyzer = Analyzer.IK_MAX_WORD)
    private String introduce;

    /**
     * 文章内容。
     */
    @IndexField(fieldType = FieldType.TEXT)
    private String content;

    /**
     * 发布时间。
     */
    @IndexField(fieldType = FieldType.DATE, dateFormat = GlobalConst.DATE_TIME_FORMAT)
    private LocalDateTime createTime;

    /**
     * 更新时间。
     */
    @IndexField(fieldType = FieldType.DATE, dateFormat = GlobalConst.DATE_TIME_FORMAT)
    private LocalDateTime updateTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Article) {
            Article other = (Article) o;
            return Objects.equals(this.articleId, other.articleId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.articleId);
    }

}
