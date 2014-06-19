/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.rcehblt.sessionbeans;

import cl.rcehblt.entities.Prevision;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author DevelUser
 */
@Local
public interface PrevisionFacadeLocal {

    void create(Prevision prevision);

    void edit(Prevision prevision);

    void remove(Prevision prevision);

    Prevision find(Object id);

    List<Prevision> findAll();

    List<Prevision> findRange(int[] range);

    int count();
    
}
