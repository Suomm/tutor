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
import cn.easyes.common.enums.FieldType;
import cn.edu.tjnu.tutor.common.constant.GlobalConst;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 活动记录信息。
 *
 * @author 王帅
 * @since 2.0
 */
@Data
@IndexName
public class Record implements Serializable {

    private static final long serialVersionUID = -2836638793719121984L;

    /**
     * 活动记录主键。
     */
    @IndexId
    private String recordId;

    /**
     * 完成用户的主键。
     */
    @IndexField(fieldType = FieldType.INTEGER)
    private Integer userId;

    /**
     * 完成活动的主键。
     */
    @IndexField(fieldType = FieldType.KEYWORD)
    private String activityId;

    /**
     * 活动标题。
     *
     * @see Activity#getTitle()
     */
    @IndexField(fieldType = FieldType.KEYWORD)
    private String activityTitle;

    /**
     * 活动完成的时间。
     */
    @IndexField(fieldType = FieldType.DATE, dateFormat = GlobalConst.DATE_TIME_FORMAT)
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