let vue = new Vue({
    el: '#app',
    data(){
        return{
            SelectCustomerDialogVisible:false,
            SelectRoomDialogVisible:false,

            allCustomers:{},
            allRooms:{},

            showCustomers:[{}],
            showRooms:[{}],

            form:{
                time:"",
                beginDate:"",
                endDate:"",
            },
            startDatePicker: this.beginDate(),
            endDatePicker: this.processDate(),
        }
    },
    methods: {
        onSubmit(){

            let customer = this.showCustomers[0];
            let room = this.showRooms[0];
            let form = this.form
            let formData = new FormData;
            formData.append('Customer',JSON.stringify(customer))
            formData.append('Room',JSON.stringify(room))
            formData.append('Time',form.time)
            formData.append('beginDate',form.beginDate)
            formData.append('endDate',form.endDate)

            let config = {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            };

            axios.post('/NewOrder',formData,config)
                .then(function (response) {
                    alert('提交成功');
                    window.location.href='Admin_offline_rent.jsp';
                })
                .catch(function (error) {
                    alert('未知错误')
                    console.log(error);
                });

        },
        SelectCustomer(customer){
            console.log(customer)
            this.showCustomers = [customer]
            this.SelectCustomerDialogVisible = false;
        },
        SelectRoom(room){
            this.showRooms = [room]
            this.SelectRoomDialogVisible = false;
        },
        handleCloseCustomer(done){
            this.SelectCustomerDialogVisible = false;
        },
        handleCloseRoom(done){
            this.SelectRoomDialogVisible = false;
        },
        formatterColumn(row, column) {
            switch(row.Capacity){
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
        beginDate(){
            const self = this
            return {
                disabledDate(time){
                    if (self.form.endDate) {  //如果结束时间不为空，则小于结束时间
                        return new Date(self.form.endDate).getTime() < time.getTime()
                    } else {
                        return time.getTime() < Date.now()//结束时间不选时，开始时间最小值大于等于当天
                    }
                }
            }
        },
        processDate() {
            const self = this
            return {
                disabledDate(time) {
                    if (self.form.beginDate) {  //如果开始时间不为空，则结束时间大于开始时间,且大于当前时间
                        return new Date(self.form.beginDate).getTime() > time.getTime() || time.getTime() < Date.now() - 1000*3600*24
                    } else {
                        return time.getTime() < Date.now() - 1000*3600*24//开始时间不选时，结束时间最大值大于等于当天
                    }
                }
            }
        },
    },
    mounted:function() {
        let config = {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        };
        axios.post('/AllCustomer',new FormData,config)
            .then(function (response) {
                vue.allCustomers = response.data;
                console.log(vue.allCustomers);
            })
            .catch(function (error) {
                errormessage("连接数据库失败，自动刷新");
                setTimeout(refresh,2000);
            });
        axios.post('/AllRoom',new FormData,config)
            .then(function (response) {
                vue.allRooms= response.data;
                console.log(vue.allRooms);
            })
            .catch(function (error) {
                errormessage("连接数据库失败，自动刷新");
                setTimeout(refresh,2000);
            });

    },
})
