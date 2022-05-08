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

package cn.edu.tjnu.tutor.common.core.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Excel 解析结果。
 *
 * @author 王帅
 * @since 2.0
 */
@Getter
public final class ExcelResult {

    /**
     * 错误行号信息。
     */
    private final Collection<Integer> errorRows;
    /**
     * 解析成功数据量。
     */
    private int total;

    /**
     * 初始化 Excel 解析结果。
     */
    public ExcelResult() {
        errorRows = new ArrayList<>();
    }

    /**
     * 增加解析成功数据数量。
     *
     * @param size 解析成功数量
     */
    public void addTotal(int size) {
        total += size;
    }

    /**
     * 设置解析失败行号。
     *
     * @param row 行号
     */
    public void setErrorRow(Integer row) {
        errorRows.add(row);
    }

}