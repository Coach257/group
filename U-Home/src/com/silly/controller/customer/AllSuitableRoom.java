package com.silly.controller.customer;

import com.silly.controller.GetFilePath;
import com.silly.entity.Admin;
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

@WebServlet("/AllSuitableRoom")

public class AllSuitableRoom extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map impfileMap = GetFilePath.get(req);
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        HttpSession session=req.getSession();
        Customer customer= (Customer) session.getAttribute("customer");//获取当前用户

        List<Room> showList = new ArrayList<Room>(),roomsList;
        List<Order> orderList;
        AdminService adminService = new AdminServiceImpl();
        CustomerService customerService = new CustomerServiceImpl();

        roomsList = customerService.ShowSuitableRoom();
        orderList = adminService.AllOrder();

        for(Room r : roomsList){
            boolean ok = true;
            for(Order o : orderList){
                if(o.getCnum() == customer.getCnum() && o.getRnum() == r.getRnum()){
                    ok = false;
                }
            }
            if(ok){
                showList.add(r);
            }
        }


        String result = "[";
        for(int i = 0;i<showList.size();i++){
            Room room = showList.get(i);
            result += room.toString();
            if(i != showList.size() - 1){
                result += ",";
            }
        }
        result += "]";

        resp.getWriter().print(result);
        return;
    }
}
