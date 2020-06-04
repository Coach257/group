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
                    /*
                    for(let customer of vue.allCustomers){
                        if(customer.Cnum != vue.sizeForm.Cnum && customer.Email == vue.sizeForm.Email){
                            callback(new Error('邮箱已被使用'))
                        }
                    }*/
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
                    /*
                    for(let customer of vue.allCustomers){
                        if(customer.Cnum != vue.sizeForm.Cnum && customer.Phone == vue.sizeForm.Phone){
                            callback(new Error('手机号已被使用'))
                        }
                    }*/
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
            size:64,
            CurrentCustomer:{},
            sizeForm: {},
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
            this.sizeForm = this.CurrentCustomer
        },
        test(){
            console.log("test");
            console.log(this.CurrentCustomer)
        },
        onSubmit() {
            console.log('submit!');
        },
        submitForm(formName) {
            this.centerDialogVisible = false
            console.log(formName)
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    let formData = new FormData();
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
        }
    },
    mounted(){
        axios.post('/CurrentCustomer',new FormData,{headers: {'Content-Type': 'multipart/form-data'}})
            .then(function (response) {
                vue.CurrentCustomer = response.data;
            })
            .catch(function (error) {
                console.log(error);
            });
        console.log('hi',this.CurrentCustomer)
    }
})