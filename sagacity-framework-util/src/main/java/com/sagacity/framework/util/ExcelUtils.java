/*
 * Copyright 2020-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sagacity.framework.util;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Excel工具类
 * @author xingyun
 * @date 2020-07-04 15:03
 */
public final class ExcelUtils {

    public static final String XLS_SUFFIX = ".xls";

    public static final String XLSX_SUFFIX = ".xlsx";

    /**
     * <p>
     *     获取 ExcelWriter 对象
     * </p>
     * @param headerAlias 标题
     * @return ExcelWriter
     */
    private static ExcelWriter getExcelWriter(Map<String, String> headerAlias) {
        ExcelWriter writer = ExcelUtil.getBigWriter();
        // 设置标题头
        writer.setHeaderAlias(headerAlias);
        // 只导出别名字段
        writer.setOnlyAlias(true);
        // 设置 Sheet
        Sheet sheet = writer.getSheet();
        // 设置筛选
        CellRangeAddress cellAddresses = new CellRangeAddress(0,0,0,headerAlias.size());
        sheet.setAutoFilter(cellAddresses);
        return writer;
    }

    public static <T> void exportExcel(List<T> tList,Map<String, String> headerAlias){
        ExcelWriter excelWriter = ExcelUtils.getExcelWriter(headerAlias);
        excelWriter.write(tList);
        HttpServletResponse response = RequestUtil.getResponse();
        // 设置 Excel 响应头
        setExcelResponseHeaders(response);
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
        } catch (IOException e) {

            e.printStackTrace();
        }

        excelWriter.flush(outputStream,true);
    }


    private static void setExcelResponseHeaders(HttpServletResponse response){
        String fileName = IdClient.nextIdStr() + ExcelUtils.XLSX_SUFFIX;
        // 下面几行是为了解决文件名乱码的问题
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
    }

}
