package com.servlets.www;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Li W
 * @version 1.8
 * @date 2020/7/15 16:08
 */
@WebServlet(name = "DirtyServlet", value = "/dirty")
public class DirtyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // 过滤器封装的request
        String username = request.getParameter("username");
        String niname = request.getParameter("niname");
        String lovename = request.getParameter("lovename");

        System.out.println("获取数据为：");
        System.out.println(username+"\n"+niname+"\n"+lovename);
    }
}
