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
 * 专业信息。
 *
 * @author 王帅
 * @since 2.0
 */
@Data
@TableName("sys_major")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Major extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 专业主键。
     */
    @TableId(type = IdType.AUTO)
    private Integer majorId;

    /**
     * 所属学院的主键。
     */
    private Integer collegeId;

    /**
     * 专业名称。
     */
    private String majorName;

    /**
     * 专业简称。
     */
    private String majorAbbr;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Major) {
            Major other = (Major) o;
            return Objects.equals(this.majorId, other.majorId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.majorId);
    }

}