package com.servlets;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import java.io.IOException;
/**
 * ʵ�ֽӿڵ����з���
 */

public class MyServlet implements Servlet{

    // 1.��ʼ��
    public void init(ServletConfig servletConfig) throws ServletException{
        // ��ʼ������
    }

    // 2.��ȡ������Ϣ
    public ServletConfig getServletConfig() {
        return null;
    }

    // 3.�ṩ����
    public void service(ServletRequest request, ServletResponse response)
            throws ServletException, IOException{
        // ע�⣺��仰���ӡ�ڷ�����Tomcat����̨
        System.out.println("�����ҵĵ�һ��Servlet");
    }

    // 4.����servlet������Ϣ
    public String getServletInfo() {
        return null;
    }

    // 5.����
    public void destroy() {}
}