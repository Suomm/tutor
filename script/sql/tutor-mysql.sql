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
-- Table structure for sys_class
-- ----------------------------
DROP TABLE IF EXISTS `sys_class`;
CREATE TABLE `sys_class`
(
    `class_id`   int         NOT NULL AUTO_INCREMENT COMMENT '班级主键',
    `major_id`   int         NOT NULL COMMENT '所属专业的主键',
    `class_name` varchar(50) NOT NULL COMMENT '班级名称',
    `grade`      char(2)     NOT NULL COMMENT '所属年级',
    PRIMARY KEY (`class_id`),
    CONSTRAINT `fk_class_major` FOREIGN KEY (`major_id`) REFERENCES `sys_major` (`major_id`)
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '班级信息表';

-- ----------------------------
-- Table structure for sys_college
-- ----------------------------
DROP TABLE IF EXISTS `sys_college`;
CREATE TABLE `sys_college`
(
    `college_id`   int         NOT NULL AUTO_INCREMENT COMMENT '学院主键',
    `college_code` int         NOT NULL COMMENT '学院编码',
    `college_name` varchar(50) NOT NULL COMMENT '学院名称',
    `leader`       varchar(50) NULL DEFAULT NULL COMMENT '学院负责人',
    `phone`        varchar(11) NULL DEFAULT NULL COMMENT '学院电话',
    `email`        varchar(50) NULL DEFAULT NULL COMMENT '学院邮箱',
    `visible`      int         NULL DEFAULT 0 COMMENT '可见性（0可见，1不可见）',
    PRIMARY KEY (`college_id`),
    UNIQUE KEY `uk_college_code` (`college_code`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '学院信息表';

-- ----------------------------
-- Table structure for sys_group
-- ----------------------------
DROP TABLE IF EXISTS `sys_group`;
CREATE TABLE `sys_group`
(
    `group_id`   int         NOT NULL AUTO_INCREMENT COMMENT '小组主键',
    `user_id`    int         NOT NULL COMMENT '创建者主键',
    `group_name` varchar(50) NOT NULL COMMENT '小组名称',
    `total`      int         NOT NULL COMMENT '小组可选人数',
    `stock`      int         NOT NULL COMMENT '小组剩余可选人数',
    `introduce`  longtext    NULL COMMENT '小组介绍',
    PRIMARY KEY (`group_id`)
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '导师小组信息表';

-- ----------------------------
-- Table structure for sys_major
-- ----------------------------
DROP TABLE IF EXISTS `sys_major`;
CREATE TABLE `sys_major`
(
    `major_id`   int         NOT NULL AUTO_INCREMENT COMMENT '专业主键',
    `college_id` int         NOT NULL COMMENT '所属学院的主键',
    `major_name` varchar(50) NOT NULL COMMENT '专业名称',
    `major_abbr` varchar(10) NOT NULL COMMENT '专业简称',
    PRIMARY KEY (`major_id`),
    CONSTRAINT `fk_major_college` FOREIGN KEY (`college_id`) REFERENCES `sys_college` (`college_id`)
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '专业信息表';

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`
(
    `menu_id`   int          NOT NULL AUTO_INCREMENT COMMENT '菜单主键',
    `menu_name` varchar(50)  NOT NULL COMMENT '菜单名称',
    `parent_id` int          NULL DEFAULT 0 COMMENT '父菜单主键',
    `order`     int          NULL DEFAULT 1 COMMENT '菜单排序',
    `path`      varchar(200) NOT NULL COMMENT '路由地址',
    `component` varchar(255) NULL DEFAULT NULL COMMENT '组件路径',
    `icon`      varchar(100) NULL DEFAULT NULL COMMENT '菜单图标',
    PRIMARY KEY (`menu_id`)
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '菜单信息表';

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`
(
    `notice_id`      int         NOT NULL AUTO_INCREMENT COMMENT '公告主键',
    `college_id`     int         NOT NULL COMMENT '公告所属学院',
    `notice_type`    int         NOT NULL COMMENT '公告类型（0面向老师，1面向学生）',
    `notice_scope`   int         NULL DEFAULT 0 COMMENT '公告范围（0普通公告，1管理员公告）',
    `notice_title`   varchar(50) NOT NULL COMMENT '公告标题',
    `notice_content` longtext    NOT NULL COMMENT '公告内容',
    `create_by`      varchar(50) NOT NULL COMMENT '发布人',
    `create_time`    datetime    NOT NULL COMMENT '发布时间',
    `update_by`      varchar(50) NULL DEFAULT NULL COMMENT '更新人',
    `update_time`    datetime    NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`notice_id`),
    CONSTRAINT `fk_notice_college` FOREIGN KEY (`college_id`) REFERENCES `sys_college` (`college_id`)
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '公告信息表';

-- ----------------------------
-- Table structure for sys_oss
-- ----------------------------
DROP TABLE IF EXISTS `sys_oss`;
CREATE TABLE `sys_oss`
(
    `oss_id`        int          NOT NULL AUTO_INCREMENT COMMENT '对象存储主键',
    `file_name`     varchar(255) NOT NULL DEFAULT '' COMMENT '文件名',
    `original_name` varchar(255) NOT NULL DEFAULT '' COMMENT '原名',
    `file_suffix`   varchar(10)  NOT NULL DEFAULT '' COMMENT '文件后缀名',
    `url`           varchar(255) NOT NULL COMMENT 'URL地址',
    PRIMARY KEY (`oss_id`)
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '对象存储信息表';

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `role_id`   int         NOT NULL AUTO_INCREMENT COMMENT '角色主键',
    `role_key`  varchar(50) NOT NULL COMMENT '角色键名',
    `role_name` varchar(50) NOT NULL COMMENT '角色名称',
    PRIMARY KEY (`role_id`),
    UNIQUE KEY `uk_role_key` (`role_key`)
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '角色信息表';

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
    `team_id`    int         NOT NULL AUTO_INCREMENT COMMENT '导师团主键',
    `college_id` int         NOT NULL COMMENT '所属学院主键',
    `team_name`  varchar(50) NOT NULL COMMENT '导师团名称',
    `introduce`  longtext    NULL COMMENT '导师团介绍信息',
    PRIMARY KEY (`team_id`),
    CONSTRAINT `fk_team_college` FOREIGN KEY (`college_id`) REFERENCES `sys_college` (`college_id`)
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '导师团信息表';

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `user_id`    int          NOT NULL AUTO_INCREMENT COMMENT '用户主键',
    `college_id` int          NOT NULL COMMENT '所属学院主键',
    `user_code`  varchar(10)  NOT NULL COMMENT '用户编号（学号或工号）',
    `user_name`  varchar(50)  NOT NULL COMMENT '用户名称',
    `email`      varchar(50)  NULL DEFAULT '' COMMENT '用户邮箱',
    `phone`      varchar(11)  NULL DEFAULT '' COMMENT '手机号码',
    `gender`     int          NULL DEFAULT 2 COMMENT '用户性别（0 女，1男，2保密）',
    `avatar`     varchar(255) NULL DEFAULT '' COMMENT '头像地址',
    `introduce`  longtext     NULL COMMENT '自我介绍',
    PRIMARY KEY (`user_id`),
    UNIQUE KEY `uk_user_code` (`user_code`),
    CONSTRAINT `fk_user_college` FOREIGN KEY (`college_id`) REFERENCES `sys_college` (`college_id`)
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '用户信息表';

-- ----------------------------
-- Table structure for gen_document
-- ----------------------------
DROP TABLE IF EXISTS `gen_document`;
CREATE TABLE `gen_document`
(
    `doc_id`   int          NOT NULL AUTO_INCREMENT COMMENT '文档主键',
    `doc_name` varchar(50)  NOT NULL COMMENT '文档名称',
    `doc_url`  varchar(255) NOT NULL COMMENT '文档样例URL',
    `doc_type` varchar(50)  NOT NULL COMMENT '文件类型（文件后缀名）',
    PRIMARY KEY (`doc_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '（教育实践）文档信息表';

-- ----------------------------
-- Table structure for gen_template
-- ----------------------------
DROP TABLE IF EXISTS `gen_template`;
CREATE TABLE `gen_template`
(
    `tmpl_id`     int          NOT NULL AUTO_INCREMENT COMMENT '模板主键',
    `doc_id`      int          NOT NULL COMMENT '所属手册主键',
    `tmpl_name`   varchar(50)  NOT NULL COMMENT '模板名称',
    `tmpl_order`  int          NOT NULL COMMENT '模板排列顺序',
    `tmpl_url`    varchar(255) NOT NULL COMMENT '模板文件URL',
    `read_only`   int          NULL DEFAULT 0 COMMENT '是否只读（0否 1是）',
    `create_time` datetime     NOT NULL COMMENT '创建时间',
    `update_time` datetime     NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`tmpl_id`),
    CONSTRAINT `fk_tmpl_doc` FOREIGN KEY (`doc_id`) REFERENCES `gen_document` (`doc_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '（教育实践）文档拆分之后的模板信息表';

-- ----------------------------
-- Table structure for gen_user_doc
-- ----------------------------
DROP TABLE IF EXISTS `gen_user_doc`;
CREATE TABLE `gen_user_doc`
(
    `user_id`     int          NOT NULL COMMENT '用户主键',
    `doc_id`      int          NOT NULL COMMENT '文档主键',
    `doc_url`     varchar(255) NOT NULL COMMENT '生成的文档URL',
    `create_time` datetime     NOT NULL COMMENT '创建时间',
    `update_time` datetime     NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`user_id`, `doc_id`),
    CONSTRAINT `gen_user_doc_fk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`),
    CONSTRAINT `gen_user_doc_fk_2` FOREIGN KEY (`doc_id`) REFERENCES `gen_document` (`doc_id`)
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '（学生）用户与生成的（教育实践）文档关联表';

-- ----------------------------
-- Table structure for gen_user_tmpl
-- ----------------------------
DROP TABLE IF EXISTS `gen_user_tmpl`;
CREATE TABLE `gen_user_tmpl`
(
    `user_id`     int          NOT NULL COMMENT '用户主键',
    `tmpl_id`     int          NOT NULL COMMENT '文档模板主键',
    `doc_url`     varchar(255) NOT NULL COMMENT '文档模板完成之后，文件上传URL',
    `create_time` datetime     NOT NULL COMMENT '上传时间',
    PRIMARY KEY (`user_id`, `tmpl_id`),
    CONSTRAINT `gen_user_tmpl_fk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`),
    CONSTRAINT `gen_user_tmpl_fk_2` FOREIGN KEY (`tmpl_id`) REFERENCES `gen_template` (`tmpl_id`)
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '（学生）用户完成（教育实践）文档模板的信息表';

-- ----------------------------
-- Table structure for ref_major_notice
-- ----------------------------
DROP TABLE IF EXISTS `ref_major_notice`;
CREATE TABLE `ref_major_notice`
(
    `major_id`  int NOT NULL COMMENT '专业主键',
    `notice_id` int NOT NULL COMMENT '公告主键',
    PRIMARY KEY (`major_id`, `notice_id`),
    CONSTRAINT `ref_major_notice_fk_1` FOREIGN KEY (`major_id`) REFERENCES `sys_major` (`major_id`),
    CONSTRAINT `ref_major_notice_fk_2` FOREIGN KEY (`notice_id`) REFERENCES `sys_notice` (`notice_id`)
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '专业和公告关联表';

-- ----------------------------
-- Table structure for ref_oss_group
-- ----------------------------
DROP TABLE IF EXISTS `ref_oss_group`;
CREATE TABLE `ref_oss_group`
(
    `oss_id`   int NOT NULL COMMENT '对象存储主键',
    `group_id` int NOT NULL COMMENT '小组主键',
    PRIMARY KEY (`group_id`, `oss_id`),
    CONSTRAINT `ref_oss_group_fk_2` FOREIGN KEY (`oss_id`) REFERENCES `sys_oss` (`oss_id`),
    CONSTRAINT `ref_oss_group_fk_3` FOREIGN KEY (`group_id`) REFERENCES `sys_group` (`group_id`)
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '附件和导师小组的关联表';

-- ----------------------------
-- Table structure for ref_oss_notice
-- ----------------------------
DROP TABLE IF EXISTS `ref_oss_notice`;
CREATE TABLE `ref_oss_notice`
(
    `oss_id`    int NOT NULL COMMENT '对象存储主键',
    `notice_id` int NOT NULL COMMENT '公告主键',
    PRIMARY KEY (`notice_id`, `oss_id`),
    CONSTRAINT `ref_oss_notice_fk_2` FOREIGN KEY (`oss_id`) REFERENCES `sys_oss` (`oss_id`),
    CONSTRAINT `ref_oss_notice_fk_3` FOREIGN KEY (`notice_id`) REFERENCES `sys_notice` (`notice_id`)
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '附件和公告的关联表';

-- ----------------------------
-- Table structure for ref_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `ref_role_menu`;
CREATE TABLE `ref_role_menu`
(
    `role_id` int NOT NULL COMMENT '角色主键',
    `menu_id` int NOT NULL COMMENT '菜单主键',
    PRIMARY KEY (`role_id`, `menu_id`),
    CONSTRAINT `ref_role_menu_fk_1` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`),
    CONSTRAINT `ref_role_menu_fk_2` FOREIGN KEY (`menu_id`) REFERENCES `sys_menu` (`menu_id`)
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '角色和菜单关联表';

-- ----------------------------
-- Table structure for ref_team_class
-- ----------------------------
DROP TABLE IF EXISTS `ref_team_class`;
CREATE TABLE `ref_team_class`
(
    `team_id`  int NOT NULL COMMENT '导师团主键',
    `class_id` int NOT NULL COMMENT '班级主键',
    PRIMARY KEY (`team_id`, `class_id`),
    CONSTRAINT `ref_team_class_fk_2` FOREIGN KEY (`class_id`) REFERENCES `sys_class` (`class_id`),
    CONSTRAINT `ref_team_class_fk_3` FOREIGN KEY (`team_id`) REFERENCES `sys_team` (`team_id`)
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '导师团与班级关联表';

-- ----------------------------
-- Table structure for ref_user_class
-- ----------------------------
DROP TABLE IF EXISTS `ref_user_class`;
CREATE TABLE `ref_user_class`
(
    `user_id`  int NOT NULL COMMENT '用户主键',
    `class_id` int NOT NULL COMMENT '班级主键',
    PRIMARY KEY (`user_id`, `class_id`),
    CONSTRAINT `ref_user_class_fk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`),
    CONSTRAINT `ref_user_class_fk_2` FOREIGN KEY (`class_id`) REFERENCES `sys_class` (`class_id`)
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '用户（学生）与班级关联表';

-- ----------------------------
-- Table structure for ref_user_group
-- ----------------------------
DROP TABLE IF EXISTS `ref_user_group`;
CREATE TABLE `ref_user_group`
(
    `user_id`  int NOT NULL COMMENT '用户主键',
    `group_id` int NOT NULL COMMENT '小组主键',
    PRIMARY KEY (`user_id`, `group_id`),
    CONSTRAINT `ref_user_group_fk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`),
    CONSTRAINT `ref_user_group_fk_2` FOREIGN KEY (`group_id`) REFERENCES `sys_group` (`group_id`)
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '用户（学生、导师）和导师小组关联表';

-- ----------------------------
-- Table structure for ref_user_role
-- ----------------------------
DROP TABLE IF EXISTS `ref_user_role`;
CREATE TABLE `ref_user_role`
(
    `user_id` int NOT NULL COMMENT '用户主键',
    `role_id` int NOT NULL COMMENT '角色主键',
    PRIMARY KEY (`user_id`, `role_id`),
    CONSTRAINT `ref_user_role_fk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`),
    CONSTRAINT `ref_user_role_fk_2` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`)
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '用户和角色关联表';

-- ----------------------------
-- Table structure for ref_user_team
-- ----------------------------
DROP TABLE IF EXISTS `ref_user_team`;
CREATE TABLE `ref_user_team`
(
    `user_id` int NOT NULL COMMENT '用户主键',
    `team_id` int NOT NULL COMMENT '导师团主键',
    PRIMARY KEY (`user_id`, `team_id`),
    CONSTRAINT `ref_user_team_fk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`),
    CONSTRAINT `ref_user_team_fk_2` FOREIGN KEY (`team_id`) REFERENCES `sys_team` (`team_id`)
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '用户（导师）和导师团关系表';

SET FOREIGN_KEY_CHECKS = 1;
