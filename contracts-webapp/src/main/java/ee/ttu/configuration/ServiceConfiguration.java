package ee.ttu.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.ws.client.core.WebServiceTemplate;

/**
 * Created by Vahur Kaar on 24.09.2014.
 */
@Configuration
@ComponentScan(basePackages = {
        "ee.ttu.controller",
        "ee.ttu.service.impl",
        "ee.ttu.converter",
        "ee.ttu.validator"
})
@PropertySource(value = "classpath:application.properties")
@EnableWebMvc
public class ServiceConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
        resourceBundleMessageSource.setBasename("messages");

        return resourceBundleMessageSource;
    }

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/WEB-INF/jsp/");
        internalResourceViewResolver.setSuffix(".jsp");

        return internalResourceViewResolver;
    }

    @Bean
    public WebServiceTemplate webServiceTemplate() {
        WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
        webServiceTemplate.setDefaultUri(environment.getProperty("core.ws.location"));
        webServiceTemplate.setMarshaller(marshaller());
        webServiceTemplate.setUnmarshaller((Unmarshaller) marshaller());

        return webServiceTemplate;
    }

    @Bean
    public Marshaller marshaller() {
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setContextPath("ee.ttu.xml");
        return jaxb2Marshaller;
    }

}
