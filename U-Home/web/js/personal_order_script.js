
function errormessage(data){
    vue.$notify({
        title: '错误',
        message: data,
        type:'error'
    });
}
function successmessage(data){
    vue.$notify({
        title: '成功',
        message: data,
        type: 'success'
    });
}
function refresh(){
    window.location.href='personal_order.jsp';
}
let vue = new Vue({
    el: '#app',
    data() {
        return{
            renewMonth:0,
            payOrder:{},
            renewOrder: {},
            MoneyNeeded:0,
            RenewDialogVisible:false,
            PayDialogVisible:false,
            allOrders:{},
            allRooms:{},
            formInline: {
                keywords: '',
            }
        }
    },
    methods: {
        download(){

        },
        renew(){
            let renewMonth = this.renewMonth
            let order = this.renewOrder

            var reg=/^[1-9]\d*$/;
            if(!reg.test(renewMonth)){
                errormessage("请输入一个正整数");
                return
            }

            let formDate = new FormData()
            formDate.append('data',JSON.stringify(order))
            formDate.append('month',this.renewMonth)

            console.log(JSON.stringify(order))


            axios.post('/RenewOrder',formDate,{headers: {'Content-Type': 'multipart/form-data'}})
                .then(function (response) {
                    successmessage("处理成功");
                    setTimeout(refresh,2000);
                })
                .catch(function (error) {
                    errormessage("处理失败,请检查");
                    console.log(error);
                });
        },
        showRenew(order){
            this.renewOrder = order;
            this.RenewDialogVisible = true;
            this.renewMonth = 0;
        },
        showPay(order){
            this.payOrder = order;
            this.PayDialogVisible = true;
            this.MoneyNeeded = order.MoneyNeeded
        },
        pay(){
            let order = this.payOrder
            let config = {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            };
            let formData = new FormData;
            formData.append('Order',JSON.stringify(order))

            axios.post('/PayOrder',formData,config)
                .then(function (response) {
                    window.location.href='personal_order.jsp';
                })
                .catch(function (error) {
                    console.log(error);
                });

        },
        PayClose(){
            this.PayDialogVisible = false
        },
        Renewclose(){
            this.RenewDialogVisible = false;
        },
        CapacityToString(Capacity) {
            switch(Capacity){
                case 1:
                    return '单人间';
                    break;
                case 2:
                    return '双人间';
                    break;
                case 4:
                    return '四人间';
                    break;
                default:
                    return '未知';
            }
        },
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
    },
    mounted:function() {
        let config = {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        };
        axios.post('/AllPersonalOrder',new FormData,config)
            .then(function (response) {
                let data = response.data;
                vue.allRooms= data.resultRoom;
                vue.allOrders = data.resultOrder;
                console.log(response.data)
                console.log(vue.allRooms)
            })
            .catch(function (error) {
                console.log(error);
            });

    },
})