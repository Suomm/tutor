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
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 活动信息。
 *
 * @author 王帅
 * @since 2.0
 */
@Data
public class ActivityVO implements Serializable {

    private static final long serialVersionUID = -4030768379811375641L;

    /**
     * 活动主键。
     */
    @ExcelIgnore
    private String activityId;

    /**
     * 发布者姓名。
     *
     * @mock 王帅
     */
    @ExcelProperty("发布人")
    private String publisher;

    /**
     * 活动发布者所属学院名称。
     *
     * @mock 化学学院
     */
    @ExcelProperty("所属学院")
    private String collegeName;

    /**
     * 活动标题。
     *
     * @mock 这是第一个活动
     */
    @ExcelProperty("活动标题")
    private String title;

    /**
     * 活动详细信息。
     *
     * @mock 这次的活动具有非凡的意义
     */
    @ExcelProperty("活动内容")
    private String content;

    /**
     * 活动发布时间。
     *
     * @mock 2022-03-01
     */
    @ExcelProperty("活动发布时间")
    private LocalDateTime createTime;

    /**
     * 活动开始时间。
     *
     * @mock 2022-04-01
     */
    @ExcelProperty("活动开始时间")
    private LocalDateTime startTime;

    /**
     * 活动结束时间。
     *
     * @mock 2022-05-01
     */
    @ExcelProperty("活动结束时间")
    private LocalDateTime endTime;

    /**
     * 活动所在小组主键（0 表示公开的活动）。
     *
     * @mock 1
     */
    @ExcelIgnore
    private Integer groupId;

    /**
     * 活动所在小组名称。
     *
     * @mock 默认小组
     */
    @ExcelProperty("所在小组名称")
    private Integer groupName;

    /**
     * 相关文件链接。
     */
    @ExcelIgnore
    private List<String> fileLinks;

}