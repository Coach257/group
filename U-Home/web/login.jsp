<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>U-Home</title>
    <link rel="stylesheet" href="css/login_style.css">
    <script type="text/javascript" src="js/jquery-3.3.1.js"></script>
</head>
<body>
<%
    String loginerrMsg=(String)request.getAttribute("loginerrMsg");
    if (loginerrMsg==null)loginerrMsg="";
    else {
        loginerrMsg="<label><span style=\"color: red; \">"+loginerrMsg+"</span></label>";
    }
%>
<script type="text/javascript">
    function signin() {
        $.ajax({
            url: '/signin',
            type: 'post',
            dataType: 'text',
            data: $('#1').serialize(),
            success: function (data) {//回调成功执行
                $("#result").html("<label><span style=\"color: #ff0000; \">" + data + "</span></label>");

            }
        });
    }
</script>
<%@include file="Nav.jsp"%>
  <div class="content">
      <form action="/login" method="post">
        <div class="form sign-in">
            <h2>欢迎回来</h2>
            <label>
                <span>用户名</span>
                <input class="input" type="email" name="name" />
            </label>
            <label>
                <span>密码</span>
                <input class="input" type="password" name="password"/>
            </label>
            <label>
                <input type="radio" name="type" value="lodger" checked>我是租客
                <input type="radio" name="type" value="worker">我是师傅
                <input type="radio" name="type" value="admin">我是客服
            </label>
            <%=loginerrMsg%>
            <button type="button" class="submit" onclick="this.form.submit()">登 录</button>
        </div>
      </form>
        <div class="sub-cont">
            <div class="img">
                <div class="img__text m--up">
                    <h2>还未注册？</h2>
                    <p>立即注册，寻找你的优家！</p>
                </div>
                <div class="img__text m--in">
                    <h2>已有帐号？</h2>
                    <p>有帐号就登录吧，好久不见了！</p>
                </div>
                <div class="img__btn">
                    <span class="m--up">注 册</span>
                    <span class="m--in">登 录</span>
                </div>
            </div>
            <form id="1">
            <div class="form sign-up">
                <h2>立即注册</h2>
                <label>
                    <span>用户名</span>
                    <input  class="input" type="text"  name="name"/>
                </label>
                <label>
                    <span>邮箱</span>
                    <input class="input" type="email"  name="email" />
                </label>
                <label>
                    <span>手机号码</span>
                    <input class="input" type="text" name="phone"/>
                </label>
                <label>
                    <span>密码</span>
                    <input class="input" type="password" name="password"/>
                </label>
                <div id="result">
                </div>
                <button type="button" class="submit" onclick="signin()">注 册</button>
            </div>
            </form>
        </div>
    </div>
      <script src="js/login_script.js"></script>
<footer style="position: fixed;">
    <div class="bottom_nav">
        <li><a href="#">法律声明</a></li>
        <li><a href="#">隐私政策</a></li>
        <li><a href="#">关于我们</a></li>
    </div>
    <hr>
    <h3>优家，有你就有家</h3>
    <span class="copyright">©Copyright 2020 最优秀的软工小组版权所有</span>
</footer>
</body>
</html>
