/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.rcehblt.sessionbeans;

import cl.rcehblt.entities.PuebloOriginario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author DevelUser
 */
@Local
public interface PuebloOriginarioFacadeLocal {

    void create(PuebloOriginario puebloOriginario);

    void edit(PuebloOriginario puebloOriginario);

    void remove(PuebloOriginario puebloOriginario);

    PuebloOriginario find(Object id);

    List<PuebloOriginario> findAll();

    List<PuebloOriginario> findRange(int[] range);

    int count();
    
}
