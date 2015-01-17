package ee.ttu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Vahur Kaar on 10.01.2015.
 */
@Controller
public class LogoutController {

    @Autowired
    private Environment environment;

    @RequestMapping(value = "/logout.do")
    public ModelAndView logout(HttpServletRequest request) throws UnsupportedEncodingException {
        String logoutUrl = environment.getProperty("cas.location") + "/logout";
        return new ModelAndView("redirect:" + logoutUrl);
    }

}
