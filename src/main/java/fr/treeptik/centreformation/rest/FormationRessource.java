package fr.treeptik.centreformation.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import fr.treeptik.centreformation.exception.ServiceException;
import fr.treeptik.centreformation.model.Formation;
import fr.treeptik.centreformation.service.FormationService;

@ApplicationPath("rest")
@Path("formation")
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class FormationRessource extends Application {

	
	@Inject
	private FormationService formationService;

	public FormationService getFormationService() {
		return formationService;
	}

	public void setFormationService(FormationService formationService) {
		this.formationService = formationService;
	}
	
	@GET
	@Path("id/{id}") //http://localhost:8080/CentreFormation/rest/formation/id/3
	public Formation getFromRestByID(@PathParam("id") Integer id){
		Formation formation = new Formation();
		formation.setId(id);
		return formationService.find(formation);
		
	}
	
	@POST
	public Formation saveFromRest(Formation formation) throws ServiceException {
		return formationService.save(formation);

	}
	
	@GET
	public List<Formation> findAllFromRest(){
		return formationService.findAll();
	}
	
	@DELETE
	@Path("id/{id}")
	public void deleteFromRest(@PathParam("id") Integer id) throws ServiceException{
		Formation formation = new Formation();
		formation.setId(id);
		formationService.delete(formation);
	}
	
}
