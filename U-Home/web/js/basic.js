new Vue({
    el:"#main_head",
    data:{
      isfixed:false,
    },
    mounted(){
      window.addEventListener("scroll",this.fun)
    },
    destroy(){
      window.removeEventListener("scroll",this.fun)
    },
    methods:{
      fun:function () {
        var x=window.pageYOffset;
        if(x>0){
          this.isfixed=true;
        }
        else {
          this.isfixed=false;
        }

      }
    }
  })
