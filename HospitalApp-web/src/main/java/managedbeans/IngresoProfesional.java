/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import cl.rcehblt.entities.Cargo;
import cl.rcehblt.entities.GrupoProfesional;
import cl.rcehblt.entities.Local;
import cl.rcehblt.entities.Persona;
import cl.rcehblt.entities.Profesional;
import cl.rcehblt.entities.Subespecialidad;
import cl.rcehblt.persona.PersonaNegocioLocal;
import cl.rcehblt.profesional.ProfesionalNegocioLocal;
import cl.rcehblt.sessionbeans.PersonaFacadeLocal;
import cl.rcehblt.sessionbeans.ProfesionalFacadeLocal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    private String rutString;
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
    private List<Profesional> listaProfesionales;

    /**
     * Constructor de la clase.
     */
    public IngresoProfesional() {
    }

    /**
     * Postconstructor. Inicializar algunos datos del profesional.
     */
    @PostConstruct
    public void init() {
        persona = new Persona();
        profesional = new Profesional();
        nombres = "";
        apellidoMaterno = "";
        apellidoPaterno = "";
        listaProfesionales = profesionalFacade.findAll();
    }

    /**
     * Agregar un profesional. Agrega un profesional al sistema con los datos
     * ingresados en el formulario correspondiente, arrojando los mensajes
     * respectivos ya sea cuando ocurra algún error de validación o cuando el
     * ingreso sea exitoso.
     */
    public void agregarProfesional() {
        rutCompleto = rutCompleto.toUpperCase();
        rutCompleto = rutCompleto.replace(".", "");
        rutCompleto = rutCompleto.replace("-", "");
        if (rutCompleto.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error de registro.", "Debe indicar un rut."));
            return;
        } else if (rutCompleto.length() < 8) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error de registro.", "Ingrese un rut válido."));
            return;
        }
        rut = Integer.valueOf(rutCompleto.substring(0, rutCompleto.length() - 1));
        digitoVerificador = rutCompleto.charAt(rutCompleto.length() - 1) + "";
        if (personaNegocio.busquedaPersonaRut(rut).size() > 0) {
            persona = personaNegocio.busquedaPersonaRut(rut).get(0);
            System.out.println("------"+persona.getPersTipopersona());
            if (persona.getPersTipopersona() != 1) {
                System.out.println("IF!!!!!!!!!!!!");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Registro ya existe.", "El rut indicado ya está registrado."));
                return;
            } else {
                persona.setPersTipopersona(3);
//                personaFacade.edit(persona);
           //     FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ingreso realizado.", "Profesional: " + persona.getPersNombres() + " " + persona.getPersApepaterno() + " " + persona.getPersApematerno()));
            }
        }// else {
            //setear activo a true
            if (persona.getPersRut() == 0) {
                persona.setPersApepaterno(apellidoPaterno);
                persona.setPersApematerno(apellidoMaterno);
                persona.setPersNombres(nombres);
                persona.setPersDireccion(direccion);
                persona.setPersFnacimiento(fechaNacimiento);
                persona.setPersDv(digitoVerificador);
                persona.setPersRut(rut);
                persona.setPersNacionalidad(nacionalidad);
                persona.setPersTipopersona(2);
            }
            profesional = new Profesional();
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
            if (fechaDesde != null && fechaHasta != null) {
                if (fechaDesde.before(fechaHasta)) {
                    profesional.setProfFechadesde(fechaDesde);
                    profesional.setProfeFechahasta(fechaHasta);

                    personaFacade.edit(persona);
                    profesionalFacade.create(profesional);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ingreso realizado.", "Profesional: " + persona.getPersNombres() + " " + persona.getPersApepaterno() + " " + persona.getPersApematerno()));
                    this.resetData();
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Las fechas no coinciden, ingrese una correcta."));
                }
            } else {
                profesional.setProfFechadesde(fechaDesde);
                profesional.setProfeFechahasta(fechaHasta);

                personaFacade.edit(persona);
                profesionalFacade.create(profesional);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ingreso realizado.", "Profesional: " + nombres + " " + apellidoPaterno + " " + apellidoMaterno));
                this.resetData();
            }

       // }
    }

    public void buscar(ActionEvent actionEvent) {
        rut = Integer.parseInt(rutCompleto);
        if (personaNegocio.busquedaPersonaRut(rut).size() > 0) {
            persona = personaNegocio.busquedaPersonaRut(rut).get(0);
            profesional = profesionalNegocio.busquedaProfesionalIdPersona(persona.getIdPersona());
            this.apellidoMaterno = persona.getPersApematerno();
            this.apellidoPaterno = persona.getPersApepaterno();
            this.nombres = persona.getPersNombres();
            this.digitoVerificador = persona.getPersDv();
            this.direccion = persona.getPersDireccion();
            this.email = persona.getPersEmail();
            this.fechaNacimiento = persona.getPersFnacimiento();
            this.digitoVerificador = persona.getPersDv();
            this.descripcion = "".concat(nombres).concat(" ").concat(apellidoPaterno).concat(" ").concat(apellidoMaterno);
            if (profesional != null) {
                this.cargoId = profesional.getIdCargo().getIdCargo();
                this.especialidadId = profesional.getIdSubespecialidad().getIdEspecialidad().getIdEspecialidad();
                this.fechaDesde = profesional.getProfFechadesde();
                this.fechaHasta = profesional.getProfeFechahasta();
                if(profesional.getProIdProfesional() != null)
                    this.medicoReferenciaId = profesional.getProIdProfesional().getIdProfesional();
                this.localId = profesional.getIdLocal().getIdLocal();
                this.grupoId = profesional.getIdGrupoprofesional().getIdGrupoprofesional();
                this.subEspecialidadId = profesional.getIdSubespecialidad().getIdSubespecialidad();
                this.rutCompleto = rutCompleto.concat(this.digitoVerificador);
            }
        }

    }

    public List<String> completarRut(String query) {
        List<String> listaFiltrada = new ArrayList<String>();
        for (Profesional profesional1 : listaProfesionales) {
            if (profesional1.getIdPersona().getPersRut().toString().startsWith(query) && !listaFiltrada.contains(profesional1.getIdPersona().getPersRut().toString())) {
                listaFiltrada.add(profesional1.getIdPersona().getPersRut().toString());
            }
        }
        return listaFiltrada;
    }

    /**
     * Actualizar descripción. Concatena el nombre con los apellidos mientras
     * estos se escriben para mostrarlos en el campo correspondiente del
     * formulario.
     *
     * @param actionEvent Evento en la página xhtml que acciona la función.
     */
    public void actualizarDescripcion(ActionEvent actionEvent) {
        descripcion = nombres.concat(" " + apellidoPaterno).concat(" " + apellidoMaterno);
    }

    /**
     * Resetear información. Se reinician los datos para poder hacer un nuevo
     * ingreso.
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

    public List<Profesional> getListaProfesionales() {
        return listaProfesionales;
    }

    public void setListaProfesionales(List<Profesional> listaProfesionales) {
        this.listaProfesionales = listaProfesionales;
    }

    public String getRutString() {
        return rutString;
    }

    public void setRutString(String rutString) {
        this.rutString = rutString;
    }

}
