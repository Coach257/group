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
    window.location.href='personal_reply.jsp';
}
let vue = new Vue({
    el: '#app',
    data() {
        return {
            showComplaint: [],
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
        axios.post('/AllPersonalComplaint',new FormData,config)
            .then(function (response) {
                vue.showComplaint = response.data;
                console.log(vue.showComplaint);
            })
            .catch(function (error) {
                console.log(error);
            });

    },
})
