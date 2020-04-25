<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: shizh
  Date: 2020/4/23
  Time: 13:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>test</title>
  </head>
  <body>
    <%
      List<String> names=new ArrayList<String>();
      List<Integer>ages=new ArrayList<Integer>();
      names.add("a");
      names.add("b");
      names.add("c");
      ages.add(12);
      ages.add(11);
      ages.add(13);
    %>
    <table border="1">
        <tr>
            <th>姓名</th>
            <th>年龄</th>
        </tr>
        <%
            for(int i=0;i<names.size();i++){
        %>
        <tr>
            <td>
                <%=names.get(i)%>
            </td>
            <td>
                <%=ages.get(i)%>
            </td>
        </tr>
        <%
            }
        %>
    </table>
  </body>
</html>
