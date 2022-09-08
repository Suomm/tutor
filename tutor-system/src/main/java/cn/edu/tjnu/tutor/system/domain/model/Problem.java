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
 * 问题信息。
 *
 * @author 王帅
 * @since 1.0
 */
@Data
@IndexName
public class Problem implements Serializable {

    private static final long serialVersionUID = 5559693170878148296L;

    /**
     * 问题主键。
     */
    @IndexId
    private String problemId;

    /**
     * 发布问题的用户主键。
     *
     * @see User#getUserId()
     */
    private Integer userId;

    /**
     * 提问者姓名。
     *
     * @see User#getUserName()
     */
    @IndexField(fieldType = FieldType.KEYWORD)
    private String questioner;

    /**
     * 发布问题者所属学院名称。
     */
    @IndexField(fieldType = FieldType.KEYWORD)
    private String collegeName;

    /**
     * 问题标题。
     */
    @IndexField(fieldType = FieldType.TEXT, analyzer = Analyzer.IK_SMART, searchAnalyzer = Analyzer.IK_MAX_WORD)
    private String title;

    /**
     * 详细内容。
     */
    @IndexField(fieldType = FieldType.TEXT)
    private String content;

    /**
     * 创建时间。
     */
    @IndexField(fieldType = FieldType.DATE, dateFormat = GlobalConst.DATE_TIME_FORMAT)
    private LocalDateTime createTime;

    /**
     * 可见范围（0 导师小组/班级内可见，1 精选问题/公开）。
     */
    private Integer scope;

    /**
     * 当前状态（0 未解决，1 线上解决，2 线下解决）。
     */
    private Integer status;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Problem) {
            Problem other = (Problem) o;
            return Objects.equals(this.problemId, other.problemId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.problemId);
    }

}
