/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.RCE.www.persona;

import cl.RCE.www.entities.Persona;
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
public class PersonaNegocio implements PersonaNegocioLocal {
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
    public List<Persona> busquedaPersonaNombre(String personaNombre) {
        Query q = em.createNamedQuery("Persona.findByPersNombres").setParameter("persNombres", personaNombre);        
        return q.getResultList();
    }

  
    @Override
    public List<Persona> busquedaPersonaRut(int personaRut) {
        
        Query q = em.createNamedQuery("Persona.findByPersRut").setParameter("persRut", personaRut);  
        return q.getResultList();
    }


    @Override
    public List<Persona> busquedaPersonaApellidoPaterno(String personaApellido) {
        Query q = em.createNamedQuery("Persona.findByPersApepaterno").setParameter("persApepaterno", personaApellido);  
        return q.getResultList();
    }

    /*public List<String> buscarEducacion(int id){
        
        Query q = em.createNativeQuery("select educ_nombre from educacion where id_educacion = ?1 ") 
                .setParameter(1,id);
        return q.getResultList();
        
    }
    
    public List<String> buscarPuebloOriginario(int id){
        Query q = em.createNativeQuery("select reli_nombre from educacion where id_pueblooriginario = ?1 ") 
                .setParameter(1,id);
        return q.getResultList();
    }
    public List<String> buscarReligion(int id){
        Query q = em.createNativeQuery("select reli_nombre from pueblo_originario where id_religion = ?1 ") 
                .setParameter(1,id);
        return q.getResultList();
    }
    public List<String> buscarEstadoConyugal(int id){
        Query q = em.createNativeQuery("select estad_nombre from estado_conyugal where id_estadoconyugal = ?1 ") 
                .setParameter(1,id);
        return q.getResultList();
    }
            
    public List<String> buscarPrevision(int id){
         Query q = em.createNativeQuery("select previ_nombre from prevision where id_prevision = ?1 ") 
                .setParameter(1,id);
        return q.getResultList();
    }
    public List<String> buscarTipoPrevision(int id){
        Query q = em.createNativeQuery("select clas_nombre from tipo_prevision where id_tipoprevision = ?1 ") 
                .setParameter(1,id);
        return q.getResultList();
    }
    public List<String> buscarLeyesSociales(int id){
         Query q = em.createNativeQuery("select leyes_nombre from leyes_sociales where id_leyessociales = ?1 ") 
                .setParameter(1,id);
        return q.getResultList();
    }
    public List<String> buscarGenero(int id){
          Query q = em.createNativeQuery("select estad_nombre from genero where id_genero = ?1 ") 
                .setParameter(1,id);
        return q.getResultList();
    }
    public List<String> buscarConsultorio(int id){
        Query q = em.createNativeQuery("select cons_nombre from consultorio where id_consultorio = ?1 ") 
                .setParameter(1,id);
        return q.getResultList();
    }*/
}
