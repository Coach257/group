<%@ page import="java.lang.reflect.Array" %>
<%@ page import="java.util.Arrays" %><%--
  Created by IntelliJ IDEA.
  User: shizh
  Date: 2020/4/24
  Time: 19:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>test3</title>
    <%
        String[] id=(String[]) request.getAttribute("id");//获取传递过来的参数
    %>
    <%=Arrays.toString(id)%>
</head>
<body>

</body>
</html>
