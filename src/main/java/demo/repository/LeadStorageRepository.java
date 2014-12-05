package demo.repository;

import java.util.List;

import demo.domain.Lead;
import demo.domain.LeadData;



public interface LeadStorageRepository {
    public List<Lead> getLead(Long orderId, Long partnerId);
    
    public Lead getLead(Long leadId);

    public LeadData getLeadData(Long leadId);

    public Long saveLead(Lead lead);

    public Long updateLead(Lead lead);

    public Long saveLeadData(LeadData leadData);

    public Long updateLeadData(LeadData leadData);

    public void updateLeadStatus(Long orderId, Long partnerId, String status);

    /**
     * @param columns
     * @return
     * @deprecated since 0.0.14 - should be replaced with the listLeads(LeadStorageQueryContext,...) call with no pagination params
     */
//    @Deprecated
//    public List<Object[]> listLeadColumns(List<LeadQueryColumnParam> columns);
//
//    public List<Object[]> listLeads(LeadStorageQueryContext queryContext);
//
//    public Long getCount(LeadStorageQueryContext queryContext);
}
