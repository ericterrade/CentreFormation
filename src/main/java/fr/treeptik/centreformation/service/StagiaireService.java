package fr.treeptik.centreformation.service;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.PersistenceException;

import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.logging.Logger;

import fr.treeptik.centreformation.dao.StagiaireDAO;
import fr.treeptik.centreformation.exception.DAOException;
import fr.treeptik.centreformation.model.Stagiaire;
import fr.treeptik.centreformation.model.StagiaireDTO;
import fr.treeptik.centreformation.exception.ServiceException;

@Stateless
public class StagiaireService implements Serializable {

	private static final long serialVersionUID = 1L;

	private Logger logger = Logger.getLogger(StagiaireService.class);

	@Inject
	private StagiaireDAO stagiaireDAO;

	@PostConstruct
	public void init() {
		System.out.println("initialisation");
	}

	@Transactional
	public Stagiaire save(Stagiaire stagiaire) throws ServiceException {

		try {
			return stagiaireDAO.save(stagiaire);
		} catch (DAOException e) {
			throw new ServiceException("Erreur de Sauvegarde du stagiaire", e);
		}
	}

	public List<Stagiaire> findAll() {
		return stagiaireDAO.findAll();
	}

	@Transactional
	public void delete(Stagiaire stagiaire) throws ServiceException{
		stagiaire = find(stagiaire);
		try {
			stagiaireDAO.remove(stagiaire);
		} catch (DAOException e) {
			throw new ServiceException("Erreur de Suppression du stagiaire", e);
		}

	}

	@Transactional
	public Stagiaire update(Stagiaire stagiaire) throws ServiceException {
		try {
			return stagiaireDAO.update(stagiaire);
		} catch (DAOException e) {
			throw new ServiceException("Erreur de mise à jour du stagiaire", e);
		}
	}

	public Stagiaire find(Stagiaire stagiaire) {
		return stagiaireDAO.findById(stagiaire.getId());
	}
	
	public List<StagiaireDTO> findAllStagiaireDTO(){
		return stagiaireDAO.findAllStagiaireDTO();
	}

}
