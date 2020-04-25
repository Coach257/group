package com.test.myservlet;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.Enumeration;

public class myservlet implements Servlet{
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println(servletConfig.getServletName());
        System.out.println(servletConfig.getInitParameter("username"));
        Enumeration<String> element=servletConfig.getInitParameterNames();
        while(element.hasMoreElements()){
            String res=element.nextElement();
            System.out.println(res+":"+servletConfig.getInitParameter(res));
        }
        System.out.println(servletConfig.getServletContext().getInitParameter("username"));
    }

    public ServletConfig getServletConfig() {
        return null;
    }

    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        String id =servletRequest.getParameter("id");
        System.out.println("received");
        servletResponse.setContentType("text/html;charset=UTF-8");
        servletResponse.getWriter().write("你好"+id);
    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {

    }
}
