package fr.treeptik.centreformation.webservices;

import java.util.List;

import javax.inject.Inject;
import javax.jws.WebService;

import fr.treeptik.centreformation.exception.ServiceException;
import fr.treeptik.centreformation.model.Formateur;
import fr.treeptik.centreformation.service.FormateurService;

@WebService
public class FormateurWS {

	@Inject
	private FormateurService formateurService;

	public Formateur saveFromWebService(Formateur formateur)
			throws ServiceException {
		return formateurService.save(formateur);
	}

	public List<Formateur> findAllFromWebService() {
		return formateurService.findAll();
	}
}
