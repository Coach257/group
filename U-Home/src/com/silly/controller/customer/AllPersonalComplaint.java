package com.silly.controller.customer;

import com.alibaba.fastjson.JSON;
import com.silly.controller.GetFilePath;
import com.silly.entity.Complaint;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/AllPersonalComplaint")
public class AllPersonalComplaint extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map impfileMap = GetFilePath.get(req);
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        HttpSession session=req.getSession();
        Customer customer= (Customer) session.getAttribute("customer");//获取当前用户
        CustomerService customerService=new CustomerServiceImpl();
        List<Complaint> list=customerService.ShowMyComplaint(customer);
        String result = "[";
        for(int i = 0;i<list.size();i++){
            Complaint complaint = list.get(i);
            result += JSON.toJSONString(complaint);
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
}
