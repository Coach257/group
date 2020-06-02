let vue = new Vue({
    el: '#app',
    data(){
        var validateName = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入房间名称'));
            } else {
                callback();
            }
        };
        var validatePlace = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入房间地址'));
            } else {
                callback();
            }
        };
        var validateCostPerDay = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入日租金'));
            } else {
                if (value !== '') {
                    var reg=/^[0-9]*$/;
                    if(!reg.test(value)){
                        callback(new Error('日租金不合法'));
                    }
                }
                callback();
            }
        };
        var validateCapacity = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请选择类型'));
            } else {
                callback();
            }
        };
        return {
            keyword:"",
            allRooms:[],
            showRooms:[],
            dialogVisible: false,
            options: [{
                value: 1,
                label: '单人房'
            }, {
                value: 2,
                label: '双人房'
            }, {
                value: 4,
                label: '四人房'
            },],
            dialogImageUrl: '',
            dialogimgVisible: false,
            addForm:{
                Place:'',
                Rname:'',
                Capacity: '',
                CostPerDay: '',
                File:'',
            },
            rules: {
                Rname: [
                    { validator: validateName,trigger: 'blur' }
                ],
                Place: [
                    { validator:validatePlace, trigger: 'blur' },
                ],
                Capacity: [
                    { validator:validateCapacity, trigger: 'blur' },
                ],
                CostPerDay: [
                    { validator:validateCostPerDay, trigger: 'blur' },
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
        findRoomByKeyword(){//根据关键字查询
            let keyWord = this.keyword
            this.showRooms = this.allRooms.filter((c)=>(c.Rname.indexOf(keyWord)!=-1))
        },
        changestate(){//暂停、恢复出租

        },
        handleModify(){
            this.dialogVisible = true;
        },
        handleRemove(file, fileList) {
            console.log(file, fileList);
        },
        handlePreview(file) {
            console.log(file);
        },
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
                    if (valid) {
                        let formData = new FormData();
                        formData.append('File',this.addForm.File);
                        formData.append('Rname',this.addForm.Rname);
                        formData.append('Place',this.addForm.Place);
                        formData.append('Capacity',this.addForm.Capacity);
                        formData.append('CostPerDay',this.addForm.CostPerDay);
                        let config = {
                            headers: {
                                'Content-Type': 'multipart/form-data'
                            }
                        };
                        axios.post('/NewRoom',formData,config)
                            .then(function (response) {
                                alert('成功');
                            })
                            .catch(function (error) {
                                alert('信息不合法')
                                console.log(error);
                            });
                        this.dialogVisible=false;
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
            });
        },
        closeForm(formName){
            this.dialogVisible=false;
        },
        handleClose(done){
            this.closeForm('addForm');
        },
        FileChange(file){
          this.addForm.File=file.raw;
        },
        linkto(location){
            window.location.href=location;
        }
    },
    mounted:function() {
        let config = {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        };
        axios.post('/AllRoom',new FormData,config)
            .then(function (response) {
                vue.allRooms= response.data;
                vue.showRooms = vue.allRooms;
            })
            .catch(function (error) {
                console.log(error);
            });
    },
})
