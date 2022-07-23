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

import cn.edu.tjnu.tutor.common.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * （教育实践）文档拆分之后的模板信息。
 *
 * @author 王帅
 * @since 2.0
 */
@Data
@TableName("gen_template")
public class Template extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 模板主键。
     */
    @TableId
    private Integer tmplId;

    /**
     * 所属手册主键。
     */
    private Integer docId;

    /**
     * 模板名称。
     */
    private String tmplName;

    /**
     * 模板排列顺序。
     */
    private Integer tmplOrder;

    /**
     * 模板文件URL。
     */
    private String tmplUrl;

    /**
     * 是否只读（0 否 1 是）。
     */
    private Integer readOnly;

    /**
     * 创建时间。
     */
    private LocalDateTime createTime;

    /**
     * 更新时间。
     */
    private LocalDateTime updateTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Template) {
            Template other = (Template) o;
            return Objects.equals(this.tmplId, other.tmplId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.tmplId);
    }

}