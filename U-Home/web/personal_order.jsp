<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <!-- import CSS -->
    <link rel="stylesheet" href="element-ui/lib/theme-chalk/index.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery-3.3.1.js"></script>
    <script src="js/axios.min.js"></script>
    <title>个人中心-历史订单</title>
</head>
<body>
<div id="app">
    <el-container>
        <el-header>
            <span style="color: white;font-size: 24px;line-height: 50px; float: left;">优家，有你就有家</span>
            <el-button plain style="float: right; position:relative;top:5.2px;" @click="linkto('index.jsp')">返回首页</el-button>
        </el-header>
        <el-container height="100%">
            <el-aside width="200px">
                <el-menu
                        default-active="2"
                        class="el-menu-vertical-demo"
                        background-color="#545c64"
                        text-color="#fff"
                        active-text-color="#ffd04b">
                    <el-menu-item index="1" @click="linkto('personal_center.jsp')">
                        <i class="el-icon-user-solid"></i>
                        <span slot="title">个人信息</span>
                    </el-menu-item>
                    <el-menu-item index="2" @click="linkto('personal_order.jsp')">
                        <i class="el-icon-document"></i>
                        <span slot="title"class="is-active">历史订单</span>
                    </el-menu-item>
                    <el-menu-item index="3"@click="linkto('personal_complain.jsp')">
                        <i class="el-icon-chat-dot-round"></i>
                        <span slot="title">报修和投诉</span>
                    </el-menu-item>
                    <el-menu-item index="4" @click="linkto('personal_feedback.jsp')">
                        <i class="el-icon-message"></i>
                        <span slot="title">反馈</span>
                    </el-menu-item>
                    <el-menu-item index="5"@click="quit">
                        <span slot="title">退出登录</span>
                    </el-menu-item>
                </el-menu>
            </el-aside>
            <el-main>
                <el-row>
                    <el-col :span="8" v-for="(o, index) in 2" :key="o" :offset="index > 0 ? 2 : 0">
                        <el-card :body-style="{ padding: '0px' }">
                            <b>房屋地址：</b>北京航空航天大学十五公寓<br>
                            <b>房屋类型：</b>四人间<br>
                            <b>租住方式：</b>长租<br>
                            <b>入住时间：</b>2018-09-01<br>
                            <b>搬出时间：</b>待定<br>
                            <img src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png" class="image">
                        </el-card>
                    </el-col>
                </el-row>
            </el-main>
        </el-container>
    </el-container>
</div>
</body>
<!-- import Vue before Element -->
<script src="js/vue.js"></script>
<!-- import JavaScript -->
<script src="element-ui/lib/index.js"></script>
<script>
    new Vue({
        el: '#app',
        data() {
            return{
                tableData:Array(20).fill(item),
                formInline: {
                    keywords: '',
                }
            }
        },
        methods: {
            onSubmit() {
                console.log('submit!');
            },
            quit(){
                axios.post('/logout', {
                }).then(function (response) {
                    console.log(response);
                    window.location.href = 'index.jsp'
                }).catch(function (error) {
                    console.log(error);
                });
            },
            linkto(location){
                window.location.href=location;
            }
        }
    })
</script>
<style>
    .image {
        width: 100%;
        display: block;
    }

</style>
</html>
