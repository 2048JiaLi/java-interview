package com.filters.www;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Li W
 * @version 1.8
 * @date 2020/7/15 16:11
 *
 * HttpServletRequestWrapper类的使用
 */
@WebFilter(filterName = "DirtyFilter", value = "/dirty")
public class DirtyFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        // 在过滤器里面把数据过滤掉
        // 重写getParameter

        chain.doFilter(new Dirty((HttpServletRequest) req), resp);
    }

    static class Dirty extends HttpServletRequestWrapper {
        List<String> dirties = new ArrayList<>(); // 脏词


        public Dirty(HttpServletRequest request) {
            super(request); // 构建了一个ServletRequest对象

            dirties.add("王八蛋");
            dirties.add("SB");
            dirties.add("sb");
        }

        // 重写方法
        @Override
        public String getParameter(String name) {
            // 根据浏览器表单里的name，获取数据
            String value = super.getParameter(name);

            // 脏词过滤
            for (String dirty : dirties) {
                if (value.equals(dirty)) {
                    value = "世界和平";
                }
            }

            return value;
        }
    }


    public void init(FilterConfig config) throws ServletException {

    }

}
