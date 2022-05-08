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

import cn.edu.tjnu.tutor.common.constant.GlobalConst;
import cn.edu.tjnu.tutor.common.enums.ExceptionType;
import cn.edu.tjnu.tutor.common.exception.ServiceException;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Excel 工具类。
 *
 * @author 王帅
 * @since 2.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExcelUtils {

    /**
     * 从 Excel 中读取数据，并写入到数据库中。
     *
     * @param in   需要读取的流
     * @param head 数据类
     * @param <T>  数据类型
     */
    public static <T> void readExcel(InputStream in, Class<T> head, ReadListener<T> readListener) {
        EasyExcel.read(in, head, readListener)
                .autoCloseStream(false)
                .sheet()
                .doRead();
    }

    /**
     * 向流中写入 Excel 文件。
     *
     * @param response 需要写入的流
     * @param fileName 文件名称
     * @param head     数据类
     * @param data     数据
     * @param <T>      数据类型
     */
    public static <T> void writeExcel(HttpServletResponse response, String fileName, Class<T> head, Collection<T> data) {
        try {
            String name = URLEncoder.encode(fileName, GlobalConst.UTF_8);
            response.addHeader("Content-Disposition", "attachment;filename=" + name + ".xlsx");
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            EasyExcel.write(response.getOutputStream(), head)
                    .autoCloseStream(false)
                    .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                    .sheet()
                    .doWrite(data);
        } catch (Exception e) {
            throw new ServiceException(e, ExceptionType.EXCEL_EXPORT_FAILED, fileName);
        }
    }

    /**
     * 向流中写入 Excel 模板。
     *
     * @param response 需要写入的流
     * @param fileName 文件名称
     * @param head     数据类
     * @param <T>      数据类型
     */
    public static <T> void writeTemplate(HttpServletResponse response, String fileName, Class<T> head) {
        writeExcel(response, fileName, head, new ArrayList<>());
    }

}