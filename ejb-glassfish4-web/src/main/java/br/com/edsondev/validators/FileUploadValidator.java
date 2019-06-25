/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.edsondev.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

/**
 *
 * @author junior
 */
@FacesValidator(value = "fileUploadValidator")
public class FileUploadValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
	Part file = (Part) value;

	FacesMessage message = null;
	try {
	    if (file == null || file.getSize() <= 0 || file.getContentType().isEmpty()) {
		message = new FacesMessage("Selecione um arquivo válido");
	    } else if (!file.getContentType().endsWith("pdf")) {
		message = new FacesMessage("Selecione um arquivo no formato pdf");
	    } else if (file.getSize() > 5000000) {
		message = new FacesMessage("Arquivo muito grande. O tamanho deve ser menor ou igual a 5MB.");
	    }

	    if (message != null && !message.getDetail().isEmpty()) {
		message.setSeverity(FacesMessage.SEVERITY_ERROR);
		context.addMessage(component.getId(), message);

		throw new ValidatorException(message);
	    }

	} catch (Exception ex) {
	    throw new ValidatorException(new FacesMessage(ex.getMessage()));
	}
    }

}
