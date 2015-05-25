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
import fr.treeptik.centreformation.model.Session;
import fr.treeptik.centreformation.model.Stagiaire;
import fr.treeptik.centreformation.service.SessionService;

@ApplicationPath("rest")
@Path("session")
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class SessionRessource extends Application {

	
	@Inject
	private SessionService sessionService;

	public SessionService getSessionService() {
		return sessionService;
	}

	public void setSessionService(SessionService sessionService) {
		this.sessionService = sessionService;
	}
	
	@GET
	@Path("id/{id}") //http://localhost:8080/CentreFormation/rest/session/id/3
	public Session getFromRestByID(@PathParam("id") Integer id){
		Session session = new Session();
		session.setId(id);
		return sessionService.find(session);
		
	}
	
	@POST //{ "nom" : "Legrand", "prenom": "Jean-Luc" }
	public Session saveFromRest(Session session) throws ServiceException {
		return sessionService.save(session);

	}
	
	@GET
	public List<Session> findAllFromRest(){
		return sessionService.findAll();
	}
	
	@GET
	@Path("idSession/{id}") //http://localhost:8080/CentreFormation/rest/session/id/3
	public List<Stagiaire> findAllStagiaireFromSessionFromRest(@PathParam("id") Integer id){
		return sessionService.findAllStagiaireFromSession(id);
	}
	
	@DELETE
	@Path("id/{id}")
	public void deleteFromRest(@PathParam("id") Integer id) throws ServiceException{
		Session session = new Session();
		session.setId(id);
		sessionService.delete(session);
	}
	
}
