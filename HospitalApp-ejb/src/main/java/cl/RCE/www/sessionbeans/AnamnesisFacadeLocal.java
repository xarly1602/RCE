/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.RCE.www.sessionbeans;

import cl.RCE.www.entities.Anamnesis;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author DevelUser
 */
@Local
public interface AnamnesisFacadeLocal {

    void create(Anamnesis anamnesis);

    void edit(Anamnesis anamnesis);

    void remove(Anamnesis anamnesis);

    Anamnesis find(Object id);

    List<Anamnesis> findAll();

    List<Anamnesis> findRange(int[] range);

    int count();
    
}
