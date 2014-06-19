/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.rcehblt.sessionbeans;

import cl.rcehblt.entities.DetalleLog;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author DevelUser
 */
@Local
public interface DetalleLogFacadeLocal {

    void create(DetalleLog detalleLog);

    void edit(DetalleLog detalleLog);

    void remove(DetalleLog detalleLog);

    DetalleLog find(Object id);

    List<DetalleLog> findAll();

    List<DetalleLog> findRange(int[] range);

    int count();
    
}
