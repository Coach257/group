package com.silly.controller.customer;

import com.alibaba.fastjson.JSON;
import com.silly.entity.Customer;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

@WebServlet("/CurrentCustomer")
public class CurrentCustomer extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        //修改基本信息
        HttpSession session=req.getSession();
        Customer customer= (Customer) session.getAttribute("customer");
        String result = JSON.toJSONString(customer);
        resp.getWriter().print(result);
        System.out.println(result);

        //上传头像
        try {
            String name = customer.getCnum() + ".jpg";

            DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
            List<FileItem> list = servletFileUpload.parseRequest(req);
            for(FileItem fileItem:list) {
                if (fileItem.getFieldName().equals("File")) {
                    InputStream inputStream = fileItem.getInputStream();
                    String path = req.getServletContext().getRealPath("CustomerPic/" + name);
                    OutputStream outputStream = new FileOutputStream(path);
                    int temp = 0;
                    while ((temp = inputStream.read()) != -1) {
                        outputStream.write(temp);
                    }
                    outputStream.close();
                    inputStream.close();
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
            System.out.println(e);
        }

        return;
    }
}
