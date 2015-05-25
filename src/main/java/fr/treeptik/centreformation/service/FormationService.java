package fr.treeptik.centreformation.service;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import fr.treeptik.centreformation.dao.FormationDAO;
import fr.treeptik.centreformation.exception.DAOException;
import fr.treeptik.centreformation.exception.ServiceException;
import fr.treeptik.centreformation.model.Formation;
import fr.treeptik.centreformation.model.FormationDTO;

@Stateless
public class FormationService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FormationDAO formationDAO;

	@PostConstruct
	public void init() {
		System.out.println("initialisation");
	}

	public Formation save(Formation formation) throws ServiceException {
		try {
			return formationDAO.save(formation);
		} catch (DAOException e) {
			throw new ServiceException("Erreur de Sauvegarde du stagiaire", e);
		}
	}

	public List<Formation> findAll() {
		return formationDAO.findAll();
	}

	public void delete(Formation formation) throws ServiceException {
		formation = find(formation);
		try {
			formationDAO.remove(formation);
		} catch (DAOException e) {
			throw new ServiceException("Erreur de Suppression du formation", e);
		}
	}

	public Formation update(Formation formation) throws ServiceException {
		try {
			return formationDAO.update(formation);
		} catch (DAOException e) {
			throw new ServiceException("Erreur de mise à jour du formation", e);
		}
	}

	public Formation find(Formation formation) {
		return formationDAO.findById(formation.getId());
	}
	
	public List<FormationDTO> findAllFormationDTO() {
		return formationDAO.findAllFormationDTO();
	}

}
