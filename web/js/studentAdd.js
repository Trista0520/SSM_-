var userID = null;
var userName = null;
var sex = null;
var birthYear = null;
var grade = null;
var collegeID = null;
var submitBtn = null;
var resetBtn = null;


$(function(){
    userID = $("#userID");
    userName = $("#userName");
    sex = $("#sex");
    birthYear = $("#birthYear");
    grade = $("#grade");
    collegeID = $("#collegeID");
    submitBtn = $("#submit");
    resetBtn = $("#reset");

    /*
     * 验证
     * 失焦\获焦
     * jquery的方法传递
     */
    userID.bind("blur",function(){
        $.ajax({
            type:"GET",//请求类型
            url:"/a",//请求的url
            data:{userID:userID.val()},//请求参数
            success:function(data){//data：返回数据（json对象）
                if(data.toString()=== "exist" || data.toString()=== "wrong"){//账号已存在，错误提示
                    $("#userID").parent().removeClass("has-success");
                    $("#userID").parent().addClass("has-error");
                    $("#info-panel").removeClass("glyphicon glyphicon-ok form-control-feedback");
                    $("#info-panel").addClass("glyphicon glyphicon-remove form-control-feedback");
                    $("#userInfo").css("color","red");
                    $("#userInfo").html("用户为空或已存在");
                }else {//账号可用，正确提示
                    $("#userID").parent().removeClass("has-error");
                    $("#userID").parent().addClass("has-success");
                    $("#info-panel").removeClass("glyphicon glyphicon-remove form-control-feedback");
                    $("#info-panel").addClass("glyphicon glyphicon-ok form-control-feedback");
                    $("#userInfo").css("color","green");
                    $("#userInfo").html("");
                }
            }
        });
    });

    userName.bind("blur",function(){
        if( userName.val() === "" || userName.val() == null){//用户名为空
            $("#userName").parent().removeClass("has-success");
            $("#userName").parent().addClass("has-error");
            $("#info-panel1").removeClass("glyphicon glyphicon-ok form-control-feedback");
            $("#info-panel1").addClass("glyphicon glyphicon-remove form-control-feedback");
            $("#nameInfo").css("color","red");
            $("#nameInfo").html("用户名为空")
        }else {
            $("#userName").parent().removeClass("has-error");
            $("#userName").parent().addClass("has-success");
            $("#info-panel1").removeClass("glyphicon glyphicon-remove form-control-feedback");
            $("#info-panel1").addClass("glyphicon glyphicon-ok form-control-feedback");
            $("#nameInfo").css("color","green");
            $("#nameInfo").html("")
        }
    });

    submitBtn.bind("click",function(){
        if(userID.attr("validateStatus") != "true"){
            userID.blur();
        }else if(userName.attr("validateStatus") != "true"){
            userName.blur();
        }else{
            if(confirm("是否确认提交数据")){
                $("#editfrom").submit();
            }
        }
    });

    resetBtn.on("click",function(){
        if(referer != undefined
            && null != referer
            && "" != referer
            && "null" != referer
            && referer.length > 4){
            window.location.href = referer;
        }else{
            history.back(-1);
        }
    });
});