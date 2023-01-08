$(function(){
	$('#saveForm').validate({
		rules: {
            name       :{required:true},
            userName   :{required:true}
		},messages:{
            name :{required:"必填"},
            userName :{required:"必填"}
        }
 	});
    $('.saveBtn').click(function(){
	 if($('#saveForm').valid()){
         $.ajax({
             type: "POST",
             url: "./save",
             data: $("#saveForm").serialize(),
             headers: {"Content-type": "application/x-www-form-urlencoded;charset=UTF-8"},
             success: function (data) {
                 if (data == 1) {
                     alert("保存成功");
                     pageaction();
                     closeDialog();
                 } else {
                     alert(data);
                 }
             }
         });
		 }else{
			 alert('数据验证失败，请检查！');
		 }
	});

    $('#userName').blur(function(){
        var $this = $('#userName');
        var userName = $this.val();
        $.get("./getUserName/"+userName,{ts:new Date().getTime()},function(data){
            if(data==userName){
                alert("用户名已经存在,请重新输入！");
                //$this.val("");
            }
        });
    });
});

