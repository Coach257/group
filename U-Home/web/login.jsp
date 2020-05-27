<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>U-Home</title>
    <link rel="stylesheet" href="css/login_style.css">
    <script type="text/javascript" src="js/jquery-3.3.1.js"></script>
    <link rel="stylesheet" href="element-ui/lib/theme-chalk/index.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/axios.min.js"></script>
    <!-- import Vue before Element -->
    <script src="js/vue.js"></script>
    <!-- import JavaScript -->
    <script src="/element-ui/lib/index.js"></script>
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
            <labe>
                <span>用户名</span>
                <input class="input" type="email" name="name" />
            </labe>
            <labe>
                <span>密码</span>
                <input class="input" type="password" name="password"/>
            </labe>
            <labe>
                <input type="radio" name="type" value="lodger" checked>我是租客
                <input type="radio" name="type" value="worker">我是师傅
                <input type="radio" name="type" value="admin">我是客服
            </labe>
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
            <div class="form sign-up" id="formsignin">
                <h2>立即注册</h2>
                <el-form :model="ruleForm" :rules="rules" ref="ruleForm" style="margin-top: 5px;" label-width="100px" class="demo-ruleForm">
                    <el-form-item label="用户名" prop="username">
                        <el-input type="text" v-model="ruleForm.username" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="密码" prop="pass">
                        <el-input type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="确认密码" prop="checkPass">
                        <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="邮箱" prop="email">
                        <el-input type="text" v-model="ruleForm.email" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="手机号" prop="phone">
                        <el-input type="text" v-model="ruleForm.phone" autocomplete="off"></el-input>
                    </el-form-item>
                    <div id="signinresult"></div>
                <button type="button" class="submit" @click="submitForm('ruleForm')">注 册</button>
                </el-form>
            </div>
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

<script>
    function refresh() {
        window.location.href="login.jsp";
    }

    new Vue({
        el:"#formsignin",
        data() {
            var validatePass = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入密码'));
                } else {
                    if (this.ruleForm.checkPass !== '') {
                        this.$refs.ruleForm.validateField('checkPass');
                    }
                    callback();
                }
            };
            var validatePass2 = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请再次输入密码'));
                } else if (value !== this.ruleForm.pass) {
                    callback(new Error('两次输入密码不一致!'));
                } else {
                    callback();
                }
            };
            var validateEmail = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入邮箱'));
                } else {
                    if (value !== '') {
                        var reg=/^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
                        if(!reg.test(value)){
                            callback(new Error('邮箱不合法'));
                        }
                    }
                    callback();
                }
            };
            var validateMobilePhone = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入手机号'));
                } else {
                    if (value !== '') {
                        var reg=/^1[3456789]\d{9}$/;
                        if(!reg.test(value)){
                            callback(new Error('手机号不合法'));
                        }
                    }
                    callback();
                }
            };
            var validateUsername = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入用户名'));
                } else {
                    callback();
                }
            };
            return {
                ruleForm: {
                    pass: '',
                    checkPass: '',
                    username: '',
                    email:'',
                    phone:''
                },
                rules: {
                    pass: [
                        { validator: validatePass, trigger: 'blur' }
                    ],
                    checkPass: [
                        { validator: validatePass2, trigger: 'blur' }
                    ],
                    username: [
                        { validator: validateUsername,trigger: 'blur' }
                    ],
                    email: [
                        { validator:validateEmail, trigger: 'blur' },
                    ],
                    phone: [
                        {validator:validateMobilePhone,trigger:'blur'}
                    ]

                }
            };
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        let formData = new FormData();
                        formData.append('username', this.ruleForm.username);
                        formData.append('password', this.ruleForm.pass);
                        formData.append('email', this.ruleForm.email);
                        formData.append('phone',this.ruleForm.phone);
                        let config = {
                            headers: {
                                'Content-Type': 'multipart/form-data'
                            }
                        };
                        axios.post('/signin',formData,config)
                            .then(function (response) {
                                if (response.data==null){
                                    $("#signinresult").html("</labe><span style=\"color: blue;margin: auto; \">"
                                        + "注册成功"+
                                        "</span></labe>");
                                    setTimeout(refresh(), 1000);
                                }else {
                                    $("#signinresult").html("</labe><span style=\"color: #ff0000;margin: auto; \">"
                                        + response.data +
                                        "</span></labe>");
                                }
                            })
                            .catch(function (error) {
                                console.log(error);
                            });
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
        }
    })
</script>
</html>
