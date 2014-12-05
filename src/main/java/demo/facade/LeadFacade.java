package demo.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import demo.domain.Lead;
import demo.dto.LeadDTO;
import demo.service.LeadStorageService;

@Component
public class LeadFacade {
	private static final Logger log = LoggerFactory.getLogger(LeadFacade.class);
	@Autowired
	private LeadStorageService leadService;

	public LeadDTO getLeadDTO(Long leadId) {
		log.info(String.format("Get Lead for leadId={%s}", leadId));

		Lead lead = leadService.getLead(leadId);
		return createLeadDTO(lead);

	}

	private LeadDTO createLeadDTO(Lead lead) {
		LeadDTO dto = new LeadDTO();
		dto.setCampaignId(lead.getCampaignId());
		dto.setCreationDate(lead.getCreationDate());
		dto.setEmail(lead.getEmail());
		dto.setExportDate(lead.getExportDate());
		dto.setLeadId(lead.getLeadId());
		dto.setModificationDate(lead.getModificationDate());
		dto.setOrderId(lead.getOrderId());
		dto.setPartnerId(lead.getPartnerId());
		dto.setProductId(lead.getProductId());
		dto.setStatus(lead.getStatus());
		return dto;
	}
}
