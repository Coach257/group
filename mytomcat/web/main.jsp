<%--
  Created by IntelliJ IDEA.
  User: shizh
  Date: 2020/4/24
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>main</title>
</head>
<body>
<%
    String sessionID =session.getId();//会话标识符，同一个会话具有相同的标识符
%>
    <form action="/adminlogin.jsp">
        <input type =submit value="我是管理员"><br>
        <input type =button value="我是租客">
    </form>
<%=sessionID%>
</body>
</html>
