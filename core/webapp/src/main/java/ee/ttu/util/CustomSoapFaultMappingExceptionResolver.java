package ee.ttu.util;

import ee.ttu.exception.CoreException;
import org.springframework.ws.soap.server.endpoint.SoapFaultMappingExceptionResolver;

/**
 * Created by Vahur Kaar on 6.10.2014.
 */
public class CustomSoapFaultMappingExceptionResolver extends SoapFaultMappingExceptionResolver {

    @Override
    protected int getDepth(String exceptionMapping, Exception ex) {
        try {
            Class exceptionClass = Class.forName(exceptionMapping);

            if (exceptionClass.isInstance(ex)) {
                return 1;
            }
        } catch (ClassNotFoundException e) {
            throw new CoreException("Fault mapping exception class invalid!");
        }

        return -1;
    }
}
