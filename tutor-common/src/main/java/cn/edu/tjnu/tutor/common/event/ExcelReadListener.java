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

package cn.edu.tjnu.tutor.common.event;

import cn.edu.tjnu.tutor.common.core.domain.ExcelResult;
import cn.edu.tjnu.tutor.common.core.service.ExcelDataService;
import cn.edu.tjnu.tutor.common.util.ValidUtils;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * 默认的 Excel 数据读取监听器。
 *
 * @author 王帅
 * @since 2.0
 */
public class ExcelReadListener<T> implements ReadListener<T> {

    /**
     * Excel 解析结果保存。
     */
    @Getter
    private final ExcelResult result;

    /**
     * Excel 解析服务引用，用于保存解析成功的数据。
     */
    private final ExcelDataService<T> service;

    /**
     * 查询缓存数据存。
     */
    private final Map<Object, Object> cachedMap;

    /**
     * 初始化 Excel 数据读取监听器。
     *
     * @param service 服务层对象
     */
    public ExcelReadListener(ExcelDataService<T> service) {
        this.service = service;
        this.result = new ExcelResult();
        this.cachedMap = new HashMap<>();
    }

    @Override
    public final void invoke(T data, AnalysisContext context) {
        ValidUtils.validate(data);
        if (service.saveExcelData(data, cachedMap)) {
            result.increment();
        } else {
            setError(context);
        }
    }

    @Override
    public final void doAfterAllAnalysed(AnalysisContext context) {
        cachedMap.clear();
    }

    @Override
    public final void onException(Exception exception, AnalysisContext context) {
        setError(context);
    }

    /**
     * 标记解析失败行号。
     */
    private void setError(AnalysisContext context) {
        result.setErrorRow(context.readRowHolder().getRowIndex() + 1);
    }

}