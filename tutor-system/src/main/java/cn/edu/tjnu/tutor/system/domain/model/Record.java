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

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 活动记录信息。
 *
 * @author 王帅
 * @since 2.0
 */
@Data
@Document(indexName = "record")
public class Record implements Serializable {

    private static final long serialVersionUID = -2836638793719121984L;

    /**
     * 活动记录主键。
     */
    @Id
    private Integer recordId;

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
    private LocalDateTime completeTime;

}