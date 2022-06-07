package com.example.javaweb.utils;

import com.alibaba.excel.EasyExcel;
import org.burningwave.core.assembler.StaticComponentContainer;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author 怪兽
 * @version 1.0
 * Create by 2022/6/4 11:06
 */
public class FileDownload {
    public static void download(HttpServletResponse response, Class clazz, List list) throws IOException {
        StaticComponentContainer.Modules.exportAllToAll();
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename=book.xlsx");
        EasyExcel.write(response.getOutputStream(), clazz).sheet("模板").doWrite(list);
    }
}
