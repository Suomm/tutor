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
import java.time.LocalDate;
import java.util.Objects;

/**
 * 教育实习基本信息。
 *
 * @author 王帅
 * @since 2.0
 */
@Data
@TableName("prac_intern_info")
public class InternInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 实习信息主键。
     */
    @TableId(type = IdType.AUTO)
    private Integer infoId;

    /**
     * 实习生主键。
     */
    private Integer userId;

    /**
     * 实习类型：0 集中实习 1 自主实习。
     */
    private Integer type;

    /**
     * 实习学科。
     */
    private String subject;

    /**
     * 实习基地名称。
     */
    private String schoolName;

    /**
     * 基地实习导师姓名。
     */
    private String teacherName;

    /**
     * 实习年级。
     */
    private String grade;

    /**
     * 实习开始时间。
     */
    private LocalDate startDate;

    /**
     * 实习结束时间。
     */
    private LocalDate endDate;

    /**
     * 听课记录中学指导老师评分。
     */
    private Integer tkpfSchool;

    /**
     * 听课记录高校指导老师评分。
     */
    private Integer tkpfCollege;

    /**
     * 教案设计中学指导老师评分。
     */
    private Integer japfSchool;

    /**
     * 教案设计高校指导老师评分。
     */
    private Integer japfCollege;

    /**
     * 教研活动中学指导老师评分。
     */
    private Integer jypfSchool;

    /**
     * 教研活动高校指导老师评分。
     */
    private Integer jypfCollege;

    /**
     * 师德表现中学指导老师评分。
     */
    private Integer sdpfSchool;

    /**
     * 师德表现高校指导老师评分。
     */
    private Integer sdpfCollege;

    /**
     * 教育实习个人总结。
     */
    private String personalSummary;

    /**
     * 教育实习学生小组评价。
     */
    private String groupEvaluation;

    /**
     * 教育实习学生小组互评分数。
     */
    private Integer groupScore;

    /**
     * 教育实习中学指导教师评语。
     */
    private String sxpySchool;

    /**
     * 教育实习高校指导教师评语。
     */
    private String sxpyCollege;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof InternInfo) {
            InternInfo other = (InternInfo) o;
            return Objects.equals(this.infoId, other.infoId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.infoId);
    }

}