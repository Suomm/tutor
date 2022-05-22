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

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/**
 * 学院信息。
 *
 * @author 王帅
 * @since 2.0
 */
@Data
public class CollegeVO implements Serializable {

    private static final long serialVersionUID = -1614808098749200598L;

    /**
     * 学院主键。
     *
     * @mock 1
     */
    @ExcelIgnore
    @JsonInclude(NON_NULL)
    private Integer collegeId;

    /**
     * 学院编码。
     *
     * @mock 413
     */
    @NotNull
    @JsonInclude(NON_NULL)
    @ExcelProperty("学院编码")
    private Integer collegeCode;

    /**
     * 学院名称。
     *
     * @mock 化学学院
     */
    @NotNull
    @Length(max = 50)
    @ExcelProperty("学院名称")
    private String collegeName;

    /**
     * 学院负责人。
     *
     * @mock 负责人
     */
    @NotNull
    @Length(max = 50)
    @ExcelProperty("学院负责人")
    private String leader;

    /**
     * 学院电话。
     *
     * @mock 13000000001
     */
    @NotNull
    @Length(max = 11)
    @ExcelProperty("学院电话")
    private String phone;

    /**
     * 学院邮箱。
     *
     * @mock chemistry@email.com
     */
    @Email
    @NotNull
    @ExcelProperty("学院邮箱")
    private String email;

}