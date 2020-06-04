package com.silly.controller.admin;

import com.alibaba.fastjson.JSON;
import com.silly.controller.GetFilePath;
import com.silly.controller.tools.PDF;
import com.silly.entity.Customer;
import com.silly.entity.Order;
import com.silly.service.AdminService;
import com.silly.service.impl.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/ModifyOrder")

public class ModifyOrder extends HttpServlet {
    private final AdminService adminService= new AdminServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map impfileMap = GetFilePath.get(req);
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String data = impfileMap.get("data").toString();
        Order o = JSON.parseObject(data, Order.class);
        adminService.ChangeOrder(o);
        if(o.getMode()==4&&o.isTime()==true){
            String path=req.getServletContext().getRealPath("Contract/");
            Map<String,String>map=new HashMap<>();
            map.put("Cnum",String.valueOf(o.getCnum()));
            PDF.makePDF(path,o.getOnum(),null);
        }
        return;
    }

}
