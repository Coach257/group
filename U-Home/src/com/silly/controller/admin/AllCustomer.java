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
import java.util.List;
import java.util.Map;

@WebServlet("/AllCustomer")
public class AllCustomer extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Customer> list;
        AdminService adminService=new AdminServiceImpl();
        list=adminService.AllCustomer();
        String result = "[";
        for(int i = 0;i<list.size();i++){
            Customer c = list.get(i);
            result += c.toString();
            if(i != list.size() - 1){
                result += ",";
            }
        }
        result += "]";
        resp.getWriter().print(result);
        return;
    }
}
