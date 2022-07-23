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

import cn.edu.tjnu.tutor.common.core.domain.BaseEntity;
import cn.edu.tjnu.tutor.system.domain.entity.User;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 活动信息。
 *
 * @author 王帅
 * @since 1.0
 */
@Data
@Document(indexName = "activity")
public class Activity extends BaseEntity {

    private static final long serialVersionUID = 7232391888281110320L;

    /**
     * 活动主键。
     */
    @Id
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
    @Field(type = FieldType.Keyword)
    private String publisher;

    /**
     * 活动发布者所属学院名称。
     */
    @Field(type = FieldType.Keyword)
    private String collegeName;

    /**
     * 活动标题。
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String title;

    /**
     * 活动详细信息。
     */
    private String content;

    /**
     * 发布时间。
     */
    @Field(format = DateFormat.date_time_no_millis)
    private LocalDateTime createTime;

    /**
     * 活动范围（0 班级/小组内可见，1 公开）。
     */
    private Integer scope;

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