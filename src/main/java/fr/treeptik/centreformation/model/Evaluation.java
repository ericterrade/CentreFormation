package fr.treeptik.centreformation.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Evaluation implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EvaluationPK id;
	@Temporal(TemporalType.DATE)
	private Date dateCourante;
	@ManyToOne
	@MapsId("sessionId")
	@JoinColumn(name = "session_id")
	private Session session;
	private Integer note;
	@ManyToOne
	@MapsId("stagiaireId")
	@JoinColumn(name = "stagiaire_id")
	private Stagiaire stagiaire;
	
	public Evaluation() {
	}

	public Evaluation(EvaluationPK id, Date dateCourante, Session session,
			Integer note, Stagiaire stagiaire) {
		super();
		this.id = id;
		this.dateCourante = dateCourante;
		this.session = session;
		this.note = note;
		this.stagiaire = stagiaire;
	}

	public EvaluationPK getId() {
		return id;
	}

	public void setId(EvaluationPK id) {
		this.id = id;
	}

	public Date getDateCourante() {
		return dateCourante;
	}

	public void setDateCourante(Date dateCourante) {
		this.dateCourante = dateCourante;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Integer getNote() {
		return note;
	}

	public void setNote(Integer note) {
		this.note = note;
	}

	public Stagiaire getStagiaire() {
		return stagiaire;
	}

	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dateCourante == null) ? 0 : dateCourante.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((note == null) ? 0 : note.hashCode());
		result = prime * result + ((session == null) ? 0 : session.hashCode());
		result = prime * result
				+ ((stagiaire == null) ? 0 : stagiaire.hashCode());
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
		Evaluation other = (Evaluation) obj;
		if (dateCourante == null) {
			if (other.dateCourante != null)
				return false;
		} else if (!dateCourante.equals(other.dateCourante))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (note == null) {
			if (other.note != null)
				return false;
		} else if (!note.equals(other.note))
			return false;
		if (session == null) {
			if (other.session != null)
				return false;
		} else if (!session.equals(other.session))
			return false;
		if (stagiaire == null) {
			if (other.stagiaire != null)
				return false;
		} else if (!stagiaire.equals(other.stagiaire))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Evaluation [id=" + id + ", dateCourante=" + dateCourante
				+ ", session=" + session + ", note=" + note + ", stagiaire="
				+ stagiaire + "]";
	}

	

	
}
