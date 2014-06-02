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
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

/**
 *
 * @author DevelUser
 */
@ManagedBean
@SessionScoped
public class IngresoProfesional {
    @EJB
    private ProfesionalNegocioLocal profesionalNegocio;
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
    private String email;
    private int generoId;
    private String nacionalidad;
    private String direccion;
    private String telefonoContacto;
    @NotNull(message = "Debe indicar una fecha")
    private Date fechaDesde;
    private Date fechaHasta;
    @Past(message = "Seleccione una fecha válida")
    private Date fechaNacimiento;

    private Profesional profesional;
    private Persona persona;
    //Estas variables deberan pasar a ser del tipo Entity
    private String tipo;

    private int cargoId;
    private int medicoReferenciaId;
    private int grupoId;
    private int localId;
    private int especialidadId;
    private int subEspecialidadId;
    private int rut;

    /**
     * Constructor de la clase.
     */
    public IngresoProfesional() {
    }

    /**
     * Postconstructor.
     * Inicializar algunos datos del profesional.
     */
    @PostConstruct
    public void init() {
        persona = new Persona();
        profesional = new Profesional();
        nombres = "";
        apellidoMaterno = "";
        apellidoPaterno = "";
    }

    /**
     * Agregar un profesional.
     * Agrega un profesional al sistema con los datos ingresados en el 
     * formulario correspondiente, arrojando los mensajes respectivos ya sea 
     * cuando ocurra algún error de validación o cuando el ingreso sea exitoso.
     */
    public void agregarProfesional() {
        rutCompleto = rutCompleto.toUpperCase();
        rutCompleto = rutCompleto.replace(".", "");
        rutCompleto = rutCompleto.replace("-", "");
        if (rutCompleto.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error de registro.", "Debe indicar un rut."));
            return;
        }
        else if (rutCompleto.length() < 8){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error de registro.", "Ingrese un rut válido."));
            return;
        }
        rut = Integer.valueOf(rutCompleto.substring(0, rutCompleto.length() - 1));
        digitoVerificador = rutCompleto.charAt(rutCompleto.length() - 1) + "";
        if (personaNegocio.busquedaPersonaRut(rut,2).size() > 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Registro ya existe.", "El rut indicado ya está registrado."));
        } else {
            //setear activo a true
            persona.setPersApepaterno(apellidoPaterno);
            persona.setPersApematerno(apellidoMaterno);
            persona.setPersNombres(nombres);
            persona.setPersDireccion(direccion);
            persona.setPersFnacimiento(fechaNacimiento);
            persona.setPersDv(digitoVerificador);
            persona.setPersRut(rut);
            persona.setPersNacionalidad(nacionalidad);
            persona.setPersTipopersona(2);

            profesional.setIdCargo(new Cargo(cargoId));
            profesional.setIdGrupoprofesional(new GrupoProfesional(grupoId));
            profesional.setIdLocal(new Local(localId));
            profesional.setIdPersona(persona);
            profesional.setIdSubespecialidad(new Subespecialidad(subEspecialidadId));
            if (medicoReferenciaId != 0) {
                medicoReferenciaId = profesionalNegocio.busquedaProfesionalIdPersona(medicoReferenciaId).getIdProfesional();
                profesional.setProIdProfesional(new Profesional(medicoReferenciaId));
            }
            profesional.setProfActivo(true);
            profesional.setProfFechadesde(fechaDesde);
            profesional.setProfeFechahasta(fechaHasta);

            personaFacade.create(persona);
            profesionalFacade.create(profesional);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ingreso realizado.", "Profesional: " + nombres + " " + apellidoPaterno + " " + apellidoMaterno));
            this.resetData();

        }
    }

    /**
     * Actualizar descripción.
     * Concatena el nombre con los apellidos mientras estos se escriben para 
     * mostrarlos en el campo correspondiente del formulario.
     * @param actionEvent Evento en la página xhtml que acciona la función.
     */
    public void actualizarDescripcion(ActionEvent actionEvent) {
        descripcion = nombres.concat(" " + apellidoPaterno).concat(" " + apellidoMaterno);
    }

    /**
     * Resetear información.
     * Se reinician los datos para poder hacer un nuevo ingreso.
     */
    private void resetData() {
        persona = new Persona();
        profesional = new Profesional();
        nombres = "";
        apellidoPaterno = "";
        apellidoMaterno = "";
        telefonoContacto = "";
        direccion = "";
        email = "";
        nacionalidad = "";
        digitoVerificador = "";
        fechaNacimiento = null;
        rut = 0;
        cargoId = 0;
        descripcion = "";
        especialidadId = 0;
        fechaDesde = null;
        fechaHasta = null;
        localId = 0;
        medicoReferenciaId = 0;
        rutCompleto = "";
        subEspecialidadId = 0;

    }

    // Getters y Setters.
    public String getRutCompleto() {
        return rutCompleto;
    }

    public void setRutCompleto(String rutCompleto) {
        this.rutCompleto = rutCompleto;
    }

    public String getDigitoVerificador() {
        return digitoVerificador;
    }

    public void setDigitoVerificador(String digitoVerificador) {
        this.digitoVerificador = digitoVerificador;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGeneroId() {
        return generoId;
    }

    public void setGeneroId(int generoId) {
        this.generoId = generoId;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Profesional getProfesional() {
        return profesional;
    }

    public void setProfesional(Profesional profesional) {
        this.profesional = profesional;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public int getRut() {
        return rut;
    }

    public void setRut(int rut) {
        this.rut = rut;
    }

}
