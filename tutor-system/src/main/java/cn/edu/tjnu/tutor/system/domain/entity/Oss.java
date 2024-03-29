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
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Objects;

/**
 * 对象存储信息。
 *
 * @author 王帅
 * @since 2.0
 */
@Data
@TableName("sys_oss")
public class Oss extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 对象存储主键。
     */
    @TableId(type = IdType.AUTO)
    private Integer ossId;

    /**
     * 文件名。
     */
    private String fileName;

    /**
     * 原名。
     */
    private String originalName;

    /**
     * 文件后缀名。
     */
    private String fileSuffix;

    /**
     * URL地址。
     */
    private String url;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Oss) {
            Oss other = (Oss) o;
            return Objects.equals(this.ossId, other.ossId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.ossId);
    }

}