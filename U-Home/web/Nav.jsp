<%@ page import="com.mysql.cj.Session" %>
<%@ page import="com.silly.entity.Customer" %>
<%@ page pageEncoding="utf-8"%>
<link rel="stylesheet" type="text/css" href="css/basic.css" rel="stylesheet">
<%
    String result;
    String search;
    Customer customer= (Customer) session.getAttribute("customer");
    if(customer==null){
        result="<a href=\"login.jsp\" class=\"linktyle\">登录</a>";
        search="";
    }
    else{
        result="<a href=\"personal_center.jsp\" class=\"linktyle\">"+customer.getName()+"</a>";
        search="<form class=\"Search_box\" action=\"search.jsp\" method=\"post\">\n" +
                "    <div class=\"Search_main\">\n" +
                "      <input id=\"Search_submit\" type=\"submit\" :class=\"over==false? 'Search_submit_out':'Search_submit_over'\"\n" +
                "             @mouseover=\"funover($event)\" @mouseout=\"funout($event)\"\n" +
                "             value=\"开始找房\">\n" +
                "    </div>\n" +
                "  </form>";
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
