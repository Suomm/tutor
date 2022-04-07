<p align="center">
  <img width="320" src="https://suomm.gitee.io/tutor/images/logo.svg" alt="logo"/>
</p>
<p align="center">
    <a href="https://developer.ibm.com/languages/java/semeru-runtimes/#">
        <img src="https://img.shields.io/badge/JDK-11.0.13-brightgreen.svg" alt="JDK">
    </a>
    <a href="https://maven.apache.org/">
        <img src="https://img.shields.io/badge/Maven-3.8.1-brightgreen.svg" alt="maven">
    </a>
    <a href="https://spring.io/projects/spring-boot">
        <img src="https://img.shields.io/badge/Spring%20Boot-2.3.12.RELEASE-brightgreen.svg" alt="spring-boot">
    </a>
    <a href="https://spring.io/projects/spring-cloud">
        <img src="https://img.shields.io/badge/Spring%20Cloud-Hoxton.SR9-brightgreen.svg" alt="spring-cloud">
    </a>
    <a href="https://spring.io/projects/spring-cloud-alibaba">
        <img src="https://img.shields.io/badge/Spring%20Cloud%20Alibaba-2.2.6.RELEASE-brightgreen.svg" alt="spring-cloud-alibaba">
    </a>
    <a href="https://www.apache.org/licenses/LICENSE-2.0">
        <img src="https://img.shields.io/badge/Licenes-Apache%20License%202.0-important.svg" alt="license">
    </a>
    <a href="https://suomm.gitee.io/tutor">
        <img src="https://img.shields.io/badge/Release-2.0--RC-blueviolet.svg" alt="release">
    </a>
</p>

## 平台简介

`天津师范大学师范生学业指导系统`是围绕立德树人根本任务，基于师范生学业导师制而搭建的师生互动与交流平台。旨在满足师范生学业发展需求，解决大学期间学业疑惑，记录学业成长历程。同时，有助于导师实现学业指导活动计划、实施与效果的记录与管理，能更好地统筹并发布面向所有师范生的集体或个别导学活动，便于及时分析师范生学情，梳理育人过程中遇到的问题，促进导师交流，不断提升师范生育人质量。

## 系统模块

```
tutor
├─.gitee                         // Gitee 相关模板管理
├─conf                           // Nacos 配置信息备份
├─docs                           // 相关文档信息
├─script                         // 用于构建的脚本命令
│ ├─bin                          // 自动化本地构建命令
│ ├─docker                       // Docker 镜像文件
│ └─sql                          // 数据库 Sql 文件
├─tutor-admin                    // 监控管理模块
├─tutor-common                   // 公共接口模块
├─tutor-framework                // 核心框架模块
├─tutor-gateway                  // 网关服务模块
├─tutor-support                  // 服务支持模块
├─tutor-system                   // 系统代码模块
├─.gitignore                     // 版本控制相关配置
├─Jenkinsfile                    // 自动化构建部署脚本
├─LICENSE                        // 开源许可文件
├─pom.xml                        // Maven 父模块依赖管理
└─README.md                      // 项目介绍文件
```

## 系统架构

![架构图](https://suomm.gitee.io/tutor/images/tutor.jpg)

## 内置功能

- 导师团指导模式：建立完善的梯队结构，定期举行学术交流活动。

- 导师小组指导模式：导师针对学生的个性差异，因材施教，指导学生的思想、学习与生活，为学生的个性发展提供导航。

- 教育实践双导师指导模式：创新双导师线上指导模式，提高教学的质量和效率，丰富教育实践课程内容。

## 加入我们

您如果对这个项目感兴趣，请不要吝啬您的`Star`，或者您力有所及，可以选择加入到我们的开发之中，详细文档 [在这里](https://suomm.gitee.io/tutor) 。

## 相关项目

> 天津师范大学师范生学业指导系统系列项目

| 项目名称             | 项目介绍   | 项目地址                                   |
|------------------|--------|----------------------------------------|
| `tutor`          | 系统后台模块 | https://gitee.com/Suomm/tutor          |
| `tutor-ui`       | 用户使用界面 | https://gitee.com/Suomm/tutor-ui       |
| `tutor-admin-ui` | 运维使用界面 | https://gitee.com/Suomm/tutor-admin-ui |