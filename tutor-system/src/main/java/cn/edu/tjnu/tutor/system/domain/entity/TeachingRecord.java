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
 * 课堂教学成绩信息。
 *
 * @author 王帅
 * @since 2.0
 */
@Data
@TableName("prac_teaching_record")
public class TeachingRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 课堂教学记录主键。
     */
    @TableId(type = IdType.AUTO)
    private Integer recordId;

    /**
     * 实习生主键。
     */
    private Integer userId;

    /**
     * 试讲时间。
     */
    private LocalDate lessonDate;

    /**
     * 试讲地点。
     */
    private String place;

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
        if (o instanceof TeachingRecord) {
            TeachingRecord other = (TeachingRecord) o;
            return Objects.equals(this.recordId, other.recordId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.recordId);
    }

}