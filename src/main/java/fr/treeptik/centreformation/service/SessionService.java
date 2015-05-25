package fr.treeptik.centreformation.service;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import fr.treeptik.centreformation.dao.SessionDAO;
import fr.treeptik.centreformation.exception.DAOException;
import fr.treeptik.centreformation.exception.ServiceException;
import fr.treeptik.centreformation.model.Session;
import fr.treeptik.centreformation.model.Stagiaire;

@Stateless
public class SessionService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private SessionDAO sessionDAO;

	@PostConstruct
	public void init() {
		System.out.println("initialisation");
	}

	public Session save(Session session) throws ServiceException {
		try {
			return sessionDAO.save(session);
		} catch (DAOException e) {
			throw new ServiceException("Erreur de Sauvegarde de session", e);
		}
	}

	public List<Session> findAll() {
		return sessionDAO.findAll();
	}

	public void delete(Session session) throws ServiceException {
		session = find(session);
		try {
			sessionDAO.remove(session);
		} catch (DAOException e) {
			throw new ServiceException("Erreur de Sauvegarde de session", e);
		}
	}

	public Session update(Session session) throws ServiceException {
		try {
			return sessionDAO.update(session);
		} catch (DAOException e) {
			throw new ServiceException("Erreur de Sauvegarde de session", e);
		}
	}

	public Session find(Session session) {
		return sessionDAO.findById(session.getId());
	}

	public List<Stagiaire> findAllStagiaireFromSession(Integer id) {
		return sessionDAO.findAllStagiaireFromSession(id);
	}
}
