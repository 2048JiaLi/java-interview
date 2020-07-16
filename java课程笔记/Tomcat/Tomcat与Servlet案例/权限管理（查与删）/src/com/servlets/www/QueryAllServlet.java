package com.servlets.www;

import com.entity.Dept;
import com.services.DeptService;
import com.services.impl.DeptServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author Li W
 * @version 1.8
 * @date 2020/7/16 9:13
 */
@WebServlet(name = "QueryAllServlet", value = "/queryAll")
public class QueryAllServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DeptService deptService = new DeptServiceImpl();
        List<Dept> allDept = deptService.getAllDept();
        //response.setContentType("text/html;charset=utf-8");
        //response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();

        writer.println("<html>");
        writer.println("<head>");
        writer.println("<meta charset='utf-8'>");
        writer.println("<title>查询所有页面</title>");
        writer.println("</head>");
        writer.println("<body>");

        writer.println("<table border='1'>");
        writer.println("<tr>");
        writer.println("<th>部门编号</th>");
        writer.println("<th>部门名称</th>");
        writer.println("<th>部门地址</th>");
        writer.println("<th>操作</th>");
        writer.println("</tr>");

        for (Dept dept : allDept) {
            writer.println("<tr>");
            writer.println("<td>");
            writer.println(dept.getDeptno());
            writer.println("</td>");
            writer.println("<td>");
            writer.println(dept.getDname());
            writer.println("</td>");
            writer.println("<td>");
            writer.println(dept.getLoc());
            writer.println("</td>");
            writer.println("</tr>");
        }

        writer.println("</table>");

        writer.println("</body>");
        writer.println("</html>");
    }
}
