package demo.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import demo.domain.Lead;
import demo.domain.LeadData;
import demo.repository.LeadStorageRepository;

@Repository("leadStorageRepository")
public class LeadStorageRepositoryImpl implements LeadStorageRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Lead getLead(Long leadId) {
		return em.find(Lead.class, leadId);
	}

	@Override
	public List<Lead> getLead(Long orderId, Long partnerId) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();

		CriteriaQuery<Lead> query = builder.createQuery(Lead.class);
		Root<Lead> leadRoot = query.from(Lead.class);
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(builder.equal(leadRoot.get("orderId"), orderId));
		if (partnerId != null) {
			predicates.add(builder.and(builder.equal(leadRoot.get("partnerId"),
					partnerId)));
		}

		query.where(predicates.toArray(new Predicate[predicates.size()]));
		List<Lead> leads = getEntityManager().createQuery(query)
				.getResultList();
		return leads;
	}

	@Override
	public LeadData getLeadData(Long leadId) {
		Lead lead = getEntityManager()
				.createNamedQuery("lead.byLeadId", Lead.class)
				.setParameter("leadId", leadId).getSingleResult();
		return lead.getPayload();
	}

	@Override
	public Long saveLead(Lead lead) {
		save(lead);
		return lead.getLeadId();
	}

	@Override
	public Long updateLead(Lead lead) {
		Lead updatedLead = merge(lead);
		return updatedLead.getLeadId();
	}

	@Override
	public Long saveLeadData(LeadData leadData) {
		save(leadData);
		// TODO refactor this -> this seems to force an additional load
		// operation for the not owning side
		return leadData.getLead().getLeadId();

	}

	@Override
	public Long updateLeadData(LeadData leadData) {
		LeadData updatedLeadData = merge(leadData);
		return updatedLeadData.getLead().getLeadId();
	}

	@Override
	public void updateLeadStatus(Long orderId, Long partnerId, String status) {
		List<Lead> leads = getLead(orderId, partnerId);

		for (int i = 0; i < leads.size(); i++) {
			Lead lead = leads.get(i);
			lead.setStatus(status);
			save(lead);
		}
	}

	protected void save(Object entity) {
		getEntityManager().persist(entity);
	}

	protected <T> T merge(T entity) {
		return getEntityManager().merge(entity);
	}

	protected EntityManager getEntityManager() {
		return this.em;
	}

	// private Long getCount(String countQuery) {
	// Number count = (Number)
	// getEntityManager().createNativeQuery(countQuery).getSingleResult();
	// return count.longValue();
	// }
	//
	// @SuppressWarnings("unchecked")
	// private List<Object[]> listLeadColumns(String query) {
	// List<Object[]> leadColumns =
	// getEntityManager().createNativeQuery(query).getResultList();
	// return leadColumns;
	//
	// }
}
