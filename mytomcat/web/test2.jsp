<%@ page import="java.lang.reflect.Array" %>
<%@ page import="java.util.Arrays" %><%--
  Created by IntelliJ IDEA.
  User: shizh
  Date: 2020/4/24
  Time: 19:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>test2</title>
    <%
        request.setCharacterEncoding("UTF-8");//传入参数为中文
       String[] idstr = request.getParameterValues("id");//获取同名参数列表
       request.setAttribute("id",idstr);//打包
       request.getRequestDispatcher("/test3.jsp").forward(request,response);//传递给下一个页面
    %>
    <%=Arrays.toString(idstr)%>
</head>
<body>

</body>
</html>
