document.querySelector('.img__btn').addEventListener('click', function() {
    document.querySelector('.content').classList.toggle('s--signup')
})
function refresh() {
    window.location.href="login.jsp";
}

new Vue({
    el:"#formsignin",
    data() {
        var validatePass = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入密码'));
            } else {
                if (this.ruleForm.checkPass !== '') {
                    this.$refs.ruleForm.validateField('checkPass');
                }
                callback();
            }
        };
        var validatePass2 = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请再次输入密码'));
            } else if (value !== this.ruleForm.pass) {
                callback(new Error('两次输入密码不一致!'));
            } else {
                callback();
            }
        };
        var validateEmail = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入邮箱'));
            } else {
                if (value !== '') {
                    var reg=/^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
                    if(!reg.test(value)){
                        callback(new Error('邮箱不合法'));
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
                }
                callback();
            }
        };
        var validateUsername = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入用户名'));
            } else {
                callback();
            }
        };
        return {
            ruleForm: {
                pass: '',
                checkPass: '',
                username: '',
                email:'',
                phone:''
            },
            rules: {
                pass: [
                    { validator: validatePass, trigger: 'blur' }
                ],
                checkPass: [
                    { validator: validatePass2, trigger: 'blur' }
                ],
                username: [
                    { validator: validateUsername,trigger: 'blur' }
                ],
                email: [
                    { validator:validateEmail, trigger: 'blur' },
                ],
                phone: [
                    {validator:validateMobilePhone,trigger:'blur'}
                ]

            }
        };
    },
    methods: {
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    let formData = new FormData();
                    formData.append('username', this.ruleForm.username);
                    formData.append('password', this.ruleForm.pass);
                    formData.append('email', this.ruleForm.email);
                    formData.append('phone',this.ruleForm.phone);
                    let config = {
                        headers: {
                            'Content-Type': 'multipart/form-data'
                        }
                    };
                    axios.post('/signin',formData,config)
                        .then(function (response) {
                            if (response.data==null){
                                $("#signinresult").html("</labe><span style=\"color: blue;margin: auto; \">"
                                    + "注册成功"+
                                    "</span></labe>");
                                setTimeout(refresh(), 1000);
                            }else {
                                $("#signinresult").html("</labe><span style=\"color: #ff0000;margin: auto; \">"
                                    + response.data +
                                    "</span></labe>");
                            }
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
    }
})
