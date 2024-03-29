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

package cn.edu.tjnu.tutor.common.util;

import cn.easyes.core.biz.PageInfo;
import cn.edu.tjnu.tutor.common.core.domain.view.PageVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 分页工具类。
 *
 * @author 王帅
 * @since 2.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PageUtils {

    /**
     * 获取一个空的分页对象。
     *
     * @param <T> 数据类型
     * @return 空分页对象
     */
    public static <T> PageVO<T> empty() {
        PageVO<T> pageVO = new PageVO<>();
        pageVO.setPageNum(1);
        pageVO.setPageSize(0);
        pageVO.setTotal(0L);
        pageVO.setContent(Collections.emptyList());
        return pageVO;
    }

    /**
     * 将 Elasticsearch 分页对象转换为 {@link PageVO} 对象。
     *
     * @param page 分页对象
     * @param <T>  数据类型
     * @return 分页数据
     */
    public static <T> PageVO<T> convert(PageInfo<T> page) {
        PageVO<T> pageVO = new PageVO<>();
        pageVO.setPageNum(page.getPageNum());
        pageVO.setPageSize(page.getPages());
        pageVO.setTotal(page.getTotal());
        pageVO.setContent(page.getList());
        return pageVO;
    }

    /**
     * 将 Elasticsearch 分页对象转换为 PageVO 对象。
     *
     * @param page 分页对象
     * @param func 数据类型转换函数
     * @param <E>  Entity 类型
     * @param <V>  VO 类型
     * @return 分页数据
     */
    public static <E, V> PageVO<V> convert(PageInfo<E> page, Function<E, V> func) {
        PageVO<V> pageVO = new PageVO<>();
        pageVO.setPageNum(page.getPageNum());
        pageVO.setPageSize(page.getPages());
        pageVO.setTotal(page.getTotal());
        pageVO.setContent(page.getList()
                .stream()
                .map(func)
                .collect(Collectors.toList()));
        return pageVO;
    }

    /**
     * 将 MyBatis Plus 分页对象转换为 {@link PageVO} 对象。
     *
     * @param page 分页对象
     * @param <T>  数据类型
     * @return 分页数据
     */
    public static <T> PageVO<T> convert(IPage<T> page) {
        PageVO<T> pageVO = new PageVO<>();
        pageVO.setPageNum((int) page.getCurrent());
        pageVO.setPageSize((int) page.getSize());
        pageVO.setTotal(page.getTotal());
        pageVO.setContent(page.getRecords());
        return pageVO;
    }

}