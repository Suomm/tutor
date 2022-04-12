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

package cn.edu.tjnu.tutor.common.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 角色信息常量。
 *
 * @author 王帅
 * @since 1.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RoleConst {

    /**
     * 超级管理员：职能部门人员角色，用于设置系统相关参数，查看各个部门任务完成度。
     */
    public static final String ROLE_ROOT = "ROLE_ROOT";

    /**
     * 学院管理员：学院内部管理角色，用于设置每个学院的基本信息等操作。
     */
    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    /**
     * 导师：指导班级或者小组进行相关活动，回答学生提问等的教师角色。
     */
    public static final String ROLE_TUTOR = "ROLE_TUTOR";

    /**
     * 学生：跟随导师进行学习，完成教育实践等任务的角色。
     */
    public static final String ROLE_STUDENT = "ROLE_STUDENT";

    /**
     * 教师：没有任何进一步操作的普通教师角色。
     */
    public static final String ROLE_TEACHER = "ROLE_TEACHER";

    /**
     * 实习生：需要完成教育实践活动的特定学生角色。
     */
    public static final String ROLE_INTERN = "ROLE_INTERN";

    /**
     * 实践导师：指导实习生完成教育实践的导师角色。
     */
    public static final String ROLE_INSTRUCTOR = "ROLE_INSTRUCTOR";

}
