package com.gmail.evanloafakahaitao.computer.store.servlets;

import com.gmail.evanloafakahaitao.computer.store.config.ConfigurationManager;
import com.gmail.evanloafakahaitao.computer.store.config.properties.PageProperties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExceptionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Throwable throwable = (Throwable) req.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        Integer statusCode = (Integer) req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String servletName = (String) req.getAttribute(RequestDispatcher.ERROR_SERVLET_NAME);
        if (servletName == null) {
            servletName = "unknown";
        }
        String requestUri = (String) req.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);
        if (requestUri == null) {
            requestUri = "unknown";
        }
        System.out.println("Error details : ");
        System.out.printf("Message : %s%n", throwable.getMessage());
        System.out.printf("Status code : %d%n", statusCode);
        System.out.printf("Servlet name : %s%n", servletName);
        System.out.printf("Exception type : %s%n", throwable.getClass().getName());
        System.out.printf("Request URI : %s%n", requestUri);
        throwable.printStackTrace();
        req.setAttribute("error", statusCode);
        req.setAttribute("error_description", "Service temporarily unavailable");
        ConfigurationManager configurationManager = ConfigurationManager.getInstance();
        getServletContext().getRequestDispatcher(configurationManager.getProperty(PageProperties.ERROR_PAGE_PATH)).forward(req, resp);
    }

    @Override
    public void destroy() {
        System.out.println("Exception servlet destroyed");
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        System.out.println("Exception servlet initialized");
        super.init();
    }
}
