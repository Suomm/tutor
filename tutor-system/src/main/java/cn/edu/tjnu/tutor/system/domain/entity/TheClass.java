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

package cn.edu.tjnu.tutor.system.domain.entity;

import cn.edu.tjnu.tutor.common.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Objects;

/**
 * 班级信息。
 *
 * @author 王帅
 * @since 1.0
 */
@Data
@TableName("sys_class")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TheClass extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 班级主键。
     */
    @TableId(type = IdType.AUTO)
    private Integer classId;

    /**
     * 所属专业的主键。
     */
    private Integer majorId;

    /**
     * 班级名称。
     */
    private String className;

    /**
     * 所属年级。
     */
    private String grade;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof TheClass) {
            TheClass other = (TheClass) o;
            return Objects.equals(this.classId, other.classId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.classId);
    }

}