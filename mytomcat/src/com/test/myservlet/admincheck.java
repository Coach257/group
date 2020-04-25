package com.test.myservlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class admincheck extends httpservlet{
    private String adusername;
    private String adpassword;

    @Override
    public void init(ServletConfig config) throws ServletException {
        adusername=config.getInitParameter("adusername");
        adpassword=config.getInitParameter("adpassword");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        if(adusername.equals(username)&&adpassword.equals(password)){
            HttpSession session=req.getSession();
            session.setAttribute("username",username);
            req.getRequestDispatcher("adminmain.jsp").forward(req,resp);
        }
        else resp.sendRedirect("adminlogin.jsp");
    }
}
