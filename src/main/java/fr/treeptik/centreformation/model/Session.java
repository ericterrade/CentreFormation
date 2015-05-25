package fr.treeptik.centreformation.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Session implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@OneToOne
	private Formation formation;

	@ManyToMany
	@JsonIgnore
	private List<Stagiaire> stagiaires;

	@Temporal(TemporalType.DATE)
	private Date dateDeDebut;

	@Temporal(TemporalType.DATE)
	private Date dateDeFin;

	public Session() {
		// TODO Auto-generated constructor stub
	}

	public Session(Integer id, Formation formation, List<Stagiaire> stagiaires,
			Date dateDeDebut, Date dateDeFin) {
		super();
		this.id = id;
		this.formation = formation;
		this.stagiaires = stagiaires;
		this.dateDeDebut = dateDeDebut;
		this.dateDeFin = dateDeFin;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	public List<Stagiaire> getStagiaires() {
		return stagiaires;
	}

	public void setStagiaires(List<Stagiaire> stagiaires) {
		this.stagiaires = stagiaires;
	}

	public Date getDateDeDebut() {
		return dateDeDebut;
	}

	public void setDateDeDebut(Date dateDeDebut) {
		this.dateDeDebut = dateDeDebut;
	}

	public Date getDateDeFin() {
		return dateDeFin;
	}

	public void setDateDeFin(Date dateDeFin) {
		this.dateDeFin = dateDeFin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dateDeDebut == null) ? 0 : dateDeDebut.hashCode());
		result = prime * result
				+ ((dateDeFin == null) ? 0 : dateDeFin.hashCode());
		result = prime * result
				+ ((formation == null) ? 0 : formation.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((stagiaires == null) ? 0 : stagiaires.hashCode());
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
		Session other = (Session) obj;
		if (dateDeDebut == null) {
			if (other.dateDeDebut != null)
				return false;
		} else if (!dateDeDebut.equals(other.dateDeDebut))
			return false;
		if (dateDeFin == null) {
			if (other.dateDeFin != null)
				return false;
		} else if (!dateDeFin.equals(other.dateDeFin))
			return false;
		if (formation == null) {
			if (other.formation != null)
				return false;
		} else if (!formation.equals(other.formation))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (stagiaires == null) {
			if (other.stagiaires != null)
				return false;
		} else if (!stagiaires.equals(other.stagiaires))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Session [id=" + id + ", formation=" + formation
				+ ", stagiaires=" + stagiaires + ", dateDeDebut=" + dateDeDebut
				+ ", dateDeFin=" + dateDeFin + "]";
	}

}
