let vue = new Vue({
    el: '#app',
    data:{
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
            Raddress:'',
            Rname:'',
            Capacity: '',
            CostPerDay: '',
            File:'',
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
            let formData = new FormData();
            formData.append('File',this.addForm.File);
            formData.append('Rname',this.addForm.Rname);
            formData.append('Raddress',this.addForm.Raddress);
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
                    console.log(error);
                });
            this.dialogVisible=false;
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
