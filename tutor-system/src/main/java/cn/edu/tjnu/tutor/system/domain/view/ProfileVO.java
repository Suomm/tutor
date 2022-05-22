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

package cn.edu.tjnu.tutor.system.domain.view;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户基本信息。
 *
 * @author 王帅
 * @since 2.0
 */
@Data
public class ProfileVO implements Serializable {

    private static final long serialVersionUID = 6553711926701165612L;

    /**
     * 用户编号（学号或工号）。
     *
     * @mock 2040050143
     */
    private String userCode;

    /**
     * 用户名称。
     *
     * @mock 王帅
     */
    private String userName;

    /**
     * 用户邮箱。
     *
     * @mock chemistry@email.com
     */
    private String email;

    /**
     * 手机号码。
     *
     * @mock 13000000001
     */
    private String phone;

    /**
     * 用户性别（0女，1男，2保密）。
     *
     * @mock 1
     */
    private Integer gender;

    /**
     * 头像地址。
     *
     * @mock ""
     */
    private String avatar;

    /**
     * 自我介绍。
     *
     * @mock 天津师范大学化学学院学生
     */
    private String introduce;

    /**
     * （学生）所属专业名称。
     *
     * @mock 化学（师范）
     */
    private String majorName;

    /**
     * （学生）所属班级名称。
     *
     * @mock 化学2001
     */
    private String className;

    /**
     * 所属学院详细信息。
     */
    private CollegeVO college;

}