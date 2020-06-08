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
            avatarPath:"",
            addForm:{
                File:"",
            },
            formInline: {
                keywords: '',
            },
            squareUrl: "https://cube.elemecdn.com/9/c2/f0ee8a3c7c9638a54940382568c9dpng.png",
            centerDialogVisible: false,
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
    methods: {
        ModifyCustomer(){
            this.centerDialogVisible = true
            this.sizeForm = this.CurrentCustomer;
        },
        test(){
            console.log("test");
            console.log(this.CurrentCustomer)
        },
        onSubmit() {
            console.log('submit!');
        },
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    //修改基本信息
                    let formData = new FormData();
                    console.log(this.sizeForm)
                    formData.append('data',JSON.stringify(this.sizeForm))
                    let config = {
                        headers: {
                            'Content-Type': 'multipart/form-data'
                        }
                    };
                    axios.post('/ModifyCustomer',formData,config)
                        .then(function (response) {
                            vue.modifyDialogVisible = false;
                            console.log(response)
                            this.CurrentCustomer = this.sizeForm
                        })
                        .catch(function (error) {
                            console.log(error);
                        });
                    //上传头像
                    if(this.addForm.File) {
                        formData = new FormData();
                        formData.append('File', this.addForm.File);
                        axios.post('/ModifyAvator', formData, config)
                            .then(function (response) {
                                vue.modifyDialogVisible = false;
                                console.log(response)
                                vue.CurrentCustomer = vue.sizeForm
                            })
                            .catch(function (error) {
                                console.log(error);
                            });
                    }
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
            this.centerDialogVisible = false;
            console.log(formName);
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
