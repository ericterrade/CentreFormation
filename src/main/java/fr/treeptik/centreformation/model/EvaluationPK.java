package fr.treeptik.centreformation.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class EvaluationPK implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer stagiaireId;
	private Integer sessionId;

	public EvaluationPK() {
	}

	public EvaluationPK(Integer stagiaireId, Integer sessionId) {
		super();
		this.stagiaireId = stagiaireId;
		this.sessionId = sessionId;
	}

	public Integer getStagiaireId() {
		return stagiaireId;
	}

	public void setStagiaireId(Integer stagiaireId) {
		this.stagiaireId = stagiaireId;
	}

	public Integer getSessionId() {
		return sessionId;
	}

	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((sessionId == null) ? 0 : sessionId.hashCode());
		result = prime * result
				+ ((stagiaireId == null) ? 0 : stagiaireId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EvaluationPK other = (EvaluationPK) obj;
		if (sessionId == null) {
			if (other.sessionId != null)
				return false;
		} else if (!sessionId.equals(other.sessionId))
			return false;
		if (stagiaireId == null) {
			if (other.stagiaireId != null)
				return false;
		} else if (!stagiaireId.equals(other.stagiaireId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EvaluationPK [stagiaireId=" + stagiaireId + ", sessionId="
				+ sessionId + "]";
	}

}
