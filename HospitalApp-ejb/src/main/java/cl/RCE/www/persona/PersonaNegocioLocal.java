/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.RCE.www.persona;

import cl.RCE.www.entities.Persona;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author DevelUser
 */
@Local
public interface PersonaNegocioLocal {
    public List<Persona> busquedaPersonaNombre(String personaNombre);
    public List<Persona> busquedaPersonaRut(int personaRut);
    public List<Persona> busquedaPersonaApellidoPaterno(String personaApellido);
}
