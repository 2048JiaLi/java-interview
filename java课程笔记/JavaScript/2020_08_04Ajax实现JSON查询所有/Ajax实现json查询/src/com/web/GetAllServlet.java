package com.web;

import com.services.UserService;
import com.services.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Li W
 * @version 1.8
 * @date 2020/8/4 15:24
 */
@WebServlet(name = "GetAllServlet", value = "/getall")
public class GetAllServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserServiceImpl();
        String all = userService.getAll();
//        System.out.println(all);
        PrintWriter writer = response.getWriter();
        if (all!=null) {
            writer.print(all);
        } else {
            writer.print("error");
        }
    }
}
