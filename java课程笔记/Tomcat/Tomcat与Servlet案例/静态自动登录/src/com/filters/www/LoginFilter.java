package com.filters.www;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * @author Li W
 * @version 1.8
 * @date 2020/7/14 15:30
 *
 * 这里好像有点问题，有时候再次访问时，并没有执行过滤器，但是有时候又是正常的
 */
@WebFilter(filterName = "LoginFilter", value = "/login.html")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        // session里取username

        // 浏览器传过来的是有HTTP协议的，所以这里可以直接将父类强转为ttpServlet
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        //System.out.println(username);
        //System.out.println(session.getId());

        if (username != null) {
            // session没有失效，会话没有关闭，可直接跳转到欢迎页面
            response.sendRedirect("/TestAutoLogin/welcome.html");
        } else {
            // session失效，从Cookie里面拿数据
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie :
                        cookies) {
                    String name = cookie.getName();
                    // 当前cookie里面有userInfo信息，表示用户登录过
                    if ("userInfo".equals(name)) {
                        String value = cookie.getValue();
                        // admin#774411
                        String[] split = value.split("#");// 这里因为存的时候是 username#password形式
                        // 给session存一次（因为session没有）
                        session.setAttribute("username",split[0]);
                        response.sendRedirect("/TestAutoLogin/welcome.html");
                    }
                }
            }
        }

        // cookie里取用户名和密码

        // 没有登录过，也没有自动登录，放行到login.html执行
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
