<%--
  Created by IntelliJ IDEA.
  User: shizh
  Date: 2020/4/24
  Time: 20:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>check</title>
</head>
<body>
    <%
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        if(username.equals("admin")&&password.equals("123123")){
            request.setAttribute("username",username);
            request.getRequestDispatcher("adminmain.jsp").forward(request,response);
        }
        else{
            response.sendRedirect("adminlogin.jsp");
        }
    %>
</body>
</html>
