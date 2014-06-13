/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import cl.RCE.www.entities.Cargo;
import cl.RCE.www.entities.GrupoProfesional;
import cl.RCE.www.entities.Local;
import cl.RCE.www.entities.Persona;
import cl.RCE.www.entities.Profesional;
import cl.RCE.www.entities.Subespecialidad;
import cl.RCE.www.persona.PersonaNegocioLocal;
import cl.RCE.www.profesional.ProfesionalNegocioLocal;
import cl.RCE.www.sessionbeans.PersonaFacadeLocal;
import cl.RCE.www.sessionbeans.ProfesionalFacadeLocal;
import java.util.ArrayList;
import java.util.Date;
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
    private Date fechaDesdeAux;
    private Date fechaHasta; 
    private boolean activoAux;
    private String buscado;
    private String opcion;

    public BuscarProfesional() {
    }
     /**
     * Postconstructor.
     * Se crea a la persona que será seleccionada para poder editarla, se obtiene
     * una lista de todos los profesionales que hay en el sistema y se iniciliza 
     * la opcion de busqueda en 1 (busqueda por rut).
     */
    @PostConstruct
    public void init() {
        personaSeleccionada = new Persona();
        personasObject = personaFacade.findAll();
        for (int i = personasObject.size() - 1; i >= 0; i--) {
            if (personasObject.get(i).getPersTipopersona() != 2) {
                personasObject.remove(i);
            }
        }
        opcion = "1";
    }
    /**
     * Buscar a un Profesional.
     * Dependiendo la opción que eliga el usuario se buscará al profesional,
     * puede ser por rut, nombre o apellido paterno.
     * Finalmente la función tendrá la lista con los profesionales que 
     * coincidan con lo buscado.
     */
    public void buscarPersona() {
        if (buscado.isEmpty()) {
            personasObject = personaFacade.findAll();
            for (int i = personasObject.size() - 1; i >= 0; i--) {
                if (personasObject.get(i).getPersTipopersona() != 2) {
                    personasObject.remove(i);
                }
            }
            return;
        }
        switch (Integer.parseInt(opcion)) {
            case 1:
                try {
                    personasObject = personaNegocio.busquedaPersonaRut(Integer.parseInt(buscado), 2);
                } catch (NumberFormatException ex) {
                    personasObject = personaNegocio.busquedaPersonaRut(-1, 0);
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

    
    public List<String> completarBusqueda(String query) {
        List<String> listaFiltrada = new ArrayList<String>();
        if (opcion.equals("1")) {
            for (Persona persona : personasObject) {
                if (persona.getPersRut().toString().startsWith(query) && !listaFiltrada.contains(persona.getPersRut().toString())) {
                    listaFiltrada.add(persona.getPersRut().toString());
                }
            }
        }
        else if (opcion.equals("2")) {
            for (Persona persona : personasObject) {
                if (persona.getPersNombres().startsWith(query) && !listaFiltrada.contains(persona.getPersNombres())) {
                    listaFiltrada.add(persona.getPersNombres());
                }
            }
        }        
        else if (opcion.equals("3")) {
            for (Persona persona : personasObject) {
                if (persona.getPersApepaterno().startsWith(query) && !listaFiltrada.contains(persona.getPersApepaterno())) {
                    listaFiltrada.add(persona.getPersApepaterno());
                }
            }
        }
        return listaFiltrada;
    }
    
    /**
     * Buscar Por Especialidad.
     * Se busca a todos los profesionales que coincidan con la especialidad solicitada.
     */
    public void buscarPorEspecialidad() {
        List<Profesional> temp = profesionalNegocio.busquedaProfesionalEspecialidad(especialidadId);
        personasObject.clear();
        for (Profesional profesional : temp) {
            personasObject.add(profesional.getIdPersona());
        }
    }

    /**
     * Buscar Por Sub-especialidad.
     * Se busca a todos los profesionales que coincidan con la sub-especialidad solicitada.
     */
    public void buscarPorSubespecialidad() {
        List<Profesional> temp = profesionalNegocio.busquedaProfesionalSubespecialidad(subEspecialidadId);
        personasObject.clear();
        for (Profesional profesional : temp) {
            personasObject.add(profesional.getIdPersona());
        }
    }
     /**
     * Actualizar datos.
     * Función que actualiza los datos del profesional, se crean las entidades relacionadas con el paciente
     * como el cargo, la sub-especialidad, entre otros.
     * Se setean los datos para el profesional según corresponda.
     * Finalmente se actualizan los datos de la persona y del profesional.
     */
    public void actualizar() {
        fechaDesdeAux = profesionalSeleccionado.getProfFechadesde();
        if (profesionalSeleccionado.getIdPersona().getIdPersona() == medicoReferenciaId) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "El profesional es el mismo que su encargado."));
        } else {
            System.out.println("estoy en el PRIMER ESLSEEE CTMMMMM");
            profesionalSeleccionado.setProfeFechahasta(fechaHasta);
            
            profesionalSeleccionado.setIdCargo(new Cargo(cargoId));
            profesionalSeleccionado.setIdGrupoprofesional(new GrupoProfesional(grupoId));
            profesionalSeleccionado.setIdLocal(new Local(localId));                  
            profesionalSeleccionado.setIdSubespecialidad(new Subespecialidad(subEspecialidadId));

            if(medicoReferenciaId != 0){                    
                profesionalSeleccionado.setProIdProfesional(new Profesional(medicoReferenciaId)); 
            }
            
            if(fechaHasta == null){
                System.out.println("ESTOY EN EL `PRIMER IF");
                personaFacade.edit(personaSeleccionada);
                profesionalFacade.edit(profesionalSeleccionado);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Datos Actualizado.", "Datos actualizados correctamente"));
                
            }
            else if(fechaDesdeAux.before(fechaHasta)){
                System.out.println("ESTOY EN EL SEGUNDO IF");
                personaFacade.edit(personaSeleccionada);
                profesionalFacade.edit(profesionalSeleccionado);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Datos Actualizado.", "Datos actualizados correctamente"));
        
            }
            else{
                System.out.println("ESTOY EN EL ELSE");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Las fechas no coinciden, ingrese una correcta."));
            }
        }

                    

    }

    // Getters y Setters
    public Date getFechaDesdeAux() {
        return fechaDesdeAux;
    }

    public void setFechaDesdeAux(Date fechaDesdeAux) {
        this.fechaDesdeAux = fechaDesdeAux;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
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
      /**
     * Setear Persona seleccionada.
     * Luego de setear a la persona seleccionada, se buscará al profesional que corresponda 
     * a la busqueda y se guardará en una variable auxiliar el estado del profesional (activo o no).
     */
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
