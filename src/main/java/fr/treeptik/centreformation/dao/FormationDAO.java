package fr.treeptik.centreformation.dao;

import java.util.List;

import fr.treeptik.centreformation.model.Formation;
import fr.treeptik.centreformation.model.FormationDTO;

public interface FormationDAO extends GenericDAO<Formation, Integer>{

	List<FormationDTO> findAllFormationDTO();

}