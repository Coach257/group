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
    window.location.href='Admin_control_home.jsp';
}
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
                file:'',
                fileList:[],
                limitNum:1
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
        formatterColumn(row, column) {
            switch(row.Capacity){
                case 1:
                    return '单人间';
                    break;
                case 2:
                    return '双人间';
                    break;
                case 4:
                    return '四人间';
                    break;
                default:
                    return '未知';
            }
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
        findRoomByKeyword(){//根据关键字查询
            let keyWord = this.keyword
            this.showRooms = this.allRooms.filter((c)=>(
                c.Rname.indexOf(keyWord)!=-1 || c.Place.indexOf(keyWord)!=-1
            ))
        },
        changestate(row){//暂停、恢复出租
            row.CanUse = !row.CanUse
            let formData = new FormData();
            for(let key in row){
                formData.append(key,row[key])
            }
            let config = {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            };
            axios.post('/ModifyRoomCanUse',formData,config)
                .then(function (response) {
                    successmessage("修改成功");
                })
                .catch(function (error) {
                    errormessage("修改失败，请检查");
                    console.log(error);
                });
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
                    formData.append('File',this.addForm.file);
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
                            successmessage("添加成功");
                            setTimeout(refresh,2000);
                        })
                        .catch(function (error) {
                            errormessage("添加失败，请检查");
                            console.log(error);
                        });
                }
            });
        },
        closeForm(formName){
            if (this.$refs[formName]!==undefined) {
                this.$refs[formName].resetFields();
            }
            this.$refs.upload.clearFiles()
            this.dialogVisible=false;
        },
        handleClose(done){
            this.closeForm('addForm');
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
                console.log(vue.allRooms);
            })
            .catch(function (error) {
                errormessage("连接数据库失败，自动刷新");
                setTimeout(refresh,2000);
            });
    },
})
