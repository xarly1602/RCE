/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import cl.rcehblt.anamnesis.AnamnesisNegocioLocal;
import cl.rcehblt.entities.Anamnesis;
import cl.rcehblt.entities.Paciente;
import cl.rcehblt.entities.Profesional;
import cl.rcehblt.sessionbeans.AnamnesisFacadeLocal;
import cl.rcehblt.sessionbeans.PacienteFacadeLocal;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
@Named(value = "buscarAnamnesis")
@SessionScoped
public class BuscarAnamnesis implements Serializable {

    @EJB
    private AnamnesisNegocioLocal anamnesisNegocio;
    @EJB
    private AnamnesisFacadeLocal anamnesisFacade;
    @EJB
    private PacienteFacadeLocal pacienteFacade;

    private Paciente paciente;
    private Profesional profesional;
    private Anamnesis anamnesisPaciente;
    private List<Anamnesis> listaAnamnesis;
    private List<Paciente> listaPacientes;
    private List<String> patologiasMat;
    private String anamnesis;
    private String causaCesarea;
    private String partoPrem;
    private String embMultiple;
    private String mortinato;
    private String mortineonato;
    private String imc;
    private String du;
    private String presentacion;
    private String posicion;
    private String consistencia;
    private String borramiento;
    private String plano;
    private String membranas;
    private String bishop;
    private String motivoConsulta;
    private String motivoUltimaConsulta;
    private Date fur;
    private Date furop;
    private Date fpp;
    private Date ecoPrecoz;
    private Date ultimoEmbarazo;
    private Date ultimoControl;
    private boolean acorde;
    private int eg;
    private int ingresoPor;
    private int gestas;
    private int partos;
    private int vaginales;
    private int cesareas;
    private int abortos;
    private int menorPeso;
    private int mayorPeso;
    private int pulso;
    private int presionArterial;
    private int alturaUterina;
    private int lcf;
    private int tactoVag;
    private int dilatacion;
    private int controlCarop;
    private float temperatura;
    private float peso;
    private float talla;
    // Estas serán entities.
    private String diagnostico;
    private String indicaciones;

    /**
     * Constructor de la clase.
     */
    public BuscarAnamnesis() {
    }

    /**
     * Postconstructor. Inicializar variables y establecer valores por default.
     */
    @PostConstruct
    public void init() {
        ingresoPor = 1;
        presentacion = "Cefálica";
        tactoVag = 1;
        controlCarop = 2;
        paciente = new Paciente();
        anamnesisPaciente = new Anamnesis();
        listaAnamnesis = new ArrayList<Anamnesis>();
        listaPacientes = pacienteFacade.findAll();
        patologiasMat = new ArrayList<String>();
    }

    /**
     * Buscar anamnesis. Método que obtiene una lista de las anamnesis
     * pertenecientes a un determinado paciente.
     *
     * @param idPaciente Id del paciente que se desea buscar.
     */
    public void buscarAnamnesis(int idPaciente) {
        this.setListaAnamnesis(anamnesisNegocio.buscaAnamnesisPaciente(idPaciente));
    }

    /**
     * Guardar cambios. Método que guarda los cambios realizados en una
     * anamnesis determinada.
     */
    public void guardarCambios() {
        String temp = "";
        for (String patologia : patologiasMat) {
            temp = temp.concat(patologia);
        }
        anamnesisPaciente.setAnamPatologias(temp);
        anamnesisFacade.edit(anamnesisPaciente);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", "Se han guardado los cambios exitosamente."));
    }

    /**
     * Calcular Índice de Masa Corporal (IMC). Calcula el IMC a partir de los
     * datos de peso y talla indicados en el formulario correspondiente.
     *
     * @param actionEvent Evento en la página xhtml que acciona la función.
     */
    public void calcularIMC(ActionEvent actionEvent) {
        if (anamnesisPaciente.getAnamTalla() > 0 && anamnesisPaciente.getAnamPeso() > 0) {
            double temp = anamnesisPaciente.getAnamPeso() / (anamnesisPaciente.getAnamTalla() * anamnesisPaciente.getAnamTalla());
            DecimalFormat df = new DecimalFormat("#.##");
            anamnesisPaciente.setAnamImc(df.format(temp));
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Debe ingresar el peso y la talla deben ser valores positivos distintos de cero."));
        }
    }

    // Getters y Setters
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

    public Anamnesis getAnamnesisPaciente() {
        return anamnesisPaciente;
    }

    public void setAnamnesisPaciente(Anamnesis anamnesisPaciente) {
        String[] temp = anamnesisPaciente.getAnamPatologias().split(";");
        patologiasMat.addAll(Arrays.asList(temp));
        this.anamnesisPaciente = anamnesisPaciente;
    }

    public List<Anamnesis> getListaAnamnesis() {
        System.out.println("GET LISTA");
        try {
            for (Anamnesis anamnesis1 : listaAnamnesis) {
                System.out.println("-->" + anamnesis1.getIdAnamnesis());
            }
        } catch (Exception e) {
        }
        return listaAnamnesis;
    }

    public void setListaAnamnesis(List<Anamnesis> listaAnamnesis) {
        this.listaAnamnesis = listaAnamnesis;
    }

    public List<Paciente> getListaPacientes() {
        return listaPacientes;
    }

    public void setListaPacientes(List<Paciente> listaPacientes) {
        this.listaPacientes = listaPacientes;
    }

    public List<String> getPatologiasMat() {
        return patologiasMat;
    }

    public void setPatologiasMat(List<String> patologiasMat) {
        this.patologiasMat = patologiasMat;
    }

    public String getAnamnesis() {
        return anamnesis;
    }

    public void setAnamnesis(String anamnesis) {
        this.anamnesis = anamnesis;
    }

    public String getCausaCesarea() {
        return causaCesarea;
    }

    public void setCausaCesarea(String causaCesarea) {
        this.causaCesarea = causaCesarea;
    }

    public String getPartoPrem() {
        return partoPrem;
    }

    public void setPartoPrem(String partoPrem) {
        this.partoPrem = partoPrem;
    }

    public String getEmbMultiple() {
        return embMultiple;
    }

    public void setEmbMultiple(String embMultiple) {
        this.embMultiple = embMultiple;
    }

    public String getMortinato() {
        return mortinato;
    }

    public void setMortinato(String mortinato) {
        this.mortinato = mortinato;
    }

    public String getMortineonato() {
        return mortineonato;
    }

    public void setMortineonato(String mortineonato) {
        this.mortineonato = mortineonato;
    }

    public String getImc() {
        return imc;
    }

    public void setImc(String imc) {
        this.imc = imc;
    }

    public String getDu() {
        return du;
    }

    public void setDu(String du) {
        this.du = du;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public String getConsistencia() {
        return consistencia;
    }

    public void setConsistencia(String consistencia) {
        this.consistencia = consistencia;
    }

    public String getBorramiento() {
        return borramiento;
    }

    public void setBorramiento(String borramiento) {
        this.borramiento = borramiento;
    }

    public String getPlano() {
        return plano;
    }

    public void setPlano(String plano) {
        this.plano = plano;
    }

    public String getMembranas() {
        return membranas;
    }

    public void setMembranas(String membranas) {
        this.membranas = membranas;
    }

    public String getBishop() {
        return bishop;
    }

    public void setBishop(String bishop) {
        this.bishop = bishop;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public String getMotivoUltimaConsulta() {
        return motivoUltimaConsulta;
    }

    public void setMotivoUltimaConsulta(String motivoUltimaConsulta) {
        this.motivoUltimaConsulta = motivoUltimaConsulta;
    }

    public Date getFur() {
        return fur;
    }

    public void setFur(Date fur) {
        this.fur = fur;
    }

    public Date getFurop() {
        return furop;
    }

    public void setFurop(Date furop) {
        this.furop = furop;
    }

    public Date getFpp() {
        return fpp;
    }

    public void setFpp(Date fpp) {
        this.fpp = fpp;
    }

    public Date getEcoPrecoz() {
        return ecoPrecoz;
    }

    public void setEcoPrecoz(Date ecoPrecoz) {
        this.ecoPrecoz = ecoPrecoz;
    }

    public Date getUltimoEmbarazo() {
        return ultimoEmbarazo;
    }

    public void setUltimoEmbarazo(Date ultimoEmbarazo) {
        this.ultimoEmbarazo = ultimoEmbarazo;
    }

    public Date getUltimoControl() {
        return ultimoControl;
    }

    public void setUltimoControl(Date ultimoControl) {
        this.ultimoControl = ultimoControl;
    }

    public boolean isAcorde() {
        return acorde;
    }

    public void setAcorde(boolean acorde) {
        this.acorde = acorde;
    }

    public int getEg() {
        return eg;
    }

    public void setEg(int eg) {
        this.eg = eg;
    }

    public int getIngresoPor() {
        return ingresoPor;
    }

    public void setIngresoPor(int ingresoPor) {
        this.ingresoPor = ingresoPor;
    }

    public int getGestas() {
        return gestas;
    }

    public void setGestas(int gestas) {
        this.gestas = gestas;
    }

    public int getPartos() {
        return partos;
    }

    public void setPartos(int partos) {
        this.partos = partos;
    }

    public int getVaginales() {
        return vaginales;
    }

    public void setVaginales(int vaginales) {
        this.vaginales = vaginales;
    }

    public int getCesareas() {
        return cesareas;
    }

    public void setCesareas(int cesareas) {
        this.cesareas = cesareas;
    }

    public int getAbortos() {
        return abortos;
    }

    public void setAbortos(int abortos) {
        this.abortos = abortos;
    }

    public int getMenorPeso() {
        return menorPeso;
    }

    public void setMenorPeso(int menorPeso) {
        this.menorPeso = menorPeso;
    }

    public int getMayorPeso() {
        return mayorPeso;
    }

    public void setMayorPeso(int mayorPeso) {
        this.mayorPeso = mayorPeso;
    }

    public int getPulso() {
        return pulso;
    }

    public void setPulso(int pulso) {
        this.pulso = pulso;
    }

    public int getPresionArterial() {
        return presionArterial;
    }

    public void setPresionArterial(int presionArterial) {
        this.presionArterial = presionArterial;
    }

    public int getAlturaUterina() {
        return alturaUterina;
    }

    public void setAlturaUterina(int alturaUterina) {
        this.alturaUterina = alturaUterina;
    }

    public int getLcf() {
        return lcf;
    }

    public void setLcf(int lcf) {
        this.lcf = lcf;
    }

    public int getTactoVag() {
        return tactoVag;
    }

    public void setTactoVag(int tactoVag) {
        this.tactoVag = tactoVag;
    }

    public int getDilatacion() {
        return dilatacion;
    }

    public void setDilatacion(int dilatacion) {
        this.dilatacion = dilatacion;
    }

    public int getControlCarop() {
        return controlCarop;
    }

    public void setControlCarop(int controlCarop) {
        this.controlCarop = controlCarop;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getTalla() {
        return talla;
    }

    public void setTalla(float talla) {
        this.talla = talla;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getIndicaciones() {
        return indicaciones;
    }

    public void setIndicaciones(String indicaciones) {
        this.indicaciones = indicaciones;
    }

}
