package demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import demo.domain.LeadData;
import demo.service.LeadStorageService;


@Controller("rest/xml")
public class HelloController {
	
	@Autowired
	private LeadStorageService leadStorageService;
	
    @RequestMapping("/hello")
    public ModelAndView index() {
        ModelAndView mav=new ModelAndView("hello");
    	mav.addObject("helloMessage", "Greetings from GradleSpringBoot");
    	LeadData data=leadStorageService.getLeadData(1l);
        return mav;
    }
}
