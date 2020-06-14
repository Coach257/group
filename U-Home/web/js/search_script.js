
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
    window.location.href='search.jsp';
}
let vue = new Vue({
    el: '#app',
    data() {
        return {
            order:{

            },
            keyword:"",
            allRooms:[],
            showRooms:[],
            form:{
                startTime:'',
                endTime:'',
            },
            tableData: [],
            RentVisible:false,
            startDatePicker: this.beginDate(),
            endDatePicker: this.processDate(),
        }
    },
    methods: {
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
        findRoomByKeyword(){//根据关键字查询
            let keyWord = this.keyword
            this.showRooms = this.allRooms.filter((c)=>(
                c.Rname.indexOf(keyWord)!=-1 || c.Place.indexOf(keyWord)!=-1
            ))
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
        rent(row,time){
            this.order.row = row;
            this.order.time = time;
            this.RentVisible=true;
        },
        closeForm(){
            this.RentVisible=false;
        },
        submitForm(){
            console.log(this.form.startTime);
            console.log(this.form.endTime);
            this.RentVisible=false;
            let formData = new FormData;
            let config = {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            };

            console.log(JSON.stringify(this.order.row))
            formData.append('Room',JSON.stringify(this.order.row))
            formData.append('Time',this.order.time)
            formData.append('startTime',this.form.startTime)
            formData.append('endTime',this.form.endTime)

            axios.post('/RentRoom',formData,config)
                .then(function (response) {
                    successmessage("租房成功");
                    setTimeout(refresh,2000);
                })
                .catch(function (error) {
                    errormessage("操作超时，将自动刷新");
                    setTimeout(refresh,2000);
                });
        },
        beginDate(){
            const self = this
            return {
                disabledDate(time){
                    if (self.form.endTime) {  //如果结束时间不为空，则小于结束时间
                        return new Date(self.form.endTime).getTime() < time.getTime()
                    } else {
                         return time.getTime() < Date.now()//结束时间不选时，开始时间最小值大于等于当天
                    }
                }
            }
        },
        processDate() {
            const  self = this
            return {
                disabledDate(time) {
                    if (self.form.startTime) {  //如果开始时间不为空，则结束时间大于开始时间,且大于当前时间
                        return new Date(self.form.startTime).getTime() > time.getTime() || time.getTime() < Date.now() - 1000*3600*24
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
        axios.post('/AllSuitableRoom',new FormData,config)
            .then(function (response) {
                vue.allRooms= response.data;
                vue.showRooms = vue.allRooms;
            })
            .catch(function (error) {
                console.log(error);
            });
    },
})
