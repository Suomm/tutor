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
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 活动记录信息。
 *
 * @author 王帅
 * @since 2.0
 */
@Data
@Document(indexName = "record")
public class Record extends BaseEntity {

    private static final long serialVersionUID = -2836638793719121984L;

    /**
     * 活动记录主键。
     */
    @Id
    private String recordId;

    /**
     * 完成用户的主键。
     */
    private Integer userId;

    /**
     * 完成活动的主键。
     */
    private Integer activityId;

    /**
     * 活动标题。
     *
     * @see Activity#getTitle()
     */
    @Field(type = FieldType.Keyword)
    private String activityTitle;

    /**
     * 活动完成的时间。
     */
    @Field(format = DateFormat.date_time_no_millis)
    private LocalDateTime completeTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Record) {
            Record other = (Record) o;
            return Objects.equals(this.recordId, other.recordId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.recordId);
    }

}