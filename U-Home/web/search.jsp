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
            <el-form :inline="true" class="demo-form-inline" >
                <el-form-item>
                    <el-input prefix-icon="el-icon-search" v-model="keyword" placeholder="请输入关键词" ></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="submit" @click="findRoomByKeyword">查询</el-button>
                </el-form-item>
            </el-form>
        </el-header>
        <el-main>
            <el-table :data="showRooms" style="width: 100%">
                <el-table-column label="房源名" width="180">
                    <template slot-scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.Rname }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="图片展示" width="180">
                    <template slot-scope="scope">
                        <el-image style="width: 100px; height: 100px" :src="'RoomPic/'+scope.row.Rnum+'.jpg'"></el-image>
                    </template>
                </el-table-column>
                <el-table-column label="地址" width="180">
                    <template slot-scope="scope">
                        <span style="margin-left: 10px">{{ scope.row.Place}}</span>
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
                            <p>已有{{ scope.row.EmptyOrNot}}位其他租客</p>
                            <div slot="reference" class="name-wrapper">
                                <el-tag size="large">{{CapacityToString(scope.row.Capacity)}}</el-tag>
                            </div>
                        </el-popover>
                    </template>
                </el-table-column>
                <el-table-column label="租房方式">
                    <template slot-scope="scope">
                        <el-button
                                size="large"
                                @click="rent(scope.row)">长租
                        </el-button>
                        <el-button
                                size="large"
                                type="danger"
                                @click="rent(scope.row)">短租
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>

            <el-dialog title="x租" :visible.sync="RentVisible">
                <div style="width:100%;text-align:center">
                    <el-form :model="form" ref="form" :inline="true"  class="center" >
                        <el-form-item label="开始时间" prop="startTime" >
                            <el-date-picker v-model="form.startTime"  format="yyyy-MM-dd" value-format="yyyy-MM-dd" clearable style="width: 100%"
                                            :picker-options="startDatePicker" type="date"  :placeholder="dialogStatus=='view'?'':'请输入订阅开始时间'"></el-date-picker>
                        </el-form-item>
                        <el-form-item label="结束时间" prop="endTime">
                            <el-date-picker v-model="form.endTime"  format="yyyy-MM-dd" value-format="yyyy-MM-dd" clearable style="width: 100%"
                                            :picker-options="endDatePicker"   type="date"  :placeholder="dialogStatus=='view'?'':'请输入订阅结束时间'"></el-date-picker>
                        </el-form-item>
                        <el-form-item>
                            <el-button @click="closeForm">取 消</el-button>
                            <el-button type="submit" @click="submitForm">提 交</el-button>
                        </el-form-item>
                    </el-form>
                </div>
            </el-dialog>
        </el-main>
    </el-container>
</div>
</body>
<script src="js/vue.js"></script>
<script src="element-ui/lib/index.js"></script>
<script src="js/search_script.js"></script>

</html>
