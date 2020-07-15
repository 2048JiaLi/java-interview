package com.filters.www;

import com.entity.User;
import com.services.UserService;
import com.services.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Li W
 * @version 1.8
 * @date 2020/7/15 9:06
 */
@WebFilter(filterName = "LoginFilter", value = "/login.html")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpServletRequest request = (HttpServletRequest) req;

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user!=null) {
            response.sendRedirect("/TestAutoLogin/welcome.html");
        } else {
            Cookie[] cookies = request.getCookies();
            if (cookies!=null) {
                for (Cookie cookie:
                     cookies) {
                    String name = cookie.getName();
                    if (name.equals("userInfo")) {
                        String value = cookie.getValue();
                        String[] split = value.split("#");
                        // 拿到用户名和密码信息，进行查询
                        UserService userService = new UserServiceImpl();
                        User user1 = userService.checkUser(split[0], split[1]);
                        if (user1!=null) {
                            response.sendRedirect("/TestAutoLogin/welcome.html");
                        } else {
                            response.sendRedirect("/TestAutoLogin/fail.html");
                        }
                    }
                }
            }
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
