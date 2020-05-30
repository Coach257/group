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
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private SignLoginServiceImpl signLoginService=new SignLoginServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password =req.getParameter("password");
        String type = req.getParameter("type");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        Object res=signLoginService.login(name,password,type);
        if(res==null){
            String result;
            result="账号或者密码错误";
            req.setAttribute("loginerrMsg",result);
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
        else {
            HttpSession session=req.getSession();
            switch (type){
                case "lodger":
                    Customer customer=(Customer)res;
                    session.setAttribute("customer",customer);
                    req.getRequestDispatcher("index.jsp").forward(req,resp);
                    break;
                case "worker":
                    Worker worker=(Worker)res;
                    session.setAttribute("worker",worker);
                    resp.sendRedirect("repair_order.jsp");
                    break;
                case "admin":
                    Admin admin=(Admin)res;
                    session.setAttribute("admin",admin);
                    resp.sendRedirect("Admin_control_customer.jsp");
                    break;
            }
        }
    }
}
