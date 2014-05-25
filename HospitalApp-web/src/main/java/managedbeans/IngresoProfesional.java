/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbeans;

import cl.RCE.www.entities.Cargo;
import cl.RCE.www.entities.Especialidad;
import cl.RCE.www.entities.Genero;
import cl.RCE.www.entities.GrupoProfesional;
import cl.RCE.www.entities.Local;
import cl.RCE.www.entities.Persona;
import cl.RCE.www.entities.Profesional;
import cl.RCE.www.entities.Subespecialidad;
import cl.RCE.www.persona.PersonaNegocioLocal;
import cl.RCE.www.sessionbeans.PersonaFacadeLocal;
import cl.RCE.www.sessionbeans.ProfesionalFacadeLocal;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

/**
 *
 * @author DevelUser
 */
@ManagedBean
@RequestScoped
public class IngresoProfesional {
    @EJB
    private ProfesionalFacadeLocal profesionalFacade;
    @EJB
    private PersonaFacadeLocal personaFacade;
    @EJB
    private PersonaNegocioLocal personaNegocio;
    
    private String rutCompleto;
    private String digitoVerificador;
    @Size(min = 2, message = "El nombre debe tener más de un caracter.")
    private String nombres;
    @Size(min = 2, message = "El apellido debe tener más de un caracter.")
    private String apellidoPaterno;
    @Size(min = 2, message = "El apellido debe tener más de un caracter.")
    private String apellidoMaterno;
    private String descripcion;
    private int generoId;
    private String nacionalidad;
    @Size(min = 1, message = "Debe indicar una dirección.")
    private String direccion;
    @Size(min = 1, message = "Debe indicar un teléfono de contacto.")
    private String telefonoContacto;
    private Date fechaDesde;
    private Date fechaHasta;
    @Past(message = "Seleccione una fecha válida")
    private Date fechaNacimiento;
    
    
    private Profesional profesional;
    private Persona persona;
    //Estas variables deberan pasar a ser del tipo Entity
    private String tipo;
    
    
    int cargoId;
    int medicoReferenciaId;
    int grupoId;
    int localId;
    int especialidadId;
    int subEspecialidadId;
    int rut;
    

   
    public IngresoProfesional() {
    }
    @PostConstruct
    public void init(){
        persona = new Persona();
        profesional = new Profesional();                
    }
    
    public void agregarProfesional(){
        rutCompleto = rutCompleto.toUpperCase();
        rutCompleto = rutCompleto.replace(".", "");
        rutCompleto = rutCompleto.replace("-", "");
        rut = Integer.valueOf(rutCompleto) / 10;
        digitoVerificador = rutCompleto.charAt(rutCompleto.length() - 1) + "";
        
        if (personaNegocio.busquedaPersonaRut(rut).size() > 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Registro ya existe.", "Profesional: " + nombres + " " + apellidoPaterno + " " + apellidoMaterno + " ya registrado."));
        } else if (rutCompleto.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error de registro.", "Debe indicar un rut."));
        } else {
            //setear activo a true
            persona.setIdGenero(new Genero(generoId));
            persona.setPersApepaterno(apellidoPaterno);
            persona.setPersApematerno(apellidoPaterno);
            persona.setPersDireccion(direccion);
            persona.setPersFnacimiento(fechaNacimiento);
            persona.setPersDv(digitoVerificador);
            persona.setPersRut(rut);
            persona.setPersNacionalidad(nacionalidad);
            persona.setPersTelcontacto(telefonoContacto);
            persona.setPersTipopersona(2);
            
            profesional.setIdCargo(new Cargo(cargoId));
            profesional.setIdGrupoprofesional(new GrupoProfesional(grupoId));
            profesional.setIdLocal(new Local(localId));
            profesional.setIdPersona(persona);            
            profesional.setIdSubespecialidad(new Subespecialidad(subEspecialidadId));
            profesional.setProIdProfesional(new Profesional(medicoReferenciaId));
            profesional.setProfActivo(true);
            profesional.setProfFechadesde(fechaDesde);
            profesional.setProfeFechahasta(fechaHasta);
            
            personaFacade.create(persona);
            profesionalFacade.create(profesional);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Admisión realizada.", "Profesional: " + nombres + " " + apellidoPaterno + " " + apellidoMaterno));
            this.resetData();
            
        }
    }
    
    private void resetData() {
        persona = new Persona();
        profesional= new Profesional();
        nombres = "";
        apellidoPaterno = "";
        apellidoMaterno = "";        
        telefonoContacto = "";
        direccion = "";
        nacionalidad = "";        
        digitoVerificador = "";        
        fechaNacimiento = new Date();        
        rut = 0;
        generoId = 0;
        cargoId = 0;
        descripcion = "";
        especialidadId = 0;
        fechaDesde = new Date();
        fechaHasta = new Date();
        localId = 0;
        medicoReferenciaId = 0;
        rutCompleto = "";
        subEspecialidadId = 0;       
        
    }
    
    
}
