<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8">
  <title>U-Home</title>
  <link rel="stylesheet" type="text/css" href="css/search.css">
  <script src="js/jquery-3.3.1.js"></script>
  <script src="js/vue.js"></script>
</head>
<body>
<%@include file="Nav.jsp"%>
<form class="SSearch_box" action="search.jsp" method="post">
    <div class="SSearch_main">
      <input type="text" class="SSearch_input" placeholder="请输入小区/商圈/地铁站等..." />
      <input id="Search_submit" type="submit" :class="over==false? 'SSearch_submit_out':'SSearch_submit_over'"
             @mouseover="funover($event)" @mouseout="funout($event)"
             value="开始找房">
    </div>
</form>
  <hr style="width:80%; margin:auto;"/>
<div class="container">
    <div class="box">
        <span class="icon-cont">
            <img src="images/p1.jpg" width="100%">
        </span>
        <h3>合租·方恒东景2居室</h3>
        <br><br>
        <ul>
            <li>11.1㎡ | 5/29层</li>
            <li>海淀，距离地铁站100m</li>
            <li style="color: orange;font-size: 30px">3390 ￥/月</li>
        </ul>
        <br><br>
    </div>
    <div class="box">
        <span class="icon-cont">
            <img src="images/p1.jpg" width="100%">
        </span>
        <h3>合租·方恒东景2居室</h3>
        <br><br>
        <ul>
            <li>11.1㎡ | 5/29层</li>
            <li>海淀，距离地铁站100m</li>
            <li style="color: orange;font-size: 30px">3390 ￥/月</li>
        </ul>
        <br><br>
    </div>
    <div class="box">
        <span class="icon-cont">
            <img src="images/p1.jpg" width="100%">
        </span>
        <h3>合租·方恒东景2居室</h3>
        <br><br>
        <ul>
            <li>11.1㎡ | 5/29层</li>
            <li>海淀，距离地铁站100m</li>
            <li style="color: orange;font-size: 30px">3390 ￥/月</li>
        </ul>
        <br><br>
    </div>
</div>
<div class="container">
    <div class="box">
        <span class="icon-cont">
            <img src="images/p1.jpg" width="100%">
        </span>
        <h3>合租·方恒东景2居室</h3>
        <br><br>
        <ul>
            <li>11.1㎡ | 5/29层</li>
            <li>海淀，距离地铁站100m</li>
            <li style="color: orange;font-size: 30px">3390 ￥/月</li>
        </ul>
        <br><br>
    </div>
    <div class="box">
        <span class="icon-cont">
            <img src="images/p1.jpg" width="100%">
        </span>
        <h3>合租·方恒东景2居室</h3>
        <br><br>
        <ul>
            <li>11.1㎡ | 5/29层</li>
            <li>海淀，距离地铁站100m</li>
            <li style="color: orange;font-size: 30px">3390 ￥/月</li>
        </ul>
        <br><br>
    </div>
    <div class="box">
        <span class="icon-cont">
            <img src="images/p1.jpg" width="100%">
        </span>
        <h3>合租·方恒东景2居室</h3>
        <br><br>
        <ul>
            <li>11.1㎡ | 5/29层</li>
            <li>海淀，距离地铁站100m</li>
            <li style="color: orange;font-size: 30px">3390 ￥/月</li>
        </ul>
        <br><br>
    </div>
</div>
<%@include file="Bottom.jsp"%>
</body>
<script src="js/search.js"></script>
</html>
