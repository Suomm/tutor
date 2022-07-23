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

import cn.edu.tjnu.tutor.common.validation.constraints.UserCode;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 用户信息。
 *
 * @author 王帅
 * @since 2.0
 */
@Data
public class UserVO implements Serializable {

    private static final long serialVersionUID = 7516817215395798067L;

    /**
     * 用户主键。
     *
     * @mock 1
     */
    @ExcelIgnore
    private Integer userId;

    /**
     * 所属学院主键。
     *
     * @mock 1
     */
    @ExcelIgnore
    private Integer collegeId;

    /**
     * 用户编号（学号或工号）。
     *
     * @mock 2040050143
     */
    @NotNull
    @UserCode
    @ExcelProperty("用户编号")
    private String userCode;

    /**
     * 用户名称。
     *
     * @mock 王帅
     */
    @NotNull
    @Length(max = 50)
    @ExcelProperty("用户名称")
    private String userName;

    /**
     * 所属学院名称。
     *
     * @mock 化学学院
     */
    @NotNull
    @ExcelProperty("所属学院")
    private String collegeName;

}