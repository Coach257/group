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
    window.location.href='Admin_todo_repair.jsp';
}
let vue=new Vue({
    el: '#app',
    data(){
        return {
            showFix:[],
            submitForm:{
                Fnum:'',
                Wnum:''
            },
            allWorkers:[],
            showWorkers:[],
            dialogVisible:false,
            keyword:'',
            visible:false,
            innerVisible:false,
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
        choose(row){
            this.submitForm.Fnum=row.Fnum;
            console.log(row.Fnum);
            this.dialogVisible=true;
        },
        handleClose(){
            this.submitForm.Fnum='';
            this.dialogVisible=false;
            this.keyword='';
            this.findWorkerByKeyword();
        },
        findWorkerByKeyword(){//根据关键字查询
            let keyWord = this.keyword
            this.showWorkers = this.allWorkers.filter((c)=>(c.Name.indexOf(keyWord)!=-1))
        },
        select(row){
          this.submitForm.Wnum=row.Wnum;
          console.log(row.Wnum);
          this.innerVisible=true;
        },
        submit(){
            let formData = new FormData();
            formData.append('Fnum',this.submitForm.Fnum);
            formData.append('Wnum',this.submitForm.Wnum);
            let config = {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            };
            axios.post('/ChooseWorker',formData,config)
                .then(function (response) {
                    successmessage("处理成功！");
                    setTimeout(refresh,1000);
                })
                .catch(function (error) {
                    errormessage("处理失败，请检查");
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
        axios.post('/AllFix',new FormData,config)
            .then(function (response) {
                vue.showFix= response.data;
                console.log(vue.showFix);
            })
            .catch(function (error) {
                errormessage("连接数据库失败，自动刷新");
                setTimeout(refresh,2000);
            });
        axios.post('/AllWorker',new FormData,config)
            .then(function (response) {
                vue.allWorkers= response.data;
                vue.showWorkers = vue.allWorkers;
                console.log(vue.allWorkers);
            })
            .catch(function (error) {
                errormessage("连接数据库失败，自动刷新");
                setTimeout(refresh,2000);
            });
    },
})
