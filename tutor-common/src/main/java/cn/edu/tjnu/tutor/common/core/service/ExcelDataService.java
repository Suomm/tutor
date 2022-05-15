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

import java.util.List;
import java.util.Map;

/**
 * Excel 数据服务。
 *
 * @param <T> 数据类型
 * @author 王帅
 * @since 2.0
 */
public interface ExcelDataService<T> {

    /**
     * 获取 Excel 文档头部标题信息。
     *
     * @return 标题信息
     */
    Class<T> getExcelHead();

    /**
     * 获取 Excel 填充数据。
     *
     * @return 数据集合
     */
    List<T> getExcelData();

    /**
     * 保存从 Excel 解析到的数据。
     *
     * @param vo 视图数据
     * @param cachedMap 缓存数据
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    boolean saveExcelData(T vo, Map<Object, Object> cachedMap);

}