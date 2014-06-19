/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.rcehblt.profesional;

import cl.rcehblt.entities.Profesional;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author DevelUser
 */
@Local
public interface ProfesionalNegocioLocal {
    public Profesional busquedaProfesionalIdPersona(int id);
    public List<Profesional> busquedaProfesionalEspecialidad(int id);
    public List<Profesional> busquedaProfesionalSubespecialidad(int id);
}
