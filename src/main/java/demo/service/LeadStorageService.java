package demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import demo.domain.Lead;
import demo.domain.LeadData;
import demo.repository.LeadStorageRepository;

@Component
public class LeadStorageService {
	
	@Autowired
	private LeadStorageRepository leadStorageRepository;
	
	public LeadData getLeadData(Long leadId)
	{
		return leadStorageRepository.getLeadData(leadId);
	}
	
	public Lead getLead(Long leadId)
	{
		return leadStorageRepository.getLead(leadId);
	}

}
