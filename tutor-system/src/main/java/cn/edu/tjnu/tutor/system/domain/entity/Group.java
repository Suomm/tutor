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
import lombok.Data;

import java.util.Objects;

/**
 * 导师小组信息。
 *
 * @author 王帅
 * @since 2.0
 */
@Data
@TableName("sys_group")
public class Group extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 小组主键。
     */
    @TableId(type = IdType.AUTO)
    private Integer groupId;

    /**
     * 创建者主键。
     */
    private Integer userId;

    /**
     * 小组组长主键。
     */
    private Integer leaderId;

    /**
     * 小组名称。
     */
    private String groupName;

    /**
     * 小组可选人数。
     */
    private Integer total;

    /**
     * 小组剩余可选人数。
     */
    private Integer stock;

    /**
     * 小组介绍。
     */
    private String introduce;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Group) {
            Group other = (Group) o;
            return Objects.equals(this.groupId, other.groupId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.groupId);
    }

}