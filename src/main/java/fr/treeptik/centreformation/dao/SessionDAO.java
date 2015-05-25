package fr.treeptik.centreformation.dao;

import java.util.List;

import fr.treeptik.centreformation.model.Session;
import fr.treeptik.centreformation.model.Stagiaire;

public interface SessionDAO extends GenericDAO<Session, Integer>{

	List<Stagiaire> findAllStagiaireFromSession(Integer id);

}
