/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.rcehblt.paciente;

import cl.rcehblt.entities.Paciente;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author DevelUser
 */
@Local
public interface PacienteNegocioLocal {
    public Paciente busquedaPacienteId(int id);
    public Paciente busquedaPacienteIdPersona(int id);
    public Paciente busquedaPacienteNumeroFicha(String nFicha);
}

