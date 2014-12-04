package demo.controller;

import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ch.qos.logback.classic.Logger;
import demo.domain.LeadData;
import demo.repository.LeadStorageRepository;


@Controller
public class HelloController {
	
	
	
	@Autowired
	private LeadStorageRepository leadStorageRepository;
	
    @RequestMapping("/hello")
    public ModelAndView index() {
        ModelAndView mav=new ModelAndView("hello");
    	mav.addObject("helloMessage", "Greetings from GradleSpringBoot");
    	LeadData data=leadStorageRepository.getLeadData(1l);
        return mav;
    }
}
