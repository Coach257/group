package com.silly.controller;

import com.silly.entity.Admin;
import com.silly.entity.Customer;
import com.silly.entity.Worker;
import com.silly.service.impl.SignLoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private SignLoginServiceImpl signLoginService=new SignLoginServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map impfileMap = GetFilePath.get(req);
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String name = impfileMap.get("username").toString();
        String password =impfileMap.get("password").toString();
        String type = impfileMap.get("type").toString();
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        Object res=signLoginService.login(name,password,type);
        if(res==null){
            String result;
            result="账号或者密码错误";
            resp.getWriter().print(result);
        }
        else {
            System.out.println("ok");
            HttpSession session=req.getSession();
            switch (type){
                case "lodger":
                    Customer customer=(Customer)res;
                    session.setAttribute("customer",customer);
                    resp.getWriter().print("index.jsp");
                    break;
                case "worker":
                    Worker worker=(Worker)res;
                    session.setAttribute("worker",worker);
                    resp.getWriter().print("repair_order.jsp");
                    break;
                case "admin":
                    Admin admin=(Admin)res;
                    session.setAttribute("admin",admin);
                    resp.getWriter().print("Admin_control_customer.jsp");
                    break;
            }
        }
    }
}
