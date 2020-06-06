package com.silly.controller.worker;

import com.alibaba.fastjson.JSON;
import com.silly.entity.*;
import com.silly.service.AdminService;
import com.silly.service.impl.AdminServiceImpl;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/WorkerFix")
public class WorkerFix extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        Worker worker= (Worker) session.getAttribute("worker");
        List<Fix> list;
        AdminService adminService=new AdminServiceImpl();
        list=adminService.WorkerFix(worker);
        String result = "[";
        for(int i = 0;i<list.size();i++){
            Fix fix = list.get(i);
            result += JSON.toJSONString(fix);
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
