/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import cl.rcehblt.entities.Comuna;
import cl.rcehblt.entities.ConsentimientoInformado;
import cl.rcehblt.entities.Consultorio;
import cl.rcehblt.entities.Educacion;
import cl.rcehblt.entities.EstadoConyugal;
import cl.rcehblt.entities.LeyesSociales;
import cl.rcehblt.entities.Paciente;
import cl.rcehblt.entities.Persona;
import cl.rcehblt.entities.Prevision;
import cl.rcehblt.entities.PuebloOriginario;
import cl.rcehblt.entities.Religion;
import cl.rcehblt.entities.TipoPrevision;
import cl.rcehblt.paciente.PacienteNegocioLocal;
import cl.rcehblt.persona.PersonaNegocioLocal;
import cl.rcehblt.sessionbeans.ConsentimientoInformadoFacadeLocal;
import cl.rcehblt.sessionbeans.PacienteFacadeLocal;
import cl.rcehblt.sessionbeans.PersonaFacadeLocal;
import cl.rcehblt.tipoprevision.TipoPrevisionNegocioLocal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

/**
 *
 * @author DevelUser
 */
@ManagedBean
@SessionScoped

public class BuscarPaciente {

    @EJB
    private ConsentimientoInformadoFacadeLocal consentimientoInformadoFacade;
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
    List<ConsentimientoInformado> consentimientos;
    List<ConsentimientoInformado> consentimientosSeleccionadosEst;
    List<ConsentimientoInformado> consentimientosSeleccionadosInter;

    private Persona personaSeleccionada;
    private Paciente pacienteSeleccionado;
    private ConsentimientoInformado consentimientoInformadoSeleccionado;

    private String estadoConsentimiento;
    private String estadoConsentimientoVIH;
    private String estadoConsentimientoEst;

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

    private String interversion = "Interversion";
    private String vih = "VIH";
    private String esterilizacion = "Esterilizacion";
    private String tipoAux;

    /**
     * Constructor de la clase.
     */
    public BuscarPaciente() {
    }

    /**
     * Postconstructor. Se crea a la persona que será seleccionada para poder
     * editarla, se obtiene una lista de todos los pacientes que hay en el
     * sistema y se iniciliza la opcion de busqueda en 1 (busqueda por rut).
     */
    @PostConstruct
    public void init() {
        personaSeleccionada = new Persona();
        consentimientos = consentimientoInformadoFacade.findAll();
        personasObject = personaFacade.findAll();
        for (int i = personasObject.size() - 1; i >= 0; i--) {
            if (personasObject.get(i).getPersTipopersona() == 2) {
                personasObject.remove(i);
            }
        }
        opcion = "1";
    }

    /**
     * Buscar a una Paciente. Dependiendo la opción que eliga el usuario se
     * buscará al paciente, puede ser por rut, nombre o apellido paterno.
     * Finalmente la función tendrá la lista con los pacientes que coincidan con
     * lo buscado.
     */
    public void buscarPersona() {
        if (buscado.isEmpty()) {
            personasObject = personaFacade.findAll();
            for (int i = personasObject.size() - 1; i >= 0; i--) {
                if (personasObject.get(i).getPersTipopersona() == 2) {
                    personasObject.remove(i);
                }
            }
            return;
        }
        switch (Integer.parseInt(opcion)) {
            case 1:
                try {
                    personasObject = personaNegocio.busquedaPersonaRut(Integer.parseInt(buscado), 1);
                    personasObject.addAll(personaNegocio.busquedaPersonaRut(Integer.parseInt(buscado), 3));
                } catch (NumberFormatException ex) {
                    personasObject = personaNegocio.busquedaPersonaRut(-1, 0);
                }
                break;
            case 2:
                personasObject = personaNegocio.busquedaPersonaNombre(buscado, 1);
                personasObject.addAll(personaNegocio.busquedaPersonaNombre(buscado, 3));
                break;
            case 3:
                personasObject = personaNegocio.busquedaPersonaApellidoPaterno(buscado, 1);
                personasObject.addAll(personaNegocio.busquedaPersonaApellidoPaterno(buscado, 3));
                break;
            default:
                break;
        }
    }

    /**
     * Completar búsqueda. Función que realiza el autocompletado de los datos en
     * el formulario de pacientes mediante el criterio elegido, el cual puede
     * ser por Rut, Nombre o Apellido Paterno.
     *
     * @param query Elemento mediante el que se quiere realizar el
     * autocompletado
     * @return Lista de resultados según criterio elegido.
     */
    public List<String> completarBusqueda(String query) {
        List<String> listaFiltrada = new ArrayList<String>();
        if (opcion.equals("1")) {
            for (Persona persona : personasObject) {
                if (persona.getPersRut().toString().startsWith(query) && !listaFiltrada.contains(persona.getPersRut().toString())) {
                    listaFiltrada.add(persona.getPersRut().toString());
                }
            }
        } else if (opcion.equals("2")) {
            for (Persona persona : personasObject) {
                if (persona.getPersNombres().startsWith(query) && !listaFiltrada.contains(persona.getPersNombres())) {
                    listaFiltrada.add(persona.getPersNombres());
                }
            }
        } else if (opcion.equals("3")) {
            for (Persona persona : personasObject) {
                if (persona.getPersApepaterno().startsWith(query) && !listaFiltrada.contains(persona.getPersApepaterno())) {
                    listaFiltrada.add(persona.getPersApepaterno());
                }
            }
        }
        return listaFiltrada;
    }

    /**
     * Actualizar datos. Función que actualiza los datos del paciente, se crean
     * las entidades relacionadas con el paciente como la comuna, eduación,
     * entre otros. Se setean los datos para la persona y para el paciente según
     * corresponda. Finalmente se actualizan los datos de la persona y del
     * paciente.
     */
    public void actualizar() {
        comuna = new Comuna(comunaId);
        educacion = new Educacion(educacionId);
        puebloOriginario = new PuebloOriginario(puebloOriginarioId);
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
        if (pacienteFallecidoAux == true) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "El paciente esta registrado como fallecido."));
        } else {
            if (pacienteSeleccionado.getIdTipoprevision().getIdTipoprevision() == 0 && (pacienteSeleccionado.getIdPrevision().getIdPrevision() == 1 || pacienteSeleccionado.getIdPrevision().getIdPrevision() == 2)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Debe poner un tipo de previsión."));
            } else {
                personaFacade.edit(personaSeleccionada);
                pacienteFacade.edit(pacienteSeleccionado);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Datos Actualizados"));
            }
        }
    }

    /**
     * Filtrar consentimiento informado de una intervención. Función que busca
     * los consentimientos asociados a un paciente determinado.
     *
     * @param consentimientosAuxInt Lista en la que se guardarán los resultados.
     * @param idPaciente Id del paciente que se desea buscar.
     * @return Lista con los consentimientos asociados al paciente indicado.
     */
    private List<ConsentimientoInformado> filtrarConsentimientoInt(List<ConsentimientoInformado> consentimientosAuxInt, int idPaciente) {
        for (int i = consentimientosAuxInt.size() - 1; i >= 0; i--) {
            if (consentimientosAuxInt.get(i).getIdPaciente().getIdPaciente() != idPaciente) {
                consentimientosAuxInt.remove(i);
            }
        }
        for (int i = consentimientosAuxInt.size() - 1; i >= 0; i--) {
            if (!(consentimientosAuxInt.get(i).getConsentTipo().compareTo("Intervension") == 0)) {
                consentimientosAuxInt.remove(i);
            }
        }
        return consentimientosAuxInt;
    }

    /**
     * Filtrar consentimiento informado de una esterilización. Función que busca
     * los consentimientos asociados a un paciente determinado.
     *
     * @param consentimientosAuxInt Lista en la que se guardarán los resultados.
     * @param idPaciente Id del paciente que se desea buscar.
     * @return Lista con los consentimientos asociados al paciente indicado.
     */
    private List<ConsentimientoInformado> filtrarConsentimientoEst(List<ConsentimientoInformado> consentimientosAux, int idPaciente) {
        for (int i = consentimientosAux.size() - 1; i >= 0; i--) {
            if (consentimientosAux.get(i).getIdPaciente().getIdPaciente() != idPaciente) {
                consentimientosAux.remove(i);
            }
        }
        for (int i = consentimientosAux.size() - 1; i >= 0; i--) {
            if (!(consentimientosAux.get(i).getConsentTipo().compareTo("Esterilizacion") == 0)) {
                consentimientosAux.remove(i);
            }
        }
        return consentimientosAux;
    }

    /**
     * Editar consentimiendo de intervención. Función que actualiza el estado
     * del consentimiento seleccionado.
     */
    public void editarConsentimiento() {
        consentimientoInformadoSeleccionado.setConsentEstado(estadoConsentimiento);
        consentimientoInformadoFacade.edit(consentimientoInformadoSeleccionado);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Estado actualizado."));

    }

    /**
     * Editar consentimiendo de examen de vih. Función que actualiza el estado
     * del consentimiento seleccionado.
     */
    public void editarConsentimientoVIH() {
        consentimientoInformadoSeleccionado.setConsentEstado(estadoConsentimientoVIH);
        consentimientoInformadoFacade.edit(consentimientoInformadoSeleccionado);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Estado actualizado."));
    }

    /**
     * Editar consentimiendo de esterilización. Función que actualiza el estado
     * del consentimiento seleccionado.
     */
    public void editarConsentimientoEst() {
        consentimientoInformadoSeleccionado.setConsentEstado(estadoConsentimientoEst);
        consentimientoInformadoFacade.edit(consentimientoInformadoSeleccionado);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Estado actualizado."));

    }

    /**
     * Buscar la lista de los tipos de prevision. Se buscará la lista de tipos
     * de prevision según el id de la previsión que ingreso el usuario, luego al
     * tipo de previsión se le seteará una prevision de id cero (que no existe),
     * para que no muestre un tipo de previsión que no corresponda a la
     * previsión correcta.
     */
    public void buscaTipo(AjaxBehaviorEvent event) {
        listaTipos = tipoPrevisionNegocio.busquedaTipoIdPrevision(previsionId);
        pacienteSeleccionado.setIdTipoprevision(new TipoPrevision(0));

    }

    // Setters y getters.
    public String getEstadoConsentimientoVIH() {
        return estadoConsentimientoVIH;
    }

    public void setEstadoConsentimientoVIH(String estadoConsentimientoVIH) {
        this.estadoConsentimientoVIH = estadoConsentimientoVIH;
    }

    public String getEstadoConsentimientoEst() {
        return estadoConsentimientoEst;
    }

    public void setEstadoConsentimientoEst(String estadoConsentimientoEst) {
        this.estadoConsentimientoEst = estadoConsentimientoEst;
    }

    public String getInterversion() {
        return interversion;
    }

    public void setInterversion(String interversion) {
        this.interversion = interversion;
    }

    public String getVih() {
        return vih;
    }

    public void setVih(String vih) {
        this.vih = vih;
    }

    public String getEsterilizacion() {
        return esterilizacion;
    }

    public void setEsterilizacion(String esterilizacion) {
        this.esterilizacion = esterilizacion;
    }

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
     * Setear Persona seleccionada. Luego de setear a la persona seleccionada,
     * se buscará al paciente que corresponda a la busqueda y se guardará en una
     * variable auxiliar el estado del paciente (fallecido o no).
     */
    public void setPersonaSeleccionada(Persona personaSeleccionada) {
        this.personaSeleccionada = personaSeleccionada;
        consentimientos = consentimientoInformadoFacade.findAll();
        pacienteSeleccionado = pacienteNegocio.busquedaPacienteIdPersona(personaSeleccionada.getIdPersona());
        pacienteFallecidoAux = pacienteSeleccionado.getPaciFallecido();
        consentimientosSeleccionadosEst = filtrarConsentimientoEst(consentimientos, pacienteSeleccionado.getIdPaciente());
        consentimientos = consentimientoInformadoFacade.findAll();
        consentimientosSeleccionadosInter = filtrarConsentimientoInt(consentimientos, pacienteSeleccionado.getIdPaciente());
    }

    public List<ConsentimientoInformado> getConsentimientosSeleccionadosInter() {
        return consentimientosSeleccionadosInter;
    }

    public void setConsentimientosSeleccionadosInter(List<ConsentimientoInformado> consentimientosSeleccionadosInter) {
        this.consentimientosSeleccionadosInter = consentimientosSeleccionadosInter;
    }

    public List<ConsentimientoInformado> getConsentimientosSeleccionadosEst() {
        return consentimientosSeleccionadosEst;
    }

    public void setConsentimientosSeleccionados(List<ConsentimientoInformado> consentimientosSeleccionadosEst) {
        this.consentimientosSeleccionadosEst = consentimientosSeleccionadosEst;
    }

    public List<ConsentimientoInformado> getConsentimientos() {
        return consentimientos;
    }

    public void setConsentimientos(List<ConsentimientoInformado> consentimientos) {
        this.consentimientos = consentimientos;
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

    public ConsentimientoInformado getConsentimientoInformadoSeleccionado() {
        return consentimientoInformadoSeleccionado;
    }

    public void setConsentimientoInformadoSeleccionado(ConsentimientoInformado consentimientoInformadoSeleccionado) {
        this.consentimientoInformadoSeleccionado = consentimientoInformadoSeleccionado;
        tipoAux = consentimientoInformadoSeleccionado.getConsentTipo();
    }

    public String getEstadoConsentimiento() {
        return estadoConsentimiento;
    }

    public void setEstadoConsentimiento(String estadoConsentimiento) {
        this.estadoConsentimiento = estadoConsentimiento;
    }

    public String getTipoAux() {
        return tipoAux;
    }

    public void setTipoAux(String tipoAux) {
        this.tipoAux = tipoAux;
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
