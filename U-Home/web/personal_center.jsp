<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <!-- import CSS -->
    <link rel="stylesheet" href="element-ui/lib/theme-chalk/index.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery-3.3.1.js"></script>
    <script src="js/axios.min.js"></script>
    <title>个人中心-个人信息</title>
</head>
<style>
    .image {
        width: 100%;
        display: block;
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
                        default-active="1"
                        class="el-menu-vertical-demo"
                        background-color="#545c64"
                        text-color="#fff"
                        active-text-color="#ffd04b">
                    <el-menu-item index="1">
                        <i class="el-icon-user-solid"></i>
                        <span slot="title" @click="linkto('personal_center.jsp')">个人信息</span>
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
                        <span slot="title" class="is-active">我要报修</span>
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
                <el-form ref="CurrentCustomer" :model="CurrentCustomer" label-width="80px" size="mini">
                    <el-form-item label="头像：">
                        <el-avatar shape="square" :size="size" :src="avatarPath"
                                   :preview-src-list="[avatarPath]"></el-avatar>
                    </el-form-item>
                    <el-form-item label="用户名：">
                        {{CurrentCustomer.Name}}
                    </el-form-item>
                    <el-form-item label="电话：">
                        {{CurrentCustomer.Phone}}
                    </el-form-item>
                    <el-form-item label="邮箱：">
                        {{CurrentCustomer.Email}}
                    </el-form-item>
                    <el-form-item size="large">
                        <el-button type="primary" @click="ModifyCustomer">修改资料</el-button>
                    </el-form-item>
                    <el-form-item size="large">
                        <el-button type="primary" @click="ModifyCode">修改密码</el-button>
                    </el-form-item>
                </el-form>

                    <el-dialog title="修改资料" :visible.sync="centerDialogVisible" width="30%" center :before-close="handleClose">
                        <el-form ref="sizeForm" :model="sizeForm" :rules="rules" label-width="80px" size="mini">
                            <el-form-item label="头像：">
                                <el-avatar shape="square" :size="size" :src="avatarPath"
                                           :preview-src-list="[avatarPath]"></el-avatar>
                            </el-form-item>
                                <el-upload
                                        class="upload-demo"
                                        action="https://jsonplaceholder.typicode.com/posts/"
                                        :on-preview="handlePreview"
                                        :on-remove="handleRemove"
                                        :on-change="FileChange"
                                        list-type="picture">
                                    <el-button size="small" type="primary">点击上传</el-button>
                                    <div slot="tip" class="el-upload__tip">只能上传一张jpg</div>
                                </el-upload>
                            <div style="margin: 20px 0;"></div>
                            <el-form-item label="姓名：" prop="Name">
                                <el-input v-model="sizeForm.Name"></el-input>
                            </el-form-item>
                            <el-form-item label="电话：" prop="Phone">
                                <el-input v-model="sizeForm.Phone"></el-input>
                            </el-form-item>
                            <el-form-item label="邮箱：" prop="Email">
                                <el-input v-model="sizeForm.Email"></el-input>
                            </el-form-item>
                        </el-form>
                        <span slot="footer" class="dialog-footer">
                        <el-button @click="handleClose">取 消</el-button>
                        <el-button type="submit" @click="submitForm('sizeForm')">保 存</el-button>
                      </span>
                    </el-dialog>
                <el-dialog title="修改密码" :visible.sync="codeDialogVisible" width="40%" center :before-close="handlecodeClose">
                    <el-form ref="codeForm" :model="codeForm" :rules="rules2" label-width="80px" size="mini">
                        <el-form-item label="原密码" prop="ocode" >
                            <el-input type="password" v-model="codeForm.ocode"></el-input>
                        </el-form-item>
                        <el-form-item label="新密码" prop="code" show-password>
                            <el-input type="password" v-model="codeForm.code"></el-input>
                        </el-form-item>
                        <el-form-item label="确认" prop="checkcode" show-password>
                            <el-input type="password" v-model="codeForm.checkcode"></el-input>
                        </el-form-item>
                    </el-form>
                    <span slot="footer" class="dialog-footer">
                        <el-button @click="handlecodeClose">取 消</el-button>
                        <el-button type="submit" @click="submitcodeForm('codeForm')">保 存</el-button>
                      </span>
                </el-dialog>

            </el-main>
        </el-container>
    </el-container>
</div>
</body>
<script src="js/vue.js"></script>
<script src="element-ui/lib/index.js"></script>
<script src="js/personal_center_script.js"></script>
</html>
