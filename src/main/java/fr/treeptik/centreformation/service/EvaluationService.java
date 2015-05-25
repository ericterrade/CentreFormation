package fr.treeptik.centreformation.service;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

import fr.treeptik.centreformation.dao.EvaluationDAO;
import fr.treeptik.centreformation.exception.DAOException;
import fr.treeptik.centreformation.model.Evaluation;

@Stateless
public class EvaluationService implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private EvaluationDAO evaluationDAO;

	@PostConstruct
	public void init() {
		System.out.println("initialisation");
	}

	public Evaluation save(Evaluation evaluation) throws DAOException {
		return evaluationDAO.save(evaluation);
	}

	public List<Evaluation> findAll() {
		return evaluationDAO.findAll();
	}

	public void delete(Evaluation evaluation) throws DAOException {
		evaluationDAO.remove(evaluation);
	}

	public Evaluation update(Evaluation evaluation) throws DAOException {
		return evaluationDAO.update(evaluation);
	}
	
}
