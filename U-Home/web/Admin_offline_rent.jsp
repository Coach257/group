<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <!-- import CSS -->
    <link rel="stylesheet" href="element-ui/lib/theme-chalk/index.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery-3.3.1.js"></script>
    <script src="js/axios.min.js"></script>
    <title>客服管理中心</title>
</head>
<body>
<div id="app">
    <el-header style="font-size: 12px;height: 50px; line-height: 50px;">
        <span style="color: white;font-size: 24px;line-height: 50px; float: left;">优家，有你就有家</span>
        <el-button plain style="float: right; position:relative;top:5.2px;" @click="quit">退出</el-button>
    </el-header>
    <el-container style="position:absolute; height: 100%; width: 100%; border: 1px solid #eee">
        <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
            <el-menu :default-openeds="['1','2','3']">
                <el-submenu index="1">
                    <template slot="title"><i class="el-icon-message"></i>待处理</template>
                    <el-menu-item index="1-1" @click="linkto('Admin_todo_apply.jsp')">待处理申请</el-menu-item>
                    <el-menu-item index="1-2" @click="linkto('Admin_todo_complaint.jsp')">待处理投诉</el-menu-item>
                    <el-menu-item index="1-3" @click="linkto('Admin_todo_repair.jsp')">待处理报修</el-menu-item>
                </el-submenu>
                <el-submenu index="2">
                    <template slot="title"><i class="el-icon-monitor"></i>管理</template>
                    <el-menu-item index="2-1" @click="linkto('Admin_control_customer.jsp')">管理租客</el-menu-item>
                    <el-menu-item index="2-2" @click="linkto('Admin_control_worker.jsp')">管理师傅</el-menu-item>
                    <el-menu-item index="2-3" @click="linkto('Admin_control_contract.jsp')">管理合同</el-menu-item>
                    <el-menu-item index="2-4" @click="linkto('Admin_control_home.jsp')">管理房源</el-menu-item>
                </el-submenu>
                <el-submenu index="3">
                    <template slot="title"><i class="el-icon-reading"></i>其他</template>
                    <el-menu-item index="3-1" class="is-active" @click="linkto('Admin_offline_rent.jsp')">线下租房</el-menu-item>
                </el-submenu>
            </el-menu>
        </el-aside>
        <el-container>
            <el-main>
                <h1>这是线下租房页面</h1>
            </el-main>
        </el-container>
    </el-container>
</div>
</body>
<script src="js/vue.js"></script>
<script src="/element-ui/lib/index.js"></script>
<script>
    new Vue({
        el: '#app',
        methods: {
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
</html>
