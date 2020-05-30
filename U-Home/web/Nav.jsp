<%@ page import="com.mysql.cj.Session" %>
<%@ page import="com.silly.entity.Customer" %>
<%@ page pageEncoding="utf-8"%>
<link rel="stylesheet" type="text/css" href="css/basic.css" rel="stylesheet">
<%
    String result;
    Customer customer= (Customer) session.getAttribute("customer");
    if(customer==null){
        result="<a href=\"login.jsp\" class=\"linktyle\">登录</a>";
    }
    else{
        result="<a href=\"personal_center.jsp\" class=\"linktyle\">"+customer.getName()+"</a>";
    }
%>
<section id="main_head" :class="isfixed==false ? 'main_head_notfixed':'main_head_fixed'">
    <div class="main_head_left">
        优家，有你就有家
    </div>
    <div class="main_head_right">
        <a href="index.jsp" class="linktyle">首页</a>
        <%=result%>
    </div>
</section>
<script src="js/basic.js"></script>
