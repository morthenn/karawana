package allinone.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    
    private final Logger log = LoggerFactory.getLogger(getClass());
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainPage() {
        log.info("QWqwEASD");
        System.out.println("weasdasdasd");
        return "/pages/main";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        
        return "/pages/login";
    }
    
    @ResponseBody
    @RequestMapping(value = "/login2", method = RequestMethod.GET)
    public String login2() {
        
        return "404";
    }
}