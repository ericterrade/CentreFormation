package fr.treeptik.centreformation.service;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import fr.treeptik.centreformation.dao.FormateurDAO;
import fr.treeptik.centreformation.exception.DAOException;
import fr.treeptik.centreformation.exception.ServiceException;
import fr.treeptik.centreformation.model.Formateur;

@Stateless
public class FormateurService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FormateurDAO formateurDAO;

	@PostConstruct
	public void init() {
		System.out.println("initialisation");
	}

	public Formateur save(Formateur formateur) throws ServiceException {
		try {
			return formateurDAO.save(formateur);
		} catch (DAOException e) {
			throw new ServiceException("Erreur de Sauvegarde du formateur", e);
		}
	}

	public List<Formateur> findAll() {
		return formateurDAO.findAll();
	}

	public void delete(Formateur formateur) throws ServiceException {
		formateur = find(formateur);
		try {
			formateurDAO.remove(formateur);
		} catch (DAOException e) {
			throw new ServiceException("Erreur de suppression du formateur", e);
		}
	}

	public Formateur update(Formateur formateur) throws ServiceException{
		try {
			return formateurDAO.update(formateur);
		} catch (DAOException e) {
			throw new ServiceException("Erreur de mise à jour du formateur", e);
		}
	}

	public Formateur find(Formateur formateur) {
		return formateurDAO.findById(formateur.getId());
	}
}
