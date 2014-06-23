/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.rcehblt.episodio;

import cl.rcehblt.entities.Episodios;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author DevelUser
 */
@Stateless
public class EpisodioNegocio implements EpisodioNegocioLocal {
    @PersistenceContext(unitName = "edu.pingeso.primeraapp_HospitalApp-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

    public void persist(Object object) {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public List<Episodios> busquedaEpisodioIdPaciente(int idPaciente){
        Query q = em.createNamedQuery("Episodios.findByIdPaciente");
        q.setParameter("idPaciente", idPaciente);
        List<Episodios> res = q.getResultList();
        if (res.isEmpty()) {
            return null;
        }
        else {
            return res;
        }        
    }
}
