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
                File:'',
            },
            rules: {
                textarea: [
                    { validator: validateText,trigger: 'blur' }
                ],
                File: [
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
        FileChange(file){
            this.addForm.File=file.raw;
        },
    }
})
