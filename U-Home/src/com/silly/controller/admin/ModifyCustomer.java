package com.silly.controller.admin;

import com.silly.controller.GetFilePath;
import com.silly.entity.Admin;
import com.silly.entity.Customer;
import com.silly.entity.Worker;
import com.silly.service.AdminService;
import com.silly.service.SignLoginService;
import com.silly.service.impl.AdminServiceImpl;
import com.silly.service.impl.SignLoginServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/ModifyCustomer")

public class ModifyCustomer extends HttpServlet {
    private final AdminService adminService= new AdminServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map impfileMap = GetFilePath.get(req);
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String cnum = impfileMap.get("cnum").toString();
        String username=impfileMap.get("name").toString();
        String email=impfileMap.get("email").toString();
        String phone=impfileMap.get("phone").toString();
        String password=impfileMap.get("code").toString();

        Customer customer = new Customer(Integer.valueOf(cnum),username,email,phone,password);

        adminService.ChangeCustomer(customer);
        resp.getWriter().print(cnum+username+email+phone+password);
        return;
    }

}
