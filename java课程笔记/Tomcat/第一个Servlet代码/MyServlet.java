package com.servlets;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import java.io.IOException;
/**
 * 实现接口的所有方法
 */

public class MyServlet implements Servlet{

    // 1.初始化
    public void init(ServletConfig servletConfig) throws ServletException{
        // 初始化工作
    }

    // 2.获取配置信息
    public ServletConfig getServletConfig() {
        return null;
    }

    // 3.提供服务
    public void service(ServletRequest request, ServletResponse response)
            throws ServletException, IOException{
        // 注意：这句话会打印在服务器Tomcat控制台
        System.out.println("这是我的第一个Servlet");
    }

    // 4.返回servlet基本信息
    public String getServletInfo() {
        return null;
    }

    // 5.销毁
    public void destroy() {}
}