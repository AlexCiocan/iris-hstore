package demo.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = Lead.TABLE_NAME)
@NamedQueries({ @NamedQuery(name = "lead.byLeadId", query = "select l from Lead l join fetch l.payload where l.leadId=:leadId"),})
public class Lead implements Serializable {
    private static final long serialVersionUID = 3139895201814574652L;
    public static final String EMAIL_COLUMN_NAME = "email";
    public static final String LEAD_ID_COLUMN_NAME = "lead_id";
    public static final String ORDER_ID_COLUMN_NAME = "order_id";
    public static final String TABLE_NAME = "lead";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = LEAD_ID_COLUMN_NAME)
    private Long leadId;
    @Column(name = "order_id")
    private Long orderId;
    @Column(name = "partner_id")
    private Long partnerId;
    @Column(name = "product_id")
    private String productId;
    @Column(name = "campaign_id")
    private Long campaignId;

    @OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL },optional=false, mappedBy = "lead")
    @PrimaryKeyJoinColumn
    private LeadData payload;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date")
    private Date creationDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modification_date")
    private Date modificationDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "export_date")
    private Date exportDate;

    private String status;

    @Column(name = EMAIL_COLUMN_NAME)
    private String email;

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public Date getExportDate() {
        return exportDate;
    }

    public void setExportDate(Date exportDate) {
        this.exportDate = exportDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPayload(LeadData payload) {

        this.payload = payload;

    }

    public Lead() {
    }

    public Lead(String name, Long orderId, Long partnerId) {
        this.orderId = orderId;
        this.partnerId = partnerId;
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    public Long getLeadId() {
        return leadId;
    }

    public void setLeadId(Long leadId) {
        this.leadId = leadId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Long partnerId) {
        this.partnerId = partnerId;
    }

    public LeadData getPayload() {
        return this.payload;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
        result = prime * result + ((exportDate == null) ? 0 : exportDate.hashCode());
        result = prime * result + ((modificationDate == null) ? 0 : modificationDate.hashCode());
        result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
        result = prime * result + ((partnerId == null) ? 0 : partnerId.hashCode());
        result = prime * result + ((payload == null) ? 0 : payload.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((leadId == null) ? 0 : leadId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Lead other = (Lead) obj;
        if (creationDate == null) {
            if (other.creationDate != null) {
                return false;
            }
        } else if (!creationDate.equals(other.creationDate)) {
            return false;
        }
        if (exportDate == null) {
            if (other.exportDate != null) {
                return false;
            }
        } else if (!exportDate.equals(other.exportDate)) {
            return false;
        }
        if (modificationDate == null) {
            if (other.modificationDate != null) {
                return false;
            }
        } else if (!modificationDate.equals(other.modificationDate)) {
            return false;
        }
        if (orderId == null) {
            if (other.orderId != null) {
                return false;
            }
        } else if (!orderId.equals(other.orderId)) {
            return false;
        }
        if (partnerId == null) {
            if (other.partnerId != null) {
                return false;
            }
        } else if (!partnerId.equals(other.partnerId)) {
            return false;
        }
        if (payload == null) {
            if (other.payload != null) {
                return false;
            }
        } else if (!payload.equals(other.payload)) {
            return false;
        }
        if (status != other.status) {
            return false;
        }
        if (leadId == null) {
            if (other.leadId != null) {
                return false;
            }
        } else if (!leadId.equals(other.leadId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Lead [leadId=" + leadId + ", productId=" + productId + ", orderId=" + orderId + ", partnerId="
                + partnerId + ", payload=" + ", creationDate=" + creationDate + ", modificationDate="
                + modificationDate + ", exportDate=" + exportDate + ", status=" + status + "]";
    }


}
