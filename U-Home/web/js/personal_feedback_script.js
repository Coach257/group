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
    window.location.href='personal_feedback.jsp';
}
let vue=new Vue({
    el: '#app',
    data(){
        return {
            showFix:[],
            allWorkers:[],
            showWorker:{
              Wnum:'',
              Name:'',
              Code:'',
              DealTime:'',
              Score:''
            },
            dialogFormVisible:false,
            visible:false,
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
        check(row){
            this.dialogFormVisible=true;
            let showWorkers = this.allWorkers.filter((o)=>(o.Wnum == row.Wnum))
            if(showWorkers.length){
                this.showWorker=showWorkers[0];
            }
            console.log(this.showWorker);
        },
        submit(row){
            let formData = new FormData();
            formData.append('Value',row.value);
            formData.append('Fnum',row.Fnum);
            let config = {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            };
            axios.post('/JudgeWorker',formData,config)
                .then(function (response) {
                    successmessage("评价成功！");
                    setTimeout(refresh,2000);
                })
                .catch(function (error) {
                    errormessage("评价失败，请检查");
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
        axios.post('/MyFix',new FormData,config)
            .then(function (response) {
                vue.showFix= response.data;
                console.log(vue.showFix);
            })
            .catch(function (error) {
                console.log(error);
            });
        axios.post('/AllWorker',new FormData,config)
            .then(function (response) {
                vue.allWorkers= response.data;
            })
            .catch(function (error) {
                console.log(error);
            });
    },
})
