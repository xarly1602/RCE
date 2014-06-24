/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import cl.rcehblt.anamnesis.AnamnesisNegocioLocal;
import cl.rcehblt.entities.Anamnesis;
import cl.rcehblt.entities.ConsentimientoInformado;
import cl.rcehblt.entities.Paciente;
import cl.rcehblt.entities.Persona;
import cl.rcehblt.entities.Profesional;
import cl.rcehblt.paciente.PacienteNegocioLocal;
import cl.rcehblt.persona.PersonaNegocioLocal;
import cl.rcehblt.profesional.ProfesionalNegocioLocal;
import cl.rcehblt.sessionbeans.ConsentimientoInformadoFacadeLocal;
import java.awt.event.ActionEvent;
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

public class ConsentimientoInf {

    @EJB
    private AnamnesisNegocioLocal anamnesisNegocio;
    @EJB
    private ConsentimientoInformadoFacadeLocal consentimientoInformadoFacade;
    @EJB
    private ProfesionalNegocioLocal profesionalNegocio;
    @EJB
    private PacienteNegocioLocal pacienteNegocio;
    @EJB
    private PersonaNegocioLocal personaNegocio;

    private String rutAux;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nombreApellidoRepresentante;
    private String rutRepresentante;
    private String nombresProf;
    private String apellidoPaternoProf;
    private String apellidoMaternoProf;
    private String estado;
    private int rut;
    private int rutProf;
    private Date fecha;
    private Date fechaNacimiento;
    private String texto;
    private String numeroFicha;
    private String direccion;
    private String consultorio;
    private String comunaConsultorio;
    private String paridad;
    private String fo;
    private int hijosVivos;
    private boolean embarazada;
    private Date fpp;
    private Persona persona;
    private Persona personaProfesional;
    private Paciente paciente;
    private Profesional profesional;
    private ConsentimientoInformado consentimientoInformado;
    private List<Anamnesis> anamnesis;

    private boolean guardado;
    private boolean guardadoVIH;
    private boolean guardadoEst;

    /**
     * Constructor de la clase.
     */
    public ConsentimientoInf() {
    }

    /**
     * Postconstructor. Se inicializan algunos datos y se setean valores por
     * default.
     */
    @PostConstruct
    public void init() {
        persona = new Persona();
        personaProfesional = new Persona();
        paciente = new Paciente();
        profesional = new Profesional();
        fecha = new Date();
        guardado = false;
        guardadoVIH = false;
        guardadoEst = false;
    }

    /**
     * Buscar paciente. Función que realiza la búsqueda de un paciente por el
     * rut indicado en el formulario correspondiente.
     *
     * @param actionEvent Evento en la página xhtml que activa la función.
     */
    public void buscarPaciente(ActionEvent actionEvent) {
        rutAux = Integer.toString(rut);
        if (rutAux.isEmpty()) {
            return;
        }
        if (!personaNegocio.busquedaPersonaRut(rut, 1).isEmpty()) {
            persona = personaNegocio.busquedaPersonaRut(rut, 1).get(0);
            paciente = pacienteNegocio.busquedaPacienteIdPersona(persona.getIdPersona());
            this.apellidoMaterno = persona.getPersApematerno();
            this.apellidoPaterno = persona.getPersApepaterno();
            this.nombres = persona.getPersNombres();
            this.rut = persona.getPersRut();
            this.direccion = persona.getPersDireccion();
            this.fechaNacimiento = persona.getPersFnacimiento();
            this.numeroFicha = paciente.getPaciNficha();
            this.consultorio = paciente.getIdConsultorio().getConsNombre();
            this.anamnesis = anamnesisNegocio.buscaAnamnesisPaciente(paciente.getIdPaciente());
            this.fpp = anamnesis.get(0).getAnamFpp();

        } else {
            this.resetDataPaciente();
        }
    }

    /**
     * Buscar profesional. Función que busca un profesional por el rut que se
     * indica en el formulario correspondiente.
     *
     * @param actionEvent Evento en la página xhtml que activa la función.
     */
    public void buscarProfesional(ActionEvent actionEvent) {
        rutAux = Integer.toString(rutProf);
        if (rutAux.isEmpty()) {
            return;
        }
        if (!personaNegocio.busquedaPersonaRut(rutProf, 2).isEmpty()) {
            personaProfesional = personaNegocio.busquedaPersonaRut(rutProf, 2).get(0);
            this.apellidoMaternoProf = personaProfesional.getPersApematerno();
            this.apellidoPaternoProf = personaProfesional.getPersApepaterno();
            this.nombresProf = personaProfesional.getPersNombres();
            this.rutProf = personaProfesional.getPersRut();
            profesional = profesionalNegocio.busquedaProfesionalIdPersona(personaProfesional.getIdPersona());
        } else {
            this.resetDataProfesional();
        }
    }

    /**
     * Guardar consentimiento de un examen. Guarda el consentimiento de un
     * examen con los datos indicados en el formulario correspondiente.
     */
    public void guardarConsentimientoExamen() {
        if (rut == 0 || rutProf == 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Debe Llenar rut de paciente y de profesional."));
        } else {
            consentimientoInformado = new ConsentimientoInformado();
            consentimientoInformado.setConsentEstado(estado);
            consentimientoInformado.setConsentNombreresponsable(nombreApellidoRepresentante);
            consentimientoInformado.setConsentRutresponsable(rutRepresentante);
            consentimientoInformado.setConsentTexto(texto);
            consentimientoInformado.setConsentFecha(fecha);
            consentimientoInformado.setConsentTipo("Intervension");
            consentimientoInformado.setIdPaciente(paciente);
            consentimientoInformado.setIdProfesional(profesional);
            consentimientoInformadoFacade.create(consentimientoInformado);
            guardado = true;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ingresado.", "Consentimiento informado ingresado correctamente"));
        }
    }

    /**
     * Guardar consentimiento de un examen de VIH. Guarda el consentimiento de
     * un examen de VIH con los datos indicados en el formulario
     * correspondiente.
     */
    public void guardarConsentimientoVIH() {
        if (rut == 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Debe Llenar rut del paciente."));
        } else {
            consentimientoInformado = new ConsentimientoInformado();
            consentimientoInformado.setConsentEstado(estado);
            consentimientoInformado.setConsentNombreresponsable(nombreApellidoRepresentante);
            consentimientoInformado.setConsentRutresponsable(rutRepresentante);
            consentimientoInformado.setConsentTipo("VIH");
            consentimientoInformado.setConsentFecha(fecha);
            consentimientoInformado.setIdPaciente(paciente);
            consentimientoInformadoFacade.create(consentimientoInformado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ingresado.", "Consentimiento informado ingresado correctamente"));
            guardadoVIH = true;
        }
    }

    /**
     * Guardar consentimiento de un esterilización. Guarda el consentimiento de
     * una esterilización quirúrgica con los datos indicados en el formulario
     * correspondiente.
     */
    public void guardarConsentimientoEst() {
        if (rut == 0 || rutProf == 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Debe Llenar rut de paciente y de profesional."));
        } else {
            consentimientoInformado = new ConsentimientoInformado();
            consentimientoInformado.setConsentEstado(estado);
            consentimientoInformado.setConsentNombreresponsable(nombreApellidoRepresentante);
            consentimientoInformado.setConsentRutresponsable(rutRepresentante);
            consentimientoInformado.setConsentTexto(texto);
            consentimientoInformado.setConsentTipo("Esterilizacion");
            consentimientoInformado.setConsentEmbarazada(embarazada);
            consentimientoInformado.setConsentFecha(fecha);
            consentimientoInformado.setConsentFechaparto(fpp);
            consentimientoInformado.setConsentFo(fo);
            consentimientoInformado.setConsentHijosvivos(hijosVivos);
            consentimientoInformado.setConsentParidad(paridad);
            consentimientoInformado.setIdPaciente(paciente);
            consentimientoInformado.setIdProfesional(profesional);
            consentimientoInformadoFacade.create(consentimientoInformado);
            guardadoEst = true;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ingresado.", "Consentimiento informado ingresado correctamente"));
        }
    }

    /**
     * Limpiar datos. Se resetean datos del profesional y del paciente
     */
    public void limpiarDatos() {
        this.resetDataPaciente();
        this.resetDataProfesional();
    }

    /**
     * Resetear datos del paciente. Se reinician los datos correspondientes al
     * paciente.
     */
    public void resetDataPaciente() {
        rut = 0;
        numeroFicha = "";
        apellidoMaterno = "";
        apellidoPaterno = "";
        nombres = "";
        nombreApellidoRepresentante = "";
        rutRepresentante = "";
        guardado = false;
        guardadoVIH = false;
        guardadoEst = false;
        texto = "";
        fechaNacimiento = null;
        consultorio = "";
        hijosVivos = 0;
        embarazada = false;
        direccion = "";
        paridad = "";
    }

    /**
     * Resetear datos del profesional. Se reinician los datos correspondientes
     * al profesional.
     */
    public void resetDataProfesional() {
        rutProf = 0;
        apellidoMaternoProf = "";
        apellidoPaternoProf = "";
        nombresProf = "";
    }

    // Getters y Setters
    public boolean isGuardado() {
        return guardado;
    }

    public void setGuardado(boolean guardado) {
        this.guardado = guardado;
    }

    public boolean isGuardadoVIH() {
        return guardadoVIH;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public boolean isGuardadoEst() {
        return guardadoEst;
    }

    public void setGuardadoEst(boolean guardadoEst) {
        this.guardadoEst = guardadoEst;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(String consultorio) {
        this.consultorio = consultorio;
    }

    public String getComunaConsultorio() {
        return comunaConsultorio;
    }

    public void setComunaConsultorio(String comunaConsultorio) {
        this.comunaConsultorio = comunaConsultorio;
    }

    public String getParidad() {
        return paridad;
    }

    public void setParidad(String paridad) {
        this.paridad = paridad;
    }

    public String getFo() {
        return fo;
    }

    public void setFo(String fo) {
        this.fo = fo;
    }

    public int getHijosVivos() {
        return hijosVivos;
    }

    public void setHijosVivos(int hijosVivos) {
        this.hijosVivos = hijosVivos;
    }

    public boolean isEmbarazada() {
        return embarazada;
    }

    public void setEmbarazada(boolean embarazada) {
        this.embarazada = embarazada;
    }

    public Date getFpp() {
        return fpp;
    }

    public void setFpp(Date fpp) {
        this.fpp = fpp;
    }

    public void setGuardadoVIH(boolean guardadoVIH) {
        this.guardadoVIH = guardadoVIH;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getRutProf() {
        return rutProf;
    }

    public void setRutProf(int rutProf) {
        this.rutProf = rutProf;
    }

    public String getNombresProf() {
        return nombresProf;
    }

    public void setNombresProf(String nombresProf) {
        this.nombresProf = nombresProf;
    }

    public String getApellidoPaternoProf() {
        return apellidoPaternoProf;
    }

    public void setApellidoPaternoProf(String apellidoPaternoProf) {
        this.apellidoPaternoProf = apellidoPaternoProf;
    }

    public String getApellidoMaternoProf() {
        return apellidoMaternoProf;
    }

    public void setApellidoMaternoProf(String apellidoMaternoProf) {
        this.apellidoMaternoProf = apellidoMaternoProf;
    }

    public String getNombreApellidoRepresentante() {
        return nombreApellidoRepresentante;
    }

    public void setNombreApellidoRepresentante(String nombreApellidoRepresentante) {
        this.nombreApellidoRepresentante = nombreApellidoRepresentante;
    }

    public String getRutRepresentante() {
        return rutRepresentante;
    }

    public void setRutRepresentante(String rutRepresentante) {
        this.rutRepresentante = rutRepresentante;
    }

    public String getNumeroFicha() {
        return numeroFicha;
    }

    public void setNumeroFicha(String numeroFicha) {
        this.numeroFicha = numeroFicha;
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

    public int getRut() {
        return rut;
    }

    public void setRut(int rut) {
        this.rut = rut;

    }

}
