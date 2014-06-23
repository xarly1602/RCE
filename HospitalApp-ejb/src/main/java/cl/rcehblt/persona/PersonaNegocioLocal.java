/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.rcehblt.persona;

import cl.rcehblt.entities.Persona;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author DevelUser
 */
@Local
public interface PersonaNegocioLocal {   
    public List<Persona> busquedaPersonaApellidoPaterno(String personaApellido, int tipo);
    public List<Persona> busquedaPersonaNombre(String personaNombre, int tipo);
    public List<Persona> busquedaPersonaRut(int personaRut, int tipo);
    public List<Persona> busquedaPersonaRut(int personaRut);
    public List<Persona> busquedaPersonaSexo(int sexoId, int tipo);
    public List<Persona> busquedaPersonaFechaNacimiento(Date fechaNacimiento, int tipo);
}
