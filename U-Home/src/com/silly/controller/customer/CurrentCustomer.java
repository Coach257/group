package com.silly.controller.customer;

import com.alibaba.fastjson.JSON;
import com.silly.entity.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/CurrentCustomer")
public class CurrentCustomer extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        HttpSession session=req.getSession();

        Customer customer= (Customer) session.getAttribute("customer");
        String result = JSON.toJSONString(customer);
        resp.getWriter().print(result);
        return;
    }
}
