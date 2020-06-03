package com.silly.controller.admin;

import com.alibaba.fastjson.JSON;
import com.silly.entity.Order;
import com.silly.service.AdminService;
import com.silly.service.impl.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/AllOrder")
public class AllOrder extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Order> list;
        AdminService adminService=new AdminServiceImpl();
        list = new ArrayList<Order>();
        list.add(new Order(1591189600,123,123,0,123,
                java.sql.Date.valueOf("2000-1-1"),true,java.sql.Date.valueOf("2000-12-31")));

        //list = adminService.AllOrder();

        String result = "[";
        for(int i = 0;i<list.size();i++){
            Order order = list.get(i);
            result += JSON.toJSONString(order);
            if(i != list.size() - 1){
                result += ",";
            }
        }
        result += "]";
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().print(result);
        System.out.println(result);
        return;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
