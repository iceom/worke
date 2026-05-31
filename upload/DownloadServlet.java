package com.download;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String fileName = request.getParameter("filename");
        if (fileName == null) {
            response.getWriter().println("参数为空");
            return;
        }

        // 文件路径
        String filePath = getServletContext().getRealPath("/uploads") + File.separator + fileName;
        File file = new File(filePath);
        if (!file.exists()) {
            response.getWriter().println("文件不存在");
            return;
        }

        // 1. 响应头设置（关键）
        response.setContentType("application/octet-stream");
        response.setContentLength((int) file.length());

        // 2. 中文文件名处理
        String agent = request.getHeader("User-Agent");
        String encodeName;
        if (agent.contains("MSIE") || agent.contains("Trident")) {
            encodeName = URLEncoder.encode(fileName, "UTF-8");
        } else {
            encodeName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
        }
        response.setHeader("Content-Disposition", "attachment; filename=\"" + encodeName + "\"");

        // 3. 流输出
        try (FileInputStream in = new FileInputStream(file)) {
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) != -1) {
                response.getOutputStream().write(buf, 0, len);
            }
        }
    }
}