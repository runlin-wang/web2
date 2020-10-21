package com.ntvu.web2.servlet;

import com.ntvu.web2.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * vale 属性 等价于 web.xml 的 <url-pattern> 标签
 */
@WebServlet(name = "loginController", value = "/doLogin", description = "登录验证服务")
public class LoginControllerServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String path = req.getContextPath();
        String username = req.getParameter("username");
        String pass = req.getParameter("pass");

        System.out.println(String.format("username = %s, password = %s, path = %s", username, pass, path));

        if (new LoginService().login(username, pass)) {
            // 保存用户名
            HttpSession session = req.getSession();
            session.setAttribute("CurrentUserName",username);
            session.setMaxInactiveInterval(1800);//30minutes
            // 跳转
            resp.sendRedirect(path + "/admin/main.jsp");

        }else {
            req.setAttribute("txtLoginName", username);
            resp.sendRedirect(path + "/index.html");

//            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login");
//            rd.forward(req,resp);
        }
    }

}
