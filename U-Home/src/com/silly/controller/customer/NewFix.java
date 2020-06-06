package com.silly.controller.customer;

import com.silly.entity.Customer;
import com.silly.service.CustomerService;
import com.silly.service.impl.CustomerServiceImpl;
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

@WebServlet("/NewFix")
public class NewFix extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        HttpSession session=req.getSession();
        Customer customer= (Customer) session.getAttribute("customer");
        int FNum=(int)(System.currentTimeMillis()/1000);
        String string=null;
        try {
            DiskFileItemFactory fileItemFactory=new DiskFileItemFactory();
            ServletFileUpload servletFileUpload=new ServletFileUpload(fileItemFactory);
            List<FileItem> list =servletFileUpload.parseRequest(req);
            for(FileItem fileItem:list){
                if(fileItem.isFormField()){
                    String name=fileItem.getFieldName();
                    String value=fileItem.getString("UTF-8");
                    System.out.println(name+":"+value);
                    switch (name){
                        case "Content":string=value;break;
                    }
                }
                else{
                    String name = String.valueOf(FNum)+".jpg";
                    InputStream inputStream=fileItem.getInputStream();
                    String path=req.getServletContext().getRealPath("FixPic/"+name);
                    OutputStream outputStream=new FileOutputStream(path);
                    int temp=0;
                    while((temp=inputStream.read())!=-1){
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
        CustomerService customerService=new CustomerServiceImpl();
        customerService.MakeFix(FNum,customer,string);
    }
}
