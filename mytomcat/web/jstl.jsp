<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.test.myservlet.User" %><%--
  Created by IntelliJ IDEA.
  User: shizh
  Date: 2020/4/25
  Time: 12:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>jstl</title>
</head>
<body>
    <%
        List<User> list=new ArrayList<User>();
        list.add(new User("a",12));
        list.add(new User("b",13));
        list.add(new User("c",12));
        request.setAttribute("List",list);
    %>
    <table>
        <tr>
            <td>姓名</td>
            <td>年龄</td>
        </tr>
        <c:forEach items="${List}" var="list">
            <tr>
                <td>${list.name}</td>
                <td>${list.age}</td>
            </tr>
        </c:forEach>

    </table>
    ${requestScope.List}
</body>
</html>
