package com.silly.controller.admin;

import com.silly.controller.GetFilePath;
import com.silly.entity.Customer;
import com.silly.service.AdminService;
import com.silly.service.impl.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/ModifyCustomer")

public class ModifyCustomer extends HttpServlet {
    private final AdminService adminService= new AdminServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map impfileMap = GetFilePath.get(req);
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String cnum = impfileMap.get("cnum").toString();
        String username=impfileMap.get("name").toString();
        String email=impfileMap.get("email").toString();
        String phone=impfileMap.get("phone").toString();
        String password=impfileMap.get("code").toString();

        Customer customer = new Customer(Integer.valueOf(cnum),username,email,phone,password);

        adminService.ChangeCustomer(customer);
        return;
    }

}
