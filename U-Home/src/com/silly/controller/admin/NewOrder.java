package com.silly.controller.admin;

import com.alibaba.fastjson.JSON;
import com.silly.controller.GetFilePath;
import com.silly.entity.Customer;
import com.silly.entity.Order;
import com.silly.entity.Room;
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
import java.text.SimpleDateFormat;
import java.util.Map;

@WebServlet("/NewOrder")
public class NewOrder extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map impfileMap = GetFilePath.get(req);
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        Customer customer = JSON.parseObject(impfileMap.get("Customer").toString(),Customer.class);
        Room room = JSON.parseObject(impfileMap.get("Room").toString(),Room.class);
        Boolean time = Boolean.valueOf(impfileMap.get("Time").toString());
        java.sql.Date startTime = strToDate(impfileMap.get("beginDate").toString());
        java.sql.Date endTime = strToDate(impfileMap.get("endDate").toString());

        CustomerService customerService =  new CustomerServiceImpl();
        customerService.AddAOrder(customer,room,time,startTime,endTime);
    }
    public static java.sql.Date strToDate(String strDate) {
        String str = strDate;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date d = null;
        try {
            d = format.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.sql.Date date = new java.sql.Date(d.getTime());
        return date;
    }
}

