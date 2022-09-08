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

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for prac_intern_info
-- ----------------------------
DROP TABLE IF EXISTS `prac_intern_info`;
CREATE TABLE `prac_intern_info`
(
    `info_id`          int                                                          NOT NULL AUTO_INCREMENT COMMENT '实习信息主键',
    `user_id`          int                                                          NOT NULL COMMENT '实习生主键',
    `type`             int                                                          NULL DEFAULT NULL COMMENT '实习类型',
    `subject`          varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '实习学科',
    `school_name`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '实习基地名称',
    `teacher_name`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '基地实习导师姓名',
    `grade`            varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '实习年级',
    `start_date`       date                                                         NULL DEFAULT NULL COMMENT '实习开始时间',
    `end_date`         date                                                         NULL DEFAULT NULL COMMENT '实习结束时间',
    `tkpf_school`      int                                                          NULL DEFAULT NULL COMMENT '听课记录中学指导老师评分',
    `tkpf_college`     int                                                          NULL DEFAULT NULL COMMENT '听课记录高校指导老师评分',
    `japf_school`      int                                                          NULL DEFAULT NULL COMMENT '教案设计中学指导老师评分',
    `japf_college`     int                                                          NULL DEFAULT NULL COMMENT '教案设计高校指导老师评分',
    `jypf_school`      int                                                          NULL DEFAULT NULL COMMENT '教研活动中学指导老师评分',
    `jypf_college`     int                                                          NULL DEFAULT NULL COMMENT '教研活动高校指导老师评分',
    `sdpf_school`      int                                                          NULL DEFAULT NULL COMMENT '师德表现中学指导老师评分',
    `sdpf_college`     int                                                          NULL DEFAULT NULL COMMENT '师德表现高校指导老师评分',
    `personal_summary` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci    NULL COMMENT '教育实习个人总结',
    `group_evaluation` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci    NULL COMMENT '教育实习学生小组评价',
    `group_score`      int                                                          NULL DEFAULT NULL COMMENT '教育实习学生小组互评分数',
    `sxpy_school`      longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci    NULL COMMENT '教育实习中学指导教师评语',
    `sxpy_college`     longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci    NULL COMMENT '教育实习高校指导教师评语',
    PRIMARY KEY (`info_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '教育实习基本信息表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of prac_intern_info
-- ----------------------------

-- ----------------------------
-- Table structure for prac_leader_work
-- ----------------------------
DROP TABLE IF EXISTS `prac_leader_work`;
CREATE TABLE `prac_leader_work`
(
    `work_id`        int                                                           NOT NULL AUTO_INCREMENT COMMENT '班主任实习工作记录主键',
    `user_id`        int                                                           NOT NULL COMMENT '实习生主键',
    `class_name`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '实习班级',
    `student_amount` int                                                           NULL DEFAULT NULL COMMENT '班级学生人数',
    `base_info`      longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci     NULL COMMENT '基本情况',
    `daily_work`     longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci     NULL COMMENT '日常工作',
    `work_plan`      longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci     NULL COMMENT '班主任工作计划',
    `education_plan` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci     NULL COMMENT '班级综合育人课外活动设计实施方案',
    `class_activity` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci     NULL COMMENT '独立开展班级活动记录',
    `doc_link`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '上传的文件链接地址',
    `mark_school`    int                                                           NULL DEFAULT NULL COMMENT '中学指导教师评分',
    `mark_college`   int                                                           NULL DEFAULT NULL COMMENT '高校指导教师评分',
    PRIMARY KEY (`work_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '班主任实习工作信息表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of prac_leader_work
-- ----------------------------

-- ----------------------------
-- Table structure for prac_lecture_note
-- ----------------------------
DROP TABLE IF EXISTS `prac_lecture_note`;
CREATE TABLE `prac_lecture_note`
(
    `note_id`          int                                                           NOT NULL AUTO_INCREMENT COMMENT '实习听课记录主键',
    `user_id`          int                                                           NOT NULL COMMENT '实习生主键',
    `lecture_content`  varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '听课内容',
    `lecture_type`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '课程类型',
    `teacher_name`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '任课教师',
    `class_name`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '听课班级',
    `teaching_process` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci     NULL COMMENT '教学过程',
    `experience`       longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci     NULL COMMENT '听课体会及建议',
    `personal_summary` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci     NULL COMMENT '个人反思与总结',
    `group_evaluation` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci     NULL COMMENT '小组评价',
    `start_date`       date                                                          NOT NULL COMMENT '听课开始日期',
    `doc_link`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '上传的文件链接地址',
    PRIMARY KEY (`note_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '实习听课记录信息表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of prac_lecture_note
-- ----------------------------

-- ----------------------------
-- Table structure for prac_lesson_plan
-- ----------------------------
DROP TABLE IF EXISTS `prac_lesson_plan`;
CREATE TABLE `prac_lesson_plan`
(
    `plan_id`          int                                                           NOT NULL COMMENT '实习教案主键',
    `user_id`          int                                                           NOT NULL COMMENT '实习生主键',
    `subject_name`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL COMMENT '课题名称',
    `subject_type`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '课程类型',
    `teaching_process` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci     NULL COMMENT '教学过程',
    `design_idea`      longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci     NULL COMMENT '课程设计思路',
    `personal_summary` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci     NULL COMMENT '个人反思与总结',
    `group_evaluation` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci     NULL COMMENT '小组评价',
    `start_date`       date                                                          NOT NULL COMMENT '教案撰写日期',
    `doc_link`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '上传的文件链接地址',
    PRIMARY KEY (`plan_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '实习教案信息表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of prac_lesson_plan
-- ----------------------------

-- ----------------------------
-- Table structure for prac_teaching_record
-- ----------------------------
DROP TABLE IF EXISTS `prac_teaching_record`;
CREATE TABLE `prac_teaching_record`
(
    `record_id`    int                                                          NOT NULL COMMENT '课堂教学记录主键',
    `user_id`      int                                                          NOT NULL COMMENT '实习生主键',
    `lesson_date`  date                                                         NULL DEFAULT NULL COMMENT '试讲时间',
    `place`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '试讲地点',
    `mark_school`  int                                                          NULL DEFAULT NULL COMMENT '中学指导教师评分',
    `mark_college` int                                                          NULL DEFAULT NULL COMMENT '高校指导教师评分',
    PRIMARY KEY (`record_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '课堂教学成绩信息表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of prac_teaching_record
-- ----------------------------

-- ----------------------------
-- Table structure for prac_teaching_study
-- ----------------------------
DROP TABLE IF EXISTS `prac_teaching_study`;
CREATE TABLE `prac_teaching_study`
(
    `study_id`         int                                                           NOT NULL AUTO_INCREMENT COMMENT '教研活动主键',
    `user_id`          int                                                           NOT NULL COMMENT '实习生主键',
    `content`          varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '教研内容',
    `type`             varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NULL DEFAULT NULL COMMENT '教研方式',
    `teaching_process` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci     NULL COMMENT '教研过程',
    `design_idea`      longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci     NULL COMMENT '教研思路',
    `personal_summary` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci     NULL COMMENT '个人反思与总结',
    `group_evaluation` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci     NULL COMMENT '小组评价',
    `start_date`       date                                                          NOT NULL COMMENT '教研日期',
    `doc_link`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '上传的文件链接地址',
    PRIMARY KEY (`study_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '教研活动记录信息表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of prac_teaching_study
-- ----------------------------

-- ----------------------------
-- Table structure for ref_major_notice
-- ----------------------------
DROP TABLE IF EXISTS `ref_major_notice`;
CREATE TABLE `ref_major_notice`
(
    `major_id`  int NOT NULL COMMENT '专业主键',
    `notice_id` int NOT NULL COMMENT '公告主键',
    PRIMARY KEY (`major_id`, `notice_id`) USING BTREE,
    INDEX `ref_major_notice_fk_2` (`notice_id`) USING BTREE,
    CONSTRAINT `ref_major_notice_fk_1` FOREIGN KEY (`major_id`) REFERENCES `sys_major` (`major_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `ref_major_notice_fk_2` FOREIGN KEY (`notice_id`) REFERENCES `sys_notice` (`notice_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '专业和公告关联表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ref_major_notice
-- ----------------------------

-- ----------------------------
-- Table structure for ref_oss_group
-- ----------------------------
DROP TABLE IF EXISTS `ref_oss_group`;
CREATE TABLE `ref_oss_group`
(
    `oss_id`   int NOT NULL COMMENT '对象存储主键',
    `group_id` int NOT NULL COMMENT '小组主键',
    PRIMARY KEY (`group_id`, `oss_id`) USING BTREE,
    INDEX `ref_oss_group_fk_2` (`oss_id`) USING BTREE,
    CONSTRAINT `ref_oss_group_fk_2` FOREIGN KEY (`oss_id`) REFERENCES `sys_oss` (`oss_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `ref_oss_group_fk_3` FOREIGN KEY (`group_id`) REFERENCES `sys_group` (`group_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '附件和导师小组的关联表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ref_oss_group
-- ----------------------------

-- ----------------------------
-- Table structure for ref_oss_notice
-- ----------------------------
DROP TABLE IF EXISTS `ref_oss_notice`;
CREATE TABLE `ref_oss_notice`
(
    `oss_id`    int NOT NULL COMMENT '对象存储主键',
    `notice_id` int NOT NULL COMMENT '公告主键',
    PRIMARY KEY (`notice_id`, `oss_id`) USING BTREE,
    INDEX `ref_oss_notice_fk_2` (`oss_id`) USING BTREE,
    CONSTRAINT `ref_oss_notice_fk_2` FOREIGN KEY (`oss_id`) REFERENCES `sys_oss` (`oss_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `ref_oss_notice_fk_3` FOREIGN KEY (`notice_id`) REFERENCES `sys_notice` (`notice_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '附件和公告的关联表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ref_oss_notice
-- ----------------------------

-- ----------------------------
-- Table structure for ref_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `ref_role_menu`;
CREATE TABLE `ref_role_menu`
(
    `role_id` int NOT NULL COMMENT '角色主键',
    `menu_id` int NOT NULL COMMENT '菜单主键',
    PRIMARY KEY (`role_id`, `menu_id`) USING BTREE,
    INDEX `ref_role_menu_fk_2` (`menu_id`) USING BTREE,
    CONSTRAINT `ref_role_menu_fk_1` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `ref_role_menu_fk_2` FOREIGN KEY (`menu_id`) REFERENCES `sys_menu` (`menu_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '角色和菜单关联表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ref_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for ref_team_class
-- ----------------------------
DROP TABLE IF EXISTS `ref_team_class`;
CREATE TABLE `ref_team_class`
(
    `team_id`  int NOT NULL COMMENT '导师团主键',
    `class_id` int NOT NULL COMMENT '班级主键',
    PRIMARY KEY (`team_id`, `class_id`) USING BTREE,
    INDEX `ref_team_class_fk_2` (`class_id`) USING BTREE,
    CONSTRAINT `ref_team_class_fk_2` FOREIGN KEY (`class_id`) REFERENCES `sys_class` (`class_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `ref_team_class_fk_3` FOREIGN KEY (`team_id`) REFERENCES `sys_team` (`team_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '导师团与班级关联表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ref_team_class
-- ----------------------------

-- ----------------------------
-- Table structure for ref_user_class
-- ----------------------------
DROP TABLE IF EXISTS `ref_user_class`;
CREATE TABLE `ref_user_class`
(
    `user_id`  int NOT NULL COMMENT '用户主键',
    `class_id` int NOT NULL COMMENT '班级主键',
    PRIMARY KEY (`user_id`, `class_id`) USING BTREE,
    INDEX `ref_user_class_fk_2` (`class_id`) USING BTREE,
    CONSTRAINT `ref_user_class_fk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `ref_user_class_fk_2` FOREIGN KEY (`class_id`) REFERENCES `sys_class` (`class_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '用户（学生）与班级关联表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ref_user_class
-- ----------------------------

-- ----------------------------
-- Table structure for ref_user_group
-- ----------------------------
DROP TABLE IF EXISTS `ref_user_group`;
CREATE TABLE `ref_user_group`
(
    `user_id`  int NOT NULL COMMENT '用户主键',
    `group_id` int NOT NULL COMMENT '小组主键',
    PRIMARY KEY (`user_id`, `group_id`) USING BTREE,
    INDEX `ref_user_group_fk_2` (`group_id`) USING BTREE,
    CONSTRAINT `ref_user_group_fk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `ref_user_group_fk_2` FOREIGN KEY (`group_id`) REFERENCES `sys_group` (`group_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '用户（学生、导师）和导师小组关联表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ref_user_group
-- ----------------------------

-- ----------------------------
-- Table structure for ref_user_role
-- ----------------------------
DROP TABLE IF EXISTS `ref_user_role`;
CREATE TABLE `ref_user_role`
(
    `user_id` int NOT NULL COMMENT '用户主键',
    `role_id` int NOT NULL COMMENT '角色主键',
    PRIMARY KEY (`user_id`, `role_id`) USING BTREE,
    INDEX `ref_user_role_fk_2` (`role_id`) USING BTREE,
    CONSTRAINT `ref_user_role_fk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `ref_user_role_fk_2` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '用户和角色关联表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ref_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for ref_user_team
-- ----------------------------
DROP TABLE IF EXISTS `ref_user_team`;
CREATE TABLE `ref_user_team`
(
    `user_id` int NOT NULL COMMENT '用户主键',
    `team_id` int NOT NULL COMMENT '导师团主键',
    PRIMARY KEY (`user_id`, `team_id`) USING BTREE,
    INDEX `ref_user_team_fk_2` (`team_id`) USING BTREE,
    CONSTRAINT `ref_user_team_fk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `ref_user_team_fk_2` FOREIGN KEY (`team_id`) REFERENCES `sys_team` (`team_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '用户（导师）和导师团关系表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ref_user_team
-- ----------------------------

-- ----------------------------
-- Table structure for sys_class
-- ----------------------------
DROP TABLE IF EXISTS `sys_class`;
CREATE TABLE `sys_class`
(
    `class_id`   int                                                    NOT NULL AUTO_INCREMENT COMMENT '班级主键',
    `major_id`   int                                                    NOT NULL COMMENT '所属专业的主键',
    `class_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '班级名称',
    `grade`      char(2) CHARACTER SET utf8 COLLATE utf8_general_ci     NOT NULL COMMENT '所属年级',
    PRIMARY KEY (`class_id`) USING BTREE,
    INDEX `fk_class_major` (`major_id`) USING BTREE,
    CONSTRAINT `fk_class_major` FOREIGN KEY (`major_id`) REFERENCES `sys_major` (`major_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '班级信息表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_class
-- ----------------------------

-- ----------------------------
-- Table structure for sys_college
-- ----------------------------
DROP TABLE IF EXISTS `sys_college`;
CREATE TABLE `sys_college`
(
    `college_id`   int                                                    NOT NULL AUTO_INCREMENT COMMENT '学院主键',
    `college_code` int                                                    NOT NULL COMMENT '学院编码',
    `college_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学院名称',
    `leader`       varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学院负责人',
    `phone`        varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学院电话',
    `email`        varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学院邮箱',
    `visible`      int                                                    NULL DEFAULT 0 COMMENT '可见性（0可见，1不可见）',
    PRIMARY KEY (`college_id`) USING BTREE,
    UNIQUE INDEX `uk_college_code` (`college_code`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '学院信息表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_college
-- ----------------------------

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`
(
    `config_id`    int                                                     NOT NULL AUTO_INCREMENT COMMENT '参数主键',
    `config_name`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '参数名称',
    `config_key`   varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '参数键名',
    `config_value` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '参数键值',
    PRIMARY KEY (`config_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '参数配置表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_config
-- ----------------------------

-- ----------------------------
-- Table structure for sys_group
-- ----------------------------
DROP TABLE IF EXISTS `sys_group`;
CREATE TABLE `sys_group`
(
    `group_id`   int                                                    NOT NULL AUTO_INCREMENT COMMENT '小组主键',
    `user_id`    int                                                    NOT NULL COMMENT '创建者主键',
    `group_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '小组名称',
    `total`      int                                                    NOT NULL COMMENT '小组可选人数',
    `stock`      int                                                    NOT NULL COMMENT '小组剩余可选人数',
    `introduce`  longtext CHARACTER SET utf8 COLLATE utf8_general_ci    NULL COMMENT '小组介绍',
    PRIMARY KEY (`group_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '导师小组信息表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_group
-- ----------------------------

-- ----------------------------
-- Table structure for sys_major
-- ----------------------------
DROP TABLE IF EXISTS `sys_major`;
CREATE TABLE `sys_major`
(
    `major_id`   int                                                    NOT NULL AUTO_INCREMENT COMMENT '专业主键',
    `college_id` int                                                    NOT NULL COMMENT '所属学院的主键',
    `major_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '专业名称',
    `major_abbr` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '专业简称',
    PRIMARY KEY (`major_id`) USING BTREE,
    INDEX `fk_major_college` (`college_id`) USING BTREE,
    CONSTRAINT `fk_major_college` FOREIGN KEY (`college_id`) REFERENCES `sys_college` (`college_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '专业信息表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_major
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`
(
    `menu_id`   int                                                     NOT NULL AUTO_INCREMENT COMMENT '菜单主键',
    `menu_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '菜单名称',
    `parent_id` int                                                     NULL DEFAULT 0 COMMENT '父菜单主键',
    `weight`    int                                                     NULL DEFAULT 1 COMMENT '菜单权重',
    `path`      varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '路由地址',
    `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件路径',
    `icon`      varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
    PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '菜单信息表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`
(
    `notice_id`      int                                                    NOT NULL AUTO_INCREMENT COMMENT '公告主键',
    `college_id`     int                                                    NOT NULL COMMENT '公告所属学院',
    `notice_type`    int                                                    NOT NULL COMMENT '公告类型（0面向老师，1面向学生）',
    `notice_scope`   int                                                    NULL DEFAULT 0 COMMENT '公告范围（0普通公告，1管理员公告）',
    `notice_title`   varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公告标题',
    `notice_content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci    NOT NULL COMMENT '公告内容',
    `create_by`      varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发布人',
    `create_time`    datetime                                               NOT NULL COMMENT '发布时间',
    `update_by`      varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
    `update_time`    datetime                                               NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`notice_id`) USING BTREE,
    INDEX `fk_notice_college` (`college_id`) USING BTREE,
    CONSTRAINT `fk_notice_college` FOREIGN KEY (`college_id`) REFERENCES `sys_college` (`college_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '公告信息表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------

-- ----------------------------
-- Table structure for sys_oss
-- ----------------------------
DROP TABLE IF EXISTS `sys_oss`;
CREATE TABLE `sys_oss`
(
    `oss_id`        int                                                     NOT NULL AUTO_INCREMENT COMMENT '对象存储主键',
    `file_name`     varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '文件名',
    `original_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '原名',
    `file_suffix`   varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL DEFAULT '' COMMENT '文件后缀名',
    `url`           varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'URL地址',
    PRIMARY KEY (`oss_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '对象存储信息表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_oss
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `role_id`   int                                                    NOT NULL AUTO_INCREMENT COMMENT '角色主键',
    `role_key`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色键名',
    `role_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
    PRIMARY KEY (`role_id`) USING BTREE,
    UNIQUE INDEX `uk_role_key` (`role_key`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 8
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '角色信息表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role`
VALUES (1, 'ROLE_ROOT', '超级管理员');
INSERT INTO `sys_role`
VALUES (2, 'ROLE_ADMIN', '学院管理员');
INSERT INTO `sys_role`
VALUES (3, 'ROLE_TUTOR', '导师');
INSERT INTO `sys_role`
VALUES (4, 'ROLE_STUDENT', '学生');
INSERT INTO `sys_role`
VALUES (5, 'ROLE_TEACHER', '教师');
INSERT INTO `sys_role`
VALUES (6, 'ROLE_INTERN', '实习生');
INSERT INTO `sys_role`
VALUES (7, 'ROLE_INSTRUCTOR', '实践导师');

-- ----------------------------
-- Table structure for sys_team
-- ----------------------------
DROP TABLE IF EXISTS `sys_team`;
CREATE TABLE `sys_team`
(
    `team_id`    int                                                    NOT NULL AUTO_INCREMENT COMMENT '导师团主键',
    `college_id` int                                                    NOT NULL COMMENT '所属学院主键',
    `team_name`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '导师团名称',
    `introduce`  longtext CHARACTER SET utf8 COLLATE utf8_general_ci    NULL COMMENT '导师团介绍信息',
    PRIMARY KEY (`team_id`) USING BTREE,
    INDEX `fk_team_college` (`college_id`) USING BTREE,
    CONSTRAINT `fk_team_college` FOREIGN KEY (`college_id`) REFERENCES `sys_college` (`college_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '导师团信息表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_team
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `user_id`    int                                                     NOT NULL AUTO_INCREMENT COMMENT '用户主键',
    `college_id` int                                                     NOT NULL COMMENT '所属学院主键',
    `user_code`  varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '用户编号（学号或工号）',
    `user_name`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '用户名称',
    `email`      varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT '' COMMENT '用户邮箱',
    `phone`      varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT '' COMMENT '手机号码',
    `gender`     int                                                     NULL DEFAULT 2 COMMENT '用户性别（0 女，1男，2保密）',
    `avatar`     varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '头像地址',
    `introduce`  longtext CHARACTER SET utf8 COLLATE utf8_general_ci     NULL COMMENT '自我介绍',
    PRIMARY KEY (`user_id`) USING BTREE,
    UNIQUE INDEX `uk_user_code` (`user_code`) USING BTREE,
    INDEX `fk_user_college` (`college_id`) USING BTREE,
    CONSTRAINT `fk_user_college` FOREIGN KEY (`college_id`) REFERENCES `sys_college` (`college_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '用户信息表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
