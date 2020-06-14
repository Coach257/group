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
                        default-active="2"
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
            <el-main height="100%">
                <el-col :span="8" v-for="(room, index) in allRooms" :offset="index > 0 ? 2 : 0">
                    <el-card :body-style="{ padding: '0px' }">
                        <el-form label-width="100px" size="mini">
                            <el-form-item label="房屋名称：">
                                {{room.Rname}}
                            </el-form-item>
                            <el-form-item label="房屋地址：">
                                {{room.Place}}
                            </el-form-item>
                            <el-form-item label="房屋类型：">
                                {{CapacityToString(room.Capacity)}}
                            </el-form-item>
                            <el-form-item label="租住方式：">
                                {{allOrders[index].Time?"长租":"短租"}}
                            </el-form-item>
                            <el-form-item label="入住时间：">
                                {{allOrders[index].BeginDate}}
                            </el-form-item>
                            <el-form-item label="搬出时间：">
                                {{allOrders[index].EndDate}}
                            </el-form-item>
                            <div v-if="allOrders[index].Time != 7">
                                <el-form-item v-if="allOrders[index].Time&&(allOrders[index].Mode!=2)" label="续约：">
                                    <el-button type="primary" @click="showRenew(allOrders[index])">续 约</el-button>
                                </el-form-item>
                                <el-form-item label="审核状态：">
                                    {{(allOrders[index].Mode==2)?"等待审核":"已审核"}}
                                </el-form-item>
                                <el-form-item v-if="allOrders[index].Mode==3" label="付款情况：">
                                    未付款
                                    <el-button type="primary" @click="showPay(allOrders[index])">付 款</el-button>
                                </el-form-item>
                                <el-form-item v-if="allOrders[index].Mode==4" label="付款情况：">
                                    已付款
                                </el-form-item>

                                <el-form-item v-if="allOrders[index].Time&&(allOrders[index].Mode!=2)" label="下载合同：">
                                    <a :href="'/ModifyOrder?name='+allOrders[index].Onum">下载合同</a>
                                </el-form-item>
                            </div>
                            <el-form-item v-if="allOrders[index].Time == 7" label="订单状态">
                                已结束
                            </el-form-item>
                        </el-form>
                        <img :src="'RoomPic/'+room.Rnum+'.jpg'" class="image">
                    </el-card>
                </el-col>

                <%--弹窗--%>
                <el-dialog title="付款" :visible.sync="PayDialogVisible" :before-close="PayClose">
                    <div style="width:100%;text-align:center">
                        <el-form ref="form"  label-width="80px">
                            <el-form-item label="付款金额">
                                <el-input v-model="MoneyNeeded"></el-input>
                            </el-form-item>
                            <el-button type="primary" @click="pay">付 款</el-button>
                        </el-form>
                    </div>
                </el-dialog>

                <%--弹窗--%>
                <el-dialog title="续租" :visible.sync="RenewDialogVisible" :before-close="Renewclose">
                    <div style="width:100%;text-align:center">
                        <el-form ref="form"  label-width="80px">
                            <el-form-item label="续约月数">
                                <el-input v-model="renewMonth"></el-input>
                            </el-form-item>
                            <el-button type="primary" @click="renew">续 约</el-button>
                        </el-form>
                    </div>
                </el-dialog>

            </el-main>
        </el-container>
    </el-container>
</div>
</body>
<script src="js/vue.js"></script>
<script src="element-ui/lib/index.js"></script>
<script src="js/personal_order_script.js"></script>
<style>
    .image {
        width: 100%;
        display: block;
    }

</style>
</html>
