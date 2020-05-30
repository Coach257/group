let vue = new Vue({
    el: '#app',
    data:{
        keyword:"",
        allRooms:[],
        showRooms:[],
        dialogVisible: false,
        options: [{
            value: '选项1',
            label: '单人房'
        }, {
            value: '选项2',
            label: '双人房'
        }, {
            value: '选项3',
            label: '四人房'
        },],
        dialogImageUrl: '',
        dialogimgVisible: false,
        addForm:{
            Rnum:'',
            RName:'',
            Place: '',
            Capacity: '',
            CostPerDay: '',
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
            this.showRooms = this.allRooms.filter((c)=>(c.name.indexOf(keyWord)!=-1))
        },
        changestate(){//暂停、恢复出租

        },
        handleModify(){
            this.dialogVisible = true;
        },
        handleRemove(file, fileList) {
            console.log(file, fileList);
        },
        handlePictureCardPreview(file) {
            this.dialogImageUrl = file.url;
            this.dialogVisible = true;
        },
        submitForm(formName) {
            this.dialogVisible=false;
        },
        closeForm(formName){
            this.dialogVisible=false;
        },
        handleClose(done){
            this.closeForm('addForm');
        },
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