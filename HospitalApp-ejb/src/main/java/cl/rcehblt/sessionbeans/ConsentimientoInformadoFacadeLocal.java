/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.rcehblt.sessionbeans;

import cl.rcehblt.entities.ConsentimientoInformado;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author DevelUser
 */
@Local
public interface ConsentimientoInformadoFacadeLocal {

    void create(ConsentimientoInformado consentimientoInformado);

    void edit(ConsentimientoInformado consentimientoInformado);

    void remove(ConsentimientoInformado consentimientoInformado);

    ConsentimientoInformado find(Object id);

    List<ConsentimientoInformado> findAll();

    List<ConsentimientoInformado> findRange(int[] range);

    int count();
    
}
