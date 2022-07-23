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

import java.util.Objects;

/**
 * （教育实践）文档信息。
 *
 * @author 王帅
 * @since 2.0
 */
@Data
@TableName("gen_document")
public class Document extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 文档主键。
     */
    @TableId
    private Integer docId;

    /**
     * 文档名称。
     */
    private String docName;

    /**
     * 文档样例URL。
     */
    private String docUrl;

    /**
     * 文件类型（文件后缀名）。
     */
    private String docType;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Document) {
            Document other = (Document) o;
            return Objects.equals(this.docId, other.docId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.docId);
    }

}