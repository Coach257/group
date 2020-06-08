package com.silly.controller.customer;

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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/RenewOrder")

public class RenewOrder extends HttpServlet {
    private final AdminService adminService= new AdminServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map impfileMap = GetFilePath.get(req);
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String data = impfileMap.get("data").toString();
        int month = Integer.valueOf(impfileMap.get("month").toString());
        System.out.println(month);
        System.out.println(data);

        Order o = JSON.parseObject(data, Order.class);
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(o.getEndDate());

        calendar.add(Calendar.MONTH, month);
        o.setEndDate(calendar.getTime());

        System.out.println("增加月份后的日期："+o.getEndDate());


        adminService.ChangeOrder(o);

        /*if(o.getMode()==4&&o.isTime()==true){
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
        }*/
        return;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filename=req.getParameter("name")+".pdf";
        resp.setContentType("application/x-msdownload");
        resp.setHeader("Content-Disposition","attachment;filename="+filename);
        OutputStream outputStream=resp.getOutputStream();
        String path=req.getServletContext().getRealPath("Contract/"+filename);
        InputStream inputStream=new FileInputStream(path);
        int temp=0;
        while((temp=inputStream.read())!=-1){
            outputStream.write(temp);
        }
        outputStream.close();
        inputStream.close();
    }
}
