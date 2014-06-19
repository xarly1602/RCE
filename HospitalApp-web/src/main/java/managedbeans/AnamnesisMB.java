/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbeans;

import cl.rcehblt.entities.Anamnesis;
import cl.rcehblt.entities.Paciente;
import cl.rcehblt.entities.Profesional;
import cl.rcehblt.sessionbeans.AnamnesisFacadeLocal;
import cl.rcehblt.sessionbeans.PacienteFacadeLocal;
import cl.rcehblt.sessionbeans.ProfesionalFacadeLocal;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;

/**
 *
 * @author DevelUser
 */
@ManagedBean
@SessionScoped
public class AnamnesisMB {
    
    @EJB
    private ProfesionalFacadeLocal profesionalFacade;
    @EJB
    private AnamnesisFacadeLocal anamnesisFacade;
    @EJB
    private PacienteFacadeLocal pacienteFacade;
    
    private Paciente paciente;
    private Profesional profesional;
    private Anamnesis anamnesisPaciente;
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
    @Min(value = 1, message = "Debe ingresar el pulso del paciente.")
    private int pulso;
    @Min(value = 1, message = "Debe ingresar la presión arterial del paciente.")
    private int presionArterial;
    private int alturaUterina;
    private int lcf;    
    private int tactoVag;
    private int dilatacion;
    private int controlCarop;
    private int edad;
    @DecimalMin(value = "0.1", message = "Debe ingresar la temperatura del paciente.")
    private float temperatura;
    @DecimalMin(value = "0.1", message = "Debe ingresar el peso del paciente.")
    private float peso;
    @DecimalMin(value = "0.1", message = "Debe ingresar la talla del paciente.")
    private float talla;
    // Estas serán entities.
    private String diagnostico;
    private String indicaciones;

    /**
     * Constructor de la clase.
     */
    public AnamnesisMB() {
    }

    /**
     * Postconstructor.
     * Se inicializan algunos datos de importancia.
     */
    @PostConstruct
    public void init() {
        //paciente = pacienteFacade.find(3);
        paciente = new Paciente();
        patologiasMat = new ArrayList<String>();
        ingresoPor = 1;
        presentacion = "Cefálica";
        tactoVag = 1;
        controlCarop = 2;
        //anamnesisPaciente = new Anamnesis();
    }
    
    /**
     * Guardar anamnesis.
     * Método que hace la persistencia de los datos indicados en el formulario
     * correspondiente.
     */
    public void guardarAnamnesis() {
        anamnesisPaciente = new Anamnesis();
        anamnesisPaciente.setAnamAbortos(abortos);
        anamnesisPaciente.setAnamAu(alturaUterina);
        anamnesisPaciente.setAnamBishop(bishop);
        anamnesisPaciente.setAnamCausacesarea(causaCesarea);
        anamnesisPaciente.setAnamCesareas(cesareas);
        anamnesisPaciente.setAnamDescripcion(anamnesis);
        anamnesisPaciente.setAnamDiagnostico(diagnostico);
        anamnesisPaciente.setAnamDu(du);
        anamnesisPaciente.setAnamEcoprecoz(ecoPrecoz);
        anamnesisPaciente.setAnamEg(eg);
        anamnesisPaciente.setAnamEmbmultiple(embMultiple);
        anamnesisPaciente.setAnamFpp(fpp);
        anamnesisPaciente.setAnamFur(fur);
        anamnesisPaciente.setAnamFurop(furop);
        anamnesisPaciente.setAnamGestas(gestas);
        anamnesisPaciente.setAnamImc(imc);
        anamnesisPaciente.setAnamIndicaciones(indicaciones);
        anamnesisPaciente.setAnamIngresopor(ingresoPor);
        anamnesisPaciente.setAnamLcf(lcf);
        anamnesisPaciente.setAnamMayorpeso(mayorPeso);
        anamnesisPaciente.setAnamMenorpeso(menorPeso);
        anamnesisPaciente.setAnamMortinato(mortinato);
        anamnesisPaciente.setAnamMortineonato(mortineonato);
        anamnesisPaciente.setAnamMotivoconsulta(motivoConsulta);
        anamnesisPaciente.setAnamMotivoultima(motivoConsulta);
        anamnesisPaciente.setAnamPa(presionArterial);
        anamnesisPaciente.setAnamPartoprem(partoPrem);
        anamnesisPaciente.setAnamPartos(partos);
        String patologias = "";
        for (String string : patologiasMat) {
            patologias = patologias.concat(string).concat(";");
        }
        anamnesisPaciente.setAnamPatologias(patologias);
        anamnesisPaciente.setAnamAcorde(acorde);
        anamnesisPaciente.setAnamFechacreacion(Calendar.getInstance().getTime());
        anamnesisPaciente.setAnamPeso((double) peso);
        if (tactoVag == 1) {
            anamnesisPaciente.setAnamBorramiento(borramiento);
            anamnesisPaciente.setAnamConsistencia(consistencia);
            anamnesisPaciente.setAnamPosicion(posicion);
            anamnesisPaciente.setAnamDilatacion(dilatacion);
            anamnesisPaciente.setAnamMembranas(membranas);
            anamnesisPaciente.setAnamPlano(plano);
        }        
        anamnesisPaciente.setAnamPresentacion(presentacion);
        anamnesisPaciente.setAnamPulso(pulso);
        anamnesisPaciente.setAnamTalla((double) talla);
        anamnesisPaciente.setAnamTemperatura((double) temperatura);
        anamnesisPaciente.setAnamUltimocontrol(ultimoControl);
        anamnesisPaciente.setAnamUltimoemb(ultimoEmbarazo);
        anamnesisPaciente.setAnamVaginales(vaginales);
        
        anamnesisPaciente.setIdPaciente(paciente);
        
        anamnesisFacade.edit(anamnesisPaciente);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Se ha guardado exitosamente la anamnesis"));
        
    }

    /**
     * Calcular la FPP mediante la fur.
     * Método que calcula la fecha probable de parto a partir de la fecha de la
     * última regla indicada en el formulario.
     */
    public void calcularFppFur(){
        if (fur != null) {
            Calendar today = Calendar.getInstance();
            long milisToday = today.getTimeInMillis();
            Calendar furC = Calendar.getInstance();
            furC.setTime(fur);
            long milisFur = furC.getTimeInMillis();
            long diferencia = milisToday - milisFur;
            long dias = diferencia / (24 * 60 * 60 * 1000);
            //furC = Calendar.getInstance();
            furC.add(Calendar.DATE,280);
            fpp = furC.getTime();
        }
    }
    
    /**
     * Calcular la FPP mediante la furop.
     * Método que calcula la fecha probable de parto a partir de la fecha de la
     * última regla operacional indicada en el formulario.
     */
    public void calcularFppFurop(){
        if (furop != null) {
            Calendar today = Calendar.getInstance();
            long milisToday = today.getTimeInMillis();
            Calendar furC = Calendar.getInstance();
            furC.setTime(furop);
            long milisFur = furC.getTimeInMillis();
            long diferencia = milisToday - milisFur;
            long dias = diferencia / (24 * 60 * 60 * 1000);
            //furC = Calendar.getInstance();
            furC.add(Calendar.DATE,280);
            fpp = furC.getTime();
        }
    }
    
    /**
     * Calcular gestas.
     * Método que calcula las gestas a partir de la cantidad de partos vaginales,
     * cesareas y abortos que haya tenido la paciente.
     */
    public void calculaGestas(){
        this.partos = this.vaginales + this.cesareas;
        this.gestas = this.partos + this.abortos;
    }
    
    /**
     * Calcular Índice de Masa Corporal (IMC). 
     * Calcula el IMC a partir de los
     * datos de peso y talla indicados en el formulario correspondiente.
     *
     * @param actionEvent Evento en la página xhtml que acciona la función.
     */
    public void calcularIMC(ActionEvent actionEvent) {
        if (talla > 0 && peso > 0) {
            float temp = peso / (talla * talla);
            DecimalFormat df = new DecimalFormat("#.##");
            imc = df.format(temp);
        }
        else
           FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", "Debe ingresar el peso y la talla deben ser valores positivos distintos de cero.")); 
    }
    
    //Getters y Setters.
    public Paciente getPaciente() {
        return paciente;
    }
    
    public void setPaciente(Paciente paciente) {
        int aux = Calendar.getInstance().getTime().getYear() - paciente.getIdPersona().getPersFnacimiento().getYear();
        if(Calendar.getInstance().getTime().getMonth() < paciente.getIdPersona().getPersFnacimiento().getMonth())
            aux --;
        this.edad = aux;
        this.paciente = paciente;
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
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

    public Anamnesis getAnamnesisPaciente() {
        return anamnesisPaciente;
    }

    public void setAnamnesisPaciente(Anamnesis anamnesisPaciente) {
        this.anamnesisPaciente = anamnesisPaciente;
    }
    
}
