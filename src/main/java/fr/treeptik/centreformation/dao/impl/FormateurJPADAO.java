package fr.treeptik.centreformation.dao.impl;

import java.util.List;

import javax.ejb.Stateless;

import fr.treeptik.centreformation.dao.FormateurDAO;
import fr.treeptik.centreformation.model.Formateur;
import fr.treeptik.centreformation.model.Stagiaire;

@Stateless
public class FormateurJPADAO extends GenericJPADAO<Formateur, Integer> implements FormateurDAO{

	public FormateurJPADAO() {
		super(Formateur.class);
	}
	
	public List<Stagiaire> findAllStagiaireFromSession(Integer id) {
		return null;

//		TypedQuery<Formateur> query = entityManager.createQuery(
//				"select st from Formateur f1 left join f.formation f2 where se.id= :id", Stagiaire.class).setParameter("id", id);
//		return query.getResultList();
	}

}
