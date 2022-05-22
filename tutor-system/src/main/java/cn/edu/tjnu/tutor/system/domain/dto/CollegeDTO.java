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

import cn.edu.tjnu.tutor.common.validation.constraints.Phone;
import cn.edu.tjnu.tutor.common.validation.groups.Insert;
import cn.edu.tjnu.tutor.common.validation.groups.Update;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

/**
 * <p>学院信息传输对象，用于超级管理员（ROLE_ROOT）操作学院信息。
 *
 * <p>插入学院信息时：
 * <blockquote><pre>
 *     {
 *         "collegeCode": 413,
 *         "collegeName": "化学学院",
 *         "leader": "负责人",
 *         "phone": "13000000001",
 *         "email": "chemistry@email.com"
 *     }
 * </pre></blockquote>
 *
 * <p>更新学院信息时：
 * <blockquote><pre>
 *     {
 *         "collegeId": 1,
 *         "leader": "负责人",
 *         "phone": "13000000001",
 *         "email": "chemistry@email.com"
 *     }
 * </pre></blockquote>
 *
 * @author 王帅
 * @since 2.0
 */
@Data
public class CollegeDTO implements Serializable {

    private static final long serialVersionUID = 2217010167021543775L;

    /**
     * 学院主键。
     *
     * @mock 1
     */
    @Null(groups = Insert.class)
    @NotNull(groups = Update.class)
    private Integer collegeId;

    /**
     * 学院编码。
     *
     * @mock 413
     */
    @Null(groups = Update.class)
    @NotNull(groups = Insert.class)
    private Integer collegeCode;

    /**
     * 学院名称。
     *
     * @mock 化学学院
     */
    @Length(max = 50)
    @Null(groups = Update.class)
    @NotNull(groups = Insert.class)
    private String collegeName;

    /**
     * 学院负责人。
     *
     * @mock 负责人
     */
    @Length(max = 50)
    private String leader;

    /**
     * 学院电话。
     *
     * @mock 13000000001
     */
    @Phone
    private String phone;

    /**
     * 学院邮箱。
     *
     * @mock chemistry@email.com
     */
    @Email
    private String email;

}