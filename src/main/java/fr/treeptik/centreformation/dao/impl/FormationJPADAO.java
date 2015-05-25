package fr.treeptik.centreformation.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import fr.treeptik.centreformation.dao.FormationDAO;
import fr.treeptik.centreformation.model.Formation;
import fr.treeptik.centreformation.model.FormationDTO;

@Stateless
public class FormationJPADAO extends GenericJPADAO<Formation, Integer> implements FormationDAO{

	public FormationJPADAO() {
		super(Formation.class);
	}
	

	public List<FormationDTO> findAllFormationDTO(){

		TypedQuery<FormationDTO> query = entityManager.createQuery(
				"select new fr.treeptik.centreformation.model.FormationDTO(f.id, f.intitule, f.niveau, f.duree ) from Formation f ", FormationDTO.class);
		return query.getResultList();
	}

}
