package com.servlets.www;

import com.dao.impl.DeptDaoImpl;
import com.services.impl.DeptServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Li W
 * @version 1.8
 * @date 2020/7/16 10:33
 */
@WebServlet(name = "DeleteServlet", value = "/delete")
public class DeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String no = request.getParameter("no");
        DeptServiceImpl deptService = new DeptServiceImpl();

        int delete = deptService.delete(Integer.valueOf(no));

        if (delete > 0) {
            response.sendRedirect("/AccessManage/admin");
        }
    }
}
