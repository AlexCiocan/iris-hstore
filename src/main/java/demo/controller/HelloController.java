package demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HelloController {
    @RequestMapping("/hello")
    public String index(ModelAndView mav) {
        mav.addObject("helloMessage", "Greetings from GradleSpringBoot");
        return "hello";
    }
}
