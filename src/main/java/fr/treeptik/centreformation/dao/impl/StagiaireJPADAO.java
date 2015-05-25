package fr.treeptik.centreformation.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import fr.treeptik.centreformation.dao.StagiaireDAO;
import fr.treeptik.centreformation.dao.impl.GenericJPADAO;
import fr.treeptik.centreformation.model.FormationDTO;
import fr.treeptik.centreformation.model.Stagiaire;
import fr.treeptik.centreformation.model.StagiaireDTO;

@Stateless
public class StagiaireJPADAO extends GenericJPADAO<Stagiaire, Integer>
		implements StagiaireDAO {

	public StagiaireJPADAO() {
		super(Stagiaire.class);
	}

	public List<StagiaireDTO> findAllStagiaireDTO(){

		TypedQuery<StagiaireDTO> query = entityManager.createQuery(//Integer id, String nom, String prenom, String email
				"select new fr.treeptik.centreformation.model.StagiaireDTO(s.id, s.nom, s.prenom, s.email) from Stagiaire s ", StagiaireDTO.class);
		return query.getResultList();
	}
}
