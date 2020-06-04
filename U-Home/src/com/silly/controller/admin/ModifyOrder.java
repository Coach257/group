package com.silly.controller.admin;

import com.alibaba.fastjson.JSON;
import com.silly.controller.GetFilePath;
import com.silly.controller.tools.PDF;
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
import java.io.IOException;
import java.text.SimpleDateFormat;
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
        System.out.println(data);
        Order o = JSON.parseObject(data, Order.class);
        adminService.ChangeOrder(o);
        if(o.getMode()==4&&o.isTime()==true){
            String path=req.getServletContext().getRealPath("Contract/");
            Customer customer=adminService.FindByCnum(o.getCnum());
            Room room=adminService.FindByRnum(o.getRnum());
            Map<String,String>map=new HashMap<>();
            String[] begindate = new SimpleDateFormat("yyyy-MM-dd").format(o.getBeginDate()).toString().split("-");
            String[] enddate = new SimpleDateFormat("yyyy-MM-dd").format(o.getEndDate()).toString().split("-");
            map.put("Cnum",String.valueOf(o.getCnum()));
            map.put("Raddress",String.valueOf(room.getPlace()));
            map.put("NowYear",begindate[0]);
            map.put("NowMonth",begindate[1]);
            map.put("NowDay",begindate[2]);
            map.put("EndYear",enddate[0]);
            map.put("EndMonth",enddate[1]);
            map.put("EndDay",enddate[2]);
            map.put("Time",String.valueOf((o.getEndDate().getTime()-o.getBeginDate().getTime())/(1000*24*3600)));
            PDF.makePDF(path,o.getOnum(),map);
        }
        return;
    }

}
