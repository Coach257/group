let vue = new Vue({
    el: '#app',
    data() {
        return{
            allOrders:{},
            allRooms:{},
            formInline: {
                keywords: '',
            }
        }
    },
    methods: {
        pay(order){
            console.log(order)

            let config = {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            };
            let formData = new FormData;
            formData.append('Order',JSON.stringify(order))

            axios.post('/PayOrder',formData,config)
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