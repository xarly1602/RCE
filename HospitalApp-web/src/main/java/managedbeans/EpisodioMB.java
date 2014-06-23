package managedbeans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import cl.rcehblt.entities.Paciente;
import cl.rcehblt.entities.Profesional;
import cl.rcehblt.entities.Episodios;
import cl.rcehblt.episodio.EpisodioNegocioLocal;
import cl.rcehblt.sessionbeans.EpisodiosFacadeLocal;
import cl.rcehblt.sessionbeans.PacienteFacadeLocal;
import cl.rcehblt.sessionbeans.ProfesionalFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author DevelUser
 */
@Named(value = "episodioMB")
@SessionScoped
public class EpisodioMB implements Serializable {
    @EJB
    private EpisodiosFacadeLocal episodiosFacade;
    @EJB
    private EpisodioNegocioLocal episodioNegocio;
    @EJB
    private ProfesionalFacadeLocal profesionalFacade;
    @EJB
    private PacienteFacadeLocal pacienteFacade;

    boolean abierto;
    String nombre;
    Paciente paciente;
    Profesional profesional;
    Episodios episodio;
    List<String> consultas;
    private List<Paciente> listaPacientes;
    private List<Episodios> listaEpisodios;
    
    public EpisodioMB() {
    }
    
    @PostConstruct
    public void init(){
        listaPacientes = pacienteFacade.findAll();
        listaEpisodios = new ArrayList<Episodios>();
        consultas = new ArrayList<String>();
        abierto = true;
        paciente = new Paciente();
        profesional = new Profesional();
        episodio = new Episodios();
    }            

    public void buscarEpisodios(int idPaciente){
        listaEpisodios = episodioNegocio.busquedaEpisodioIdPaciente(idPaciente);        
    }
    
    public void nuevoEpisodio(){
        boolean epiAbierto = false;
        listaEpisodios = episodioNegocio.busquedaEpisodioIdPaciente(paciente.getIdPaciente());        
        for (Episodios episodio1 : listaEpisodios) {
            if (episodio1.getAbierto()) {
                epiAbierto = true;
                break;
            }
        }
        if (!epiAbierto) {
            Date fecha = Calendar.getInstance().getTime();
            episodio.setAbierto(abierto);
            episodio.setFecha(fecha);
            episodio.setIdPaciente(paciente);
            episodio.setNombre(nombre);
            episodiosFacade.edit(episodio);
            this.resetData();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Se ha generado un episodio exitosamente."));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ya existe un episodio abierto."));
        }
    }

    public void cerrarEpisodio(int idEpisodio){
        episodio = episodiosFacade.find(idEpisodio);
        episodio.setAbierto(Boolean.FALSE);
        episodiosFacade.edit(episodio);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Se ha cerrado el episodio."));
    }
    
    private void resetData(){
        nombre = "";
        episodio = new Episodios();
    }
    
    public boolean isAbierto() {
        return abierto;
    }

    public void setAbierto(boolean abierto) {
        this.abierto = abierto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<String> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<String> consultas) {
        this.consultas = consultas;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Profesional getProfesional() {
        return profesional;
    }

    public void setProfesional(Profesional profesional) {
        this.profesional = profesional;
    }

    public List<Paciente> getListaPacientes() {
        return listaPacientes;
    }

    public void setListaPacientes(List<Paciente> listaPacientes) {
        this.listaPacientes = listaPacientes;
    }

    public Episodios getEpisodio() {
        return episodio;
    }

    public void setEpisodio(Episodios episodio) {
        this.episodio = episodio;
    }

    public List<Episodios> getListaEpisodios() {
        return listaEpisodios;
    }

    public void setListaEpisodios(List<Episodios> listaEpisodios) {
        this.listaEpisodios = listaEpisodios;
    }
    
}
