/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.rcehblt.sessionbeans;

import cl.rcehblt.entities.Subespecialidad;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author DevelUser
 */
@Local
public interface SubespecialidadFacadeLocal {

    void create(Subespecialidad subespecialidad);

    void edit(Subespecialidad subespecialidad);

    void remove(Subespecialidad subespecialidad);

    Subespecialidad find(Object id);

    List<Subespecialidad> findAll();

    List<Subespecialidad> findRange(int[] range);

    int count();
    
}
