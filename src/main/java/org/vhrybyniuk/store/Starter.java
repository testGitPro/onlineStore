package org.vhrybyniuk.store;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.vhrybyniuk.store.dao.jdbc.JdbcProductDao;
import org.vhrybyniuk.store.dao.jdbc.JdbcUserDao;
import org.vhrybyniuk.store.security.SecurityService;
import org.vhrybyniuk.store.servlets.Product.AddServlet;
import org.vhrybyniuk.store.servlets.Product.DeleteServlet;
import org.vhrybyniuk.store.servlets.Product.EditServlet;
import org.vhrybyniuk.store.servlets.Product.ProductsServlet;
import org.vhrybyniuk.store.servlets.User.LogInServlet;
import org.vhrybyniuk.store.servlets.User.LogOutServlet;
import org.vhrybyniuk.store.servlets.User.RegistrationServlet;


public class Starter {
    private static final int DEFAULT_PORT = 8080;

    public static void main(String[] args) throws Exception {

        JdbcProductDao jdbcProductDao = new JdbcProductDao();
        JdbcUserDao jdbcUserDao = new JdbcUserDao();

        SecurityService securityService = new SecurityService(jdbcUserDao);


        ProductsServlet productsServlet = new ProductsServlet(securityService);
        AddServlet addServlet = new AddServlet(securityService);
        EditServlet editServlet = new EditServlet(securityService);
        DeleteServlet deleteServlet = new DeleteServlet(securityService);
        LogInServlet logInServlet = new LogInServlet(securityService);
        LogOutServlet logOutServlet = new LogOutServlet(securityService);
        RegistrationServlet registrationServlet = new RegistrationServlet();
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addServlet(new ServletHolder(productsServlet), "/");
        context.addServlet(new ServletHolder(productsServlet), "/products");
        context.addServlet(new ServletHolder(logInServlet), "/login");
        context.addServlet(new ServletHolder(logOutServlet), "/logout");
        context.addServlet(new ServletHolder(registrationServlet), "/registration");
        context.addServlet(new ServletHolder(editServlet), "/ftl/edit");
        context.addServlet(new ServletHolder(addServlet), "/ftl/add");
        context.addServlet(new ServletHolder(deleteServlet), "/ftl/delete");


        Server server = new Server(DEFAULT_PORT);
        server.setHandler(context);

        server.start();
    }
}
