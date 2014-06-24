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

@FacesValidator("Validator.PhoneValidator")
public class PhoneValidator implements Validator {

    private String phone;

    /**
     * Constructor de la clase.
     */
    public PhoneValidator() {
    }

    /**
     * Validar. Valida si un telefono ingresado es válido o no.
     *
     * @param context
     * @param component
     * @param value Teléfono que se desea validar.
     * @throws ValidatorException Arrojada cuando el teléfono es inválido.
     */
    @Override
    public void validate(FacesContext context, UIComponent component,
            Object value) throws ValidatorException {

        phone = value.toString();
        if (!validarTelefono(phone)) {

            FacesMessage msg = new FacesMessage("Ingrese un teléfono correcto.", "Teléfono inválido.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);

        } else if (phone.length() != 0 && phone.length() < 7) {
            FacesMessage msg = new FacesMessage("Largo de teléfono incorrecto.", "Teléfono inválido.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }

    }

    /**
     * Validar teléfono. Valida si un telefono ingresado es correcto o no al
     * analizar si solo contiene números.
     *
     * @param phone String correspondiente al teléfono que se desea validar.
     * @return True cuando el teléfono cumple con las condiciones de largo
     * correcto y sólo numeros o primer carácter es un signo '+' y retorna False
     * cuando no se cumple alguna de las condiciones mencionadas antes.
     */
    public static boolean validarTelefono(String phone) {
        for (int i = 0; i < phone.length(); i++) {
            if (i == 0 && !Character.isDigit(phone.charAt(i)) && phone.charAt(i) != '+') {
                return false;
            } else if (!Character.isDigit(phone.charAt(i)) && i > 0) {
                return false;
            }
        }
        return true;
    }
}
