package fr.treeptik.centreformation.rest;


import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import fr.treeptik.centreformation.exception.ServiceException;
import fr.treeptik.centreformation.model.Stagiaire;
import fr.treeptik.centreformation.model.StagiaireDTO;
import fr.treeptik.centreformation.service.StagiaireService;



@ApplicationPath("rest")
@Path("stagiaire")
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class StagiaireRessource extends Application {

	
	@Inject
	private StagiaireService stagiaireService;
	
	@GET
	@Path("id/{id}") //http://localhost:8080/CentreFormation/rest/stagiaire/id/3
	public Stagiaire getFromRestByID(@PathParam("id") Integer id){
		Stagiaire stagiaire = new Stagiaire();
		stagiaire.setId(id);
		return stagiaireService.find(stagiaire);
		
	}
	
	@POST //{ "nom" : "Legrand", "prenom": "Jean-Luc" }
	public Stagiaire saveFromRest(Stagiaire stagiaire) throws ServiceException {
		return stagiaireService.save(stagiaire);

	}
	
	@PUT 
	public Stagiaire updateFromRest(Stagiaire stagiaire) throws ServiceException {
		return stagiaireService.update(stagiaire);

	}
	
	@GET
	public List<Stagiaire> findAllFromRest(){
		return stagiaireService.findAll();
	}
	
	@DELETE
	@Path("id/{id}")
	public void deleteFromRest(@PathParam("id") Integer id) throws ServiceException{
		Stagiaire stagiaire = new Stagiaire();
		stagiaire.setId(id);
		stagiaireService.delete(stagiaire);
	}
	
	@OPTIONS
	public List<StagiaireDTO> extraFromRest(){
		return stagiaireService.findAllStagiaireDTO();
	}
}
