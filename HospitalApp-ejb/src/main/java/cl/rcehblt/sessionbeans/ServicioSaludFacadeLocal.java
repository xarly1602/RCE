/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.rcehblt.sessionbeans;

import cl.rcehblt.entities.ServicioSalud;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author DevelUser
 */
@Local
public interface ServicioSaludFacadeLocal {

    void create(ServicioSalud servicioSalud);

    void edit(ServicioSalud servicioSalud);

    void remove(ServicioSalud servicioSalud);

    ServicioSalud find(Object id);

    List<ServicioSalud> findAll();

    List<ServicioSalud> findRange(int[] range);

    int count();
    
}
