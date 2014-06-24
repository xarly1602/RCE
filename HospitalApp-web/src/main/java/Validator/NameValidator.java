/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("Validator.NameValidator")
public class NameValidator implements Validator {

    private String name;

    /**
     * Constructor de la clase.
     */
    public NameValidator() {
    }

    /**
     * Validar. Valida si un nombre o apellido indicado es correcto o no, es
     * decir, que solo tenga letras.
     *
     * @param context
     * @param component
     * @param value Nombre o apellido que se desea validar.
     * @throws ValidatorException Arrojada cuando el nombre o apellido analizado
     * es inválido.
     */
    @Override
    public void validate(FacesContext context, UIComponent component,
            Object value) throws ValidatorException {

        name = value.toString();
        if (!validarNombre(name)) {

            FacesMessage msg
                    = new FacesMessage("Campo inválido.",
                            "No puede contener números.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);

        }

    }

    /**
     * Validar nombre. Valida si un nombre o apellido es válido o no verificando
     * que éste no contenga números.
     *
     * @param name String correspondiente al nombre o apellido que se desea
     * validar.
     * @return True cuando el nombre o apellido contiene sólo letras y False si
     * es que éste tiene algún número.
     */
    public static boolean validarNombre(String name) {
        boolean validacion;
        validacion = !name.contains("0") && !name.contains("1") && !name.contains("2") && !name.contains("3") && !name.contains("4") && !name.contains("5") && !name.contains("6") && !name.contains("7") && !name.contains("9");

        return validacion;
    }
}
