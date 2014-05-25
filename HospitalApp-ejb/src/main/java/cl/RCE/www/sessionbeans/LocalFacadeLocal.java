/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.RCE.www.sessionbeans;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author DevelUser
 */
@Local
public interface LocalFacadeLocal {

    void create(cl.RCE.www.entities.Local local);

    void edit(cl.RCE.www.entities.Local local);

    void remove(cl.RCE.www.entities.Local local);

    cl.RCE.www.entities.Local find(Object id);

    List<cl.RCE.www.entities.Local> findAll();

    List<cl.RCE.www.entities.Local> findRange(int[] range);

    int count();
    
}
