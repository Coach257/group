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
            keyword:'',
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
        findOrderByKeyword: function () {//查询用户
            let keyWord = this.keyword
            this.showOrders = this.allOrders.filter((c)=>(
                c.Onum.toString().indexOf(keyWord)!=-1 || c.Cnum.toString().indexOf(keyWord)!=-1 || c.Rnum.toString().indexOf(keyWord)!=-1
            ))
        },
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
                vue.showOrders = vue.allOrders.filter((o)=>(o.Mode === 4 && o.Time===true))
                console.log(vue.showOrders)
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
