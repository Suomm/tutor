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

/**
 * 导师团信息。
 *
 * @author 王帅
 * @since 2.0
 */
@Data
@TableName("sys_team")
public class Team implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 导师团主键。
     */
    @TableId(type = IdType.AUTO)
    private Integer teamId;

    /**
     * 所属学院主键。
     */
    private Integer collegeId;

    /**
     * 导师团名称。
     */
    private String teamName;

    /**
     * 导师团介绍信息。
     */
    private String introduce;

}