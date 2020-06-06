
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
    window.location.href='repair_order.jsp';
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
            showFix:[],
            replyForm:{
                textarea:'',
                Fnum:'',
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
        reply(row) {
          this.replyForm.Fnum=row.Fnum;
          this.DialogVisible=true;
        },
        closeform() {
          this.replyForm.textarea='';
          this.replyForm.Fnum='';
          this.DialogVisible=false;
        },
        replyform(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    let formData = new FormData();
                    formData.append('Fnum',this.replyForm.Fnum);
                    formData.append('Reply',this.replyForm.textarea);
                    let config = {
                        headers: {
                            'Content-Type': 'multipart/form-data'
                        }
                    };
                    axios.post('/DealFix',formData,config)
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
                    errormessage("错误，请检查");
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
        axios.post('/WorkerFix',new FormData,config)
            .then(function (response) {
                vue.showFix= response.data;
                console.log(vue.showFix);
            })
            .catch(function (error) {
                console.log(error);
            });
    },
})
