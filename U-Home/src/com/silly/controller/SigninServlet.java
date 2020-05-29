package com.silly.controller;

import com.silly.service.SignLoginService;
import com.silly.service.impl.SignLoginServiceImpl;
import net.sf.json.JSONObject;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/signin")
public class SigninServlet extends HttpServlet {
    private final SignLoginService signLoginService=new SignLoginServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map impfileMap = GetFilePath.get(req);
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String username=impfileMap.get("username").toString();
        String email=impfileMap.get("email").toString();
        String phone=impfileMap.get("phone").toString();
        String password=impfileMap.get("password").toString();
        int cum= (int) (System.currentTimeMillis()/1000);
        String result= signLoginService.signup(cum,username,email,phone,password);
        resp.getWriter().print(result);
        return;

    }

}
