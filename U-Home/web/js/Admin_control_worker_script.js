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
    window.location.href='Admin_control_worker.jsp';
}
let vue =new Vue({
    el: '#app',
    data(){
        var validateName = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入师傅姓名'));
            } else {
                callback();
            }
        };
        var validateCode = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入师傅密码'));
            } else {
                callback();
            }
        };
        return {
            keyword:"",
            allWorkers:[],
            showWorkers:[],
            dialogVisible: false,
            addForm:{
                Name:'',
                Code:'',
            },
            rules: {
                Name: [
                    { validator: validateName,trigger: 'blur' }
                ],
                Code: [
                    { validator:validateCode, trigger: 'blur' },
                ],
            }
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
        handleModify(){
            this.dialogVisible = true;
            console.log(this.dialogVisible);
        },
        findWorkerByKeyword(){//根据关键字查询
            let keyWord = this.keyword
            this.showWorkers = this.allWorkers.filter((c)=>(c.Name.indexOf(keyWord)!=-1))
        },
        closeForm(formName){
            if (this.$refs[formName]!==undefined) {
                this.$refs[formName].resetFields();
            }
            this.dialogVisible=false;
        },
        handleClose(formName){
            this.closeForm(formName);
        },
        submitForm(formName){
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    let formData = new FormData();
                    formData.append('Name',this.addForm.Name);
                    formData.append('Code',this.addForm.Code);
                    let config = {
                        headers: {
                            'Content-Type': 'multipart/form-data'
                        }
                    };
                    axios.post('/NewWorker',formData,config)
                        .then(function (response) {
                            successmessage("添加成功");
                            setTimeout(refresh,2000);
                        })
                        .catch(function (error) {
                            errormessage("添加失败，请检查");
                            console.log(error);
                        });
                    this.dialogVisible=false;
                }
            });
        }
    },
    mounted:function() {
        let config = {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        };
        axios.post('/AllWorker',new FormData,config)
            .then(function (response) {
                vue.allWorkers= response.data;
                vue.showWorkers = vue.allWorkers;
            })
            .catch(function (error) {
                console.log(error);
            });
    },
})
