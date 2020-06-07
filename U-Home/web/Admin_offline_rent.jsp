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
                <h3>租客信息</h3>
                <el-table :data="showCustomers">
                    <el-table-column prop="Cnum" label="编号">
                    </el-table-column>
                    <el-table-column prop="Name" label="用户名">
                    </el-table-column>
                    <el-table-column prop="Email" label="邮箱">
                    </el-table-column>
                    <el-table-column prop="Phone" label="手机号">
                    </el-table-column>
                    <el-table-column>
                        <template slot-scope="scope">
                            <el-button type="primary" @click="handleModify(scope.row)">修改</el-button>
                        </template>
                    </el-table-column>
                    <el-table-column>
                        <template slot-scope="scope">
                            <el-button type="primary" @click="handleDelete(scope.row)">删除</el-button>
                        </template>
                    </el-table-column>
                </el-table>

                <h3>房间信息</h3>
                <el-table :data="showRooms">
                    <el-table-column prop="Rnum" label="编号">
                    </el-table-column>
                    <el-table-column prop="Rname" label="房间名称">
                    </el-table-column>
                    <el-table-column prop="Place" label="房间地址">
                    </el-table-column>
                    <el-table-column prop="Capacity" :formatter="formatterColumn" label="房间类型">
                    </el-table-column>
                    <el-table-column prop="CostPerDay"  label="日租金">
                    </el-table-column>

                    <el-table-column>
                        <template slot-scope="scope" >
                            <div v-if="scope.row.EmptyOrNot">
                                <el-button type="primary" v-if="scope.row.CanUse" @click="changestate(scope.row)">暂停出租</el-button>
                                <el-button type="primary" v-else @click="changestate(scope.row)">恢复出租</el-button>
                            </div>
                        </template>

                    </el-table-column>
                </el-table>

                <h3>租借类型</h3>
                <br>
                <el-radio-group v-model="form.time">
                    <el-radio label="true" border>长 租</el-radio>
                    <el-radio label="false" border>短 租</el-radio>
                </el-radio-group>
                <br>
                <h3>开始时间</h3>
                <br>
                <el-date-picker v-model="form.beginDate"  format="yyyy-MM-dd" value-format="yyyy-MM-dd" clearable style="width: 100%"
                                :picker-options="startDatePicker" type="date"  ></el-date-picker>
                <br>
                <h3>结束时间</h3>
                <br>
                <el-date-picker v-model="form.endDate"  format="yyyy-MM-dd" value-format="yyyy-MM-dd" clearable style="width: 100%"
                                :picker-options="startDatePicker" type="date"  ></el-date-picker>

                <el-button type="primary" @click="onSubmit">提 交</el-button>

            </el-main>
        </el-container>
    </el-container>
</div>
</body>
<script src="js/vue.js"></script>
<script src="/element-ui/lib/index.js"></script>
<script src="js/Admin_office_rent_script.js"></script>

</html>
