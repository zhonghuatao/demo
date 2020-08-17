<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="js/jquery.min.js"></script>

    <link rel="stylesheet" type="text/css" href="css/login.css">
    <script type="text/javascript">
        $(function () {
            $("#username").change(function () {
                //使用ajax 做username 的异步验证 checkUsername?username=xxxx
                $.get("${pageContext.request.contextPath}/login?methods=checkUserName", "username=" + this.value, function (data) {
                    if (data == 1) {
                        $("#usernameMsg").html("用户名不可用").css("color", "red");
                        $("#registerBtn").attr("disabled", true);
                    } else {
                        $("#usernameMsg").html("用户名可用").css("color", "green");
                        $("#registerBtn").removeAttr("disabled");
                    }
                })
            });
            $("#registerBtn").click(function () {
                var pwd = $('input[name="u_password"]').val();
                var pwdAgain = $('input[name="passwordAgain"]').val();
                if (pwd === pwdAgain && pwd != "" && pwd != null) {
                    $("#registerMsg").html("");
                    <%--$.get("${pageContext.request.contextPath}/reg?methods=register", "username="+$("#username").val()+"");--%>
                    $.ajax({
                        url:"${pageContext.request.contextPath}/reg?methods=register",
                        data:{
                            u_name:$("#username").val(),
                            u_password:pwd,
                            u_email:$('input[name="u_email"]').val(),
                            u_sex:$('input[name="u_sex"]').val()
                        }
                    });
                } else {
                    $("#registerMsg").html("密码不一致").css("color", "red");
                }
            });
            $.ajaxSetup( {
                //设置ajax请求结束后的执行动作
                complete : function(XMLHttpRequest, textStatus) {
                    // 通过XMLHttpRequest取得响应头，REDIRECT
                    var redirect = XMLHttpRequest.getResponseHeader("REDIRECT");//若HEADER中含有REDIRECT说明后端想重定向
                    if (redirect == "REDIRECT") {
                        var win = window;
                        while (win != win.top){
                            win = win.top;
                        }
                        //将后端重定向的地址取出来,使用win.location.href去实现重定向的要求
                        win.location.href= XMLHttpRequest.getResponseHeader("CONTEXTPATH");
                    }
                }
            });
        })
    </script>
    <title>注册</title>
</head>
<body>
<div class="regist">
    <div class="regist_center">
        <div class="regist_top">
            <div class="left fl"><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;会员注册</div>
            <div class="right fr">
                <a href="index.jsp" target="_black">小米商城</a>
            </div>
            <div class="clear"></div>
            <div class="xian center"></div>
        </div>
        <div class="center-block" style="margin-top: 80px;">
            <form class="form-horizontal"  method="post">

                <div class="form-group">
                    <label class="col-sm-2 control-label">用户名</label>
                    <div class="col-sm-8" style="width: 40%">
                        <input type="text" id="username" name="u_name" class="form-control col-sm-10"
                               placeholder="Username"/>
                    </div>
                    <div class="col-sm-2">
                        <p class="text-danger"><span class="help-block " id="usernameMsg"></span></p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">密码</label>
                    <div class="col-sm-8" style="width: 40%">
                        <input type="password" name="u_password"
                               class="form-control  col-sm-10" placeholder="Password"/>
                    </div>
                    <div class="col-sm-2">
                        <p class="text-danger"><span id="helpBlock" class="help-block ">请输入6位以上字符</span></p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">确认密码</label>
                    <div class="col-sm-8" style="width: 40%">
                        <input type="password" name="passwordAgain" class="form-control col-sm-10"
                               placeholder="Password Again"/>
                    </div>
                    <div class="col-sm-2">
                        <p class="text-danger"><span id="helpBlock" class="help-block ">两次密码要输入一致哦</span></p>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label">邮箱</label>
                    <div class="col-sm-8" style="width: 40%">
                        <input type="text" name="u_email" class="form-control col-sm-10"
                               placeholder="Email"/>
                    </div>
                    <div class="col-sm-2">
                        <p class="text-danger"><span id="helpBlock" class="help-block ">填写正确邮箱格式</span></p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">性别</label>
                    <div class="col-sm-8" style="width: 40%">
                        <label class="radio-inline">
                            <input type="radio" name="u_sex" value="男"> 男
                        </label> <label class="radio-inline">
                        <input type="radio" name="u_sex" value="女"> 女
                    </label>
                    </div>
                    <div class="col-sm-2">
                        <p class="text-danger"><span id="helpBlock" class="help-block ">你是帅哥 还是美女</span></p>
                    </div>
                </div>
                <hr>
                <div class="form-group">
                    <div class="col-sm-7 col-sm-push-2">
                        <input id="registerBtn" type="button" value="注册" class="btn btn-primary  btn-lg"
                               style="width: 200px;" disabled/> &nbsp; &nbsp;
                        <input type="reset" value="重置" class="btn btn-default  btn-lg" style="width: 200px;"/>
                    </div>
                </div>
                <div> <p id="registerMsg">${session.registerMsg}</p></div>
            </form>

        </div>
    </div>
</div>

</body>
</html>
