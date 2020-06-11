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
    window.location.href='personal_center.jsp';
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
                        if(customer.Cnum != vue.sizeForm.Cnum && customer.Email == vue.sizeForm.Email){
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
                        if(customer.Cnum != vue.sizeForm.Cnum && customer.Phone == vue.sizeForm.Phone){
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
                if (value !== '') {
                    for(let customer of vue.allCustomers){
                        if(customer.Cnum != vue.sizeForm.Cnum && customer.Name == vue.sizeForm.Name){
                            callback(new Error('名字已被使用'))
                        }
                    }
                }
                callback();
            }
        };
        var validatePass = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入密码'));
            } else {
                if (this.codeForm.code !== '') {
                    this.$refs.codeForm.validateField('checkPass');
                }
                callback();
            }
        };
        var validatePass2 = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请再次输入密码'));
            } else if (value !== this.codeForm.code) {
                callback(new Error('两次输入密码不一致!'));
            } else {
                callback();
            }
        };
        var validatePass3 = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入原密码'));
            } else if (value !== this.CurrentCustomer.Code) {
                callback(new Error('密码错误!'));
            } else {
                callback();
            }
        };
        return{
            size:64,
            CurrentCustomer:{},
            allCustomers:{},
            sizeForm: {
                Cnum:'',
                Name:'',
                Email:'',
                Phone:'',
                Code:'',
            },
            codeForm:{
                code: '',
                checkcode:'',
                ocode:''
            },
            avatarPath:"",
            addForm:{
                File:"",
            },
            formInline: {
                keywords: '',
            },
            squareUrl: "https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png",
            centerDialogVisible: false,
            codeDialogVisible:false,
            rules: {
                Name: [
                    { validator: validateUserName,trigger: 'blur' }
                ],
                Email: [
                    { validator:validateEmail, trigger: 'blur' },
                ],
                Phone: [
                    {validator:validateMobilePhone,trigger:'blur'}
                ],
            },
            rules2:{
                code:[
                    {validator:validatePass,trigger:'blur'}
                ],
                checkcode:[
                    {validator:validatePass2,trigger:'blur'}
                ],
                ocode:[
                    {validator:validatePass3,trigger:'blur'}
                ]
            },
        }
    },
    methods: {
        ModifyCustomer(){
            this.centerDialogVisible = true;
            this.sizeForm.Code = this.CurrentCustomer.Code;
            this.sizeForm.Phone=this.CurrentCustomer.Phone;
            this.sizeForm.Email=this.CurrentCustomer.Email;
            this.sizeForm.Cnum=this.CurrentCustomer.Cnum;
            this.sizeForm.Name=this.CurrentCustomer.Name;
        },
        ModifyCode(){
            this.codeDialogVisible=true;
        },
        closeForm(forname){
            if (this.$refs[forname]!==undefined) {
                this.$refs[forname].resetFields();
            }
        },
        handleClose(){
            this.closeForm('sizeForm');
            this.centerDialogVisible=false;
        },
        handlecodeClose(){
            this.closeForm('codeForm');
            this.codeDialogVisible=false;
        },
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    //修改基本信息
                    let formData = new FormData();
                    console.log(this.sizeForm)
                    formData.append('data', JSON.stringify(this.sizeForm))
                    let config = {
                        headers: {
                            'Content-Type': 'multipart/form-data'
                        }
                    };
                    axios.post('/ModifyCustomer', formData, config)
                        .then(function (response) {
                            successmessage("修改成功");
                            console.log(response)
                            this.CurrentCustomer = this.sizeForm
                            vue.centerDialogVisible = false;
                        })
                        .catch(function (error) {
                            errormessage("修改失败，请检查");
                        });
                    //上传头像
                    if (this.addForm.File) {
                        formData = new FormData();
                        formData.append('File', this.addForm.File);
                        axios.post('/ModifyAvator', formData, config)
                            .then(function (response) {
                                successmessage("头像上传成功");
                                console.log(response)
                                setTimeout(refresh,5000);
                            })
                            .catch(function (error) {
                                errormessage("头像上传失败，请检查");
                            });
                    }
                }
            })
        },
        submitcodeForm(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    let formData = new FormData();
                    this.sizeForm.Code=this.codeForm.code;
                    this.sizeForm.Phone=this.CurrentCustomer.Phone;
                    this.sizeForm.Email=this.CurrentCustomer.Email;
                    this.sizeForm.Cnum=this.CurrentCustomer.Cnum;
                    this.sizeForm.Name=this.CurrentCustomer.Name;
                    console.log(this.sizeForm);
                    formData.append('data', JSON.stringify(this.sizeForm))
                    let config = {
                        headers: {
                            'Content-Type': 'multipart/form-data'
                        }
                    };
                    axios.post('/ModifyCustomer', formData, config)
                        .then(function (response) {
                            successmessage("修改成功");
                            setTimeout(refresh,2000);
                        })
                        .catch(function (error) {
                            errormessage("修改失败，请检查");
                        });
                }
            })
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
        handleRemove(file, fileList) {
            console.log(file, fileList);
        },
        handlePreview(file) {
            console.log(file);
        },
        linkto(location){
            window.location.href=location;
        },
        FileChange(file){
            this.addForm.File=file.raw;
        },
    },
    mounted(){
        axios.post('/CurrentCustomer',new FormData,{headers: {'Content-Type': 'multipart/form-data'}})
            .then(function (response) {
                vue.CurrentCustomer = response.data;
                console.log(vue.CurrentCustomer)
                vue.avatarPath = "CustomerPic/"+(vue.CurrentCustomer.Cnum)+".jpg"
            })
            .catch(function (error) {
                console.log(error);
            });
        axios.post('/AllCustomer',new FormData,{headers: {'Content-Type': 'multipart/form-data'}})
            .then(function (response) {
                vue.allCustomers = response.data;
                console.log(vue.allCustomers);
            })
            .catch(function (error) {
                errormessage("连接数据库失败，自动刷新");
                setTimeout(refresh,2000);
            });
    }
})
