package com.silly.controller.admin;

import com.silly.controller.GetFilePath;
import com.silly.entity.Room;
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
import java.util.Map;

@WebServlet("/NewRoom")
public class NewRoom extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        System.out.println("new3");
        Room room = new Room();
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
                        case "Rname":room.setRName(value);break;
                        case "Place":room.setPlace(value);break;
                        case "Capacity":room.setCapacity(Integer.valueOf(value));break;
                        case "CostPerDay":room.setCostPerDay(Integer.valueOf(value));break;
                    }
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
            System.out.println(e);
        }
        room.setRnum((int) (System.currentTimeMillis()/1000));
        room.setCanUse(true);
        room.setEmptyOrNot(true);
        AdminService adminService = new AdminServiceImpl();
        adminService.AddRoom(room);
        System.out.println(room.toString());
    }
}
