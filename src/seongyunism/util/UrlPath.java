package seongyunism.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class UrlPath implements ServletContextListener {

 
    public UrlPath() {}
    
    //path�� ServletContext�� ������ ���� 
    public void contextInitialized(ServletContextEvent arg0) {
    	//System.out.println(arg0.getServletContext().getContextPath());
    	arg0.getServletContext().setAttribute("path", arg0.getServletContext().getContextPath());
    }

    public void contextDestroyed(ServletContextEvent arg0) {
    }
	
}
