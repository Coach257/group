  $('.box').click(function(){
        $(this).toggleClass('selected');
  })
  new Vue({
      el:"#Search_submit",
      data:{
          over:false,
      },
      methods: {
          funover:function () {
              this.over=true;
          },
          funout:function () {
              this.over=false;
          }
      }

  })