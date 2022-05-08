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

package cn.edu.tjnu.tutor.system.domain.dto;

import cn.edu.tjnu.tutor.common.validation.groups.Insert;
import cn.edu.tjnu.tutor.common.validation.groups.Update;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * <p>学院信息传输对象，用于超级管理员（ROLE_ROOT）操作学院信息。
 *
 * <p>插入学院信息时：<br>
 * {@code
 *     {
 *         "collegeCode": "413",
 *         "collegeName": "化学学院",
 *         ... // 其他可选数据插入项
 *     }
 * }
 *
 * <p>更新学院信息时：<br>
 * {@code
 *     {
 *         "collegeId": 1,
 *         ... // （除学院编码和名称之外）需要更新的数据项
 *     }
 * }
 *
 * @author 王帅
 * @since 2.0
 */
@Data
public class CollegeDTO implements Serializable {

    private static final long serialVersionUID = 2217010167021543775L;

    /**
     * 学院主键。
     */
    @Null(groups = Insert.class)
    @NotNull(groups = Update.class)
    private Integer collegeId;

    /**
     * 学院编码。
     */
    @Null(groups = Update.class)
    @NotNull(groups = Insert.class)
    private Integer collegeCode;

    /**
     * 学院名称。
     */
    @Size(max = 50)
    @Null(groups = Update.class)
    @NotNull(groups = Insert.class)
    private String collegeName;

    /**
     * 学院负责人。
     */
    @Size(max = 50)
    private String leader;

    /**
     * 学院电话。
     */
    @Size(max = 11)
    private String phone;

    /**
     * 学院邮箱。
     */
    @Email
    private String email;

}