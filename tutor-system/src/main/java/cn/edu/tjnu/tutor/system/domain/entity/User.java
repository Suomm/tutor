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
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * 用户信息。
 *
 * @author 王帅
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_user")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户主键。
     */
    @TableId(type = IdType.AUTO)
    private Integer userId;

    /**
     * 所属学院主键。
     */
    private Integer collegeId;

    /**
     * 用户编号（学号或工号）。
     */
    private String userCode;

    /**
     * 用户名称。
     */
    private String userName;

    /**
     * 用户邮箱。
     */
    private String email;

    /**
     * 手机号码。
     */
    private String phone;

    /**
     * 用户性别（0 女，1 男，2 保密）。
     */
    private Integer gender;

    /**
     * 头像地址。
     */
    private String avatar;

    /**
     * 自我介绍。
     */
    private String introduce;

    /**
     * 角色绑定。
     */
    @TableField(exist = false)
    private Integer[] roleIds;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof User) {
            User other = (User) o;
            return Objects.equals(this.userId, other.userId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.userId);
    }

}