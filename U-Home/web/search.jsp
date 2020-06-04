<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <!-- import CSS -->
    <link rel="stylesheet" href="element-ui/lib/theme-chalk/index.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery-3.3.1.js"></script>
    <script src="js/axios.min.js"></script>
    <script src="js/vue.js"></script>
    <title>搜索你的优家</title>
</head>
<style>
    .el-header{
        background-color: #B3C0D1;
        color: #333;
        text-align: center;
        line-height: 60px;
    }
    .el-main {
        background-color: #E9EEF3;
        color: #333;
        text-align: center;
        line-height: 160px;
    }
</style>
<body>
<div id="app">
    <el-container>
        <el-header>
            <span style="color: white;font-size: 24px;line-height: 50px; float: left;">优家，有你就有家</span>
            <el-button plain style="float: right; position:relative;top:5.2px;" @click="linkto('index.jsp')">返回首页
            </el-button>
            <div style="margin: 10px 0;"></div>
            <el-form :inline="true" :model="form" class="demo-form-inline" >
                <el-form-item>
                    <el-input prefix-icon="el-icon-search" v-model="form.input" placeholder="请输入关键词" ></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="submit" @click="findRoomByKeyword">查询</el-button>
                </el-form-item>
            </el-form>
        </el-header>
        <el-main>
            <el-table :data="tableData" style="width: 100%">
                <el-table-column label="房源名" width="180">
                    <template slot-scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.Rname }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="图片展示" width="180">
                    <template slot-scope="scope">
                        <el-image style="width: 100px; height: 100px" :src="scope.row.url"></el-image>
                    </template>
                </el-table-column>
                <el-table-column label="地址" width="180">
                    <template slot-scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.Raddress}}</span>
                    </template>
                </el-table-column>
                <el-table-column label="日租金" width="180">
                    <template slot-scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.CostPerDay }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="房间类型" width="180">
                    <template slot-scope="scope">
                        <el-popover trigger="hover" placement="top">
                            <p>已有{{ scope.row.Empty}}位其他租客</p>
                            <div slot="reference" class="name-wrapper">
                                <el-tag size="large">{{scope.row.Capacity}}</el-tag>
                            </div>
                        </el-popover>
                    </template>
                </el-table-column>
                <el-table-column label="租房方式">
                    <template slot-scope="scope">
                        <el-button
                                size="large"
                                @click="handleEdit(scope.$index, scope.row)">长租
                        </el-button>
                        <el-button
                                size="large"
                                type="danger"
                                @click="handleDelete(scope.$index, scope.row)">短租
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
        </el-main>
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
            return {
                form:{
                    input:'',
                },
                tableData: [{
                    url: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
                    Raddress: '北京航空航天大学',
                    Capacity: '四人间',
                    Empty: 2,
                    Rname: 'BUAA',
                    CostPerDay: '223',
                },
                ]
            }
        },
        methods: {
            onSubmit() {
                console.log('submit!');
            },
            quit() {
                axios.post('/logout', {}).then(function (response) {
                    console.log(response);
                    window.location.href = 'index.jsp'
                }).catch(function (error) {
                    console.log(error);
                });
            },
            linkto(location) {
                window.location.href = location;
            },
            handleEdit(index, row) {
                console.log(index, row);
            },
            handleDelete(index, row) {
                console.log(index, row);
            }
        }
    })
</script>

</html>
