package com.ntvu.web2.servlet;

import com.ntvu.web2.entity.SystemUsers;
import com.ntvu.web2.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 实现用户注册功能
 */
@WebServlet(name = "register", value = "/doRegister", description = "注册服务")
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 从页面的输入框获取值
        String loginName = req.getParameter("txtLoginName");
        String pwd = req.getParameter("pwd");
        String pwd2 = req.getParameter("pwd2");
        String telephone = req.getParameter("telephone");
        String email = req.getParameter("email");

        SystemUsers user = new SystemUsers(0, loginName, pwd, telephone, email);
        user.setLoginName(req.getParameter("txtLoginName"));
        user.setLoginPassword(req.getParameter("pwd"));
        user.setTelephone(req.getParameter("telephone"));
        user.setEmail(req.getParameter("email"));

        String path = req.getContextPath();

        // 验证数据合法性 用户名、密码、手机号、邮箱不为空，两次密码必须一致
        if (loginName == null || loginName.trim().equals("") || pwd == null || pwd.equals("") || telephone == null || email.trim().equals("") || !pwd.equals(pwd2)) {
            req.setAttribute("txtLoginName", loginName);
            req.setAttribute("telephone", telephone);
            req.setAttribute("email", email);
            resp.sendRedirect(path + "/register.jsp");
            return;
        }



        // 注册成功跳转登录页面 失败继续注册
//        boolean success = new LoginService().register(loginName, pwd, "", telephone, email, true, 2);
        boolean success = new LoginService().register(user);
        if (success) {
            resp.sendRedirect(path + "/index.html");
//            resp.sendRedirect(path + String.format("/doLogin?username=%s&password=%s", loginName, pwd));
        } else {
            req.setAttribute("txtLoginName", loginName);
            req.setAttribute("telephone", telephone);
            req.setAttribute("email", email);
            req.getRequestDispatcher(path + "/register.jsp").forward(req, resp);
        }
    }

}
