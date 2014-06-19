/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.rcehblt.sessionbeans;

import cl.rcehblt.entities.Sector;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author DevelUser
 */
@Local
public interface SectorFacadeLocal {

    void create(Sector sector);

    void edit(Sector sector);

    void remove(Sector sector);

    Sector find(Object id);

    List<Sector> findAll();

    List<Sector> findRange(int[] range);

    int count();
    
}
