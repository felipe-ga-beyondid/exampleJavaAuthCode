package com.beyondid.servlet.login;

import org.eclipse.jetty.annotations.AnnotationConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.webapp.WebInfConfiguration;
import org.eclipse.jetty.webapp.WebXmlConfiguration;

import java.io.File;

/**
 * Simple embedded web application to make running this sample easier. Servlet and Context Listener are registered
 * via annotations and should work in a traditional WAR file app the same way.
 */
public class Application {

    public static void main(String[] args) throws Exception {

        WebAppContext webapp = new WebAppContext();
        webapp.setBaseResource(Resource.newResource(new File( "src/main/webapp")));
        webapp.setContextPath("/");
        webapp.setWelcomeFiles(new String[]{"index.jsp"});
        
        webapp.setParentLoaderPriority(true);
        webapp.setConfigurations(new Configuration[] {
            new AnnotationConfiguration(),
            new WebInfConfiguration(),
            new WebXmlConfiguration()
        });

        // scan everything for annotations, jstl, web fragments, etc
        webapp.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern", ".*");

        Server server = new Server(8080);
        server.setHandler(webapp);
        server.start();
        server.join();
    }
}