/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.rcehblt.sessionbeans;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author DevelUser
 */
@Local
public interface LocalFacadeLocal {

    void create(cl.rcehblt.entities.Local local);

    void edit(cl.rcehblt.entities.Local local);

    void remove(cl.rcehblt.entities.Local local);

    cl.rcehblt.entities.Local find(Object id);

    List<cl.rcehblt.entities.Local> findAll();

    List<cl.rcehblt.entities.Local> findRange(int[] range);

    int count();
    
}
