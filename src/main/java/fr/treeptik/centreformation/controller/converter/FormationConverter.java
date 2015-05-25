package fr.treeptik.centreformation.controller.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import fr.treeptik.centreformation.model.Formation;
import fr.treeptik.centreformation.service.FormationService;

@FacesConverter("formationConverter")
public class FormationConverter implements Converter {
	@Inject
	private FormationService formationService;
	
	@Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
                return formationService.find(new Formation());
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
            }
        }
        else {
            return null;
        }
    }
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((Formation) object).getId());
        }
        else {
            return null;
        }
    }
  
}   
