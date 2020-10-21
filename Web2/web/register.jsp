<%@ page import="com.ntvu.web2.util.Tools" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>用户注册</title>
    <meta name="keywords" content="" />
    <meta name="description" content="" />

    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="fonts/iconic/css/material-design-iconic-font.min.css">
    <link rel="stylesheet" type="text/css" href="css/util.css">
    <link rel="stylesheet" type="text/css" href="css/main.css">
</head>

<body>

<div class="limiter">
    <div class="container-login100" style="background-image: url('images/bg-01.jpg');">
        <div class="wrap-login100 p-l-55 p-r-55 p-t-65 p-b-54">
            <form class="login100-form validate-form" action="/doRegister">
                <span class="login100-form-title p-b-49">注册</span>

                <div class="wrap-input100 validate-input m-b-23" data-validate="请输入用户名">
                    <span class="label-input100">用户名</span>
                    <input class="input100" type="text" name="txtLoginName" placeholder="请输入用户名" autocomplete="off" value="<%=Tools.isEmpty(request.getAttribute("txtLoginName"), "")/* request.getAttribute("txtLoginName") != null ? request.getAttribute("txtLoginName"): ""*/%>">
                    <span class="focus-input100" data-symbol="&#xf206;"></span>
                </div>

                <div class="wrap-input100 validate-input" data-validate="请输入密码">
                    <span class="label-input100">密码</span>
                    <input class="input100" type="password" name="pwd" placeholder="请输入密码">
                    <span class="focus-input100" data-symbol="&#xf190;"></span>
                </div>

                <div class="wrap-input100 validate-input" data-validate="请再次输入密码">
                    <span class="label-input100">确认密码</span>
                    <input class="input100" type="password" name="pwd2" placeholder="请再次输入密码">
                    <span class="focus-input100" data-symbol="&#xf190;"></span>
                </div>

                <div class="wrap-input100 validate-input m-b-23" data-validate="请输入手机号">
                    <span class="label-input100">手机号</span>
                    <input class="input100" type="text" name="telephone" placeholder="请输入手机号" autocomplete="off" value="<%=Tools.isEmpty(request.getAttribute("telephone"), "")%>">
                    <span class="focus-input100" data-symbol="&#xf206;"></span>
                </div>

                <div class="wrap-input100 validate-input m-b-23" data-validate="请输入邮箱">
                    <span class="label-input100">邮箱</span>
                    <input class="input100" type="text" name="email" placeholder="请输入邮箱" autocomplete="off"  value="<%=Tools.isEmpty(request.getAttribute("email"), "")%>">
                    <span class="focus-input100" data-symbol="&#xf206;"></span>
                </div>

                <div class="container-login100-form-btn">
                    <div class="wrap-login100-form-btn">
                        <div class="login100-form-bgbtn"></div>
                        <button class="login100-form-btn">注 册</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="plugins/jquery/jquery-3.2.1.min.js"></script>
<script src="js/main.js"></script>
</body>

</html>