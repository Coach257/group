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
<style>
    .el-row{
        margin-top: 20px;
    }
</style>
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
                    <el-menu-item index="2-1"><a href="Admin_control_customer.jsp">管理租客</a></el-menu-item>
                    <el-menu-item index="2-2"><a href="Admin_control_worker.jsp">管理师傅</a></el-menu-item>
                    <el-menu-item index="2-3"><a href="Admin_control_contract.jsp">管理合同</a></el-menu-item>
                    <el-menu-item index="2-4" class="is-active"><a href="Admin_control_home.jsp">管理房源</a></el-menu-item>
                </el-submenu>
                <el-submenu index="3">
                    <template slot="title"><i class="el-icon-reading"></i>其他</template>
                    <el-menu-item index="3-1"><a href="Admin_offline_rent.jsp">线下租房</a></el-menu-item>
                </el-submenu>
            </el-menu>
        </el-aside>
        <el-container>
            <el-main>
                <el-row>
                <el-button type="primary" plain @click="handleModify">添加房源</el-button>
                </el-row>
                <el-row>
                <el-form :inline="true" :model="formInline" class="demo-form-inline" >
                    <el-form-item>
                        <el-input prefix-icon="el-icon-search" v-model="formInline.user" placeholder="请输入关键词" ></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="submit">查询</el-button>
                    </el-form-item>
                </el-form>
                </el-row>
                <el-table :data="showRooms">
                    <el-table-column prop="Rnum" label="编号">
                    </el-table-column>
                    <el-table-column prop="Rname" label="房间名称">
                    </el-table-column>
                    <el-table-column prop="Place" label="房间地址">
                    </el-table-column>
                    <el-table-column prop="Capacity" label="房间类型">
                    </el-table-column>
                    <el-table-column prop="CostPerDay" label="日租金">
                    </el-table-column>
                    <el-table-column>
                            <el-button type="primary" @click="changestatus">暂停出租</el-button>
                    </el-table-column>
                </el-table>
                <el-dialog title="添加房源" :visible.sync="dialogVisible" :before-close="handleClose">
                    <div style="width:100%;text-align:center">
                        <el-form :model="addForm" ref="addForm" :inline="true"  class="center" >
                                <el-upload
                                        action="https://jsonplaceholder.typicode.com/posts/"
                                        list-type="picture-card"
                                        :on-preview="handlePictureCardPreview"
                                        :on-remove="handleRemove">
                                    <i class="el-icon-plus"></i>
                                </el-upload>
                                <el-dialog :visible.sync="dialogimgVisible">
                                    <img width="100%" :src="dialogImageUrl" alt="">
                                </el-dialog>
                            <el-row>
                            <el-form-item label="房间名称" prop="rname">
                                <el-input v-model="addForm.rname" ></el-input>
                            </el-form-item>
                            </el-row>
                            <el-row>
                            <el-form-item label="房间地址" prop="raddress">
                                <el-input v-model="addForm.raddress" ></el-input>
                            </el-form-item>
                            </el-row>
                            <el-row>
                            <el-form-item label="日租金" prop="rcostperday">
                                <el-input v-model="addForm.rcostperday" ></el-input>
                            </el-form-item>
                            </el-row>
                            <el-row>
                                <el-select v-model="value" placeholder="房间类型">
                                    <el-option
                                            v-for="item in options"
                                            :key="item.value"
                                            :label="item.label"
                                            :value="item.value">
                                    </el-option>
                                </el-select>
                            </el-row>
                            <el-row>
                            <el-form-item>
                                <el-button @click="closeForm('addForm')">取 消</el-button>
                                <el-button type="submit" @click="submitForm('addForm')">提 交</el-button>
                            </el-form-item>
                            </el-row>
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
<script src="js/Admin_control_home_script.js"></script>
</html>
