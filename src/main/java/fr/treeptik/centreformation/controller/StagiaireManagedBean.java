package fr.treeptik.centreformation.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.transaction.RollbackException;

import org.jboss.logging.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import fr.treeptik.centreformation.exception.DAOException;
import fr.treeptik.centreformation.exception.ServiceException;
import fr.treeptik.centreformation.model.Stagiaire;
import fr.treeptik.centreformation.service.StagiaireService;

@ManagedBean
@ViewScoped
public class StagiaireManagedBean {

	private List<Stagiaire> filteredStagiaires;

	private Stagiaire stagiaire;

	private Stagiaire selectedStagiaire;

	private ListDataModel<Stagiaire> stagiaires;

	private List<Stagiaire> selectedStagiaires;

	private Logger logger = Logger.getLogger(StagiaireManagedBean.class);

	@Inject
	private StagiaireService stagiaireService;

	@PostConstruct
	public void init() {
		stagiaire = new Stagiaire();
		stagiaires = new ListDataModel<Stagiaire>();
		stagiaires.setWrappedData(stagiaireService.findAll());
	}

	public Stagiaire getStagiaire() {
		return stagiaire;
	}

	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}

	public String create() throws ServiceException {
		stagiaireService.save(stagiaire);
		return "stagiaire-list";
	}

	public String remove(Stagiaire stagiaire){
		RequestContext requestContext = RequestContext.getCurrentInstance();
		
		try {
			stagiaireService.delete(stagiaire);
			stagiaires.setWrappedData(stagiaireService.findAll());
			FacesContext
			.getCurrentInstance()
			.addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Info",
							"Le stagiaire "+stagiaire.getPrenom()+" "+stagiaire.getNom()+" a bien été supprimé ! "));
			requestContext.update("fTable:singleDT");
		} catch (EJBException | ServiceException e) {
			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_FATAL,
									"Attention!",
									"Vous ne pouvez pas supprimer "+stagiaire.getPrenom()+" "+stagiaire.getNom()+" il appartient à une session ! "));
		}
		return "stagiaire-list";
	}

	public ListDataModel<Stagiaire> getStagiaires() {
		return stagiaires;
	}

	public void setStagiaires(ListDataModel<Stagiaire> stagiaires) {
		this.stagiaires = stagiaires;
	}

	public List<Stagiaire> getFilteredStagiaires() {
		return filteredStagiaires;
	}

	public void setFilteredStagiaires(List<Stagiaire> filteredStagiaires) {
		this.filteredStagiaires = filteredStagiaires;
	}

	public void setService(StagiaireService service) {
		this.stagiaireService = service;
	}

	public List<Stagiaire> getSelectedStagiaires() {
		return selectedStagiaires;
	}

	public void setSelectedStagiaires(List<Stagiaire> selectedStagiaires) {
		this.selectedStagiaires = selectedStagiaires;
	}

	public Stagiaire getSelectedStagiaire() {
		return selectedStagiaire;
	}

	public void setSelectedStagiaire(Stagiaire selectedStagiaire) {
		this.selectedStagiaire = selectedStagiaire;
	}

	public void onDateSelect(SelectEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		facesContext.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected",
						format.format(event.getObject())));
	}

	public void onRowEdit(RowEditEvent event) throws ServiceException {
		System.out.println("onRowEdit sysout");
		logger.info("onRowEdit logger");
		Stagiaire stagiaire = (Stagiaire) event.getObject();
		stagiaireService.update(stagiaire);
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
						"Le Stagiaire " + stagiaire.getPrenom() + " "
								+ stagiaire.getNom() + " a été modifié"));
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onCellEdit(CellEditEvent event) throws ServiceException {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		logger.debug("onCellEdit");
		if (newValue != null && !newValue.equals(oldValue)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Cell Changed", "Old: " + oldValue + ", New:" + newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

}
