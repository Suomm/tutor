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

package cn.edu.tjnu.tutor.system.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * （学生）用户与生成的（教育实践）文档关联。
 *
 * @author 王帅
 * @since 2.0
 */
@Data
@TableName("gen_user_doc")
public class UserDoc implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户主键。
     */
    @TableId
    private Integer userId;

    /**
     * 文档主键。
     */
    private Integer docId;

    /**
     * 生成的文档URL。
     */
    private String docUrl;

    /**
     * 创建时间。
     */
    private LocalDateTime createTime;

    /**
     * 更新时间。
     */
    private LocalDateTime updateTime;

}