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

package cn.edu.tjnu.tutor.system.domain.extra;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * （学生）用户完成（教育实践）文档模板的信息。
 *
 * @author 王帅
 * @since 2.0
 */
@Data
@TableName("gen_user_tmpl")
public class UserTmpl implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户主键。
     */
    @TableId
    private Integer userId;

    /**
     * 文档模板主键。
     */
    private Integer tmplId;

    /**
     * 文档模板完成之后，文件上传URL。
     */
    private String docUrl;

    /**
     * 上传时间。
     */
    private LocalDateTime createTime;

}