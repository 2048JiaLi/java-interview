package com.sessions.validates;

import cn.dsna.util.images.ValidateCode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Li W
 * @version 1.8
 * @date 2020/7/13 11:12
 *
 * 验证码登录
 */
@WebServlet(name = "ValidateServlet", value = "/validate")
public class ValidateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ValidateCode code = new ValidateCode(120,60,4,10);
        String code1 = code.getCode();

        code.write(response.getOutputStream());

        /**
         * 这里可以将该验证码用session存储，
         *
         * 然后另写一个servlet，去判断用户在浏览器输出的验证码是否匹配
         */
    }
}
