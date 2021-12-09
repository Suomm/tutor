SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- 活动表
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity`
(
    `id`          int                                                     NOT NULL AUTO_INCREMENT COMMENT '活动表的主键',
    `teacher_id`  int                                                     NOT NULL COMMENT '活动发起的导师ID',
    `title`       varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '活动标题',
    `content`     text CHARACTER SET utf8 COLLATE utf8_general_ci         NOT NULL COMMENT '活动内容',
    `enclosure`   varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '活动详情文件地址',
    `start_time`  datetime                                                NOT NULL COMMENT '活动开始时间',
    `create_time` datetime                                                NOT NULL COMMENT '活动创建时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- 活动与班级的绑定表
-- ----------------------------
DROP TABLE IF EXISTS `activity_class`;
CREATE TABLE `activity_class`
(
    `id`          int NOT NULL AUTO_INCREMENT COMMENT '主键',
    `activity_id` int NOT NULL COMMENT '活动ID',
    `class_id`    int NOT NULL COMMENT '班级ID',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- 文章表
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`
(
    `id`          int                                                     NOT NULL AUTO_INCREMENT COMMENT '文章的主键',
    `show_image`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章的头图',
    `user_id`     int                                                     NOT NULL COMMENT '文章的创建者',
    `activity_id` int                                                     NULL DEFAULT NULL COMMENT '关联活动的ID',
    `title`       varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章标题',
    `content`     longtext CHARACTER SET utf8 COLLATE utf8_general_ci     NOT NULL COMMENT '文章内容',
    `original_id` int                                                     NULL DEFAULT NULL COMMENT '转载文章的ID',
    `create_time` datetime                                                NOT NULL COMMENT '发布时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- 文章评论表
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`
(
    `id`          int                                             NOT NULL AUTO_INCREMENT COMMENT '文章评论的主键',
    `user_id`     int                                             NOT NULL COMMENT '评论用户ID',
    `article_id`  int                                             NOT NULL COMMENT '评论文章ID',
    `content`     text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论内容',
    `create_time` datetime                                        NOT NULL COMMENT '评论时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- 学院表
-- ----------------------------
DROP TABLE IF EXISTS `faculty`;
CREATE TABLE `faculty`
(
    `id`          int                                                    NOT NULL COMMENT '学院ID',
    `full_name`   varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学院全称',
    `simple_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学院简称',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- 初始化学院信息
-- ----------------------------
INSERT INTO `faculty`
VALUES (407, '化学学院', '化学');

-- ----------------------------
-- 菜单表
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`
(
    `id`        int(11)                                                 NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
    `name`      varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '菜单名称',
    `parent_id` bigint(20)                                              NULL     DEFAULT 0 COMMENT '父菜单ID',
    `path`      varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '路由地址',
    `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT NULL COMMENT '组件路径',
    `icon`      varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '#' COMMENT '菜单图标',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 29
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- 初始化菜单表
-- ----------------------------
INSERT INTO `menu`
VALUES (1, '系统管理', 0, 'root', NULL, 'system');
INSERT INTO `menu`
VALUES (2, '学院管理', 1, 'faculty', 'root/faculty/index', 'post');
INSERT INTO `menu`
VALUES (3, '班级管理', 1, 'classes', 'root/classes/index', 'tab');
INSERT INTO `menu`
VALUES (4, '教师管理', 1, 'teachers', 'root/teachers/index', 'user');
INSERT INTO `menu`
VALUES (5, '权限管理', 1, 'admins', 'root/admins/index', 'monitor');
INSERT INTO `menu`
VALUES (6, '系统管理', 0, 'admin', NULL, 'system');
INSERT INTO `menu`
VALUES (7, '公告管理', 6, 'notice', 'admin/notice/index', 'log');
INSERT INTO `menu`
VALUES (8, '教师管理', 6, 'teachers', 'admin/teachers/index', 'nested');
INSERT INTO `menu`
VALUES (9, '导师管理', 6, 'mentors', 'admin/mentors/index', 'skill');
INSERT INTO `menu`
VALUES (10, '实习管理', 6, 'tutors', 'admin/tutors/index', 'druid');
INSERT INTO `menu`
VALUES (11, '主页推荐', -1, 'welcome', 'welcome/student/index', 'star');
INSERT INTO `menu`
VALUES (12, '主页推荐', -1, 'welcome', 'welcome/teacher/index', 'star');
INSERT INTO `menu`
VALUES (13, '我的活动', 0, 'activity', NULL, 'skill');
INSERT INTO `menu`
VALUES (14, '班级活动', 13, 'classes', 'student/activity/classes/index', 'table');
INSERT INTO `menu`
VALUES (15, '小组活动', 13, 'related', 'student/activity/related/index', 'post');
INSERT INTO `menu`
VALUES (16, '活动统计', 13, 'finished', 'student/activity/finished/index', 'tab');
INSERT INTO `menu`
VALUES (17, '我的导师', 0, 'student', NULL, 'people');
INSERT INTO `menu`
VALUES (18, '可选导师', 17, 'optional', 'student/optional/index', 'peoples');
INSERT INTO `menu`
VALUES (19, '我的导师', 17, 'selected', 'student/selected/index', 'select');
INSERT INTO `menu`
VALUES (20, '已选导师', 17, 'temp', 'student/temp/index', 'textarea');
INSERT INTO `menu`
VALUES (21, '教育实习', -1, 'internship', 'student/internship/index', 'online');
INSERT INTO `menu`
VALUES (22, '实习打分', -1, 'internship', 'teacher/trainee/index', 'online');
INSERT INTO `menu`
VALUES (23, '我的学生', 0, 'teacher', NULL, 'people');
INSERT INTO `menu`
VALUES (24, '我的班级', 23, 'classes', 'teacher/classes/index', 'education');
INSERT INTO `menu`
VALUES (25, '我的小组', 23, 'served', 'teacher/served/index', 'druid');
INSERT INTO `menu`
VALUES (26, '申请列表', 23, 'selected', 'teacher/selected/index', 'dict');
INSERT INTO `menu`
VALUES (27, '我的活动', -1, 'activity', 'teacher/activity/index', 'cascader');
INSERT INTO `menu`
VALUES (28, '我的问题', -1, 'problem', 'teacher/problem/index', 'question');
INSERT INTO `menu`
VALUES (29, '我的问题', -1, 'problem', 'student/problem/index', 'question');

-- ----------------------------
-- 公告表
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`
(
    `id`          int                                                     NOT NULL AUTO_INCREMENT COMMENT '主键',
    `title`       varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '标题',
    `content`     text CHARACTER SET utf8 COLLATE utf8_general_ci         NOT NULL COMMENT '内容',
    `faculty_id`  int                                                     NOT NULL COMMENT '学院编号',
    `scope`       int                                                     NOT NULL COMMENT '可见性\r\n0：学生\r\n1：教师\r\n2：全部',
    `enclosure`   varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公告附件',
    `create_time` datetime                                                NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- 问题表
-- ----------------------------
DROP TABLE IF EXISTS `problem`;
CREATE TABLE `problem`
(
    `id`            int                                                     NOT NULL AUTO_INCREMENT COMMENT '问题的主键',
    `title`         varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '问题的标题',
    `content`       longtext CHARACTER SET utf8 COLLATE utf8_general_ci     NOT NULL COMMENT '问题的内容',
    `teacher_id`    int                                                     NOT NULL COMMENT '回答问题的教师ID',
    `student_id`    int                                                     NOT NULL COMMENT '提出问题的学生ID',
    `create_time`   datetime                                                NOT NULL COMMENT '问题发布的时间',
    `is_solve`      int                                                     NULL DEFAULT 0 COMMENT '0 未解决 1 解决',
    `reply_time`    datetime                                                NULL DEFAULT NULL COMMENT '问题回复时间',
    `reply_content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci     NOT NULL COMMENT '问题回复内容',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- 角色表
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`
(
    `id`         int                                                    NOT NULL COMMENT '角色主键',
    `role_name`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名',
    `role_label` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色描述',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- 初始化角色信息
-- ----------------------------
INSERT INTO `role`
VALUES (1, 'ROLE_ROOT', '超级管理员');
INSERT INTO `role`
VALUES (2, 'ROLE_ADMIN', '学院管理员');
INSERT INTO `role`
VALUES (3, 'ROLE_MENTOR', '导师');
INSERT INTO `role`
VALUES (4, 'ROLE_STUDENT', '学生');
INSERT INTO `role`
VALUES (5, 'ROLE_TEACHER', '普通教师');
INSERT INTO `role`
VALUES (6, 'ROLE_TRAINEE', '实习生');
INSERT INTO `role`
VALUES (7, 'ROLE_TUTOR', '指导教师');

-- ----------------------------
-- 角色与菜单的绑定表
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`
(
    `id`      int NOT NULL AUTO_INCREMENT COMMENT '主键',
    `role_id` int NOT NULL COMMENT '角色ID',
    `menu_id` int NOT NULL COMMENT '菜单ID',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- 初始化角色所对应的菜单
-- ----------------------------
INSERT INTO `role_menu`
VALUES (1, 1, 1);
INSERT INTO `role_menu`
VALUES (2, 1, 2);
INSERT INTO `role_menu`
VALUES (3, 1, 3);
INSERT INTO `role_menu`
VALUES (4, 1, 4);
INSERT INTO `role_menu`
VALUES (5, 1, 5);
INSERT INTO `role_menu`
VALUES (6, 2, 6);
INSERT INTO `role_menu`
VALUES (7, 2, 7);
INSERT INTO `role_menu`
VALUES (8, 2, 8);
INSERT INTO `role_menu`
VALUES (9, 2, 9);
INSERT INTO `role_menu`
VALUES (10, 2, 10);
INSERT INTO `role_menu`
VALUES (11, 4, 11);
INSERT INTO `role_menu`
VALUES (12, 5, 12);
INSERT INTO `role_menu`
VALUES (13, 4, 13);
INSERT INTO `role_menu`
VALUES (14, 4, 14);
INSERT INTO `role_menu`
VALUES (15, 4, 15);
INSERT INTO `role_menu`
VALUES (16, 4, 16);
INSERT INTO `role_menu`
VALUES (17, 4, 17);
INSERT INTO `role_menu`
VALUES (18, 4, 18);
INSERT INTO `role_menu`
VALUES (19, 4, 19);
INSERT INTO `role_menu`
VALUES (20, 4, 20);
INSERT INTO `role_menu`
VALUES (21, 6, 21);
INSERT INTO `role_menu`
VALUES (22, 7, 22);
INSERT INTO `role_menu`
VALUES (23, 3, 23);
INSERT INTO `role_menu`
VALUES (24, 3, 24);
INSERT INTO `role_menu`
VALUES (25, 3, 25);
INSERT INTO `role_menu`
VALUES (26, 3, 26);
INSERT INTO `role_menu`
VALUES (27, 3, 27);
INSERT INTO `role_menu`
VALUES (28, 3, 28);
INSERT INTO `role_menu`
VALUES (29, 4, 29);

-- ----------------------------
-- 角色与用户的绑定表
-- ----------------------------
DROP TABLE IF EXISTS `role_user`;
CREATE TABLE `role_user`
(
    `id`      int NOT NULL AUTO_INCREMENT COMMENT '主键',
    `role_id` int NOT NULL COMMENT '角色ID',
    `user_id` int NOT NULL COMMENT '用户ID',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- 初始化用户的角色绑定
-- ----------------------------
INSERT INTO `role_user`
VALUES (1, 4, 1);
INSERT INTO `role_user`
VALUES (2, 1, 1);

-- ----------------------------
-- 学生表
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`
(
    `id`             int                                                 NOT NULL AUTO_INCREMENT COMMENT '主键',
    `student_number` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学号',
    `class_id`       int                                                 NULL DEFAULT NULL COMMENT '班级编号',
    `user_id`        int                                                 NOT NULL COMMENT '用户信息编号',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- 学生表的信息
-- ----------------------------
INSERT INTO `student`
VALUES (1, '2030070006', 1, 1);

-- ----------------------------
-- 学生与活动的绑定表
-- ----------------------------
DROP TABLE IF EXISTS `student_activity`;
CREATE TABLE `student_activity`
(
    `id`          int NOT NULL AUTO_INCREMENT COMMENT '主键',
    `student_id`  int NOT NULL COMMENT '学生ID',
    `activity_id` int NOT NULL COMMENT '活动ID',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- 学生与其导师的绑定表
-- ----------------------------
DROP TABLE IF EXISTS `student_teacher`;
CREATE TABLE `student_teacher`
(
    `id`         int NOT NULL AUTO_INCREMENT COMMENT '主键',
    `student_id` int NOT NULL COMMENT '学生ID',
    `teacher_id` int NOT NULL COMMENT '教师ID',
    `is_temp`    int NULL DEFAULT 0 COMMENT '0 非临时 1 临时',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- 教师表
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`
(
    `id`             int                                                     NOT NULL AUTO_INCREMENT COMMENT '主键',
    `teacher_number` char(6) CHARACTER SET utf8 COLLATE utf8_general_ci      NOT NULL COMMENT '工号',
    `qq_group`       varchar(9) CHARACTER SET utf8 COLLATE utf8_general_ci   NULL DEFAULT NULL COMMENT 'qq交流群',
    `wechat_group`   varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信交流群图片地址',
    `achievement`    text CHARACTER SET utf8 COLLATE utf8_general_ci         NULL COMMENT '个人成就',
    `field`          text CHARACTER SET utf8 COLLATE utf8_general_ci         NULL COMMENT '主攻领域',
    `user_id`        int                                                     NOT NULL COMMENT '用户信息',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- 导师与其所教班级的绑定表
-- ----------------------------
DROP TABLE IF EXISTS `teacher_class`;
CREATE TABLE `teacher_class`
(
    `id`         int NOT NULL AUTO_INCREMENT COMMENT '主键',
    `class_id`   int NOT NULL COMMENT '班级编号',
    `teacher_id` int NOT NULL COMMENT '教师编号',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- 班级表
-- ----------------------------
DROP TABLE IF EXISTS `the_class`;
CREATE TABLE `the_class`
(
    `id`         int                                                     NOT NULL AUTO_INCREMENT COMMENT '班级主键',
    `class_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '班级名称',
    `grade`      int                                                     NULL DEFAULT NULL COMMENT '年级',
    `faculty_id` int                                                     NOT NULL COMMENT '学院ID',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- 初始化班级信息
-- ----------------------------
INSERT INTO `the_class`
VALUES (1, '化学2001', 2020, 407);
INSERT INTO `the_class`
VALUES (2, '化学2002', 2020, 407);
INSERT INTO `the_class`
VALUES (3, '化学2003', 2020, 407);
INSERT INTO `the_class`
VALUES (4, '化学2004', 2020, 407);
INSERT INTO `the_class`
VALUES (5, '化学2005', 2020, 407);

-- ----------------------------
-- 用户表
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`         int                                                     NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`       varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '姓名',
    `faculty_id` int                                                     NOT NULL COMMENT '学院编号',
    `gender`     tinyint                                                 NULL DEFAULT NULL COMMENT '性别：1男 0女',
    `avatar`     varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
    `phone`      varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL COMMENT '手机号',
    `qq`         varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL COMMENT 'qq号',
    `wechat`     varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL COMMENT '微信号',
    `portrait`   varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '肖像',
    `email`      varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
    `birthday`   timestamp                                               NULL DEFAULT NULL COMMENT '生日',
    `introduce`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个人简介',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- 初始化用户信息
-- ----------------------------
INSERT INTO `user`
VALUES (1, '王帅', 407, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
