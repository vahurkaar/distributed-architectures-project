package ee.ttu.controller;

import ee.ttu.util.XMLGregorianCalendarEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ws.WebServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Created by Vahur Kaar on 9.11.2014.
 */
@ControllerAdvice
public class ControllerAdviceImpl {

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
        binder.registerCustomEditor(XMLGregorianCalendar.class, new XMLGregorianCalendarEditor("dd-MM-yyyy"));
    }

    @ExceptionHandler(value = WebServiceException.class)
    public ModelAndView handleWebServiceException(WebServiceException ex) {
        ModelAndView modelAndView = new ModelAndView("error/webServiceException");
        modelAndView.addObject("message", ex.getRootCause().getMessage());
        return modelAndView;
    }

    @ExceptionHandler(value = Exception.class)
    public ModelAndView handleException(Exception ex) {
        ModelAndView modelAndView = new ModelAndView("error/exception");
        modelAndView.addObject("message", ex.getMessage());
        return modelAndView;
    }

}
