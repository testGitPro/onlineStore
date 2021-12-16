package org.vhrybyniuk.store;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
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
        ProductsServlet productsServlet = new ProductsServlet();
        AddServlet addServlet = new AddServlet();
        EditServlet editServlet = new EditServlet();
        DeleteServlet deleteServlet = new DeleteServlet();
        LogInServlet logInServlet = new LogInServlet();
        LogOutServlet logOutServlet = new LogOutServlet();
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
