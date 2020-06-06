<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <!-- import CSS -->
    <link rel="stylesheet" href="element-ui/lib/theme-chalk/index.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery-3.3.1.js"></script>
    <script src="js/axios.min.js"></script>
    <title>个人中心-维修评价</title>
</head>
<style>
    .el-row{
        margin-top: 10px;
    }
</style>
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
                        default-active="5"
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
                        <span slot="title">历史订单</span>
                    </el-menu-item>
                    <el-menu-item index="3" @click="linkto('personal_complain.jsp')">
                        <i class="el-icon-chat-dot-round"></i>
                        <span slot="title" >我要投诉</span>
                    </el-menu-item>
                    <el-menu-item index="4" @click="linkto('personal_fix.jsp')">
                        <i class="el-icon-chat-dot-round"></i>
                        <span slot="title">我要报修</span>
                    </el-menu-item>
                    <el-menu-item index="5" @click="linkto('personal_feedback.jsp')">
                        <i class="el-icon-message"></i>
                        <span slot="title">报修反馈</span>
                    </el-menu-item>
                    <el-menu-item index="6" @click="linkto('personal_reply.jsp')">
                        <i class="el-icon-message"></i>
                        <span slot="title">投诉反馈</span>
                    </el-menu-item>
                    <el-menu-item index="7" @click="quit">
                        <span slot="title">退出登录</span>
                    </el-menu-item>
                </el-menu>
            </el-aside>
            <el-main>
                <el-table :data="showFix" style="width: 100%">
                    <el-table-column label="报修图片" width="180">
                        <template slot-scope="scope">
                            <el-image style="width: 100px; height: 100px" :src="'FixPic/'+scope.row.Fnum+'.jpg'"
                                      :preview-src-list="['FixPic/'+scope.row.Fnum+'.jpg']"></el-image>
                        </template>
                    </el-table-column>
                    <el-table-column label="文字描述" width="180">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.Content}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column label="查看回复">
                        <template slot-scope="scope">
                            <el-popover
                                    placement="bottom"
                                    title="回复"
                                    width="200"
                                    trigger="click">
                                <el-row> <span>{{scope.row.Reply!=null?scope.row.Reply:"暂时还没有师傅处理哦"}}</span></el-row>
                                <el-row>
                                <el-button type="primary" :disabled="scope.row.Reply==null" @click="check(scope.row)">查看师傅信息</el-button>
                                </el-row>
                                <el-button type="primary"  slot="reference">查看回复</el-button></el-popover>
                        </template>
                    </el-table-column>
                    <el-table-column label="评价" width="180">
                        <template slot-scope="scope">
                            <el-rate
                                    v-model="scope.row.value"
                                    show-text>
                            </el-rate>
                        </template>
                    </el-table-column>
                    <el-table-column>
                        <template slot-scope="scope">
                        <el-button type="primary" @click="submit(scope.row)">评价</el-button>
                        </template>
                    </el-table-column>
                </el-table>
                <%--师傅信息--%>
                <el-dialog title="师傅信息" :visible.sync="dialogFormVisible" width="30%">
                    <el-form :model="showWorker">
                        <el-form-item label="姓名">
                            <p>{{showWorker.Name}}</p>
                        </el-form-item>
                        <el-form-item label="处理工单次数">
                            <p>{{showWorker.DealTime}}</p>
                        </el-form-item>
                        <el-form-item label="历史评分">
                            <el-rate
                                    v-model="showWorker.Score"
                                    disabled
                                    show-score
                                    text-color="#ff9900"
                                    >
                            </el-rate>
                        </el-form-item>
                    </el-form>
                </el-dialog>
            </el-main>
        </el-container>
    </el-container>
</div>
</body>
<script src="js/vue.js"></script>
<script src="element-ui/lib/index.js"></script>
<script src="js/personal_feedback_script.js"></script>
</html>
