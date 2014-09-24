package ee.ttu.configuration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.ws.transport.http.MessageDispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created by Vahur Kaar on 20.09.2014.
 */
public class ServletInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        WebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        servletContext.addListener(new ContextLoaderListener(applicationContext));

        MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
        messageDispatcherServlet.setApplicationContext(applicationContext);

        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("messageDispatcherServlet", messageDispatcherServlet);
        dispatcher.setInitParameter("transformWsdlLocations", "true");
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }
}
