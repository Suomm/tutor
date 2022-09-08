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

package cn.edu.tjnu.tutor.common.enums;

import lombok.Getter;

/**
 * 操作日志的类别
 *
 * @author 王帅
 * @since 2.0
 */
public enum Category {

    // 操作日志的所有类别。

    ACTIVITY("活动信息管理"),
    ANSWER("问题答复信息管理"),
    ARTICLE("文章信息管理"),
    CLASS("班级信息管理"),
    COLLEGE("学院信息管理"),
    COMMENT("文章评论信息管理"),
    CONFIG("参数配置信息管理"),
    INTERN_INFO("教育实习基本信息管理"),
    GROUP("导师小组信息管理"),
    LEADER_WORK("班主任实习工作记录信息管理"),
    LECTURE_NOTE("实习听课记录信息管理"),
    LESSON_PLAN("实习教案信息管理"),
    MAJOR("专业信息管理"),
    MENU("菜单信息管理"),
    NOTICE("公告信息管理"),
    OSS("对象存储信息管理"),
    PROBLEM("问题信息管理"),
    TEACHING_RECORD("课堂教学记录信息管理"),
    TEACHING_STUDY("教研活动信息管理"),
    TEAM("导师团信息管理"),
    USER("用户信息管理");

    /**
     * 类别的名称。
     */
    @Getter
    private final String name;

    Category(String name) {
        this.name = name;
    }

}