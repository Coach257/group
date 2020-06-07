let vue = new Vue({
    el: '#app',
    data(){
        return{

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
            let form = this.form
            console.log(form.time,form.beginDate,form.endDate)
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
    }
})