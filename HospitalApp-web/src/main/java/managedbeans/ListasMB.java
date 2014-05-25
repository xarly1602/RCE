/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbeans;

import cl.RCE.www.especialidad.EspecialidadNegocioLocal;
import cl.RCE.www.subespecialidad.SubespecialidadNegocioLocal;
import cl.RCE.www.entities.*;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;
import cl.RCE.www.sessionbeans.ComunaFacadeLocal;
import cl.RCE.www.sessionbeans.ConsultorioFacadeLocal;
import cl.RCE.www.sessionbeans.EducacionFacadeLocal;
import cl.RCE.www.sessionbeans.EspecialidadFacadeLocal;
import cl.RCE.www.sessionbeans.EstadoConyugalFacadeLocal;
import cl.RCE.www.sessionbeans.GeneroFacadeLocal;
import cl.RCE.www.sessionbeans.LeyesSocialesFacadeLocal;
import cl.RCE.www.sessionbeans.PersonaFacadeLocal;
import cl.RCE.www.sessionbeans.PrevisionFacadeLocal;
import cl.RCE.www.sessionbeans.PuebloOriginarioFacadeLocal;
import cl.RCE.www.sessionbeans.ReligionFacadeLocal;
import cl.RCE.www.sessionbeans.SubespecialidadFacadeLocal;
import cl.RCE.www.sessionbeans.TipoPrevisionFacadeLocal;

/**
 *
 * @author G-3
 */
@ManagedBean
@RequestScoped
public class ListasMB {
    @EJB
    private SubespecialidadNegocioLocal subespecialidadNegocio1;
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
    private List<PuebloOriginario> listaPueblos;
    private List<Religion> listaReligion;
    private List<TipoPrevision> listaTipos; 

    private String elementoBuscado;
    private int i;
    private int especialidadSeleccionadaId;
    /**
     * Creates a new instance of ListasMB
     */
    public ListasMB() {
    }
    
    @PostConstruct
    public void init(){
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
        this.filtrarListas();
    }

    public void filtrarListas(){
        int size;
        size = listaEspecialidades.size();
        for (i = size - 1; i >= 0; i--) {
            if(!listaEspecialidades.get(i).getEspeActivo()){
                listaEspecialidades.remove(i);
            }
        }
        size = listaSubespecialidad.size();
        for (i = size - 1; i >= 0; i--) {
            if (!listaSubespecialidad.get(i).getSubespeActivo()){
                listaSubespecialidad.remove(i);
            }
        }
    }
    
    // Busca una sub-especialidad por nombre.
    public void buscarSubespecialidad(){
        listaSubespecialidad = subespecialidadNegocio.busquedaSubespecialidadNombre(elementoBuscado);
        for (i = listaSubespecialidad.size() - 1; i >= 0 ; i--) {
            if (!listaSubespecialidad.get(i).getSubespeActivo()) {
                listaSubespecialidad.remove(i);
            }
        }
    }

    public void buscarEspecialidad(){
        listaEspecialidades = especialidadNegocio.busquedaEspecialidadNombre(elementoBuscado);
        for (i = listaEspecialidades.size() - 1; i >= 0; i--) {
            if (!listaEspecialidades.get(i).getEspeActivo()) {
                listaEspecialidades.remove(i);
            }
        }
    }

    public void filtrarEspecialidad(AjaxBehaviorEvent event){
        listaSubespecialidad = subespecialidadNegocio.busquedaSubespecialidadIdEspecialidad(especialidadSeleccionadaId);
        for (i = listaSubespecialidad.size() - 1; i >= 0 ; i--) {
            System.out.println(listaSubespecialidad.get(i).getSubespeNombre());
            if (!listaSubespecialidad.get(i).getSubespeActivo()) {
                System.out.println("Quitar: "+listaSubespecialidad.get(i).getSubespeNombre());
                listaSubespecialidad.remove(i);
            }
        }
    }
    
    public List<Subespecialidad> completeSub(String query) {
        List<Subespecialidad> suggestions = new ArrayList<Subespecialidad>();
         
        for(Subespecialidad p : listaSubespecialidad) {
            if(p.getSubespeNombre().startsWith(query))
                suggestions.add(p);
        }
         
        return suggestions;
    }

    public int getEspecialidadSeleccionadaId() {
        return especialidadSeleccionadaId;
    }

    public void setEspecialidadSeleccionadaId(int especialidadSeleccionadaId) {
        this.especialidadSeleccionadaId = especialidadSeleccionadaId;
    }
     
    public List<Especialidad> getListaEspecialidades() {
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

    public String getElementoBuscado() {
        return elementoBuscado;
    }

    public void setElementoBuscado(String elementoBuscado) {
        this.elementoBuscado = elementoBuscado;
    }
    
}
