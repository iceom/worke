package com.upload;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet("/upload")
public class UploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // 1. 判断是否是 multipart 上传
        if (!ServletFileUpload.isMultipartContent(request)) {
            response.getWriter().println("请用文件表单上传");
            return;
        }

        // 2. 工厂 + 解析器
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8"); // 解决中文文件名

        try {
            // 3. 解析请求
            List<FileItem> items = upload.parseRequest(request);
            for (FileItem item : items) {
                if (!item.isFormField()) { // 是文件
                    String fileName = item.getName();
                    if (fileName == null || fileName.trim().isEmpty()) continue;

                    // 截取纯文件名（去掉路径）
                    fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);

                    // 4. 唯一文件名（防重）
                    String ext = fileName.substring(fileName.lastIndexOf("."));
                    String uuidName = UUID.randomUUID() + ext;

                    // 5. 保存目录
                    String uploadPath = getServletContext().getRealPath("/uploads");
                    File uploadDir = new File(uploadPath);
                    if (!uploadDir.exists()) uploadDir.mkdirs();

                    // 6. 写文件
                    File saveFile = new File(uploadDir, uuidName);
                    item.write(saveFile);

                    response.getWriter().println("上传成功：" + uuidName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("上传失败：" + e.getMessage());
        }
    }
}