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

package cn.edu.tjnu.tutor.common.core.domain.view;

import cn.edu.tjnu.tutor.common.core.domain.query.PageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 统一分页返回对象。
 *
 * @param <T> 数据类型
 * @author 王帅
 * @since 2.0
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PageVO<T> extends PageQuery {

    private static final long serialVersionUID = -553124397223609762L;

    /**
     * 当前页的数据量。
     *
     * @mock 1
     */
    private Long total;

    /**
     * 每页的数据内容。
     */
    private List<T> content;

    /**
     * 获取分页数据的总页数。
     *
     * @return 总页数
     */
    public int totalPages() {
        return pageSize == 0 ? 1 : (int) Math.ceil((double) total / (double) pageSize);
    }

    /**
     * 判断是下一页是否还有数据。
     *
     * @return {@code true} 下一页有数据，{@code false} 下一页没有数据
     */
    public boolean hasNext() {
        return pageNum + 1 < totalPages();
    }

}