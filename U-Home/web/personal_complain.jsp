<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="element-ui/lib/theme-chalk/index.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery-3.3.1.js"></script>
    <script src="js/axios.min.js"></script>
    <title>个人中心-我要投诉</title>
</head>
<style>
    .disabled .el-upload--picture-card {
        display: none;
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
                        default-active="3"
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
                        <span slot="title" class="is-active">我要投诉</span>
                    </el-menu-item>
                    <el-menu-item index="4" @click="linkto('personal_fix.jsp')">
                        <i class="el-icon-chat-dot-round"></i>
                        <span slot="title" >我要报修</span>
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
                <el-form :model="addForm" :rules="rules" ref="addForm"  class="demo-form-inline">
                    <el-row><el-form-item prop="textarea"><el-input
                            type="textarea"
                            :rows="2"
                            width="100%"
                            placeholder="请输入内容"
                            v-model="addForm.textarea">
                    </el-input></el-form-item></el-row>
                    <div style="margin: 20px 0;"></div>
                    <el-row><el-form-item label="投诉图片">
                        <el-upload
                                class="upload-demo"
                                action="https://jsonplaceholder.typicode.com/posts/"
                                :on-preview="handlePreview"
                                :on-remove="handleRemove"
                                :on-change="FileChange"
                                :file-list="addForm.fileList"
                                :limit=addForm.limitNum
                                :auto-upload="false"
                                :on-exceed="exceedFile"
                                list-type="picture">
                            <el-button size="small" type="primary">点击上传</el-button>
                            <div slot="tip" class="el-upload__tip">只能上传一张jpg</div>
                        </el-upload></el-form-item></el-row>
                    <el-row><el-form-item>
                        <el-button type="primary" @click="submitForm('addForm')">提交</el-button>
                    </el-form-item></el-row>
                </el-form>
            </el-main>
        </el-container>
    </el-container>
</div>
</body>
<!-- import Vue before Element -->
<script src="js/vue.js"></script>
<!-- import JavaScript -->
<script src="element-ui/lib/index.js"></script>
<script src="js/personal_complain_script.js"></script>
</html>
