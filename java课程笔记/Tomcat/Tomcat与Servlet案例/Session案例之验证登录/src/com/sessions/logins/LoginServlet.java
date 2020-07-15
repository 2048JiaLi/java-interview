package com.sessions.logins;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Li W
 * @version 1.8
 * @date 2020/7/13 10:28
 */
@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 获得session
        HttpSession session = request.getSession();
        // 2. 在session里找对应的用户名是否存在
        String username = (String) session.getAttribute("username");
        System.out.println(username);
        // 3. 如果session里没有username，则收取页面传递过来的值
        if (username == null) {
            String username1 = request.getParameter("username");
            String password = request.getParameter("password");

            // 把值存在session
            if ("admin".equals(username1)) {
                session.setAttribute("username",username1);
                response.sendRedirect("/TomcatCase/index.html");
                return;
            }
            response.sendRedirect("/TomcatCase/login.html");
        } else {
            if (username.equals("admin")) {
                request.getRequestDispatcher("/index.html").forward(request,response);
            }
        }
    }
}
