package com.silly.controller.customer;

import com.alibaba.fastjson.JSON;
import com.silly.controller.GetFilePath;
import com.silly.entity.Customer;
import com.silly.entity.Order;
import com.silly.entity.Room;
import com.silly.service.AdminService;
import com.silly.service.impl.AdminServiceImpl;

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

@WebServlet("/AllPersonalOrder")
public class AllPersonalOrder extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ok");
        Map impfileMap = GetFilePath.get(req);
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        HttpSession session=req.getSession();

        Customer customer= (Customer) session.getAttribute("customer");//获取当前用户

        AdminService adminService = new AdminServiceImpl();
        List<Order> allList = adminService.AllOrder();
        List<Order> listOrder = new ArrayList<Order>();
        List<Room> listRoom = new ArrayList<Room>();

        for(Order o : allList){
            if(o.getCnum() == customer.getCnum()){
                Room r = adminService.FindByRnum(o.getRnum());
                if(r != null){
                    listRoom.add(r);
                    listOrder.add(o);
                }
            }
        }

        String resultRoom = "[";
        for(int i = 0;i<listRoom.size();i++){
            Room room = listRoom.get(i);
            resultRoom += room.toString();
            if(i != listRoom.size() - 1){
                resultRoom += ",";
            }
        }
        resultRoom += "]";

        String resultOrder = "[";
        for(int i = 0;i<listOrder.size();i++){
            Order order = listOrder.get(i);
            resultOrder += JSON.toJSONString(order);
            if(i != listOrder.size() - 1){
                resultOrder += ",";
            }
        }
        resultOrder += "]";

        String result = "{\"resultRoom\":"+resultRoom+","+"\"resultOrder\":"+resultOrder+"}";

        resp.getWriter().print(result);
        System.out.println(result);
        return;
    }
}
