package fr.treeptik.centreformation.dao.impl;

import javax.ejb.Stateless;

import fr.treeptik.centreformation.dao.EvaluationDAO;
import fr.treeptik.centreformation.model.Evaluation;

@Stateless
public class EvaluationJPADAO extends GenericJPADAO<Evaluation, Integer> implements EvaluationDAO{

	public EvaluationJPADAO() {
		super(Evaluation.class);
	}

}
