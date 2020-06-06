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
    window.location.href='personal_complain.jsp';
}
let vue=new Vue({
    el: '#app',
    data() {
        var validateFile = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请上传图片'));
            } else {
                callback();
            }
        };
        var validateText = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入文字描述'));
            } else {
                callback();
            }
        };
        return{
            addForm:{
                textarea:'',
                file:'',
                fileList:[],
                limitNum:1
            },
            rules: {
                textarea: [
                    { validator: validateText,trigger: 'blur' }
                ],
                file: [
                    { validator:validateFile, trigger: 'blur' },
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
        handleRemove(file, fileList) {
            console.log(file, fileList);
        },
        handlePreview(file) {
            console.log(file);
        },
        linkto(location) {
            window.location.href=location;
        },
        FileChange(file, fileList) {
            this.$notify({
                title: '成功',
                message: '图片添加成功',
                type: 'success'
            });
            this.addForm.file = file.raw
        },
        exceedFile(files, fileList) {
            this.$notify.warning({
                title: '警告',
                message: `只能上传一张图片`
            });
        },
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    let formData = new FormData();
                    formData.append('File',this.addForm.file);
                    formData.append('ComplainContent',this.addForm.textarea);
                    let config = {
                        headers: {
                            'Content-Type': 'multipart/form-data'
                        }
                    };
                    axios.post('/NewFix',formData,config)
                        .then(function (response) {
                            successmessage('添加成功，我们会尽快处理您的报修');
                            setTimeout(refresh,2000);
                        })
                        .catch(function (error) {
                            errormessage('添加失败，请联系管理员');
                            console.log(error);
                        });
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
})
