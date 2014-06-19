/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import cl.rcehblt.entities.Comuna;
import cl.rcehblt.entities.Consultorio;
import cl.rcehblt.entities.Educacion;
import cl.rcehblt.entities.Establecimiento;
import cl.rcehblt.entities.EstadoConyugal;
import cl.rcehblt.entities.Genero;
import cl.rcehblt.entities.LeyesSociales;
import cl.rcehblt.entities.Paciente;
import cl.rcehblt.entities.Persona;
import cl.rcehblt.entities.Prevision;
import cl.rcehblt.entities.PuebloOriginario;
import cl.rcehblt.entities.Religion;
import cl.rcehblt.entities.Sector;
import cl.rcehblt.entities.ServicioSalud;
import cl.rcehblt.entities.TipoPrevision;
import cl.rcehblt.persona.PersonaNegocioLocal;
import cl.rcehblt.sessionbeans.EstadoConyugalFacadeLocal;
import cl.rcehblt.tipoprevision.TipoPrevisionNegocioLocal;
import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import org.primefaces.event.SelectEvent;
import cl.rcehblt.sessionbeans.PacienteFacadeLocal;
import cl.rcehblt.sessionbeans.PersonaFacadeLocal;

/**
 *
 * @author DevelUser
 */
@ManagedBean
@RequestScoped
public class PacienteMB {

    @EJB
    private TipoPrevisionNegocioLocal tipoPrevisionNegocio;
    @EJB
    private PersonaNegocioLocal personaNegocio;
    @EJB
    private PacienteFacadeLocal pacienteFacade;
    @EJB
    private PersonaFacadeLocal personaFacade;
    @EJB
    private EstadoConyugalFacadeLocal estadoConyugalFacade;
    private Persona persona;
    private Paciente paciente;

    private String rutCompleto;
    @Size(min = 2, message = "El nombre debe tener más de un caracter.")
    private String nombres;
    @Size(min = 2, message = "El apellido debe tener más de un caracter.")
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String email;
    private String celular;
    private String telefono;
    @Size(min = 1, message = "Debe indicar un teléfono de contacto.")
    private String telefonoContacto;
    @Size(min = 1, message = "Debe indicar una dirección.")
    private String direccion;
    @Size(min = 1, message = "Debe indicar una nacionalidad.")
    private String nacionalidad;
    private String ocupacion;
    private String actividadEconomica;
    private String digitoVerificador;
    private String otraPrevision;
    private String numeroDepartamento; // Falta como campo
    private String numFicha;
    private String edad;
    @Past(message = "Seleccione una fecha válida")
    private Date fechaNacimiento;
    private int comunaId;
    private int rut;
    private int pasaporte;
    private int educacionId;
    private int puebloOriginarioId;
    private int religionId;
    private int estadoConyugalId;
    private int previsionId;
    private int tipoPrevisionId;
    private int leyesSocialesId;
    private int generoId;
    private int consultorioId;
    private int establecimientoId;
    private int servicioId;
    private int sectorId;
    private Genero genero;
    private Educacion educacion;
    private PuebloOriginario puebloOriginario;
    private Religion religion;
    private EstadoConyugal estadoConyugal;
    private Prevision prevision;
    private TipoPrevision tipoPrevision;
    private LeyesSociales leyesSociales;
    private Comuna comuna;
    private Consultorio consultorio;
    private Sector sector;
    private Establecimiento establecimiento;
    private ServicioSalud servicio;

    private List<EstadoConyugal> listaEstados;
    private List<TipoPrevision> listaTipos;

    /**
     * Constructor de la clase.
     */
    public PacienteMB() {
    }

    /**
     * Postconstructor:
     * Inicializar variables y establecer valores por default.
     */
    @PostConstruct
    public void init() {
        puebloOriginarioId = 12;
        religionId = 9;
        previsionId = 9;
        estadoConyugalId = 9;
        educacionId = 6;
        leyesSocialesId = 8;
        consultorioId = 1;
        persona = new Persona();
        paciente = new Paciente();
        listaEstados = estadoConyugalFacade.findAll();
    }

    /**
     * Agregar un paciente.
     * Agregar un nuevo paciente al sistema de acuerdo a los datos ingresados en
     * el formulario correspondiente, arrojando los mensajes respectivos a los 
     * distintos tipos de casos que puedan ocurrir.
     * @param actionEvent Evento en la página xhtml que acciona la función.
     */
    public void agregarPaciente(ActionEvent actionEvent) {
        rutCompleto = rutCompleto.toUpperCase();
        rutCompleto = rutCompleto.replace(".", "");
        rutCompleto = rutCompleto.replace("-", "");
        if(rutCompleto.isEmpty() && pasaporte != 0){
            rut = 0;
            digitoVerificador = "";
        }
        else if (rutCompleto.isEmpty() && pasaporte == 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error de registro.", "Debe indicar un rut o un número de pasaporte."));
            return;
        }
        else if(rutCompleto.length() < 8){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error de registro.", "Ingrese un rut válido."));
            return;
        }
        else{
            rut = Integer.valueOf(rutCompleto.substring(0, rutCompleto.length()-1));
            digitoVerificador = rutCompleto.charAt(rutCompleto.length() - 1) + "";
        }        
        if (personaNegocio.busquedaPersonaRut(rut,1).size() > 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Registro ya existe.", "Paciente: " + nombres + " " + apellidoPaterno + " " + apellidoMaterno + " ya registrado."));
        } else {
            comuna = new Comuna(comunaId);
            educacion = new Educacion(educacionId);
            puebloOriginario = new PuebloOriginario(puebloOriginarioId);
            religion = new Religion(religionId);
            estadoConyugal = new EstadoConyugal(estadoConyugalId);
            prevision = new Prevision(previsionId);
            tipoPrevision = new TipoPrevision(tipoPrevisionId);
            leyesSociales = new LeyesSociales(leyesSocialesId);
            genero = new Genero(generoId);
            consultorio = new Consultorio(consultorioId);
            persona.setIdComuna(comuna);
            persona.setIdEducacion(educacion);
            persona.setIdEstadoconyugal(estadoConyugal);
            persona.setIdGenero(genero);
            persona.setIdPueblooriginario(puebloOriginario);
            persona.setIdReligion(religion);
            persona.setPersOcupacion(ocupacion);
            persona.setPersActividad(actividadEconomica);
            persona.setPersApematerno(apellidoMaterno);
            persona.setPersApepaterno(apellidoPaterno);
            persona.setPersCelular(celular);
            persona.setPersDireccion(direccion);
            persona.setPersDv(digitoVerificador);
            persona.setPersEmail(email);
            persona.setPersFnacimiento(fechaNacimiento);
            persona.setPersNacionalidad(nacionalidad);
            persona.setPersNdepartamento(numeroDepartamento);
            persona.setPersNombres(nombres);
            persona.setPersPasaporte(pasaporte);
            persona.setPersRut(rut);
            persona.setPersTelcontacto(telefonoContacto);
            persona.setPersTelefono(telefono);
            persona.setPersTipopersona(1);

            paciente.setIdConsultorio(new Consultorio(consultorioId));
            paciente.setIdLeyessociales(new LeyesSociales(leyesSocialesId));
            paciente.setIdPersona(persona);
            paciente.setIdPrevision(new Prevision(previsionId));
            paciente.setIdTipoprevision(new TipoPrevision(tipoPrevisionId));
            paciente.setPaciFallecido(Boolean.FALSE);
            paciente.setPaciNficha(numFicha);
            if (establecimientoId != 0)
                paciente.setIdEstablecimiento(new Establecimiento(establecimientoId));
            if (sectorId != 0)
                paciente.setIdSector(new Sector(sectorId));
            if (servicioId != 0)
                paciente.setIdServiciosalud(new ServicioSalud(servicioId));
            if (previsionId == 6) {
                paciente.setPaciOtraprevision(otraPrevision);
            }
            personaFacade.create(persona);
            pacienteFacade.create(paciente);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Admisión realizada.", "Paciente: " + nombres + " " + apellidoPaterno + " " + apellidoMaterno));
            this.resetData();
        }
    }

    /**
     * Buscar un rut.
     * Se busca el rut ingresado en el formulario de creación para saber si éste
     * ya se encuentra registrado, en tal caso, se traerán los datos del 
     * paciente correspondiente y serán mostrados en la vista.
     * @param actionEvent Evento en la página xhtml que acciona la vista.
     */
    public void buscar(ActionEvent actionEvent) {
        rutCompleto = rutCompleto.toUpperCase();
        rutCompleto = rutCompleto.replace(".", "");
        rutCompleto = rutCompleto.replace("-", "");
        if (rutCompleto.isEmpty()) {
            return;
        }
        rut = Integer.valueOf(rutCompleto.substring(0, rutCompleto.length()-1));
        if (!personaNegocio.busquedaPersonaRut(rut,1).isEmpty()) {
            persona = personaNegocio.busquedaPersonaRut(rut,1).get(0);
            this.actividadEconomica = persona.getPersActividad();
            this.apellidoMaterno = persona.getPersApematerno();
            this.apellidoPaterno = persona.getPersApepaterno();
            this.celular = persona.getPersCelular();
            this.comunaId = persona.getIdComuna().getIdComuna();
            this.digitoVerificador = persona.getPersDv();
            this.direccion = persona.getPersDireccion();
            this.educacionId = persona.getIdEducacion().getIdEducacion();
            this.email = persona.getPersEmail();
            this.estadoConyugalId = persona.getIdEstadoconyugal().getIdEstadoconyugal();
            this.fechaNacimiento = persona.getPersFnacimiento();
            this.generoId = persona.getIdGenero().getIdGenero();
            this.nacionalidad = persona.getPersNacionalidad();
            this.nombres = persona.getPersNombres();
            this.ocupacion = persona.getPersOcupacion();
            this.pasaporte = persona.getPersPasaporte();
            this.puebloOriginarioId = persona.getIdPueblooriginario().getIdPueblooriginario();
            this.religionId = persona.getIdReligion().getIdReligion();
            this.rut = persona.getPersRut();
            this.telefono = persona.getPersTelefono();
            this.telefonoContacto = persona.getPersTelcontacto();
        } else {
            this.resetData();
        }
    }

    /**
     * Calcular edad.
     * Se calcula la edad según la fecha de nacimiento indicada en el 
     * formulario. Si la edad es menor que un año, se especifica la edad en 
     * cantidad de meses, días y horas, en caso contrario, solo se indica la 
     * edad en años.
     * @param event Evento en la pagina xhtml que acciona el evento.
     */
    public void calculaEdad(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Date today = Calendar.getInstance().getTime();
        int temp = today.getYear() - fechaNacimiento.getYear();
        if (today.getMonth() < fechaNacimiento.getMonth()) {
            temp--;
        } else if (today.getMonth() == fechaNacimiento.getMonth() && today.getDate() < fechaNacimiento.getDate()) {
            temp--;
        }
        if (temp == 0) {
            int months = today.getMonth() - fechaNacimiento.getMonth();
            if (months < 0) {
                months = 12 - (fechaNacimiento.getMonth() - today.getMonth());
            }
            int days = today.getDate() - fechaNacimiento.getDate();
            int hours = today.getHours() - fechaNacimiento.getHours();
            edad = months + "m " + days + "d " + hours + "h";
        } else {
            edad = String.valueOf(temp);
        }
    }

    /**
     * Reiniciar variables.
     * Se resetean las variables y se restauran las correspondientes a su valor
     * por default.
     */
    private void resetData() {
        persona = new Persona();
        paciente = new Paciente();
        nombres = "";
        apellidoPaterno = "";
        apellidoMaterno = "";
        email = "";
        celular = "";
        telefono = "";
        telefonoContacto = "";
        direccion = "";
        nacionalidad = "";
        ocupacion = "";
        actividadEconomica = "";
        digitoVerificador = "";
        numeroDepartamento = ""; // Falta como campo
        numFicha = "";
        otraPrevision = "";
        fechaNacimiento = new Date();
        comunaId = 0;
        rut = 0;
        generoId = 0;
        tipoPrevisionId = 0;
        puebloOriginarioId = 12;
        religionId = 9;
        previsionId = 9;
        estadoConyugalId = 9;
        educacionId = 6;
        leyesSocialesId = 8;
        consultorioId = 1;
    }

    /**
     * Buscar tipo de previsión.
     * Se crea la lista de los tipos de previsión correspondientes a la 
     * previsión seleccionada.
     * @param event Evento en la página xhtml que acciona la función.
     */
    public void buscaTipo(AjaxBehaviorEvent event) {
        listaTipos = tipoPrevisionNegocio.busquedaTipoIdPrevision(previsionId);
    }

    // Getters y Setters.
    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getNumFicha() {
        return numFicha;
    }

    public void setNumFicha(String numFicha) {
        this.numFicha = numFicha;
    }

    public int getConsultorioId() {
        return consultorioId;
    }

    public void setConsultorioId(int consultorioId) {
        this.consultorioId = consultorioId;
    }

    public Consultorio getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(Consultorio consultorio) {
        this.consultorio = consultorio;
    }

    public List<EstadoConyugal> getListaEstados() {
        return listaEstados;
    }

    public void setListaEstados(List<EstadoConyugal> listaEstados) {
        this.listaEstados = listaEstados;
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

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getDigitoVerificador() {
        return digitoVerificador;
    }

    public void setDigitoVerificador(String digitoVerificador) {
        this.digitoVerificador = digitoVerificador;
    }

    public int getComunaId() {
        return comunaId;
    }

    public void setComunaId(int comunaId) {
        this.comunaId = comunaId;
    }

    public int getRut() {
        return rut;
    }

    public void setRut(int rut) {
        this.rut = rut;
    }

    public int getEducacionId() {
        return educacionId;
    }

    public void setEducacionId(int educacionId) {
        this.educacionId = educacionId;
    }

    public int getPuebloOriginarioId() {
        return puebloOriginarioId;
    }

    public void setPuebloOriginarioId(int puebloOriginarioId) {
        this.puebloOriginarioId = puebloOriginarioId;
    }

    public int getReligionId() {
        return religionId;
    }

    public void setReligionId(int religionId) {
        this.religionId = religionId;
    }

    public int getEstadoConyugalId() {
        return estadoConyugalId;
    }

    public void setEstadoConyugalId(int estadoConyugalId) {
        this.estadoConyugalId = estadoConyugalId;
    }

    public int getPrevisionId() {
        return previsionId;
    }

    public void setPrevisionId(int previsionId) {
        this.previsionId = previsionId;
    }

    public int getTipoPrevisionId() {
        return tipoPrevisionId;
    }

    public void setTipoPrevisionId(int tipoPrevisionId) {
        this.tipoPrevisionId = tipoPrevisionId;
    }

    public int getLeyesSocialesId() {
        return leyesSocialesId;
    }

    public void setLeyesSocialesId(int leyesSocialesId) {
        this.leyesSocialesId = leyesSocialesId;
    }

    public int getEstablecimientoId() {
        return establecimientoId;
    }

    public void setEstablecimientoId(int establecimientoId) {
        this.establecimientoId = establecimientoId;
    }

    public int getServicioId() {
        return servicioId;
    }

    public void setServicioId(int servicioId) {
        this.servicioId = servicioId;
    }

    public int getSectorId() {
        return sectorId;
    }

    public void setSectorId(int sectorId) {
        this.sectorId = sectorId;
    }

    public Comuna getComuna() {
        return comuna;
    }

    public void setComuna(Comuna comuna) {
        this.comuna = comuna;
    }

    public String getNumeroDepartamento() {
        return numeroDepartamento;
    }

    public void setNumeroDepartamento(String numeroDepartamento) {
        this.numeroDepartamento = numeroDepartamento;
    }

    public int getGeneroId() {
        return generoId;
    }

    public void setGeneroId(int generoId) {
        this.generoId = generoId;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String getActividadEconomica() {
        return actividadEconomica;
    }

    public void setActividadEconomica(String actividadEconomica) {
        this.actividadEconomica = actividadEconomica;
    }

    public Educacion getEducacion() {
        return educacion;
    }

    public void setEducacion(Educacion educacion) {
        this.educacion = educacion;
    }

    public PuebloOriginario getPuebloOriginario() {
        return puebloOriginario;
    }

    public void setPuebloOriginario(PuebloOriginario puebloOriginario) {
        this.puebloOriginario = puebloOriginario;
    }

    public Religion getReligion() {
        return religion;
    }

    public void setReligion(Religion religion) {
        this.religion = religion;
    }

    public EstadoConyugal getEstadoConyugal() {
        return estadoConyugal;
    }

    public void setEstadoConyugal(EstadoConyugal estadoConyugal) {
        this.estadoConyugal = estadoConyugal;
    }

    public Prevision getPrevision() {
        return prevision;
    }

    public void setPrevision(Prevision prevision) {
        this.prevision = prevision;
    }

    public TipoPrevision getTipoPrevision() {
        return tipoPrevision;
    }

    public void setTipoPrevision(TipoPrevision tipoPrevision) {
        this.tipoPrevision = tipoPrevision;
    }

    public LeyesSociales getLeyesSociales() {
        return leyesSociales;
    }

    public void setLeyesSociales(LeyesSociales leyesSociales) {
        this.leyesSociales = leyesSociales;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getRutCompleto() {
        return rutCompleto;
    }

    public void setRutCompleto(String rutCompleto) {
        this.rutCompleto = rutCompleto;
    }

    public List<TipoPrevision> getListaTipos() {
        return listaTipos;
    }

    public void setListaTipos(List<TipoPrevision> listaTipos) {
        this.listaTipos = listaTipos;
    }

    public String getOtraPrevision() {
        return otraPrevision;
    }

    public void setOtraPrevision(String otraPrevision) {
        this.otraPrevision = otraPrevision;
    }

    public int getPasaporte() {
        return pasaporte;
    }

    public void setPasaporte(int pasaporte) {
        this.pasaporte = pasaporte;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public Establecimiento getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(Establecimiento establecimiento) {
        this.establecimiento = establecimiento;
    }

    public ServicioSalud getServicio() {
        return servicio;
    }

    public void setServicio(ServicioSalud servicio) {
        this.servicio = servicio;
    }

}
