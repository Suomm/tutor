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

package cn.edu.tjnu.tutor.system.domain.view;

import cn.edu.tjnu.tutor.common.validation.constraints.ClassName;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 班级信息。
 *
 * @author 王帅
 * @since 2.0
 */
@Data
public class ClassVO implements Serializable {

    private static final long serialVersionUID = 4735551938253218880L;

    /**
     * 班级主键。
     *
     * @mock 1
     */
    @ExcelIgnore
    private Integer classId;

    /**
     * 所属学院名称。
     *
     * @mock 化学学院
     */
    @NotNull
    @Length(max = 50)
    @ExcelProperty("所属学院")
    private String collegeName;

    /**
     * 所属专业名称。
     *
     * @mock 化学（师范）
     */
    @NotNull
    @Length(max = 50)
    @ExcelProperty("所属专业")
    private String majorName;

    /**
     * 班级名称。
     *
     * @mock 化学2001
     */
    @NotNull
    @ClassName
    @ExcelProperty("班级名称")
    private String className;

    /**
     * 所属年级。
     *
     * @mock 20
     */
    @ExcelIgnore
    private String grade;

}