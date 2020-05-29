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

@WebServlet("/DeleteCustomer")
public class DeleteCustomer extends HttpServlet {
    private final AdminService adminService= new AdminServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map impfileMap = GetFilePath.get(req);
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String cnum = impfileMap.get("cnum").toString();
        Customer customer = new Customer(Integer.valueOf(cnum),null,null,null,null);

        adminService.DeleteCustomer(customer);
        return;
    }
}
