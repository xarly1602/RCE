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

@FacesValidator("Validator.RutValidator")
public class RutValidator implements Validator {

    private String rut;

    /**
     * Constructor de la clase.
     */
    public RutValidator() {
    }

    /**
     * Validar. Indica si un rut ingresado es válido o no, arrojando un mensaje
     * en caso de que no sea válido.
     *
     * @param context
     * @param component
     * @param value Rut que se desea validar.
     * @throws ValidatorException Arrojada en caso de que el rut ingresado no
     * sea válido.
     */
    @Override
    public void validate(FacesContext context, UIComponent component,
            Object value) throws ValidatorException {

        rut = value.toString();
        if (!validarRut(rut)) {

            FacesMessage msg
                    = new FacesMessage("Rut inválido.",
                            "El rut ingresado no es válido.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);

        }

    }

    /**
     * Validar rut. Verifica si el rut ingresado es correcto, es decir, si
     * coincide rut con difito verificador.
     *
     * @param rut String correspondiente al rut que se desea validar.
     * @return True si el rut es válido y False si es inválido.
     */
    public static boolean validarRut(String rut) {
        boolean validacion = false;
        if (rut.isEmpty()) {
            return true;
        }
        try {
            rut = rut.toUpperCase();
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");
            int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));
            char dv = rut.charAt(rut.length() - 1);
            int m = 0, s = 1;
            for (; rutAux != 0; rutAux /= 10) {
                s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
            }
            if (dv == (char) (s != 0 ? s + 47 : 75)) {
                validacion = true;
            }
        } catch (java.lang.NumberFormatException e) {
        } catch (NullPointerException e) {
        }
        return validacion;
    }
}
