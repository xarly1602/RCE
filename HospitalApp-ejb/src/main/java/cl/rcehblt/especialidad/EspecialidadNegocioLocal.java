/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.rcehblt.especialidad;

import cl.rcehblt.entities.Especialidad;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author DevelUser
 */
@Local
public interface EspecialidadNegocioLocal {
    public List<Especialidad> busquedaEspecialidadNombre(String especialidadNombre);
}
