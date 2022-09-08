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

package cn.edu.tjnu.tutor.system.domain.dto;

import cn.edu.tjnu.tutor.common.validation.groups.Insert;
import cn.edu.tjnu.tutor.common.validation.groups.Update;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 活动信息数据传输对象。
 *
 * <p>插入活动信息时：
 * <blockquote><pre>
 *     {
 *         "title": "这是第一个活动",
 *         "content": "这次的活动具有非凡的意义",
 *         "startTime": "2022-04-01",
 *         "endTime": "2022-05-01",
 *         "groupId": 1,
 *         "fileLinks": ["https://localhost/files/6879881"]
 *     }
 * </pre></blockquote>
 *
 * <p>更新活动信息时：
 * <blockquote><pre>
 *     {
 *         "activityId": "oeLbA4MBq8gxIEWz6",
 *         "content": "这次的活动具有非凡的意义",
 *         "startTime": "2022-04-01",
 *         "endTime": "2022-05-01",
 *         "fileLinks": ["https://localhost/files/6879881"]
 *     }
 * </pre></blockquote>
 *
 * @author 王帅
 * @since 2.0
 */
@Data
public class ActivityDTO implements Serializable {

    private static final long serialVersionUID = 7049545958811799262L;

    /**
     * 活动主键。
     *
     * @mock 1
     */
    @Null(groups = Insert.class)
    @NotNull(groups = Update.class)
    private String activityId;

    /**
     * 活动标题。
     *
     * @mock 这是第一个活动
     */
    @Null(groups = Update.class)
    @NotBlank(groups = Insert.class)
    private String title;

    /**
     * 活动详细信息。
     *
     * @mock 这次的活动具有非凡的意义
     */
    @NotBlank(groups = Insert.class)
    private String content;

    /**
     * 活动开始时间。
     *
     * @mock 2022-04-01
     */
    @NotNull(groups = Insert.class)
    private LocalDateTime startTime;

    /**
     * 活动结束时间。
     *
     * @mock 2022-05-01
     */
    @NotNull(groups = Insert.class)
    private LocalDateTime endTime;

    /**
     * 活动小组主键（0 表示公开的活动）。
     *
     * @mock 1
     */
    @Range
    @Null(groups = Update.class)
    private Integer groupId;

    /**
     * 相关文件链接。
     *
     * @mock ["https://localhost/files/6879881"]
     */
    private List<String> fileLinks;

}