package fr.treeptik.centreformation.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import fr.treeptik.centreformation.dao.SessionDAO;
import fr.treeptik.centreformation.model.Session;
import fr.treeptik.centreformation.model.Stagiaire;
@Stateless
public class SessionJPADAO extends GenericJPADAO<Session, Integer> implements SessionDAO{

	public SessionJPADAO() {
		super(Session.class);
	}

	@Override
	public List<Stagiaire> findAllStagiaireFromSession(Integer id) {

		TypedQuery<Stagiaire> query = entityManager.createQuery(
				"select st from Session se left join se.stagiaires st where se.id= :id", Stagiaire.class).setParameter("id", id);
		return query.getResultList();
	}

}
