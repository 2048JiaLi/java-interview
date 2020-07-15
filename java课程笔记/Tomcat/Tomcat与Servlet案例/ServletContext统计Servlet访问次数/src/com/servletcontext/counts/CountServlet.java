package com.servletcontext.counts;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Li W
 * @version 1.8
 * @date 2020/7/13 14:39
 */
@WebServlet(name = "CountServlet", value = "/count")
public class CountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = request.getServletContext();
        Integer count = (Integer) servletContext.getAttribute("count");

        if (count == null) {
            count = 1;
            servletContext.setAttribute("count",count);
        } else {
            count++;
            servletContext.setAttribute("count",count);
        }

        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().printf("这是第%d个",count);
    }
}
