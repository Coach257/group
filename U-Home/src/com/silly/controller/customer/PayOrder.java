package com.silly.controller.customer;

import com.alibaba.fastjson.JSON;
import com.silly.controller.GetFilePath;
import com.silly.entity.Admin;
import com.silly.entity.Customer;
import com.silly.entity.Order;
import com.silly.service.AdminService;
import com.silly.service.impl.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet("/PayOrder")
public class PayOrder extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map impfileMap = GetFilePath.get(req);
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        HttpSession session=req.getSession();
        Customer customer= (Customer) session.getAttribute("customer");

        Order order = JSON.parseObject(impfileMap.get("Order").toString(),Order.class);
        order.setMode(4);

        AdminService adminService = new AdminServiceImpl();
        adminService.ChangeOrder(order);
        return;
    }
}
