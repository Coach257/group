package com.silly.controller.customer;

import com.alibaba.fastjson.JSON;
import com.silly.entity.Complaint;
import com.silly.entity.Customer;
import com.silly.entity.Fix;
import com.silly.service.CustomerService;
import com.silly.service.impl.CustomerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
@WebServlet("/MyFix")
public class MyFix extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        Customer customer=(Customer)session.getAttribute("customer");
        CustomerService customerService=new CustomerServiceImpl();
        List<Fix> list=customerService.ShowMyFix(customer);
        String result = "[";
        for(int i = 0;i<list.size();i++){
            Fix fix = list.get(i);
            result += JSON.toJSONString(fix);
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
