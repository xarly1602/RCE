/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.rcehblt.anamnesis;

import cl.rcehblt.entities.Anamnesis;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author DevelUser
 */
@Local
public interface AnamnesisNegocioLocal {
    public List<Anamnesis> buscaAnamnesisPaciente(int id);
}
