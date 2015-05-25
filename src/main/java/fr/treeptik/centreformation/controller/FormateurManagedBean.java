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
import fr.treeptik.centreformation.model.Formateur;
import fr.treeptik.centreformation.service.FormateurService;

@ManagedBean
@ViewScoped
// @ApplicationScoped (dure toujours)
// @RequestScoped (crée à chaque appel)
// @SessionScoped (durée de la session)
// @ViewScoped (pour l'ajax)
public class FormateurManagedBean {

	private List<Formateur> filteredFormateurs;

	private Formateur formateur;
	
	private Formateur selectedFormateur;

	private ListDataModel<Formateur> formateurs;
	
	private List<Formateur> selectedFormateurs;

	
	private Logger logger = Logger.getLogger(FormateurManagedBean.class);

	@Inject
	private FormateurService formateurService;

	@PostConstruct
	public void init() {
		formateur = new Formateur();
		formateurs = new ListDataModel<Formateur>();
		formateurs.setWrappedData(formateurService.findAll());
	}

	public Formateur getFormateur() {
		return formateur;
	}

	public void setFormateur(Formateur formateur) {
		this.formateur = formateur;
	}

	public String create() throws ServiceException {
		formateurService.save(formateur);
		return "formateur-list";
	}

	public void remove(Formateur formateur) throws ServiceException {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage("Edit Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
		formateurService.delete(formateur);
		formateurs.setWrappedData(formateurService.findAll());
       
        requestContext.update("form:singleDT");
        requestContext.execute("PF('form:msgs').show()");
	}

	public ListDataModel<Formateur> getFormateurs() {
		return formateurs;
	}

	public void setFormateurs(ListDataModel<Formateur> formateurs) {
		this.formateurs = formateurs;
	}

	public List<Formateur> getFilteredFormateurs() {
		return filteredFormateurs;
	}

	public void setFilteredFormateurs(List<Formateur> filteredFormateurs) {
		this.filteredFormateurs = filteredFormateurs;
	}

	public void setService(FormateurService service) {
		this.formateurService = service;
	}

	public List<Formateur> getSelectedFormateurs() {
		return selectedFormateurs;
	}

	public void setSelectedFormateurs(List<Formateur> selectedFormateurs) {
		this.selectedFormateurs = selectedFormateurs;
	}

	public Formateur getSelectedFormateur() {
		return selectedFormateur;
	}

	public void setSelectedFormateur(Formateur selectedFormateur) {
		this.selectedFormateur = selectedFormateur;
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
        FacesMessage msg = new FacesMessage("Formateur Edited");
        logger.info("onRowEdit");
        formateurService.update((Formateur)event.getObject()); 
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
