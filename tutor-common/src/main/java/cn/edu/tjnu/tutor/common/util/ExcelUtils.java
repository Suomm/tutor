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
import cn.edu.tjnu.tutor.common.core.domain.query.PageQuery;
import cn.edu.tjnu.tutor.common.core.domain.view.PageVO;
import cn.edu.tjnu.tutor.common.core.service.ExcelDataService;
import cn.edu.tjnu.tutor.common.enums.ExceptionType;
import cn.edu.tjnu.tutor.common.event.ExcelReadListener;
import cn.edu.tjnu.tutor.common.exception.ServiceException;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * Excel 工具类。
 *
 * @author 王帅
 * @since 2.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExcelUtils {

    /**
     * 分页查询默认每页最大数据量。
     */
    private static final int DEFAULT_PAGE_SIZE = 1000;

    /**
     * 从 Excel 中读取数据，并写入到数据库中。
     *
     * @param file    上传的 Excel 文件
     * @param service Excel 数据服务
     * @param <T>     数据类型
     * @return Excel 解析结果
     */
    public static <T> ExcelResult readExcel(MultipartFile file, ExcelDataService<T, ?> service) {
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
     * 导出数据到 Excel 文件。
     *
     * @param response HTTP 响应对象
     * @param fileName 文件名称
     * @param service  Excel 数据服务
     * @param query    分页查询参数
     * @param <T>      数据类型
     * @param <Q>      查询类型
     */
    public static <T, Q extends PageQuery> void writeExcel(HttpServletResponse response,
                                                           String fileName,
                                                           ExcelDataService<T, Q> service,
                                                           Q query) {
        try {
            // 设置 HTTP 响应的 Header 和 ContentType
            String name = URLEncoder.encode(fileName, GlobalConst.UTF_8);
            response.addHeader("Content-Disposition", "attachment;filename=" + name + ".xlsx");
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            // 创建 ExcelWriter 与 WriteSheet 用于写入数据
            ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream(), service.getExcelHead())
                    .autoCloseStream(false)
                    .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                    .build();
            WriteSheet writeSheet = EasyExcel.writerSheet(fileName).build();
            if (query == null) {
                // 没有分页对象则直接写入测试数据
                excelWriter.write(service.getDemoData(), writeSheet);
            } else {
                PageVO<T> pageVO;
                query.setPageNum(1);
                query.setPageSize(DEFAULT_PAGE_SIZE);
                do {
                    // 分页查询数据并写入到 Excel 文件中
                    pageVO = service.getRealData(query);
                    excelWriter.write(pageVO.getContent(), writeSheet);
                    query.setPageNum(query.getPageNum() + 1);
                } while (pageVO.hasNext());
            }
            // 完成数据写入，释放相关资源
            excelWriter.finish();
        } catch (Exception e) {
            throw new ServiceException(e, ExceptionType.EXCEL_EXPORT_FAILED, fileName);
        }
    }

    /**
     * 导出 Excel 模板文件。
     *
     * @param response HTTP 响应对象
     * @param fileName 文件名称
     * @param service  Excel 数据服务
     * @param <T>      数据类型
     */
    public static <T> void writeTemplate(HttpServletResponse response,
                                         String fileName,
                                         ExcelDataService<T, ?> service) {
        writeExcel(response, fileName, service, null);
    }

}