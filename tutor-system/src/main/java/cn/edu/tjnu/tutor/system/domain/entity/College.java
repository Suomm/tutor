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
 * 学院信息。
 *
 * @author 王帅
 * @since 1.0
 */
@Data
@TableName("sys_college")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class College extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 学院主键。
     */
    @TableId(type = IdType.AUTO)
    private Integer collegeId;

    /**
     * 学院编码。
     */
    private Integer collegeCode;

    /**
     * 学院名称。
     */
    private String collegeName;

    /**
     * 学院负责人。
     */
    private String leader;

    /**
     * 学院电话。
     */
    private String phone;

    /**
     * 学院邮箱。
     */
    private String email;

    /**
     * 可见性（0 可见，1 不可见）。
     */
    private Integer visible;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof College) {
            College other = (College) o;
            return Objects.equals(this.collegeId, other.collegeId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.collegeId);
    }

}