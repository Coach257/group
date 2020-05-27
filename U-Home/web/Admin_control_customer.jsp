<%@ page import="com.silly.entity.Customer" %>
<%@ page import="java.util.List" %>
<%@ page import="com.silly.service.AdminService" %>
<%@ page import="com.silly.service.impl.AdminServiceImpl" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!
    public String CtoString(Customer c){
        return "{\"cnum\":\""+c.getCnum() + "\"," +
                "\"name\":\""+c.getName() +"\"," +
                "\"email\":\""+c.getEmail() + "\"," +
                "\"phone\":\""+c.getPhone() + "\""+
                "}";
    }
%>
<%
    List<Customer> list;
    AdminService adminService=new AdminServiceImpl();
    list=adminService.AllCustomer();
    String tableData = "[";
    for(int i = 0;i<list.size();i++){
        Customer c = list.get(i);
        tableData += CtoString(c);
        if(i != list.size() - 1){
            tableData += ",";
        }
    }
    tableData += "]";
%>
<html>
<head>
    <meta charset="UTF-8">
    <!-- import CSS -->
    <link rel="stylesheet" href="element-ui/lib/theme-chalk/index.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery-3.3.1.js"></script>
    <script src="js/axios.min.js"></script>
    <title>客服管理中心-管理租客</title>
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
                    <el-menu-item index="1-1"><a href="Admin_todo_apply.jsp">待处理申请</a></el-menu-item>
                    <el-menu-item index="1-2"><a href="Admin_todo_complaint.jsp">待处理投诉</a></el-menu-item>
                    <el-menu-item index="1-3"><a href="Admin_todo_repair.jsp">待处理报修</a></el-menu-item>
                </el-submenu>
                <el-submenu index="2">
                    <template slot="title"><i class="el-icon-monitor"></i>管理</template>
                    <el-menu-item index="2-1" class="is-active"><a href="Admin_control_customer.jsp">管理租客</a></el-menu-item>
                    <el-menu-item index="2-2"><a href="Admin_control_worker.jsp">管理师傅</a></el-menu-item>
                    <el-menu-item index="2-3"><a href="Admin_control_contract.jsp">管理合同</a></el-menu-item>
                    <el-menu-item index="2-4"><a href="Admin_control_home.jsp">管理房源</a></el-menu-item>
                </el-submenu>
                <el-submenu index="3">
                    <template slot="title"><i class="el-icon-reading"></i>其他</template>
                    <el-menu-item index="3-1"><a href="Admin_offline_rent.jsp">线下租房</a></el-menu-item>
                </el-submenu>
            </el-menu>
        </el-aside>
        <el-container>
            <el-main>
                <%--查询用户--%>
                <el-form :inline="true" :model="formInline" class="demo-form-inline" >
                    <el-form-item>
                        <el-input prefix-icon="el-icon-search" v-model="formInline.user" placeholder="请输入关键词" ></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="submit" @click="findCustomerByKeyword">查询</el-button>
                    </el-form-item>
                </el-form>
                <%--查询结果--%>
                <el-table :data="showCustomers">
                    <el-table-column prop="cnum" label="编号">
                    </el-table-column>
                    <el-table-column prop="name" label="用户名">
                    </el-table-column>
                    <el-table-column prop="email" label="邮箱">
                    </el-table-column>
                    <el-table-column prop="phone" label="手机号">
                    </el-table-column>
                    <el-table-column>
                        <el-button type="primary" @click="open">修改</el-button>
                    </el-table-column>
                </el-table>

            </el-main>
        </el-container>
    </el-container>
</div>
</body>
<!-- import Vue before Element -->
<script src="js/vue.js"></script>
<!-- import JavaScript -->
<script src="/element-ui/lib/index.js"></script>
<script>
    new Vue({
        el: '#app',
        data() {
            return{
                allCustomers:[],
                showCustomers:[],
                formInline: {
                    user:""
                }
            }
        },
        mounted:function() {
            this.allCustomers = JSON.parse('<%=tableData%>')
            this.showCustomers = this.allCustomers
        },
        methods: {
            findCustomerByKeyword() {//查询用户
                let keyWord = this.formInline.user
                this.showCustomers = this.allCustomers.filter((c)=>(c.name.indexOf(keyWord)!=-1))
            },
            open(){
                this.$prompt('请输入邮箱', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    inputPattern: /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/,
                    inputErrorMessage: '邮箱格式不正确'
                }).then(({ value }) => {
                    this.$message({
                        type: 'success',
                        message: '你的邮箱是: ' + value
                    });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '取消输入'
                    });
                });
            },
            quit(){
                axios.post('/logout', {
                }).then(function (response) {
                    console.log(response);
                    window.location.href = 'index.jsp'
                }).catch(function (error) {
                    console.log(error);
                });
            }
        }
    })
</script>
</html>
