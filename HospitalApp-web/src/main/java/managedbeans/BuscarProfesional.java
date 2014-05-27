/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbeans;

import cl.RCE.www.entities.Cargo;
import cl.RCE.www.entities.Genero;
import cl.RCE.www.entities.GrupoProfesional;
import cl.RCE.www.entities.Local;
import cl.RCE.www.entities.Persona;
import cl.RCE.www.entities.Profesional;
import cl.RCE.www.entities.Subespecialidad;
import cl.RCE.www.persona.PersonaNegocioLocal;
import cl.RCE.www.profesional.ProfesionalNegocioLocal;
import cl.RCE.www.sessionbeans.PersonaFacadeLocal;
import cl.RCE.www.sessionbeans.ProfesionalFacadeLocal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author DevelUser
 */
@ManagedBean
@SessionScoped
public class BuscarProfesional {
    @EJB
    private ProfesionalFacadeLocal profesionalFacade;
    @EJB
    private PersonaFacadeLocal personaFacade;
    @EJB
    private ProfesionalNegocioLocal profesionalNegocio;
    @EJB
    private PersonaNegocioLocal personaNegocio;

    Persona personaSeleccionada;
    Profesional profesionalSeleccionado;
    List<Persona> personasObject;    
   
    private int cargoId;
    private int medicoReferenciaId;
    private int grupoId;
    private int localId;
    private int especialidadId;
    private int subEspecialidadId;
    
    private boolean activoAux;
    private String buscado;    
    private String opcion;
    public BuscarProfesional() {
    }
    @PostConstruct
    public void init(){
        personaSeleccionada = new Persona();
    }
    public void buscarPersona(){
        switch(Integer.parseInt(opcion)){
            case 1:
                try{
                    personasObject =  personaNegocio.busquedaPersonaRut(Integer.parseInt(buscado), 2);
                }
                catch(NumberFormatException ex){
                    personasObject =  personaNegocio.busquedaPersonaRut(-1,0);
                }
                break;
            case 2:
                personasObject = personaNegocio.busquedaPersonaNombre(buscado, 2);           
                break;
            case 3:
                personasObject = personaNegocio.busquedaPersonaApellidoPaterno(buscado, 2);
                break;
            default:
                break;
        }       
    }
    
    public void actualizar(){       
       
        if(profesionalSeleccionado.getIdPersona().getIdPersona() == medicoReferenciaId){
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "El profesional es el mismo que su encargado."));
        }   
        
        else{
            
            profesionalSeleccionado.setIdCargo(new Cargo(cargoId));
            profesionalSeleccionado.setIdGrupoprofesional(new GrupoProfesional(grupoId));
            profesionalSeleccionado.setIdLocal(new Local(localId));                  
            profesionalSeleccionado.setIdSubespecialidad(new Subespecialidad(subEspecialidadId));
          
            if(medicoReferenciaId != 0){
                profesionalSeleccionado.setProIdProfesional(new Profesional(medicoReferenciaId));            }
           
            
            personaFacade.edit(personaSeleccionada);
            profesionalFacade.edit(profesionalSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Datos Actualizado.", "Datos actualizados correctamente"));
        }
        
           

    }    


    public boolean isActivoAux() {
        return activoAux;
    }

    public void setActivoAux(boolean activoAux) {
        this.activoAux = activoAux;
    }

    public Persona getPersonaSeleccionada() {
        return personaSeleccionada;
    }

    public void setPersonaSeleccionada(Persona personaSeleccionada) {
        this.personaSeleccionada = personaSeleccionada;
        profesionalSeleccionado = profesionalNegocio.busquedaProfesionalIdPersona(personaSeleccionada.getIdPersona());
        activoAux = profesionalSeleccionado.getProfActivo();
    }

    public Profesional getProfesionalSeleccionado() {
        return profesionalSeleccionado;
    }

    public void setProfesionalSeleccionado(Profesional profesionalSeleccionado) {
        this.profesionalSeleccionado = profesionalSeleccionado;
    }

    public List<Persona> getPersonasObject() {
        return personasObject;
    }

    public void setPersonasObject(List<Persona> personasObject) {
        this.personasObject = personasObject;
    }

    public int getCargoId() {
        return cargoId;
    }

    public void setCargoId(int cargoId) {
        this.cargoId = cargoId;
    }

    public int getMedicoReferenciaId() {
        return medicoReferenciaId;
    }

    public void setMedicoReferenciaId(int medicoReferenciaId) {
        this.medicoReferenciaId = medicoReferenciaId;
    }

    public int getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(int grupoId) {
        this.grupoId = grupoId;
    }

    public int getLocalId() {
        return localId;
    }

    public void setLocalId(int localId) {
        this.localId = localId;
    }

    public int getEspecialidadId() {
        return especialidadId;
    }

    public void setEspecialidadId(int especialidadId) {
        this.especialidadId = especialidadId;
    }

    public int getSubEspecialidadId() {
        return subEspecialidadId;
    }

    public void setSubEspecialidadId(int subEspecialidadId) {
        this.subEspecialidadId = subEspecialidadId;
    }

    public String getBuscado() {
        return buscado;
    }

    public void setBuscado(String buscado) {
        this.buscado = buscado;
    }

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }
      
      
}


