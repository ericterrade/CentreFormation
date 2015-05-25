package fr.treeptik.centreformation.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;

import org.jboss.logging.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DualListModel;

import fr.treeptik.centreformation.exception.ServiceException;
import fr.treeptik.centreformation.model.Formation;
import fr.treeptik.centreformation.model.FormationDTO;
import fr.treeptik.centreformation.model.Session;
import fr.treeptik.centreformation.model.Stagiaire;
import fr.treeptik.centreformation.model.StagiaireDTO;
import fr.treeptik.centreformation.service.FormationService;
import fr.treeptik.centreformation.service.SessionService;
import fr.treeptik.centreformation.service.StagiaireService;

@ManagedBean
@ViewScoped
public class SessionManagedBean {

	private List<Session> filteredSessions;

	private Session mySession;
	private FormationDTO formation;
	private StagiaireDTO stagiaire;

	private Session selectedSession;

	private ListDataModel<Session> sessions;
	private List<FormationDTO> formations;
	private List<StagiaireDTO> stagiaires;
	private DualListModel<StagiaireDTO> model;
	
	private List<Session> selectedSessions;
	private List<Stagiaire> selectedStagiaire;

	private Logger logger = Logger.getLogger(SessionManagedBean.class);

	@Inject
	private SessionService sessionService;
	@Inject
	private FormationService formationService;
	@Inject
	private StagiaireService stagiaireService;

	@PostConstruct
	public void init() {
		mySession = new Session();
		formation = new FormationDTO();
		sessions = new ListDataModel<Session>();
		sessions.setWrappedData(sessionService.findAll());
		formations = formationService.findAllFormationDTO();
		stagiaires = stagiaireService.findAllStagiaireDTO();
		model = new DualListModel<>(stagiaires,
	            new ArrayList<StagiaireDTO>()
	        );
	}

	public Session getMySession() {
		return mySession;
	}

	public void setMySession(Session session) {
		this.mySession = session;
	}

	public String create() throws ServiceException {
		System.out.println(stagiaires);
		Formation f = new Formation();
		f.setId(formation.getId());
		f = formationService.find(f);
		for(StagiaireDTO s:stagiaires){
			Stagiaire st = new Stagiaire();
			st.setId(s.getId());
			selectedStagiaire.add(stagiaireService.find(st));
		}
		Session s = new Session(mySession.getId(),f , selectedStagiaire, mySession.getDateDeDebut(), mySession.getDateDeFin());
		sessionService.save(s);
		
		return "session-list";
	}

	public String remove(Session session){
		RequestContext requestContext = RequestContext.getCurrentInstance();
		
		try {
			sessionService.delete(session);
			sessions.setWrappedData(sessionService.findAll());
			FacesContext
			.getCurrentInstance()
			.addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Info",
							"La session "+session.getId()+" a bien été supprimé ! "));
			requestContext.update("fTable:singleDT");
		} catch (EJBException | ServiceException e) {
			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_FATAL,
									"Attention!",
									"Vous ne pouvez pas supprimer la session  ! "));
		}
		return "session-list";
	}

	public ListDataModel<Session> getSessions() {
		return sessions;
	}

	public void setSessions(ListDataModel<Session> sessions) {
		this.sessions = sessions;
	}

	public List<Session> getFilteredSessions() {
		return filteredSessions;
	}

	public void setFilteredSessions(List<Session> filteredSessions) {
		this.filteredSessions = filteredSessions;
	}

	public void setService(SessionService service) {
		this.sessionService = service;
	}

	public List<Session> getSelectedSessions() {
		return selectedSessions;
	}

	public void setSelectedSessions(List<Session> selectedSessions) {
		this.selectedSessions = selectedSessions;
	}

	public Session getSelectedSession() {
		return selectedSession;
	}

	public void setSelectedSession(Session selectedSession) {
		this.selectedSession = selectedSession;
	}

	public void onDateSelect(SelectEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		facesContext.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected",
						format.format(event.getObject())));
	}

	public void onRowEdit(RowEditEvent event) throws ServiceException {
		logger.info("onRowEdit");
		Session session = (Session) event.getObject();
		sessionService.update(session);
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
						"La Session " + session.getId() + " a été modifié"));
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public FormationDTO getFormation() {
		return formation;
	}

	public void setFormation(FormationDTO formation) {
		this.formation = formation;
	}

	public List<StagiaireDTO> getStagiaires() {
		return stagiaires;
	}

	public void setStagiaires(List<StagiaireDTO> stagiaires) {
		this.stagiaires = stagiaires;
	}

	public StagiaireDTO getStagiaire() {
		return stagiaire;
	}

	public void setStagiaire(StagiaireDTO stagiaire) {
		this.stagiaire = stagiaire;
	}
	
	public List<FormationDTO> getFormations() {
		return formations;
	}

	public void setFormations(List<FormationDTO> formations) {
		this.formations = formations;
	}

	public List<Stagiaire> getSelectedStagiaire() {
		return selectedStagiaire;
	}

	public void setSelectedStagiaire(List<Stagiaire> selectedStagiaire) {
		this.selectedStagiaire = selectedStagiaire;
	}

	public DualListModel<StagiaireDTO> getModel() {
		return model;
	}

	public void setModel(DualListModel<StagiaireDTO> model) {
		this.model = model;
	}


}
