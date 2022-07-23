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

package cn.edu.tjnu.tutor.system.domain.meta;

import cn.edu.tjnu.tutor.common.validation.groups.Insert;
import cn.edu.tjnu.tutor.common.validation.groups.Update;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

/**
 * <p>用户元数据信息，用于后台操作数据。
 *
 * <p>插入用户信息时：
 * <blockquote><pre>
 *     {
 *         "collegeId": 1,
 *         "userCode": "2040050143",
 *         "userName": "王帅",
 *         "roleIds": [1]
 *     }
 * </pre></blockquote>
 *
 * <p>更新用户信息时：
 * <blockquote><pre>
 *     {
 *         "userId": 1,
 *         "collegeId": 1,
 *         "roleIds": [1]
 *     }
 * </pre></blockquote>
 *
 * @author 王帅
 * @since 2.0
 */
@Data
public class UserMeta implements Serializable {

    private static final long serialVersionUID = -2602019142943741002L;

    /**
     * 用户主键。
     *
     * @mock 1
     */
    @Null(groups = Insert.class)
    @NotNull(groups = Update.class)
    private Integer userId;

    /**
     * 所属学院主键。
     *
     * @mock 1
     */
    @NotNull(groups = Insert.class)
    private Integer collegeId;

    /**
     * 用户编号（学号或工号）。
     *
     * @mock 2040050143
     */
    @Length(max = 50)
    @Null(groups = Update.class)
    @NotNull(groups = Insert.class)
    private String userCode;

    /**
     * 用户名称。
     *
     * @mock 王帅
     */
    @Null(groups = Update.class)
    @NotNull(groups = Insert.class)
    private String userName;

    /**
     * 角色绑定。
     *
     * @mock [1]
     */
    @NotNull(groups = Insert.class)
    private Integer[] roleIds;

}