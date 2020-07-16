package com.servlets.www;

import com.entity.User;
import com.services.UserService;
import com.services.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * @author Li W
 * @version 1.8
 * @date 2020/7/15 10:00
 *
 * 三层架构，根据数据库中用户信息，实现自动登录
 *
 * 需要用到的.jar包： druid连接池、commons-dbutils、mysql连接工具。注意：这里的依赖都要放到web/WEB-INF/lib下
 *
 * 其他需要注意的
 * 1、database.properties配置时，连接设置应该配置为driverClassName=com.mysql.cj.jdbc.Driver而不是com.mysql.jdbc.Driver（提示这个已被弃用）
 * 2、数据库的连接配置中，需要加上&serverTimezone=GMT时区设置，才能正常连接
 * 3、database.properties要直接放在src目录下
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

        if (username==null || username.trim().length()==0) {
            response.getWriter().println("用户名不能为空");
            return;
        }
        if (password==null || password.trim().length()==0) {
            response.getWriter().println("密码不能为空");
            return;
        }

        // 开始查询
        UserService userService = new UserServiceImpl();
        User user = userService.checkUser(username,password);
        if (user!=null) {
            session.setAttribute("user",user);

            Cookie cookie = new Cookie("userInfo",username+"#"+password);
            cookie.setPath("/");
            cookie.setMaxAge(60*60*24);
            response.addCookie(cookie);
            if (user.getAccess() == 1) {
                response.sendRedirect("/AccessManage/admin");
            } else {
                response.sendRedirect("/AccessManage/queryAll");
            }

        } else {
            response.sendRedirect("/AccessManage/fail.html");
        }
    }
}
