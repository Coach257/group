<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <!-- import CSS -->
    <link rel="stylesheet" href="element-ui/lib/theme-chalk/index.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery-3.3.1.js"></script>
    <script src="js/axios.min.js"></script>
    <title>维修师傅-我的工单</title>
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
                    <el-menu-item index="1" @click="linkto('repair_order.jsp')">
                        <i class="el-icon-document"></i>
                        <span slot="title" class="is-active">我的工单</span>
                    </el-menu-item>
                </el-menu>
            </el-aside>
            <el-main>
                <%--待处理工单--%>
                <el-table :data="showFix" style="width: 100%">
                    <el-table-column label="报修图片" width="300">
                        <template slot-scope="scope">
                            <el-image style="width: 100px; height: 100px" :src="'FixPic/'+scope.row.Fnum+'.jpg'"
                                      :preview-src-list="['FixPic/'+scope.row.Fnum+'.jpg']"></el-image>
                        </template>
                    </el-table-column>
                    <el-table-column label="文字描述" width="300">
                        <template slot-scope="scope">
                            <span style="margin-left: 10px">{{ scope.row.Content}}</span>
                        </template>
                    </el-table-column>
                    <el-table-column label="处理">
                        <template slot-scope="scope">
                            <el-button type="primary" @click="reply(scope.row)">回复</el-button>
                        </template>
                    </el-table-column>
                </el-table>
                <%--回复内容--%>
                <el-dialog title="回复" :visible.sync="DialogVisible" width="50%" center>
                    <el-form ref="replyForm" :model="replyForm" :rules="rules" label-width="80px" size="mini">
                        <el-row><el-form-item label="回复内容:" prop="textarea"><el-input
                                type="textarea"
                                :rows="2"
                                width="100%"
                                placeholder="请输入内容"
                                v-model="replyForm.textarea">
                        </el-input></el-form-item></el-row>
                        <el-row>
                            <el-button @click="closeform">取 消</el-button>
                            <el-button type="submit" @click="replyform('replyForm')">回 复</el-button>
                        </el-row>
                    </el-form>
                </el-dialog>

            </el-main>
        </el-container>
    </el-container>
</div>
</body>
<script src="js/vue.js"></script>
<script src="element-ui/lib/index.js"></script>
<script src="js/repair_order_script.js"></script>
</html>
