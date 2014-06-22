package managedbeans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import cl.rcehblt.entities.Paciente;
import cl.rcehblt.entities.Profesional;
import cl.rcehblt.sessionbeans.PacienteFacadeLocal;
import cl.rcehblt.sessionbeans.ProfesionalFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author DevelUser
 */
@Named(value = "episodioMB")
@SessionScoped
public class EpisodioMB implements Serializable {
    @EJB
    private ProfesionalFacadeLocal profesionalFacade;
    @EJB
    private PacienteFacadeLocal pacienteFacade;

    boolean abierto;
    String nombre;
    Paciente paciente;
    Profesional profesional;
    List<String> consultas;
    private List<Paciente> listaPacientes;
    
    public EpisodioMB() {
    }
    
    @PostConstruct
    public void init(){
        listaPacientes = pacienteFacade.findAll();
        consultas = new ArrayList<String>();
        abierto = true;
        paciente = new Paciente();
        profesional = new Profesional();
    }            

    public void nuevoEpisodio(){
        System.out.println("NUEVO EPISODIO: \n- "+nombre+"\n- "+paciente.getIdPersona().getPersNombres()+"\n- "+abierto);
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
    
}
