<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <!-- import CSS -->
    <link rel="stylesheet" href="element-ui/lib/theme-chalk/index.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery-3.3.1.js"></script>
    <script src="js/axios.min.js"></script>
    <title>客服管理中心-待处理申请</title>
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
                    <el-menu-item index="1-1" class="is-active" @click="linkto('Admin_todo_apply.jsp')">待处理申请</el-menu-item>
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
                    <el-menu-item index="3-1" @click="linkto('Admin_offline_rent.jsp')">线下租房</el-menu-item>
                </el-submenu>
            </el-menu>
        </el-aside>
        <el-container>
            <el-main>
                <%--待处理申请表格--%>
                <el-table :data="showOrders">
                    <el-table-column prop="Onum" label="订单编号">
                    </el-table-column>
                    <el-table-column prop="Cnum" label="用户编号">
                    </el-table-column>
                    <el-table-column>
                        <template slot-scope="scope">
                            <el-button type="primary" @click="CheckCustomer(scope.row)">详情</el-button>
                        </template>
                    </el-table-column>
                    <el-table-column prop="Rnum" label="房源编号">
                    </el-table-column>
                    <el-table-column>
                        <template slot-scope="scope">
                            <el-button type="primary" @click="CheckRoom(scope.row)">详情</el-button>
                        </template>
                    </el-table-column>
                    <el-table-column prop="Time" :formatter="formatterColumn" label="订单类型">
                        <%--这里显示长租或者短租--%>
                    </el-table-column>
                    <el-table-column prop="BeginDate" label="开始时间">
                        <%--这里显示长租或者短租--%>
                    </el-table-column>
                    <el-table-column prop="EndDate" label="结束时间">
                        <%--这里显示长租或者短租--%>
                    </el-table-column>
                    <el-table-column label="申请通过">
                        <template slot-scope="scope">
                            <el-button type="primary" @click="CheckPass(scope.row)">是</el-button>
                        </template>
                    </el-table-column>
                    <el-table-column >
                        <template slot-scope="scope">
                            <el-button type="primary" @click="CheckUnPass(scope.row)">否</el-button>
                        </template>
                    </el-table-column>
                </el-table>
                    <%--用户信息--%>
                <el-dialog title="用户信息" :visible.sync="CustomerDialogVisible">
                    <div style="width:100%;text-align:center">
                        <el-form :model="CustomerForm" ref="CustomerForm" :inline="true"  class="center" >
                            <el-row><el-form-item label="用户编号" prop="Cnum" >
                                <el-input v-model="CustomerForm.Cnum" :disabled="true"></el-input>
                            </el-form-item></el-row>
                            <el-row><el-form-item label="用户名" prop="Name">
                                <el-input v-model="CustomerForm.Name" :disabled="true"></el-input>
                            </el-form-item></el-row>
                            <el-row><el-form-item label="邮 箱" prop="Email">
                                <el-input v-model="CustomerForm.Email" :disabled="true"></el-input>
                            </el-form-item></el-row>
                            <el-row><el-form-item label="手机号" prop="Phone">
                                <el-input v-model="CustomerForm.Phone":disabled="true"></el-input>
                            </el-form-item></el-row>
                        </el-form>
                    </div>
                </el-dialog>
                    <%--房源信息--%>
                <el-dialog title="房源信息" :visible.sync="RoomDialogVisible">
                    <div style="width:100%;text-align:center">
                        <el-form :model="RoomForm" ref="RoomForm" :inline="true"  class="center" >
                            <el-row><el-form-item label="房源编号" prop="Rnum">
                                <el-input v-model="RoomForm.Rnum" :disabled="true"></el-input>
                            </el-form-item></el-row>
                            <el-row><el-form-item label="房间名称" prop="Rname" >
                                <el-input v-model="RoomForm.Rname" :disabled="true"></el-input>
                            </el-form-item></el-row>
                            <el-row><el-form-item label="房间地址" prop="Place" >
                                <el-input v-model="RoomForm.Place" :disabled="true"></el-input>
                            </el-form-item></el-row>
                            <el-row><el-form-item label="日租金" prop="CostPerDay">
                                <el-input v-model="RoomForm.CostPerDay" :disabled="true"></el-input>
                            </el-form-item></el-row>
                            <el-row><el-form-item label="房间类型" prop="Capacity">
                                <el-input v-model="RoomForm.Capacity" :disabled="true"></el-input>
                            </el-form-item></el-row>
                        </el-form>
                    </div>
                </el-dialog>
            </el-main>
        </el-container>
    </el-container>
</div>
</body>
<script src="js/vue.js"></script>
<script src="/element-ui/lib/index.js"></script>
<script src="js/Admin_todo_apply.js"></script>
</html>
