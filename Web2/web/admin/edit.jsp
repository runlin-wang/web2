<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>编辑用户信息</title>
    <style type="text/css">
        .form-center {
            min-width: 300px;
            min-height: 500px;
            margin: auto;
        }
        .form-center table>tr>td{
            font-weight: bold;
            font-size: 18px;
            text-align: right;
        }
    </style>
</head>
<body>
    <div class="form-center">
        <form action="main.jsp" name="editUser">
            <table>
                <tr>
                    <td>用户名：</td>
                    <td><input type="text" name="username" /></td>
                </tr>
                <tr>
                    <td>手机号：</td>
                    <td><input type="text" name="telephone" /></td>
                </tr>
                <tr>
                    <td>邮箱：</td>
                    <td><input type="text" name="email" /></td>
                </tr>
                <tr>
                    <td>状态：</td>
                    <td><input type="text" name="status" /></td>
                </tr>
                <tr>
                    <td>角色：</td>
                    <td><input type="checkbox" name="roleId" /></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" />
                        <input type="button" value="返回" onclick="window.location.href='./main.jsp'" />
                    </td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>