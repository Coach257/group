<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8">
  <title>U-Home</title>
  <link rel="stylesheet" type="text/css" href="css/index.css">
  <link rel="stylesheet" type="text/css" href="css/search.css">
  <script src="js/jquery-3.3.1.js"></script>
  <script src="js/vue.js"></script>
</head>
<body>
<%@include file="Nav.jsp"%>
<header>
  <div class="title">
    <span class="bigtitle">青年租房选优家</span><br>
    <span class="smalltitle">百万中国青年租房第一选择</span>
  </div>
    <%=search%>


</header>
<section id="main_body">
<div class="container">
    <span style="font-size: 48px;font-weight:900; text-align:left;color:#00E6B8;">热门搜索</span>
    <hr color="#00E6B8"/>
    <div class="box">
        <span class="icon-cont">
            <img src="images/p1.jpg" width="100%">
        </span>
        <h3>合租·方恒东景1居室</h3>
        <ul>
            <li>海淀，距离地铁站100m</li>
            <li style="color: orange;font-size: 30px">3390 /月</li>
        </ul>
    </div>
    <div class="box">
        <span class="icon-cont">
            <img src="images/p2.jpg" width="100%">
        </span>
        <h3>合租·方恒东景2居室</h3>
        <br><br>
        <ul>
            <li>海淀，距离地铁站100m</li>
            <li style="color: orange;font-size: 30px">4490 /月</li>
        </ul>
        <br><br>
    </div>
    <div class="box">
        <span class="icon-cont">
            <img src="images/p3.jpg" width="100%">
        </span>
        <h3>合租·方恒东景3居室</h3>
        <br><br>
        <ul>
            <li>海淀，距离地铁站100m</li>
            <li style="color: orange;font-size: 30px">5750 ￥/月</li>
        </ul>
        <br><br>
    </div>
</div>
</section>
<%@include file="Bottom.jsp"%>
</body>
<script src="js/search.js"></script>
</html>
