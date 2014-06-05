/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbeans;


import cl.RCE.www.paciente.PacienteNegocioLocal;
import cl.RCE.www.persona.PersonaNegocioLocal;
import cl.RCE.www.tipoprevision.TipoPrevisionNegocioLocal;

import cl.RCE.www.entities.Comuna;
import cl.RCE.www.entities.Consultorio;
import cl.RCE.www.entities.Educacion;
import cl.RCE.www.entities.EstadoConyugal;
import cl.RCE.www.entities.LeyesSociales;
import cl.RCE.www.entities.Paciente;
import cl.RCE.www.entities.Persona;
import cl.RCE.www.entities.Prevision;
import cl.RCE.www.entities.PuebloOriginario;
import cl.RCE.www.entities.Religion;
import cl.RCE.www.entities.TipoPrevision;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import cl.RCE.www.sessionbeans.PacienteFacadeLocal;
import cl.RCE.www.sessionbeans.PersonaFacadeLocal;

/**
 *
 * @author DevelUser
 */
@ManagedBean
@SessionScoped

public class BuscarPaciente {
    @EJB
    private TipoPrevisionNegocioLocal tipoPrevisionNegocio;
    @EJB
    private PacienteFacadeLocal pacienteFacade;
    @EJB
    private PacienteNegocioLocal pacienteNegocio;
    @EJB
    private PersonaFacadeLocal personaFacade;
    @EJB
    private PersonaNegocioLocal personaNegocio;
    
    List<Persona> personasObject;
    
    Persona personaSeleccionada;
    Paciente pacienteSeleccionado;
  
    private Educacion educacion;
    private PuebloOriginario puebloOriginario;
    private Religion religion;
    private EstadoConyugal estadoConyugal;
    private Comuna comuna;
    private LeyesSociales leyesSociales;
    private Consultorio consultorio;    
    private Prevision prevision;
    private TipoPrevision tipoPrevision;
    private List<TipoPrevision> listaTipos;
    private String buscado;    
    private String opcion;
    private boolean pacienteFallecidoAux;
    int leyesSocialesId;
    int consultorioId;
    int previsionId;
    int tipoPrevisionId;
    int comunaId;
    int religionId;
    int educacionId;
    int estadoConyugalId;
    int puebloOriginarioId;
    
    public BuscarPaciente() {
    }
    
     /**
     * Postconstructor.
     * Se crea a la persona que será seleccionada para poder editarla, se obtiene
     * una lista de todos los pacientes que hay en el sistema y se iniciliza 
     * la opcion de busqueda en 1 (busqueda por rut).
     */
    @PostConstruct
    public void init(){
        personaSeleccionada = new Persona();
        personasObject = personaFacade.findAll();
        for (int i = personasObject.size() - 1; i >= 0; i--) {
            if (personasObject.get(i).getPersTipopersona() != 1) {
                personasObject.remove(i);
            }
        }
        opcion = "1";
    }
    
     /**
     * Buscar a una Paciente.
     * Dependiendo la opción que eliga el usuario se buscará al paciente,
     * puede ser por rut, nombre o apellido paterno.
     * Finalmente la función tendrá la lista con los pacientes que 
     * coincidan con lo buscado.
     */
    public void buscarPersona(){
        switch(Integer.parseInt(opcion)){
            case 1:
                try{
                    personasObject =  personaNegocio.busquedaPersonaRut(Integer.parseInt(buscado),1);
                }
                catch(NumberFormatException ex){
                    personasObject =  personaNegocio.busquedaPersonaRut(-1,0);
                }
                break;
            case 2:
                personasObject = personaNegocio.busquedaPersonaNombre(buscado,1);           
                break;
            case 3:
                personasObject = personaNegocio.busquedaPersonaApellidoPaterno(buscado,1);
                break;
            default:
                break;
        }       
    }
    
     /**
     * Actualizar datos.
     * Función que actualiza los datos del paciente, se crean las entidades relacionadas con el paciente
     * como la comuna, eduación, entre otros.
     * Se setean los datos para la persona y para el paciente según corresponda.
     * Finalmente se actualizan los datos de la persona y del paciente.
     */
    public void actualizar(){   
        comuna = new Comuna(comunaId);
        educacion = new Educacion(educacionId);
        puebloOriginario =  new PuebloOriginario(puebloOriginarioId);
        religion = new Religion(religionId);
        estadoConyugal = new EstadoConyugal(estadoConyugalId);
        prevision = new Prevision(previsionId);
        tipoPrevision = new TipoPrevision(tipoPrevisionId);
        leyesSociales = new LeyesSociales(leyesSocialesId);
        consultorio = new Consultorio(consultorioId);
        
        personaSeleccionada.setIdComuna(comuna);
        personaSeleccionada.setIdEducacion(educacion);
        personaSeleccionada.setIdPueblooriginario(puebloOriginario);
        personaSeleccionada.setIdReligion(religion);
        personaSeleccionada.setIdEstadoconyugal(estadoConyugal);
        
        pacienteSeleccionado.setIdPrevision(prevision);
        pacienteSeleccionado.setIdConsultorio(consultorio);
        pacienteSeleccionado.setIdLeyessociales(leyesSociales);
        pacienteSeleccionado.setIdTipoprevision(tipoPrevision);
        if(pacienteFallecidoAux == true){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "El paciente esta registrado como fallecido."));
        }        
        else{
            if(pacienteSeleccionado.getIdTipoprevision().getIdTipoprevision() == 0 && (pacienteSeleccionado.getIdPrevision().getIdPrevision() == 1 || pacienteSeleccionado.getIdPrevision().getIdPrevision() == 2 )){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Debe poner un tipo de previsión."));
            }
            else{
                personaFacade.edit(personaSeleccionada);
                pacienteFacade.edit(pacienteSeleccionado);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Datos Actualizados"));
            }
            
        }        
        

    }
     /**
     * Buscar la lista de los tipos de prevision.
     * Se buscará la lista de tipos de prevision según el id de la previsión que ingreso el usuario,
     * luego al tipo de previsión se le seteará una prevision de id cero (que no existe), para que
     * no muestre un tipo de previsión que no corresponda a la previsión correcta.
     */
     public void buscaTipo(AjaxBehaviorEvent event) {
        listaTipos = tipoPrevisionNegocio.busquedaTipoIdPrevision(previsionId);
        pacienteSeleccionado.setIdTipoprevision(new TipoPrevision(0));
        
    }
    // Setters y getters. 
    public boolean isPacienteFallecidoAux() {
        return pacienteFallecidoAux;
    }

    public void setPacienteFallecidoAux(boolean pacienteFallecidoAux) {
        this.pacienteFallecidoAux = pacienteFallecidoAux;
    }

    public int getLeyesSocialesId() {
        return leyesSocialesId;
    }

    public void setLeyesSocialesId(int leyesSocialesId) {
        this.leyesSocialesId = leyesSocialesId;
    }

    public int getConsultorioId() {
        return consultorioId;
    }

    public void setConsultorioId(int consultorioId) {
        this.consultorioId = consultorioId;
    }

    public Paciente getPacienteSeleccionado() {
        return pacienteSeleccionado;
    }

    public void setPacienteSeleccionado(Paciente pacienteSeleccionado) {
        this.pacienteSeleccionado = pacienteSeleccionado;
    }
    
    public Persona getPersonaSeleccionada() {
        return personaSeleccionada;
    }
     /**
     * Setear Persona seleccionada.
     * Luego de setear a la persona seleccionada, se buscará al paciente que corresponda 
     * a la busqueda y se guardará en una variable auxiliar el estado del paciente (fallecido o no).
     */
    public void setPersonaSeleccionada(Persona personaSeleccionada) {
        this.personaSeleccionada = personaSeleccionada;
        pacienteSeleccionado = pacienteNegocio.busquedaPacienteIdPersona(personaSeleccionada.getIdPersona());
        pacienteFallecidoAux = pacienteSeleccionado.getPaciFallecido();        
    } 

    public List<TipoPrevision> getListaTipos() {
        return listaTipos;
    }

    public void setListaTipos(List<TipoPrevision> listaTipos) {
        this.listaTipos = listaTipos;
    }

    public int getTipoPrevisionId() {
        return tipoPrevisionId;
    }

    public void setTipoPrevisionId(int tipoPrevisionId) {
        this.tipoPrevisionId = tipoPrevisionId;
    }

    public int getPrevisionId() {
        return previsionId;
    }

    public void setPrevisionId(int previsionId) {
        this.previsionId = previsionId;
    }
  
    
    public int getComunaId() {
        return comunaId;
    }

    public void setComunaId(int comunaId) {
        this.comunaId = comunaId;
    }

    public int getReligionId() {
        return religionId;
    }

    public void setReligionId(int religionId) {
        this.religionId = religionId;
    }

    public int getEducacionId() {
        return educacionId;
    }

    public void setEducacionId(int educacionId) {
        this.educacionId = educacionId;
    }

    public int getEstadoConyugalId() {
        return estadoConyugalId;
    }

    public void setEstadoConyugalId(int estadoConyugalId) {
        this.estadoConyugalId = estadoConyugalId;
    }

    public int getPuebloOriginarioId() {
        return puebloOriginarioId;
    }

    public void setPuebloOriginarioId(int puebloOriginarioId) {
        this.puebloOriginarioId = puebloOriginarioId;
    }         

    

    public List<Persona> getPersonasObject() {
        return personasObject;
    }

    public void setPersonasObject(List<Persona> personasObject) {
        this.personasObject = personasObject;
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
