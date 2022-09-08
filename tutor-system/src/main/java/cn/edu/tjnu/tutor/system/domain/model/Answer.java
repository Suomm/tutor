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
 * 问题答复信息。
 *
 * @author 王帅
 * @since 2.0
 */
@Data
@IndexName
public class Answer implements Serializable {

    private static final long serialVersionUID = -5407130919934194838L;

    /**
     * 答复主键。
     */
    @IndexId
    private String answerId;

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
    @IndexField(fieldType = FieldType.KEYWORD)
    private String reviewer;

    /**
     * 追问答复的主键（0表示直接对问题进行答复）。
     *
     * @see #answerId
     */
    @IndexField(fieldType = FieldType.TEXT)
    private Integer replyId;

    /**
     * 答复内容。
     */
    @IndexField(fieldType = FieldType.TEXT, analyzer = Analyzer.IK_SMART, searchAnalyzer = Analyzer.IK_MAX_WORD)
    private String content;

    /**
     * 答复点赞数。
     */
    private Integer star;

    /**
     * 创建时间。
     */
    @IndexField(fieldType = FieldType.DATE, dateFormat = GlobalConst.DATE_TIME_FORMAT)
    private LocalDateTime createTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Answer) {
            Answer other = (Answer) o;
            return Objects.equals(this.answerId, other.answerId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.answerId);
    }

}
