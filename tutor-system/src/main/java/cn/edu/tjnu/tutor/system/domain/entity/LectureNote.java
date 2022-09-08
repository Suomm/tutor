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
 * 实习听课记录信息。
 *
 * @author 王帅
 * @since 2.0
 */
@Data
@TableName("prac_lecture_note")
public class LectureNote implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 实习听课记录主键。
     */
    @TableId(type = IdType.AUTO)
    private Integer noteId;

    /**
     * 实习生主键。
     */
    private Integer userId;

    /**
     * 听课内容。
     */
    private String lectureContent;

    /**
     * 课程类型。
     */
    private String lectureType;

    /**
     * 任课教师。
     */
    private String teacherName;

    /**
     * 听课班级。
     */
    private String className;

    /**
     * 教学过程。
     */
    private String teachingProcess;

    /**
     * 听课体会及建议。
     */
    private String experience;

    /**
     * 个人反思与总结。
     */
    private String personalSummary;

    /**
     * 小组评价。
     */
    private String groupEvaluation;

    /**
     * 听课开始日期。
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
        if (o instanceof LectureNote) {
            LectureNote other = (LectureNote) o;
            return Objects.equals(this.noteId, other.noteId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.noteId);
    }

}