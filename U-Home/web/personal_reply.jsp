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
                        default-active="6"
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

                <el-table :data="showComplaint" style="width: 100%">
                    <el-table-column label="我的投诉图片" width="300">
                        <template slot-scope="scope">
                            <el-image style="width: 100px; height: 100px" :src="'ComplaintPic/'+scope.row.CoNum+'.jpg'"
                                      :preview-src-list="['ComplaintPic/'+scope.row.CoNum+'.jpg']"></el-image>
                        </template>
                    </el-table-column>
                    <el-table-column label="我的文字描述" width="300">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.ComplaintContent}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column label="查看回复">
                        <template slot-scope="scope">
                            <el-popover
                                    placement="bottom"
                                    title="回复"
                                    width="200"
                                    trigger="click">
                                <span>{{scope.row.Reply}}</span>
                            <el-button type="primary"  slot="reference" @click="showreply(scope.row)" :disabled="!scope.row.HaveDone">
                                {{scope.row.HaveDone?"查看回复":"管理员尚未处理"}}</el-button></el-popover>
                        </template>
                    </el-table-column>
                </el-table>
            </el-main>
        </el-container>
    </el-container>
</div>
</body>
<script src="js/vue.js"></script>
<script src="element-ui/lib/index.js"></script>
<script src="js/personal_reply_script.js"></script>
</html>
