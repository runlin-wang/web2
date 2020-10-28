<%@ page import="com.ntvu.web2.entity.SystemUsers" %>
<%@ page import="com.ntvu.web2.service.LoginService" %>
<%@ page import="java.util.List" %>
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
<%

    LoginService ls =  new LoginService();
    SystemUsers user = ls.findById(Integer.parseInt(request.getParameter("id")));
    String curUserRoleName = user.getRole().getRoleName();
    List<String> roleNames = ls.getRoleNames();
%>
<body>
    <div class="form-center">
        <form action="main.jsp" name="editUser">
            <table>
                <tr>
                    <td>用户名：</td>
                    <td><input type="text" name="username" value="<%=user.getLoginName()%>" /></td>
                </tr>
                <tr>
                    <td>手机号：</td>
                    <td><input type="text" name="telephone" value="<%=user.getTelephone()%>" /></td>
                </tr>
                <tr>
                    <td>邮箱：</td>
                    <td><input type="text" name="email" value="<%=user.getEmail()%>" /></td>
                </tr>
                <tr>
                    <td>状态：</td>
                    <td><input type="checkbox" name="status" <%=user.isStatus() ? "checked" : ""%> /></td>
                </tr>
                <tr>
                    <td>角色：</td>
                    <td>
                        <select name="roles">
                            <option value="<%=curUserRoleName%>"><%=curUserRoleName%></option>
                            <%
                                for (int i = 0; i < roleNames.size(); i++) {
                                    String roleName = roleNames.get(i);
                                    // 判断是否等于当前用户的角色名称 等于则继续循环
                                    if (roleName.equals(curUserRoleName))
                                        continue;
                            %>
                            <option value="<%=roleName%>"><%=roleName%></option>
                            <%
                                }
                            %>
                        </select>
                    </td>
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