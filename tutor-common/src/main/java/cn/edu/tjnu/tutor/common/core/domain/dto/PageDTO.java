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

package cn.edu.tjnu.tutor.common.core.domain.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 分页查询对象。
 *
 * @author 王帅
 * @since 2.0
 */
@Data
public class PageDTO implements Serializable {

    private static final long serialVersionUID = 1325019744536935240L;

    /**
     * 当前页码。
     */
    @NotNull
    private Integer pageNum;

    /**
     * 每页显示数量。
     */
    @NotNull
    private Integer pageSize;

    /**
     * 生成 MyBatis Plus 分页对象。
     *
     * @return 分页对象
     */
    public <T> Page<T> page() {
        return Page.of(pageNum, pageSize);
    }

    /**
     * 生成 Elasticsearch 分页对象。
     *
     * @return 分页对象
     */
    public Pageable pageable() {
        return PageRequest.of(pageNum, pageSize);
    }

}