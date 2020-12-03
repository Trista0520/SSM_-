var userName = null;
var submitBtn = null;

$(function(){
    userName = $("#userName");
    submitBtn = $("#submitBtn");

    /*
     * 验证
     * 失焦\获焦
     * jquery的方法传递
     */
    userName.bind("blur",function(){
        $.ajax({
            type:"Get",//请求类型
            url:"/b",//请求的url
            data:{userName:userName.val()},//请求参数
            success:function(data){//data：返回数据（json对象）
                if(data.toString()=== "noexist" || data.toString()=== "wrong"){//不存在该用户
                    $("#userNameInfo").css("color","red");
                    $("#userNameInfo").html("用户为空或不存在该用户");
                }else {
                    $("#userNameInfo").html("");
                }
            }
        });
    });

    submitBtn.bind("click",function(){
        if(userName.attr("validateStatus") != "true"){
            userName.blur();
        }else{
            if(confirm("是否确认提交数据")){
                $("#from1").submit();
            }
        }
    });                                              
});