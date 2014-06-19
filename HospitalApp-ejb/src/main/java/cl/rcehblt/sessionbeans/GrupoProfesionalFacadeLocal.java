/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.rcehblt.sessionbeans;

import cl.rcehblt.entities.GrupoProfesional;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author DevelUser
 */
@Local
public interface GrupoProfesionalFacadeLocal {

    void create(GrupoProfesional grupoProfesional);

    void edit(GrupoProfesional grupoProfesional);

    void remove(GrupoProfesional grupoProfesional);

    GrupoProfesional find(Object id);

    List<GrupoProfesional> findAll();

    List<GrupoProfesional> findRange(int[] range);

    int count();
    
}
