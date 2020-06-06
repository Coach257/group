package com.silly.controller.customer;

import com.silly.controller.GetFilePath;
import com.silly.entity.Fix;
import com.silly.service.AdminService;
import com.silly.service.CustomerService;
import com.silly.service.impl.AdminServiceImpl;
import com.silly.service.impl.CustomerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/JudgeWorker")
public class JudgeWorker extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map impfileMap = GetFilePath.get(req);
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String Fnum = impfileMap.get("Fnum").toString();
        String star=impfileMap.get("Value").toString();
        CustomerService customerService=new CustomerServiceImpl();
        AdminService adminService=new AdminServiceImpl();
        Fix fix=adminService.GetFixByFnum(Integer.parseInt(Fnum));
        customerService.JudgeOnFix(fix,Integer.parseInt(star));
        return ;
    }
}
