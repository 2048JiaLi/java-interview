package com.filters.www;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * @author Li W
 * @version 1.8
 * @date 2020/7/14 15:12
 *
 * 第一次成功登录之后，用户再访问登录页面时，可实现直接跳转
 */
@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if ("admin".equals(username) && "774411".equals(password)) {
            // 登录成功
            session.setAttribute("username",username);

            Cookie cookie = new Cookie("userInfo",username+"#"+password);
            cookie.setPath("/");
            cookie.setMaxAge(60*60*24); // 一天有效期
            response.addCookie(cookie);
            response.sendRedirect("/TestAutoLogin/welcome.html");
        } else {
            response.sendRedirect("/TestAutoLogin/fail.html");
        }
    }
}
