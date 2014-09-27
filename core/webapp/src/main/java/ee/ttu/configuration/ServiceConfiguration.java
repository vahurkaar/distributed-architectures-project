package ee.ttu.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

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

    @Bean
    public DefaultWsdl11Definition customers() {
        DefaultWsdl11Definition defaultWsdl11Definition = new DefaultWsdl11Definition();
        defaultWsdl11Definition.setLocationUri("/customerService/");
        defaultWsdl11Definition.setPortTypeName("CustomerResource");
        defaultWsdl11Definition.setRequestSuffix("Request");
        defaultWsdl11Definition.setResponseSuffix("Response");
        defaultWsdl11Definition.setServiceName("CustomerService");
        defaultWsdl11Definition.setTargetNamespace("http://www.ttu.ee/hajusarhitektuurid");
        defaultWsdl11Definition.setSchema(customerSchema());

        return defaultWsdl11Definition;
    }

    @Bean
    public XsdSchema customerSchema() {
        SimpleXsdSchema xsdSchema = new SimpleXsdSchema();
        xsdSchema.setXsd(new ClassPathResource("customer.xsd"));
        return xsdSchema;
    }

    @Bean
    public Marshaller marshaller() {
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setContextPath("ee.ttu.xml");
        return jaxb2Marshaller;
    }

}
