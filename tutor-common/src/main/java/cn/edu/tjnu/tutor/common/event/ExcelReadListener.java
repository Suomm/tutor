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
import cn.edu.tjnu.tutor.common.util.ValidUtils;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.baomidou.mybatisplus.extension.service.IService;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 默认的 Excel 数据读取监听器。
 *
 * @author 王帅
 * @since 2.0
 */
public class ExcelReadListener<T> implements ReadListener<T> {

    /**
     * 批处理数量，达到这个数量就去插入数据库。
     */
    private static final int BATCH_SIZE = 100;

    /**
     * Service 层引用，用于保存解析成功的数据。
     */
    private final IService<T> service;

    /**
     * 解析成功的数据存放集合，达到一定数量批量插入数据库。
     */
    private final Collection<T> dataList;

    /**
     * Excel 解析结果保存。
     */
    @Getter
    private final ExcelResult result;

    /**
     * 初始化 Excel 数据读取监听器。
     *
     * @param service 服务层对象
     */
    public ExcelReadListener(IService<T> service) {
        this.service = service;
        this.dataList = new ArrayList<>();
        this.result = new ExcelResult();
    }

    @Override
    public void invoke(T data, AnalysisContext context) {
        ValidUtils.validate(data);
        dataList.add(data);
        if (dataList.size() >= BATCH_SIZE) {
            saveData();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveData();
    }

    @Override
    public void onException(Exception exception, AnalysisContext context) {
        // 标记错误行号，这里不抛出异常，继续读取下一行数据
        result.setErrorRow(context.readRowHolder().getRowIndex() + 1);
    }

    /**
     * 保存数据、记录结果并清理数据缓存。
     */
    private void saveData() {
        service.saveBatch(dataList);
        result.addTotal(dataList.size());
        dataList.clear();
    }

}