package org.vhrybyniuk.store;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.vhrybyniuk.store.dao.jdbc.JdbcProductDao;
import org.vhrybyniuk.store.entity.Product;
import org.vhrybyniuk.store.servlets.AddServlet;
import org.vhrybyniuk.store.servlets.DeleteServlet;
import org.vhrybyniuk.store.servlets.EditServlet;
import org.vhrybyniuk.store.servlets.ProductsServlet;


public class Starter {
    private static final int DEFAULT_PORT = 8080;

    public static void main(String[] args) throws Exception {
        ProductsServlet productsServlet = new ProductsServlet();
        AddServlet addServlet = new AddServlet();
        EditServlet editServlet= new EditServlet();
        DeleteServlet deleteServlet = new DeleteServlet();
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addServlet(new ServletHolder(productsServlet), "/");
        context.addServlet(new ServletHolder(productsServlet), "/products");
        context.addServlet(new ServletHolder(editServlet), "/ftl/edit");
        context.addServlet(new ServletHolder(addServlet), "/ftl/add");
        context.addServlet(new ServletHolder(deleteServlet), "/ftl/delete");


        Server server = new Server(DEFAULT_PORT);
        server.setHandler(context);

        server.start();
    }
}
