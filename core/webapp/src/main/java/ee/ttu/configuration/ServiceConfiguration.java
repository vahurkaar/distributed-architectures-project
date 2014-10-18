package ee.ttu.configuration;

import ee.ttu.util.CustomSoapFaultMappingExceptionResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.soap.server.endpoint.SoapFaultDefinition;
import org.springframework.ws.soap.server.endpoint.SoapFaultMappingExceptionResolver;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import java.util.Properties;

/**
 * Created by Vahur Kaar on 24.09.2014.
 */
@Configuration
@ComponentScan(basePackages = {
        "ee.ttu.endpoints",
        "ee.ttu.service",
        "ee.ttu.converter"
})
@EnableWs
public class ServiceConfiguration {

    public static final String DATABASE_EXCEPTION_MESSAGE = "SERVER,An error occurred when connecting to the database!";

    @Bean
    public SoapFaultMappingExceptionResolver exceptionResolver() {
        SoapFaultDefinition defaultFault = new SoapFaultDefinition();
        defaultFault.setFaultCode(SoapFaultDefinition.SERVER);

        Properties exceptionMappings = new Properties();
        exceptionMappings.setProperty("org.springframework.orm.jpa.JpaSystemException", DATABASE_EXCEPTION_MESSAGE);
        exceptionMappings.setProperty("org.springframework.dao.DataAccessException", DATABASE_EXCEPTION_MESSAGE);
        exceptionMappings.setProperty("org.hibernate.HibernateException", DATABASE_EXCEPTION_MESSAGE);

        SoapFaultMappingExceptionResolver soapFaultMappingExceptionResolver = new CustomSoapFaultMappingExceptionResolver();
        soapFaultMappingExceptionResolver.setDefaultFault(defaultFault);
        soapFaultMappingExceptionResolver.setOrder(1);
        soapFaultMappingExceptionResolver.setExceptionMappings(exceptionMappings);

        return soapFaultMappingExceptionResolver;
    }

    @Bean
    public DefaultWsdl11Definition core() {
        DefaultWsdl11Definition defaultWsdl11Definition = new DefaultWsdl11Definition();
        defaultWsdl11Definition.setLocationUri("/coreService/");
        defaultWsdl11Definition.setPortTypeName("CoreResource");
        defaultWsdl11Definition.setRequestSuffix("Request");
        defaultWsdl11Definition.setResponseSuffix("Response");
        defaultWsdl11Definition.setServiceName("CoreService");
        defaultWsdl11Definition.setTargetNamespace("http://www.ttu.ee/hajusarhitektuurid");
        defaultWsdl11Definition.setSchema(coreSchema());

        return defaultWsdl11Definition;
    }

    @Bean
    public XsdSchema coreSchema() {
        SimpleXsdSchema xsdSchema = new SimpleXsdSchema();
        xsdSchema.setXsd(new ClassPathResource("core.xsd"));
        return xsdSchema;
    }

    @Bean
    public Marshaller marshaller() {
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setContextPath("ee.ttu.xml");
        return jaxb2Marshaller;
    }

}
