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
    window.location.href='Admin_todo_complaint.jsp';
}
let vue=new Vue({
    el: '#app',
    data() {
        var validateText = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入文字描述'));
            } else {
                callback();
            }
        };
        return {
            showComplaint:[],
            form:{
                Reply:''
            },
            replyForm:{
                textarea: '',
                CoNum:''
            },
            DialogVisible:false,
            rules: {
                textarea: [
                    { validator: validateText,trigger: 'blur' }
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
        reply(row){
            this.DialogVisible=true;
            this.replyForm.CoNum=row.CoNum;
        },
        closeform(){
            this.DialogVisible=false;
            this.replyForm.CoNum='';
            this.replyForm.textarea='';
        },
        replyform(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    let formData = new FormData();
                    formData.append('CoNum',this.replyForm.CoNum);
                    formData.append('Reply',this.replyForm.textarea);
                    let config = {
                        headers: {
                            'Content-Type': 'multipart/form-data'
                        }
                    };
                    axios.post('/ReplyComplaint',formData,config)
                        .then(function (response) {
                            successmessage('回复成功');
                            setTimeout(refresh,2000);
                        })
                        .catch(function (error) {
                            errormessage('回复失败，请检查');
                            console.log(error);
                        });
                    this.DialogVisible=false;
                } else {
                    this.$notify.warning({
                        title: '警告',
                        message: `请检查您的输入是否正确`
                    });
                    return false;
                }
            });
        },
    },
    mounted:function() {
        let config = {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        };
        axios.post('/AllComplaint',new FormData,config)
            .then(function (response) {
                vue.showComplaint= response.data;
                console.log(vue.showComplaint);
            })
            .catch(function (error) {
                console.log(error);
            });
    },
})
