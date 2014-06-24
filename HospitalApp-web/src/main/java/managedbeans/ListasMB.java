/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import cl.rcehblt.entities.LeyesSociales;
import cl.rcehblt.entities.Especialidad;
import cl.rcehblt.entities.Consultorio;
import cl.rcehblt.entities.Sector;
import cl.rcehblt.entities.TipoPrevision;
import cl.rcehblt.entities.Prevision;
import cl.rcehblt.entities.Religion;
import cl.rcehblt.entities.Persona;
import cl.rcehblt.entities.Genero;
import cl.rcehblt.entities.Comuna;
import cl.rcehblt.entities.Local;
import cl.rcehblt.entities.Cargo;
import cl.rcehblt.entities.PuebloOriginario;
import cl.rcehblt.entities.Educacion;
import cl.rcehblt.entities.Establecimiento;
import cl.rcehblt.entities.EstadoConyugal;
import cl.rcehblt.entities.Subespecialidad;
import cl.rcehblt.entities.GrupoProfesional;
import cl.rcehblt.entities.ServicioSalud;
import cl.rcehblt.especialidad.EspecialidadNegocioLocal;
import cl.rcehblt.sessionbeans.CargoFacadeLocal;
import cl.rcehblt.sessionbeans.ComunaFacadeLocal;
import cl.rcehblt.sessionbeans.ConsultorioFacadeLocal;
import cl.rcehblt.sessionbeans.EducacionFacadeLocal;
import cl.rcehblt.sessionbeans.EspecialidadFacadeLocal;
import cl.rcehblt.sessionbeans.EstablecimientoFacadeLocal;
import cl.rcehblt.sessionbeans.EstadoConyugalFacadeLocal;
import cl.rcehblt.sessionbeans.GeneroFacadeLocal;
import cl.rcehblt.sessionbeans.GrupoProfesionalFacadeLocal;
import cl.rcehblt.sessionbeans.LeyesSocialesFacadeLocal;
import cl.rcehblt.sessionbeans.LocalFacadeLocal;
import cl.rcehblt.sessionbeans.PersonaFacadeLocal;
import cl.rcehblt.sessionbeans.PrevisionFacadeLocal;
import cl.rcehblt.sessionbeans.PuebloOriginarioFacadeLocal;
import cl.rcehblt.sessionbeans.ReligionFacadeLocal;
import cl.rcehblt.sessionbeans.SectorFacadeLocal;
import cl.rcehblt.sessionbeans.ServicioSaludFacadeLocal;
import cl.rcehblt.sessionbeans.SubespecialidadFacadeLocal;
import cl.rcehblt.sessionbeans.TipoPrevisionFacadeLocal;
import cl.rcehblt.subespecialidad.SubespecialidadNegocioLocal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author G-3
 */
@ManagedBean
@RequestScoped
public class ListasMB {

    @EJB
    private SectorFacadeLocal sectorFacade;
    @EJB
    private ServicioSaludFacadeLocal servicioSaludFacade;
    @EJB
    private EstablecimientoFacadeLocal establecimientoFacade;
    @EJB
    private LocalFacadeLocal localFacade;
    @EJB
    private GrupoProfesionalFacadeLocal grupoProfesionalFacade;
    @EJB
    private CargoFacadeLocal cargoFacade;
    @EJB
    private EspecialidadNegocioLocal especialidadNegocio;
    @EJB
    private SubespecialidadNegocioLocal subespecialidadNegocio;
    @EJB
    private TipoPrevisionFacadeLocal tipoPrevisionFacade;
    @EJB
    private ReligionFacadeLocal religionFacade;
    @EJB
    private PuebloOriginarioFacadeLocal puebloOriginarioFacade;
    @EJB
    private PrevisionFacadeLocal previsionFacade;
    @EJB
    private PersonaFacadeLocal personaFacade;
    @EJB
    private LeyesSocialesFacadeLocal leyesSocialesFacade;
    @EJB
    private GeneroFacadeLocal generoFacade;
    @EJB
    private EstadoConyugalFacadeLocal estadoConyugalFacade;
    @EJB
    private SubespecialidadFacadeLocal subespecialidadFacade;
    @EJB
    private EducacionFacadeLocal educacionFacade;
    @EJB
    private ConsultorioFacadeLocal consultorioFacade;
    @EJB
    private ComunaFacadeLocal comunaFacade;
    @EJB
    private EspecialidadFacadeLocal especialidadFacade;

    private List<Especialidad> listaEspecialidades;
    private List<Comuna> listaComuna;
    private List<Consultorio> listaConsultorio;
    private List<Educacion> listaEducacion;
    private List<Subespecialidad> listaSubespecialidad;
    private List<EstadoConyugal> listaEstados;
    private List<Genero> listaGenero;
    private List<LeyesSociales> listaLeyes;
    private List<Persona> listaPersonas;
    private List<Prevision> listaPrevision;
    private List<Persona> listaProfesionales;
    private List<PuebloOriginario> listaPueblos;
    private List<Religion> listaReligion;
    private List<TipoPrevision> listaTipos;
    private List<Cargo> listaCargos;
    private List<Local> listaLocales;
    private List<GrupoProfesional> listaGrupos;
    private List<Establecimiento> listaEstablecimientos;
    private List<Sector> listaSectores;
    private List<ServicioSalud> listaServicios;

    private String elementoBuscado;
    private String filtro;
    private int i;
    private int especialidadSeleccionadaId;

    /**
     * Constructor de la clase.
     */
    public ListasMB() {
    }

    /**
     * Postconstructor: Inicializar todas las listas de datos.
     */
    @PostConstruct
    public void init() {
        elementoBuscado = "";
        filtro = "";
        listaEspecialidades = especialidadFacade.findAll();
        listaComuna = comunaFacade.findAll();
        listaConsultorio = consultorioFacade.findAll();
        listaEducacion = educacionFacade.findAll();
        listaSubespecialidad = subespecialidadFacade.findAll();
        listaEstados = estadoConyugalFacade.findAll();
        listaGenero = generoFacade.findAll();
        listaLeyes = leyesSocialesFacade.findAll();
        listaPersonas = personaFacade.findAll();
        listaPrevision = previsionFacade.findAll();
        listaPueblos = puebloOriginarioFacade.findAll();
        listaReligion = religionFacade.findAll();
        listaCargos = cargoFacade.findAll();
        listaLocales = localFacade.findAll();
        listaGrupos = grupoProfesionalFacade.findAll();
        listaProfesionales = personaFacade.findAll();
        listaSectores = sectorFacade.findAll();
        listaServicios = servicioSaludFacade.findAll();
        listaEstablecimientos = establecimientoFacade.findAll();
        this.filtrarListas();
    }

    /**
     * Filtrar listas. Eliminar datos de las listas que se encuentren inactivos
     * en la base de datos.
     */
    public void filtrarListas() {
        int size;
        size = listaEspecialidades.size();
        for (i = size - 1; i >= 0; i--) {
            if (!listaEspecialidades.get(i).getEspeActivo()) {
                listaEspecialidades.remove(i);
            }
        }
        size = listaSubespecialidad.size();
        for (i = size - 1; i >= 0; i--) {
            if (!listaSubespecialidad.get(i).getSubespeActivo()) {
                listaSubespecialidad.remove(i);
            }
        }
        size = listaProfesionales.size();
        for (i = size - 1; i >= 0; i--) {
            if (listaProfesionales.get(i).getPersTipopersona() != 2) {
                listaProfesionales.remove(i);
            }
        }
    }

    /**
     * Buscar subespecialidad. Busca una subespecialidad por el nombre indicado
     * en el formulario.
     */
    public void buscarSubespecialidad() {
        listaSubespecialidad = subespecialidadNegocio.busquedaSubespecialidadNombre(elementoBuscado);
        for (i = listaSubespecialidad.size() - 1; i >= 0; i--) {
            if (!listaSubespecialidad.get(i).getSubespeActivo()) {
                listaSubespecialidad.remove(i);
            }
        }
    }

    /**
     * Buscar especialidad. Busca una especialidad por el nombre indicado en el
     * formulario.
     */
    public void buscarEspecialidad() {
        listaEspecialidades = especialidadNegocio.busquedaEspecialidadNombre(elementoBuscado);
        for (i = listaEspecialidades.size() - 1; i >= 0; i--) {
            if (!listaEspecialidades.get(i).getEspeActivo()) {
                listaEspecialidades.remove(i);
            }
        }
    }

    /**
     * Filtrar especialidades. Mostrar solo las subespecialidades que
     * correspondan a la especialidad seleccionada.
     *
     * @param event Evento en la página xhtml que acciona la función.
     */
    public void filtrarEspecialidad(AjaxBehaviorEvent event) {
        listaSubespecialidad = subespecialidadNegocio.busquedaSubespecialidadIdEspecialidad(especialidadSeleccionadaId);
        for (i = listaSubespecialidad.size() - 1; i >= 0; i--) {
            if (!listaSubespecialidad.get(i).getSubespeActivo()) {
                listaSubespecialidad.remove(i);
            }
        }
    }

    /**
     * Filtrar especialidades. Mostrar solo las subespecialidades que
     * correspondan a la especialidad seleccionada.
     *
     * @param event Evento en la página xhtml que acciona la función.
     */
    public void filtrarSubespecialidad(SelectEvent event) {
        if (especialidadNegocio.busquedaEspecialidadNombre(filtro) != null) {
            especialidadSeleccionadaId = especialidadNegocio.busquedaEspecialidadNombre(filtro).get(0).getIdEspecialidad();
            this.filtrarEspecialidad(event);
        }
    }

    /**
     * Completar subespecialidades. Función para realizar la búsqueda de
     * subespecialidades con autocompletar.
     *
     * @param query Nombre completo o inicio de la subespecialidad buscada.
     * @return Lista de resultados encontrados.
     */
    public List<String> completeSub(String query) {
        List<String> listaFiltrada = new ArrayList<String>();

        for (Subespecialidad p : listaSubespecialidad) {
            if (p.getSubespeNombre().startsWith(query)) {
                listaFiltrada.add(p.getSubespeNombre());
            }
        }

        return listaFiltrada;
    }

    /**
     * Completar especialidades. Función para realizar la búsqueda de
     * especialidades con autocompletar.
     *
     * @param query Nombre completo o inicio de la especialidad buscada.
     * @return Lista de resultados encontrados.
     */
    public List<String> completeEsp(String query) {
        List<String> listaFiltrada = new ArrayList<String>();

        for (Especialidad p : listaEspecialidades) {
            if (p.getEspeNombre().startsWith(query)) {
                listaFiltrada.add(p.getEspeNombre());
            }
        }

        return listaFiltrada;
    }

    // Getters y Setters
    public List<Cargo> getListaCargos() {
        return listaCargos;
    }

    public void setListaCargos(List<Cargo> listaCargos) {
        this.listaCargos = listaCargos;
    }

    public List<Local> getListaLocales() {
        return listaLocales;
    }

    public void setListaLocales(List<Local> listaLocales) {
        this.listaLocales = listaLocales;
    }

    public List<Persona> getListaProfesionales() {
        listaProfesionales = personaFacade.findAll();
        this.filtrarListas();
        return listaProfesionales;
    }

    public void setListaProfesionales(List<Persona> listaProfesionales) {
        this.listaProfesionales = listaProfesionales;
    }

    public List<GrupoProfesional> getListaGrupos() {
        return listaGrupos;
    }

    public void setListaGrupos(List<GrupoProfesional> listaGrupos) {
        this.listaGrupos = listaGrupos;
    }

    public int getEspecialidadSeleccionadaId() {
        return especialidadSeleccionadaId;
    }

    public void setEspecialidadSeleccionadaId(int especialidadSeleccionadaId) {
        this.especialidadSeleccionadaId = especialidadSeleccionadaId;
    }

    public List<Especialidad> getListaEspecialidades() {
        if (elementoBuscado.isEmpty()) {
            listaEspecialidades = especialidadFacade.findAll();
            this.filtrarListas();
        }
        return listaEspecialidades;
    }

    public void setListaEspecialidades(List<Especialidad> listaEspecialidades) {
        this.listaEspecialidades = listaEspecialidades;
    }

    public List<Comuna> getListaComuna() {
        return listaComuna;
    }

    public void setListaComuna(List<Comuna> listaComuna) {
        this.listaComuna = listaComuna;
    }

    public List<Consultorio> getListaConsultorio() {
        return listaConsultorio;
    }

    public void setListaConsultorio(List<Consultorio> listaConsultorio) {
        this.listaConsultorio = listaConsultorio;
    }

    public List<Educacion> getListaEducacion() {
        return listaEducacion;
    }

    public void setListaEducacion(List<Educacion> listaEducacion) {
        this.listaEducacion = listaEducacion;
    }

    public List<Subespecialidad> getListaSubespecialidad() {
        if (especialidadSeleccionadaId == 0 && elementoBuscado.isEmpty()) {
            listaSubespecialidad = subespecialidadFacade.findAll();
            this.filtrarListas();
        }
        return listaSubespecialidad;
    }

    public void setListaSubespecialidad(List<Subespecialidad> listaSubespecialidad) {
        this.listaSubespecialidad = listaSubespecialidad;
    }

    public List<EstadoConyugal> getListaEstados() {
        return listaEstados;
    }

    public void setListaEstados(List<EstadoConyugal> listaEstados) {
        this.listaEstados = listaEstados;
    }

    public List<Genero> getListaGenero() {
        return listaGenero;
    }

    public void setListaGenero(List<Genero> listaGenero) {
        this.listaGenero = listaGenero;
    }

    public List<LeyesSociales> getListaLeyes() {
        return listaLeyes;
    }

    public void setListaLeyes(List<LeyesSociales> listaLeyes) {
        this.listaLeyes = listaLeyes;
    }

    public List<Persona> getListaPersonas() {
        return listaPersonas;
    }

    public void setListaPersonas(List<Persona> listaPersonas) {
        this.listaPersonas = listaPersonas;
    }

    public List<Prevision> getListaPrevision() {
        return listaPrevision;
    }

    public void setListaPrevision(List<Prevision> listaPrevision) {
        this.listaPrevision = listaPrevision;
    }

    public List<PuebloOriginario> getListaPueblos() {
        return listaPueblos;
    }

    public void setListaPueblos(List<PuebloOriginario> listaPueblos) {
        this.listaPueblos = listaPueblos;
    }

    public List<Religion> getListaReligion() {
        return listaReligion;
    }

    public void setListaReligion(List<Religion> listaReligion) {
        this.listaReligion = listaReligion;
    }

    public List<TipoPrevision> getListaTipos() {
        return listaTipos;
    }

    public void setListaTipos(List<TipoPrevision> listaTipos) {
        this.listaTipos = listaTipos;
    }

    public List<Establecimiento> getListaEstablecimientos() {
        return listaEstablecimientos;
    }

    public void setListaEstablecimientos(List<Establecimiento> listaEstablecimientos) {
        this.listaEstablecimientos = listaEstablecimientos;
    }

    public List<Sector> getListaSectores() {
        return listaSectores;
    }

    public void setListaSectores(List<Sector> listaSectores) {
        this.listaSectores = listaSectores;
    }

    public List<ServicioSalud> getListaServicios() {
        return listaServicios;
    }

    public void setListaServicios(List<ServicioSalud> listaServicios) {
        this.listaServicios = listaServicios;
    }

    public String getElementoBuscado() {
        return elementoBuscado;
    }

    public void setElementoBuscado(String elementoBuscado) {
        this.elementoBuscado = elementoBuscado;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

}
