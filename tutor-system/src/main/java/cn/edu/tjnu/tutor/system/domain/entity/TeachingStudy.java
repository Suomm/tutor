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
 * 教研活动记录信息。
 *
 * @author 王帅
 * @since 2.0
 */
@Data
@TableName("prac_teaching_study")
public class TeachingStudy implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 教研活动主键。
     */
    @TableId(type = IdType.AUTO)
    private Integer studyId;

    /**
     * 实习生主键。
     */
    private Integer userId;

    /**
     * 教研内容。
     */
    private String content;

    /**
     * 教研方式。
     */
    private String type;

    /**
     * 教研过程。
     */
    private String teachingProcess;

    /**
     * 教研思路。
     */
    private String designIdea;

    /**
     * 个人反思与总结。
     */
    private String personalSummary;

    /**
     * 小组评价。
     */
    private String groupEvaluation;

    /**
     * 教研日期。
     */
    private LocalDate startDate;

    /**
     * 上传的文件链接地址。
     */
    private String docLink;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof TeachingStudy) {
            TeachingStudy other = (TeachingStudy) o;
            return Objects.equals(this.studyId, other.studyId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.studyId);
    }

}