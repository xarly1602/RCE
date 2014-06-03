/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbeans;

import cl.RCE.www.entities.Paciente;
import cl.RCE.www.sessionbeans.PacienteFacadeLocal;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author DevelUser
 */
@ManagedBean
@RequestScoped
public class AnamnesisMB {
    @EJB
    private PacienteFacadeLocal pacienteFacade;

    private Paciente paciente;
    private String enfermedadActual;
    private String menstruacion;
    private String relacionesSexuales;
    private String antecedentesMorbidos;
    private String antecedentesFamiliares;
    private int partos;
    private int abortos;
    // Estas serán entities.
    private String diagnostico;
    private String fundamentosDiagnostivo;
    private String examenes;
    private String tratamiento;
    /**
     * Constructor de la clase.
     */
    public AnamnesisMB() {
    }

    @PostConstruct
    public void init(){
        paciente = pacienteFacade.find(3);
    }
    
    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getEnfermedadActual() {
        return enfermedadActual;
    }

    public void setEnfermedadActual(String enfermedadActual) {
        this.enfermedadActual = enfermedadActual;
    }

    public String getMenstruacion() {
        return menstruacion;
    }

    public void setMenstruacion(String menstruacion) {
        this.menstruacion = menstruacion;
    }

    public String getRelacionesSexuales() {
        return relacionesSexuales;
    }

    public void setRelacionesSexuales(String relacionesSexuales) {
        this.relacionesSexuales = relacionesSexuales;
    }

    public String getAntecedentesMorbidos() {
        return antecedentesMorbidos;
    }

    public void setAntecedentesMorbidos(String antecedentesMorbidos) {
        this.antecedentesMorbidos = antecedentesMorbidos;
    }

    public String getAntecedentesFamiliares() {
        return antecedentesFamiliares;
    }

    public void setAntecedentesFamiliares(String antecedentesFamiliares) {
        this.antecedentesFamiliares = antecedentesFamiliares;
    }

    public int getPartos() {
        return partos;
    }

    public void setPartos(int partos) {
        this.partos = partos;
    }

    public int getAbortos() {
        return abortos;
    }

    public void setAbortos(int abortos) {
        this.abortos = abortos;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getFundamentosDiagnostivo() {
        return fundamentosDiagnostivo;
    }

    public void setFundamentosDiagnostivo(String fundamentosDiagnostivo) {
        this.fundamentosDiagnostivo = fundamentosDiagnostivo;
    }

    public String getExamenes() {
        return examenes;
    }

    public void setExamenes(String examenes) {
        this.examenes = examenes;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }
    
}
