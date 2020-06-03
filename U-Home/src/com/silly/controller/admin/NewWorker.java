package com.silly.controller.admin;

import com.silly.controller.GetFilePath;
import com.silly.entity.Customer;
import com.silly.entity.Room;
import com.silly.entity.Worker;
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
import java.util.Map;

@WebServlet("/NewWorker")
public class NewWorker extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map impfileMap = GetFilePath.get(req);
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String Name = impfileMap.get("Name").toString();
        String Code = impfileMap.get("Code").toString();
        int Wnum = (int) (System.currentTimeMillis()/1000);
        Worker worker = new Worker(Wnum,Name,Code,0,0);

        System.out.println(worker.toString());

        AdminService adminService = new AdminServiceImpl();
        adminService.AddWorker(worker);
    }
}

