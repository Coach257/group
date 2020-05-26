package com.silly.controller;

import com.silly.service.SignLoginService;
import com.silly.service.impl.SignLoginServiceImpl;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signin")
public class SigninServlet extends HttpServlet {
    private final SignLoginService signLoginService=new SignLoginServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String username=req.getParameter("name");
        String email=req.getParameter("email");
        String phone=req.getParameter("phone");
        String password=req.getParameter("password");
        String result;
        if(username==null||username==""){
            result="用户名不能为空";
            resp.getWriter().print(result);
        }
        else if(signLoginService.getbyname(username)){
            result="该用户名已被注册";
            resp.getWriter().print(result);
        }
        else if(email==null||email==""){
            result="邮箱地址不能为空";
            resp.getWriter().print(result);
        }
        else if(!email.matches("[0-9a-zA-Z]+@[0-9a-zA-Z]+.com")){
            result="邮箱地址不合法";
            resp.getWriter().print(result);
        }
        else if(phone==null||phone==""){
            result="手机号不能为空";
            resp.getWriter().print(result);
        }
        else if(!phone.matches("\\d{11}")){
            result="手机号不合法";
            resp.getWriter().print(result);
        }
        else if(password==null||password==""){
            result="密码不能为空";
            resp.getWriter().print(result);
        }
        else {
            int cum= (int) (System.currentTimeMillis()/1000);
            signLoginService.signin(cum,username,email,phone,password);
            result="注册成功!";
            resp.getWriter().print(result);
        }

    }
}
