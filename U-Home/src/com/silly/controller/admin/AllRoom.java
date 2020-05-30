package com.silly.controller.admin;

import com.silly.entity.Room;
import com.silly.service.AdminService;
import com.silly.service.impl.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/AllRoom")
public class AllRoom extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("!!!!!!!!!!!!!!! ");
        List<Room> list;
        AdminService adminService=new AdminServiceImpl();
        list=adminService.AllRoom();
        String result = "[";
        for(int i = 0;i<list.size();i++){
            Room room = list.get(i);
            result += room.toString();
            if(i != list.size() - 1){
                result += ",";
            }
        }
        result += "]";
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().print(result);
        return;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
