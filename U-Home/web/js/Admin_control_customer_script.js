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
    window.location.href='Admin_control_customer.jsp';
}
let vue = new Vue({
    el: '#app',
    data() {
        var validateEmail = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入邮箱'));
            } else {
                if (value !== '') {
                    var reg=/^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
                    if(!reg.test(value)){
                        callback(new Error('邮箱不合法'));
                    }
                    for(let customer of vue.allCustomers){
                        if(customer.Cnum != vue.addForm.Cnum && customer.Email == vue.addForm.Email){
                            callback(new Error('邮箱已被使用'))
                        }
                    }
                }
                callback();
            }
        };
        var validateMobilePhone = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入手机号'));
            } else {
                if (value !== '') {
                    var reg=/^1[3456789]\d{9}$/;
                    if(!reg.test(value)){
                        callback(new Error('手机号不合法'));
                    }
                    for(let customer of vue.allCustomers){
                        if(customer.Cnum != vue.addForm.Cnum && customer.Phone == vue.addForm.Phone){
                            callback(new Error('手机号已被使用'))
                        }
                    }
                }
                callback();
            }
        };
        var validateUserName = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入用户名'));
            } else {
                for(let customer of vue.allCustomers){
                    if(customer.Cnum != vue.addForm.Cnum && customer.Name == vue.addForm.Name){
                        callback(new Error('用户名已被使用'))
                    }
                }
                callback();
            }
        };
        return{
            modifyDialogVisible: false,
            allCustomers:[],
            showCustomers:[],
            formInline: {
                user:""
            },
            addForm:{
                Cnum:"",
                Name:"",
                Email:"",
                Phone:"",
                Code:""
            },
            rules: {
                Name: [
                    { validator: validateUserName,trigger: 'blur' }
                ],
                Email: [
                    { validator:validateEmail, trigger: 'blur' },
                ],
                Phone: [
                    {validator:validateMobilePhone,trigger:'blur'}
                ]

            }
        }
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
                vue.showCustomers = vue.allCustomers;
                console.log(vue.allCustomers);
            })
            .catch(function (error) {
                errormessage("连接数据库失败，自动刷新");
                setTimeout(refresh,2000);
            });

    },
    methods: {
        handleModify(row){
            this.modifyDialogVisible = true;
            this.addForm.Name=row.Name;
            this.addForm.Email=row.Email;
            this.addForm.Cnum=row.Cnum;
            this.addForm.Phone=row.Phone;
            this.addForm.Code=row.Code;
        },
        handleClose(done){
            this.closeForm('addForm');
        },
        findCustomerByKeyword() {//查询用户
            let keyWord = this.formInline.user
            this.showCustomers = this.allCustomers.filter((c)=>(c.Name.indexOf(keyWord)!=-1))
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
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    let formData = new FormData();
                    formData.append('data',JSON.stringify(this.addForm))
                    let config = {
                        headers: {
                            'Content-Type': 'multipart/form-data'
                        }
                    };
                    console.log("JSON",JSON.stringify(this.addForm))
                    axios.post('/ModifyCustomer',formData,config)
                        .then(function (response) {
                            successmessage("修改成功");
                            setTimeout(refresh,2000);
                        })
                        .catch(function (error) {
                            errormessage("修改错误，请检查");
                        });
                }
            });
        },
        closeForm(formName){
            if (this.$refs[formName]!==undefined) {
                this.$refs[formName].resetFields();
            }
            this.modifyDialogVisible=false;
        },
        linkto(location){
            window.location.href=location;
        }
    }
})
