package fr.treeptik.centreformation.webservices;

import java.util.List;

import javax.inject.Inject;
import javax.jws.WebService;

import fr.treeptik.centreformation.exception.ServiceException;
import fr.treeptik.centreformation.model.Formation;
import fr.treeptik.centreformation.service.FormationService;

@WebService
public class FormationWS {

	@Inject
	private FormationService formationService;

	public Formation saveFromWebService(Formation formation)
			throws ServiceException {
		return formationService.save(formation);
	}

	public List<Formation> findAllFromWebService() {
		return formationService.findAll();
	}
}
