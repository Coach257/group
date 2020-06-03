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
        CheckPass(row){

        },
        CheckUnPass(row){

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
                vue.showOrders = vue.allOrders;
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
