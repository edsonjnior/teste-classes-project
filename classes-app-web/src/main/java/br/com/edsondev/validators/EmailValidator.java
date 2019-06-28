/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.edsondev.validators;

import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author junior
 */
@FacesValidator(value = "emailValidator")
public class EmailValidator implements Validator {

    private static final Pattern EMAIL_PATTERN = Pattern
	    .compile("([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})");

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
	if (value == null) {
	    return;
	}

	String email = (String) value;
	boolean matches = EMAIL_PATTERN.matcher(email).matches();
	if (!matches) {
	    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Formato de email inválido", null);
	    throw new ValidatorException(msg);
	}
    }

}
