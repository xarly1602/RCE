/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.RCE.www.profesional;

import cl.RCE.www.entities.Profesional;
import javax.ejb.Local;

/**
 *
 * @author DevelUser
 */
@Local
public interface ProfesionalNegocioLocal {
    public Profesional busquedaProfesionalIdPersona(int id);
}
