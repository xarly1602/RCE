/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.rcehblt.sessionbeans;

import cl.rcehblt.entities.Establecimiento;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author DevelUser
 */
@Local
public interface EstablecimientoFacadeLocal {

    void create(Establecimiento establecimiento);

    void edit(Establecimiento establecimiento);

    void remove(Establecimiento establecimiento);

    Establecimiento find(Object id);

    List<Establecimiento> findAll();

    List<Establecimiento> findRange(int[] range);

    int count();
    
}
