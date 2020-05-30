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
<body>
<div id="app">
    <el-container>
        <el-header>
            <span style="color: white;font-size: 24px;line-height: 50px; float: left;">优家，有你就有家</span>
            <el-button plain style="float: right; position:relative;top:5.2px;" @click="quit">退出</el-button>
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
                        <span slot="title">保修和投诉</span>
                    </el-menu-item>
                    <el-menu-item index="4" @click="linkto('personal_feedback.jsp')">
                        <i class="el-icon-message"></i>
                        <span slot="title">反馈</span>
                    </el-menu-item>
                </el-menu>
            </el-aside>
            <el-main>
                <el-form ref="form" :model="sizeForm" label-width="80px" size="mini">
                    <el-form-item label="头像：">
                        <el-avatar shape="square" :size="size" :src="squareUrl"></el-avatar>
                    </el-form-item>
                    <el-form-item label="姓名：">
                    施哲纶
                </el-form-item>
                    <el-form-item label="电话：">
                        1234567890
                    </el-form-item>
                    <el-form-item label="邮箱：">
                        18373044@buaa.edu.cn
                    </el-form-item>
                    <el-form-item size="large">
                        <el-button type="primary" @click="centerDialogVisible = true">修改资料</el-button>

                        <el-dialog
                                title="修改资料"
                                :visible.sync="centerDialogVisible"
                                width="30%"
                                center>
                            <el-form ref="form" :model="sizeForm" label-width="80px" size="mini">
                                <el-form-item label="头像：">
                                    <el-avatar shape="square" :size="size" :src="squareUrl"></el-avatar>
                                </el-form-item>
                                    <el-upload
                                            class="upload-demo"
                                            action="https://jsonplaceholder.typicode.com/posts/"
                                            :on-preview="handlePreview"
                                            :on-remove="handleRemove"
                                            list-type="picture">
                                        <el-button size="small" type="primary">点击上传</el-button>
                                        <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
                                    </el-upload>
                                <div style="margin: 20px 0;"></div>
                                <el-form-item label="姓名：">
                                    <el-input v-model="sizeForm.name"></el-input>
                                </el-form-item>
                                <el-form-item label="电话：">
                                    <el-input v-model="sizeForm.cellphone"></el-input>
                                </el-form-item>
                                <el-form-item label="邮箱：">
                                    <el-input v-model="sizeForm.email"></el-input>
                                </el-form-item>
                            </el-form>
                            <span slot="footer" class="dialog-footer">
                            <el-button @click="centerDialogVisible = false">取 消</el-button>
                            <el-button type="primary" @click="centerDialogVisible = false">保 存</el-button>
                          </span>
                        </el-dialog>
                    </el-form-item>
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
<script>
    new Vue({
        el: '#app',
        data() {
            return{
                sizeForm: {
                    name: '',
                },
                formInline: {
                    keywords: '',
                },
                squareUrl: "https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png",
                centerDialogVisible: false,
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
            handleRemove(file, fileList) {
                console.log(file, fileList);
            },
            handlePreview(file) {
                console.log(file);
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
