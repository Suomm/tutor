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

package cn.edu.tjnu.tutor.system.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * 班主任实习工作信息。
 *
 * @author 王帅
 * @since 2.0
 */
@Data
@TableName("prac_leader_work")
public class LeaderWork implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 班主任实习工作记录主键。
     */
    @TableId(type = IdType.AUTO)
    private Integer workId;

    /**
     * 实习生主键。
     */
    private Integer userId;

    /**
     * 实习班级。
     */
    private String className;

    /**
     * 班级学生人数。
     */
    private Integer studentAmount;

    /**
     * 基本情况。
     */
    private String baseInfo;

    /**
     * 日常工作。
     */
    private String dailyWork;

    /**
     * 班主任工作计划。
     */
    private String workPlan;

    /**
     * 班级综合育人课外活动设计实施方案。
     */
    private String educationPlan;

    /**
     * 独立开展班级活动记录。
     */
    private String classActivity;

    /**
     * 上传的文件链接地址。
     */
    private String docLink;

    /**
     * 中学指导教师评分。
     */
    private Integer markSchool;

    /**
     * 高校指导教师评分。
     */
    private Integer markCollege;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof LeaderWork) {
            LeaderWork other = (LeaderWork) o;
            return Objects.equals(this.workId, other.workId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.workId);
    }

}