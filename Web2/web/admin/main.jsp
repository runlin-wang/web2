<%@ page import="com.ntvu.web2.entity.SystemUsers" %>
<%@ page import="com.ntvu.web2.service.LoginService" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ntvu.web2.common.Pager" %>
<%@ page import="com.ntvu.web2.util.Tools" %>
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

    String key = Tools.getNullOrEmpty(request.getParameter("key"), "");
    int pageIndex = Tools.getGreaterThanZero(request.getParameter("pageIndex"), 1);
    int pageSize = Tools.getGreaterThanZero(request.getParameter("pageSize"), 10);

    Pager<SystemUsers> pager = new LoginService().getPagers(key, pageIndex, pageSize);
    int[] pageSizes = {10, 20, 50, 100};
%>
<body>

    <p class="text-center">欢迎光临 <%=session.getAttribute("CurrentUserName").toString()%> , <a href="${pageContext.request.contextPath}/doLogout">注销</a></p>

    <hr/>
    <div class="main">
        <div class="query">
            <p>
                <label>查询条件<input type="text" name="query"></label>
                <input type="submit" value="查询">
            </p>
        </div>
        <div class="table">
            <table>
                <thead>
                    <tr>
                        <th class="width-10"><label for="checkAll">全选</label><input type="checkbox" name="checkAll" id="checkAll"></th>
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
                        for (SystemUsers user : pager.getData()) {
                    %>
                    <tr>
                        <td><label for="check<%=user.getId()%>"></label><input type="checkbox" name="check<%=user.getId()%>" id="check<%=user.getId()%>"></td>
                        <td><%=user.getLoginName()%></td>
                        <td><%=user.getTelephone()%></td>
                        <td><%=user.getEmail()%></td>
                        <td>
                            <%--此处使用 true 或 false .后缀的方式修改图片路径--%>
                            <img src="/images/<%=user.isStatus()%>.jpg" width="20px" title="<%=user.isStatus() ? "可用" : "不可用"%>" alt="true or false">
                        </td>
                        <td><%=user.getRole().getRoleName()%></td>
                        <td>
                            <input type="button" value="删除">
                            <input type="button" value="编辑" onclick="location.href='./edit.jsp?id=<%=user.getId()%>'">
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
                        <td colspan="7">
                            <label for="page-sizes">
                                每页
                                <select id="page-sizes" class="pager-number">
                                    <%
                                        for (int i : pageSizes) {

                                    %>
                                    <option value="<%=i%>" <%= i == pager.getPageSize() ? "selected" : ""%>><%=i%></option>
                                    <%
                                        }
                                    %>
                                </select>
                                条
                            </label>
                            <a href="main.jsp?pageIndex=1">首页</a>
                            <a href="main.jsp?pageIndex=<%=pageIndex - 1 > 0 ? pageIndex - 1 : 1 %>" >上一页</a>
                            <a href="main.jsp?pageIndex=<%=Math.min(pageIndex + 1, pager.getTotalPage())%>" >下一页</a>
                            <a href="main.jsp?pageIndex=<%=pager.getTotalPage()%>" >末页</a>
                            <label for="page-jump">
                                跳到第
                                <select id="page-jump" class="pager-number">
                                    <%
                                        for (int i = 1; i <= pager.getTotalPage(); i++) {
                                    %>
                                    <option value="<%=i%>" <%= i == pager.getPageIndex() ? "selected" : ""%>><%=i%></option>
                                    <%
                                        }
                                    %>
                                </select>
                                页
                            </label>
                            <%=pager.getPageIndex()%> / <%=pager.getTotalPage()%>
                            共 <%=pager.getTotalRecord()%> 条
                        </td>
                    </tr>
                </tfoot>
            </table>
        </div>
    </div>
</body>
</html>
