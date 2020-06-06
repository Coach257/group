package com.silly.controller.admin;

import com.alibaba.fastjson.JSON;
import com.silly.entity.Complaint;
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

@WebServlet("/AllComplaint")
public class AllComplaint extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Complaint> list;
        AdminService adminService=new AdminServiceImpl();
        list=adminService.UnsettledComplaint();
        String result = "[";
        for(int i = 0;i<list.size();i++){
            Complaint complaint = list.get(i);
            result += JSON.toJSONString(complaint);
            if(i != list.size() - 1){
                result += ",";
            }
        }
        result += "]";
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().print(result);
        System.out.println(result);
        return;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
