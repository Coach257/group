<%--
  Created by IntelliJ IDEA.
  User: shizh
  Date: 2020/4/24
  Time: 20:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>adminmain</title>
</head>
<body>
    欢迎！<%=request.getSession().getAttribute("username")%>
    有效时间:<%=request.getSession().getMaxInactiveInterval()%>秒
<%
    Cookie cookie=new Cookie("name","a");
    cookie.setMaxAge(20);//单条cookie存活时间
    response.addCookie(cookie);//添加、覆盖
    Cookie[] cookies=request.getCookies();
    for(Cookie cookie1:cookies){
        %>
        <%=cookie1.getName()+":"+cookie1.getValue()%><br>
    <%
    }
%>

</body>
</html>
