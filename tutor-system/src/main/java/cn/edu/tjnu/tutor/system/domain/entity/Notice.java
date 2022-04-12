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
import java.time.LocalDateTime;

/**
 * 公告信息。
 *
 * @author 王帅
 * @since 1.0
 */
@Data
@TableName("sys_notice")
public class Notice implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 公告主键。
     */
    @TableId(type = IdType.AUTO)
    private Integer noticeId;

    /**
     * 公告所属学院。
     */
    private Integer collegeId;

    /**
     * 公告类型（0面向老师，1面向学生）。
     */
    private Integer noticeType;

    /**
     * 公告范围（0普通公告，1管理员公告）。
     */
    private Integer noticeScope;

    /**
     * 公告标题。
     */
    private String noticeTitle;

    /**
     * 公告内容。
     */
    private String noticeContent;

    /**
     * 发布人。
     */
    private String createBy;

    /**
     * 发布时间。
     */
    private LocalDateTime createTime;

    /**
     * 更新人。
     */
    private String updateBy;

    /**
     * 更新时间。
     */
    private LocalDateTime updateTime;

}