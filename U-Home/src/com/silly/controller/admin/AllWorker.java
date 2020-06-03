package com.silly.controller.admin;

import com.silly.entity.Room;
import com.silly.entity.Worker;
import com.silly.service.AdminService;
import com.silly.service.impl.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/AllWorker")
public class AllWorker extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Worker> list;
        AdminService adminService=new AdminServiceImpl();
        list=adminService.AllWorker();
        String result = "[";
        for(int i = 0;i<list.size();i++){
            Worker worker= list.get(i);
            result += worker.toString();
            if(i != list.size() - 1){
                result += ",";
            }
        }
        result += "]";
        resp.setCharacterEncoding("UTF-8");
        System.out.println(result);
        resp.getWriter().print(result);
        return;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
