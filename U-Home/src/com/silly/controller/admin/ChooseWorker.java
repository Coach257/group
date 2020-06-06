package com.silly.controller.admin;

import com.silly.service.AdminService;
import com.silly.service.impl.AdminServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

@WebServlet("/ChooseWorker")
public class ChooseWorker extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int Fnum = 0,Wnum = 0;
        try {
            DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
            List<FileItem> list = servletFileUpload.parseRequest(req);
            for (FileItem fileItem : list) {
                if (fileItem.isFormField()) {
                    String name = fileItem.getFieldName();
                    String value = fileItem.getString("UTF-8");
                    System.out.println(name + ":" + value);
                    switch (name){
                        case "Fnum":
                            Fnum=Integer.parseInt(value);
                            break;
                        case "Wnum":
                            Wnum=Integer.parseInt(value);
                    }
                } else {
                    continue;
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        AdminService adminService=new AdminServiceImpl();
        adminService.settleFixOnForce(Wnum,Fnum);
        return;
    }
}
