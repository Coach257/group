package com.silly.controller.customer;

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
import java.util.List;

@WebServlet("/AllSuitableRoom")

public class AllSuitableRoom extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Room> list;
        CustomerService customerService = new CustomerServiceImpl();

        list=customerService.ShowSuitableRoom();
        String result = "[";
        for(int i = 0;i<list.size();i++){
            Room room = list.get(i);
            result += room.toString();
            if(i != list.size() - 1){
                result += ",";
            }
        }
        result += "]";
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().print(result);
        return;
    }
}
