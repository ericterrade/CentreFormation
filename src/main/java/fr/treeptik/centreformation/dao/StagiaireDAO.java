package fr.treeptik.centreformation.dao;

import java.util.List;

import fr.treeptik.centreformation.model.Stagiaire;
import fr.treeptik.centreformation.model.StagiaireDTO;
import fr.treeptik.centreformation.dao.GenericDAO;


public interface StagiaireDAO extends GenericDAO<Stagiaire, Integer>{

	List<StagiaireDTO> findAllStagiaireDTO();

}
