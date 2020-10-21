<%@ page import="com.ntvu.web2.entity.SystemUsers" %>
<%@ page import="com.ntvu.web2.service.LoginService" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>后台管理界面</title>
    <link rel="stylesheet" href="../css/admin.css" />
    <link rel="stylesheet" href="../css/bootstrap.min.css" />
</head>
<%

    String path = request.getContextPath();
    if (session.getAttribute("CurrentUserName") == null) {
        response.sendRedirect(path + "/index.html");
        return;
    }

    List<SystemUsers> lst = new LoginService().getList();
%>
<body>

    <p class="text-center">欢迎光临 <%=session.getAttribute("CurrentUserName").toString()%> , <a href="/doLogout">注销</a></p>

    <hr/>
    <div class="main">
        <div class="query">
            <p>
                查询条件<input type="text" name="query">
                <input type="submit" value="查询">
            </p>
        </div>
        <div class="table">
            <table>
                <thead>
                <tr>
                    <th class="width-10">选择<input type="checkbox" name="checkAll" id="checkAll"></th>
                    <th class="width-10">用户名</th>
                    <th class="width-10">手机号</th>
                    <th class="width-20">邮箱</th>
                    <th class="width-10">状态</th>
                    <th class="width-10">角色</th>
                    <th class="width-30">操作栏</th>
                </tr>
                </thead>
                <tbody>
                <%
                    for (SystemUsers user : lst) {
                %>
                <tr>
                    <td><input type="checkbox" name="check<%=user.getId()%>" id="check<%=user.getId()%>"></td>
                    <td><%=user.getLoginName()%></td>
                    <td><%=user.getTelephone()%></td>
                    <td><%=user.getEmail()%></td>
                    <td>
                        <%--此处使用 true 或 false .后缀的方式修改图片路径--%>
                        <img src="/images/<%=user.isStatus()%>.jpg" width="20px" title="<%=user.isStatus() ? "可用" : "不可用"%>">
                    </td>
                    <td><%=user.getRole().getRoleName()%></td>
                    <td>
                        <input type="button" value="删除">
                        <input type="button" value="编辑">
                        <input type="button" value="禁用">
                        <input type="button" value="重置密码">
                    </td>
                </tr>
                <%
                    }
                %>
                </tbody>
                <tfoot>
                <tr>
                    <td colspan="7">每页 10 条 首页 上一页 下一页 末页 跳到第 x 页 1/10 共 68 条</td>
                </tr>
                </tfoot>
            </table>
        </div>
    </div>
</body>
</html>
