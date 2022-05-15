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
import cn.edu.tjnu.tutor.common.core.domain.ExcelResult;
import cn.edu.tjnu.tutor.common.core.service.ExcelDataService;
import cn.edu.tjnu.tutor.common.enums.ExceptionType;
import cn.edu.tjnu.tutor.common.event.ExcelReadListener;
import cn.edu.tjnu.tutor.common.exception.ServiceException;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
     * @param file    上传的 Excel 文件
     * @param service Excel 数据服务
     * @param <T>     数据类型
     * @return Excel 解析结果
     */
    public static <T> ExcelResult readExcel(MultipartFile file, ExcelDataService<T> service) {
        ExcelReadListener<T> readListener = new ExcelReadListener<>(service);
        try {
            EasyExcel.read(file.getInputStream(), service.getExcelHead(), readListener)
                    .autoCloseStream(false)
                    .sheet()
                    .doRead();
        } catch (IOException e) {
            throw new ServiceException(e, ExceptionType.EXCEL_FILE_OPEN_FAILED, file.getOriginalFilename());
        }
        return readListener.getResult();
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
    private static <T> void writeExcel(HttpServletResponse response, String fileName, Class<T> head, Collection<T> data) {
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
     * 向流中写入 Excel 文件。
     *
     * @param response 需要写入的流
     * @param fileName 文件名称
     * @param service  Excel 数据服务
     * @param <T>      数据类型
     */
    public static <T> void writeExcel(HttpServletResponse response, String fileName, ExcelDataService<T> service) {
        writeExcel(response, fileName, service.getExcelHead(), service.getExcelData());
    }

    /**
     * 向流中写入 Excel 模板。
     *
     * @param response 需要写入的流
     * @param fileName 文件名称
     * @param service  Excel 数据服务
     * @param <T>      数据类型
     */
    public static <T> void writeTemplate(HttpServletResponse response, String fileName, ExcelDataService<T> service) {
        writeExcel(response, fileName, service.getExcelHead(), new ArrayList<>());
    }

}