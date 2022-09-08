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
import java.util.List;
import java.util.Objects;

/**
 * 活动信息。
 *
 * @author 王帅
 * @since 1.0
 */
@Data
@IndexName
public class Activity implements Serializable {

    private static final long serialVersionUID = 7232391888281110320L;

    /**
     * 活动主键。
     */
    @IndexId
    private String activityId;

    /**
     * 发布活动用户的主键。
     *
     * @see User#getUserId()
     */
    private Integer userId;

    /**
     * 发布者姓名。
     *
     * @see User#getUserName()
     */
    @IndexField(fieldType = FieldType.KEYWORD)
    private String publisher;

    /**
     * 活动发布者所属学院名称。
     */
    @IndexField(fieldType = FieldType.KEYWORD)
    private String collegeName;

    /**
     * 活动标题。
     */
    @IndexField(fieldType = FieldType.TEXT, analyzer = Analyzer.IK_SMART, searchAnalyzer = Analyzer.IK_MAX_WORD)
    private String title;

    /**
     * 活动详细信息。
     */
    @IndexField(fieldType = FieldType.TEXT)
    private String content;

    /**
     * 活动发布时间。
     */
    @IndexField(fieldType = FieldType.DATE, dateFormat = GlobalConst.DATE_TIME_FORMAT)
    private LocalDateTime createTime;

    /**
     * 活动开始时间。
     */
    @IndexField(fieldType = FieldType.DATE, dateFormat = GlobalConst.DATE_TIME_FORMAT)
    private LocalDateTime startTime;

    /**
     * 活动结束时间。
     */
    @IndexField(fieldType = FieldType.DATE, dateFormat = GlobalConst.DATE_TIME_FORMAT)
    private LocalDateTime endTime;

    /**
     * 活动所在小组主键（0 表示公开的活动）。
     */
    private Integer groupId;

    /**
     * 活动所在小组名称。
     */
    @IndexField(fieldType = FieldType.TEXT, analyzer = Analyzer.IK_SMART, searchAnalyzer = Analyzer.IK_MAX_WORD)
    private String groupName;

    /**
     * 相关文件链接。
     */
    private List<String> fileLinks;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Activity) {
            Activity other = (Activity) o;
            return Objects.equals(this.activityId, other.activityId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.activityId);
    }

}