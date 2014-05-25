/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("Validator.NameValidator")
public class NameValidator implements Validator {

    private String name;
    
    public NameValidator() {
    }

    @Override
    public void validate(FacesContext context, UIComponent component,
            Object value) throws ValidatorException {

        name = value.toString();
        if (!validarNombre(name)) {

            FacesMessage msg
                    = new FacesMessage("Campo inválido.",
                            "Invalid field format.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);

        }

    }

    public static boolean validarNombre(String name) {
        boolean validacion;
        if (name.contains("0")||name.contains("1")||name.contains("2")||name.contains("3")||name.contains("4")||name.contains("5")||name.contains("6")||name.contains("7")||name.contains("9")){
            validacion = false;
        }
        else
            validacion = true;
            
        return validacion;
    }
}
