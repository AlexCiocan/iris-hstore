package demo.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import demo.dto.LeadDTO;
import demo.facade.LeadFacade;

@Controller
@RequestMapping("/rest")
public class RestController {
	
	private static final Logger log=LoggerFactory.getLogger(RestController.class);
	
	@Autowired
	private LeadFacade leadFacade;
	
	@RequestMapping(method=RequestMethod.GET,value="/showLeads",produces="application/json")
	@ResponseBody
	public LeadDTO showLeads(@RequestParam(required=false)Long leadId)
	{
		log.debug("showLeads leadId={}",leadId);
		
		if(leadId==null)
			throw new RuntimeException("Please provide a leadId value");
		LeadDTO leadDTO=leadFacade.getLeadDTO(leadId);
		return leadDTO;
	}
	
	@ExceptionHandler
	public void handleException(Exception ex,HttpServletResponse response)
	{
		log.error(ex.getMessage(), ex);
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		
	}


}
