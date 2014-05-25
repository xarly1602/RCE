/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.RCE.www.tipoprevision;

import cl.RCE.www.entities.TipoPrevision;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author DevelUser
 */
@Local
public interface TipoPrevisionNegocioLocal {
    public List<TipoPrevision> busquedaTipoIdPrevision(int idPrevision);
}
