package com.silly.controller.worker;

import com.alibaba.fastjson.JSON;
import com.silly.controller.GetFilePath;
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
import java.util.Map;

@WebServlet("/DealFix")
public class DealFix extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map impfileMap = GetFilePath.get(req);
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        int Fnum = Integer.parseInt(impfileMap.get("Fnum").toString());
        String Reply = impfileMap.get("Reply").toString();
        AdminService adminService=new AdminServiceImpl();
        adminService.DealFix(Fnum,Reply);
        return;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
