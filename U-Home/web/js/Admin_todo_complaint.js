let vue=new Vue({
    el: '#app',
    data() {
        return {
            showComplaint:[],
            form:{
                Reply:''
            },
            ApplyVisible:false,
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
        axios.post('/AllComplaint',new FormData,config)
            .then(function (response) {
                vue.showComplaint= response.data;
                console.log(vue.showComplaint);
            })
            .catch(function (error) {
                console.log(error);
            });
    },
})
