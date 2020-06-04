let vue = new Vue({
    el: '#app',
    data() {
        return {
            keyword:"",
            allRooms:{},
            showRooms:{},
            form:{

            },
            tableData: [{
                url: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
                Raddress: '北京航空航天大学',
                Capacity: '四人间',
                Empty: 2,
                Rname: 'BUAA',
                CostPerDay: '223',
            },
            ]
        }
    },
    methods: {
        CapacityToString(Capacity) {
            switch(Capacity){
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
        findRoomByKeyword(){//根据关键字查询
            let keyWord = this.keyword
            this.showRooms = this.allRooms.filter((c)=>(
                c.Rname.indexOf(keyWord)!=-1 || c.Place.indexOf(keyWord)!=-1
            ))
        },
        onSubmit() {
            console.log('submit!');
        },
        quit() {
            axios.post('/logout', {}).then(function (response) {
                console.log(response);
                window.location.href = 'index.jsp'
            }).catch(function (error) {
                console.log(error);
            });
        },
        linkto(location) {
            window.location.href = location;
        },
        handleEdit(index, row) {
            console.log(index, row);
        },
        handleDelete(index, row) {
            console.log(index, row);
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