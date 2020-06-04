let vue = new Vue({
    el: '#app',
    data(){
        return{
            RoomDialogVisible:false,
            CustomerDialogVisible:false,
            CustomerForm:{
                Cnum:"",
                Name:"",
                Email:"",
                Phone:"",
            },
            RoomForm:{
                Rnum:'',
                Place:'',
                Rname:'',
                Capacity: '',
                CostPerDay: '',
            },
            allOrders:[],
            showOrders:[],
            allRooms:[],
            allCustomers:[],
        }
    },
    methods: {
        formatterColumn(row, column) {
            switch(row.Time){
                case true:
                    return '长期';
                    break;
                case false:
                    return '短期';
                default:
                    return 'WTF?!';
            }
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
        },
        CheckCustomer(row){
            this.CustomerDialogVisible=true;
            let showCustomer = this.allCustomers.filter((o)=>(o.Cnum == row.Cnum))
            if(showCustomer.length)
                this.CustomerForm = showCustomer[0]
            console.log(showCustomer)
        },
        CheckRoom(row){
            this.RoomDialogVisible=true;
            let showRoom = this.allRooms.filter((o)=>(o.Rnum == row.Rnum))
            if(showRoom.length)
                this.RoomForm = showRoom[0]
            console.log(showRoom)
        },
        //mode中1是未提交，2是未审核，3是未付款，4是已完成订单,5是审核不通过，
        CheckPass(row){
            if(row.Time){
                row.Mode = 4;
            }else{
                row.Mode = 3;
            }
            this.ModifyOrder(row)
        },
        CheckUnPass(row){
            row.Mode = 5;
            this.ModifyOrder(row)
        },
        ModifyOrder(order){
            let formDate = new FormData()
            formDate.append('data',JSON.stringify(order))
            axios.post('/ModifyOrder',formDate,{headers: {'Content-Type': 'multipart/form-data'}})
                .then(function (response) {
                    window.location.href='Admin_todo_apply.jsp';
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
    },
    mounted:function() {
        let config = {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        };
        axios.post('/AllOrder',new FormData,config)
            .then(function (response) {
                vue.allOrders= response.data;
                console.log(vue.allOrders)
                vue.showOrders = vue.allOrders.filter((o)=>(o.Mode == 2))//2是未审核
            })
            .catch(function (error) {
                console.log(error);
            });
        axios.post('/AllRoom',new FormData,config)
            .then(function (response) {
                vue.allRooms= response.data;
            })
            .catch(function (error) {
                console.log(error);
            });
        axios.post('/AllCustomer',new FormData,config)
            .then(function (response) {
                vue.allCustomers = response.data;
            })
            .catch(function (error) {
                console.log(error);
            });

    },
})
