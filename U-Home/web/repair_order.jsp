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
                <template>
                    <el-table
                            :data="tableData"
                            height="250"
                            border
                            style="width: 100%">
                        <el-table-column
                                prop="date"
                                label="日期"
                                width="180">
                        </el-table-column>
                        <el-table-column
                                prop="name"
                                label="租客姓名"
                                width="180">
                        </el-table-column>
                        <el-table-column
                                prop="address"
                                label="房屋地址">
                        </el-table-column>
                        <el-table-column
                                prop="state"
                                label="状态">
                        </el-table-column>
                    </el-table>
                </template>
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
                // tableData:Array(20).fill(item),
                formInline: {
                    keywords: '',
                },
                tableData: [{
                    date: '2016-05-03',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1518 弄',
                    state:'未解决'
                }, {
                    date: '2016-05-02',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1518 弄',
                    state:'未解决'
                }, {
                    date: '2016-05-04',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1518 弄',
                    state:'未解决'
                }, {
                    date: '2016-05-01',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1518 弄',
                    state:'未解决'
                }, {
                    date: '2016-05-08',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1518 弄',
                    state:'未解决'
                }, {
                    date: '2016-05-06',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1518 弄',
                    state:'未解决'
                }, {
                    date: '2016-05-07',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1518 弄',
                    state:'未解决'
                }]
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
