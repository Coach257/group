package com.silly.controller;

import com.silly.entity.Admin;
import com.silly.entity.Customer;
import com.silly.entity.Worker;
import com.silly.service.AdminService;
import com.silly.service.SignLoginService;
import com.silly.service.impl.AdminServiceImpl;
import com.silly.service.impl.SignLoginServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/AdminControlCustomer")

public class Admin_control_customer extends HttpServlet {
    private final SignLoginService signLoginService=new SignLoginServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map impfileMap = getFilePath(req);
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String username=impfileMap.get("key").toString();

        return;

    }
    private static Map getFilePath(HttpServletRequest request) {
        String upload_directory = "upload";
        String slash_directory = "/";

        // 上传配置
        int memory_threshold   = 1024 * 1024 * 3;  // 3MB
        int max_file_size      = 1024 * 1024 * 40; // 40MB
        int max_request_size   = 1024 * 1024 * 50; // 50MB


        // 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        factory.setSizeThreshold(memory_threshold);
        // 设置临时存储目录
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        ServletFileUpload upload = new ServletFileUpload(factory);
        // 设置最大文件上传值
        upload.setFileSizeMax(max_file_size);

        // 设置最大请求值 (包含文件和表单数据)
        upload.setSizeMax(max_request_size);

        upload.setHeaderEncoding("UTF-8");

        String uploadPath =  request.getContextPath()+slash_directory+upload_directory;
        String fileName="";
        String filePath="";
        // 如果目录不存在则创建
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        Map paramMap = new HashMap();

        try {
            // 解析请求的内容提取文件数据
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);

            if (formItems != null && formItems.size() > 0) {
                // 迭代表单数据
                for (FileItem item : formItems) {
                    // 处理不在表单中的字段
                    if (!item.isFormField()) {
                        fileName = new File(item.getName()).getName();
                        filePath = uploadPath + slash_directory + fileName;
                        File storeFile = new File(filePath);
                        item.write(storeFile);
                        paramMap.put("fileName", fileName);
                        paramMap.put("filePath", filePath);
                    }else {
                        String value = item.getString("utf-8");
                        paramMap.put(item.getFieldName(), value);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       /*Iterator<Map.Entry> entries = paramMap.entrySet().iterator();
	   while (entries.hasNext()) {
	     Map.Entry<Integer, Integer> entry = entries.next();
	     System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
	   }*/
        return paramMap;
    }
}
