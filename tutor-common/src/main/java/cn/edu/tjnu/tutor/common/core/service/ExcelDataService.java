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

package cn.edu.tjnu.tutor.common.core.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Excel 数据服务。
 *
 * @param <T> 数据类型
 * @author 王帅
 * @see cn.edu.tjnu.tutor.common.util.ExcelUtils
 * @see cn.edu.tjnu.tutor.common.event.ExcelReadListener
 * @since 2.0
 */
public interface ExcelDataService<T> {

    /**
     * 获取 Excel 文档头部标题信息。
     *
     * @return 标题信息
     * @apiNote 所返回类的属性需要包含 {@link com.alibaba.excel.annotation.ExcelProperty}
     * 注解，以便 EasyExcel 解析到 Excel 文档的头部信息。
     */
    Class<T> getExcelHead();

    /**
     * 获取 Excel 填充数据。
     *
     * @return 数据集合
     * @apiNote 该方法没有做数据的筛选，而是直接返回符合要求的所有数据。
     * @implSpec 对于 Excel 数据服务，该默认实现为：
     * <pre>{@code
     * return new ArrayList<>();
     * }</pre>
     */
    default List<T> getExcelData() {
        return new ArrayList<>();
    }

    /**
     * 保存从 Excel 解析到的数据。
     *
     * @param vo        视图对象
     * @param cachedMap 缓存数据
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    boolean saveExcelData(T vo, Map<Object, Object> cachedMap);

}