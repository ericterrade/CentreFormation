package fr.treeptik.centreformation.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;

import org.jboss.logging.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import fr.treeptik.centreformation.exception.ServiceException;
import fr.treeptik.centreformation.model.Formation;
import fr.treeptik.centreformation.service.FormationService;

@ManagedBean
@ViewScoped
// @ApplicationScoped (dure toujours)
// @RequestScoped (crée à chaque appel)
// @SessionScoped (durée de la session)
// @ViewScoped (pour l'ajax)
public class FormationManagedBean {

	private List<Formation> filteredFormations;

	private Formation formation;
	
	private Formation selectedFormation;

	private ListDataModel<Formation> formations;
	
	private List<Formation> selectedFormations;

	
	private Logger logger = Logger.getLogger(FormationManagedBean.class);

	@Inject
	private FormationService formationService;

	@PostConstruct
	public void init() {
		formation = new Formation();
		formations = new ListDataModel<Formation>();
		formations.setWrappedData(formationService.findAll());
	}

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}

	public String create() throws ServiceException {
		formationService.save(formation);
		return "formation-list";
	}

	public void remove(Formation formation) throws ServiceException {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage("Edit Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
		formationService.delete(formation);
		formations.setWrappedData(formationService.findAll());
       
        requestContext.update("form:singleDT");
        requestContext.execute("PF('form:msgs').show()");
	}

	public ListDataModel<Formation> getFormations() {
		return formations;
	}

	public void setFormations(ListDataModel<Formation> formations) {
		this.formations = formations;
	}

	public List<Formation> getFilteredFormations() {
		return filteredFormations;
	}

	public void setFilteredFormations(List<Formation> filteredFormations) {
		this.filteredFormations = filteredFormations;
	}

	public void setService(FormationService service) {
		this.formationService = service;
	}

	public List<Formation> getSelectedFormations() {
		return selectedFormations;
	}

	public void setSelectedFormations(List<Formation> selectedFormations) {
		this.selectedFormations = selectedFormations;
	}

	public Formation getSelectedFormation() {
		return selectedFormation;
	}

	public void setSelectedFormation(Formation selectedFormation) {
		this.selectedFormation = selectedFormation;
	}
	
	public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }
     
    public void click() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
         
        requestContext.update("form:display");
        requestContext.execute("PF('dlg').show()");
    }
    
    public void onRowEdit(RowEditEvent event) throws ServiceException {
        FacesMessage msg = new FacesMessage("Formation Edited");
        logger.info("onRowEdit");
        formationService.update((Formation)event.getObject()); 
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onCellEdit(CellEditEvent event) throws ServiceException {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        logger.debug("onCellEdit");
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

}
