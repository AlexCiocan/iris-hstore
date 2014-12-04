package demo.domain;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = LeadData.TABLE_NAME)
public class LeadData implements Serializable {
    private static final long serialVersionUID = 3988263845365540680L;
    public static final String TABLE_NAME = "lead_data";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OneToOne(fetch = FetchType.LAZY, optional=false,cascade = { CascadeType.ALL })
    @JoinColumn(name = Lead.LEAD_ID_COLUMN_NAME)
    private Lead lead;

    @Type(type = "demo.domain.adapter.PostgreSQLXmlType")
    @Basic(fetch = FetchType.LAZY)
    private String payload;

    public LeadData() {

    }

    public LeadData(String payload) {
        this.payload = payload;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public Lead getLead() {
        return lead;
    }

    public void setLead(Lead lead) {
        this.lead = lead;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((payload == null) ? 0 : payload.hashCode());
        result = prime * result + ((lead == null) ? 0 : lead.hashCode());
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
        LeadData other = (LeadData) obj;
        if (payload == null) {
            if (other.payload != null) {
                return false;
            }
        } else if (!payload.equals(other.payload)) {
            return false;
        }
        if (lead == null) {
            if (other.lead != null) {
                return false;
            }
        } else if (!lead.equals(other.lead)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LeadData [ payload=" + payload + "]";
    }
}
