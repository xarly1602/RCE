/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.rcehblt.subespecialidad;

import cl.rcehblt.entities.Subespecialidad;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author DevelUser
 */
@Local
public interface SubespecialidadNegocioLocal {
    public List<Subespecialidad> busquedaSubespecialidadNombre(String especialidadNombre);
    public List<Subespecialidad> busquedaSubespecialidadIdEspecialidad(int especialidadId);
}
