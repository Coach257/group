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

  <form class="Search_box" action="search.jsp" method="post">
    <div class="Search_main">
      <input type="text" class="Search_input" placeholder="请输入小区/商圈/地铁站等..." />
      <input id="Search_submit" type="submit" :class="over==false? 'Search_submit_out':'Search_submit_over'"
             @mouseover="funover($event)" @mouseout="funout($event)"
             value="开始找房">
    </div>
  </form>

</header>
<section id="main_body">
<div class="container">
    <span style="font-size: 48px;font-weight:900; text-align:left;color:#00E6B8;">热门搜索</span>
    <hr color="#00E6B8"/>
    <div class="box">
        <span class="icon-cont">
            <img src="images/p1.jpg" width="100%">
        </span>
        <h3>合租·方恒东景2居室</h3>
        <ul>
            <li>11.1㎡ | 5/29层</li>
            <li>海淀，距离地铁站100m</li>
            <li style="color: orange;font-size: 30px">3390 ￥/月</li>
            <li style="margin-top:50px; text-align:center;width:100%;">
              <a href="#">我要长租</a>
              <a href="#">我要短租</a>
            </li>
        </ul>
    </div>
    <div class="box">
        <span class="icon-cont">
            <img src="images/p2.jpg" width="100%">
        </span>
        <h3>合租·方恒东景2居室</h3>
        <br><br>
        <ul>
            <li>11.1㎡ | 5/29层</li>
            <li>海淀，距离地铁站100m</li>
            <li style="color: orange;font-size: 30px">3390 ￥/月</li>
            <li style="margin-top:50px; text-align:center;width:100%;">
              <a href="#">我要长租</a>
              <a href="#">我要短租</a>
            </li>
        </ul>
        <br><br>
    </div>
    <div class="box">
        <span class="icon-cont">
            <img src="images/p3.jpg" width="100%">
        </span>
        <h3>合租·方恒东景2居室</h3>
        <br><br>
        <ul>
            <li>11.1㎡ | 5/29层</li>
            <li>海淀，距离地铁站100m</li>
            <li style="color: orange;font-size: 30px">3390 ￥/月</li>
            <li style="margin-top:50px; text-align:center;width:100%;">
              <a href="#">我要长租</a>
              <a href="#">我要短租</a>
            </li>
        </ul>
        <br><br>
    </div>
</div>
</section>
<%@include file="Bottom.jsp"%>
</body>
<script src="js/search.js"></script>
</html>