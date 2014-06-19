/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.rcehblt.paciente;

import cl.rcehblt.entities.Paciente;
import cl.rcehblt.entities.Persona;
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
public class PacienteNegocio implements PacienteNegocioLocal {
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
    public Paciente busquedaPacienteId(int id) {
        Query q = em.createNativeQuery("select * from paciente where id_paciente = ?1 ") 
                .setParameter(1,id);
        List<Paciente> res = q.getResultList();
        if (res.isEmpty()) {
            return null;
        }
        else {
            return res.get(0);
        }
    }
    
    @Override
    public Paciente busquedaPacienteIdPersona(int id) {
        Query q = em.createNamedQuery("Paciente.findByIdPersona");
        q.setParameter("idPersona", id);
        List<Paciente> res = q.getResultList();
        if (res.isEmpty()) {
            return null;
        }
        else {
            return res.get(0);
        }
        
    }
    
    @Override
    public Paciente busquedaPacienteNumeroFicha(String nFicha) {
        Query q = em.createNamedQuery("Paciente.findByPaciNficha");
        q.setParameter("paciNficha", nFicha);
        List<Paciente> res = q.getResultList();
        if (res.isEmpty()) {
            return null;
        }
        else {
            return res.get(0);
        }
        
    }
}

