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
                vue.showCustomers = vue.allCustomers
                console.log(vue.allCustomers)
            })
            .catch(function (error) {
                console.log(error);
            });

    },
    methods: {
        handleDelete(row){//只传Cnum
            let formData = new FormData();
            formData.append('Cnum',row.Cnum);
            let config = {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            };
            axios.post('/DeleteCustomer',formData,config)
                .then(function (response) {
                    console.log(response)
                })
                .catch(function (error) {
                    console.log(error);
                });

            //更新页面
            for(let i in this.allCustomers){
                let c = this.allCustomers[i];
                if(c.Cnum == row.Cnum){
                    this.allCustomers.splice(i,1);
                    break;
                }
            }
            for(let i in this.showCustomers){
                let c = this.showCustomers[i];
                if(c.Cnum == row.Cnum){
                    this.showCustomers.splice(i,1);
                    break;
                }
            }

        },
        handleModify(row){
            this.modifyDialogVisible = true;
            this.addForm = row;
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
                    axios.post('/ModifyCustomer',formData,config)
                        .then(function (response) {
                            vue.modifyDialogVisible = false;
                            console.log(response)
                        })
                        .catch(function (error) {
                            console.log(error);
                        });
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        closeForm(formName){
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    this.modifyDialogVisible=false;
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        linkto(location){
            window.location.href=location;
        }
    }
})
