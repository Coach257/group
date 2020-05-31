package com.silly.controller.admin;

import com.silly.controller.GetFilePath;
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
import java.util.Map;

@WebServlet("/NewRoom")
public class NewRoom extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            DiskFileItemFactory fileItemFactory=new DiskFileItemFactory();
            ServletFileUpload servletFileUpload=new ServletFileUpload(fileItemFactory);
            List<FileItem> list =servletFileUpload.parseRequest(req);
            for(FileItem fileItem:list){
                if(fileItem.isFormField()){
                    String name=fileItem.getFieldName();
                    String value=fileItem.getString("UTF-8");
                    System.out.println(name+":"+value);
                }
                else{
                    String name=new String(fileItem.getName().getBytes("GBK"),"UTF-8");
                    InputStream inputStream=fileItem.getInputStream();
                    String path=req.getServletContext().getRealPath("RoomPic/"+name);
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
        }

    }
}
