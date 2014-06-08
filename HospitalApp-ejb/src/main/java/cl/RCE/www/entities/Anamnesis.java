/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.RCE.www.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DevelUser
 */
@Entity
@Table(name = "anamnesis")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Anamnesis.findAll", query = "SELECT a FROM Anamnesis a"),
    @NamedQuery(name = "Anamnesis.findByIdAnamnesis", query = "SELECT a FROM Anamnesis a WHERE a.idAnamnesis = :idAnamnesis"),
    @NamedQuery(name = "Anamnesis.findByAnamDescripcion", query = "SELECT a FROM Anamnesis a WHERE a.anamDescripcion = :anamDescripcion"),
    @NamedQuery(name = "Anamnesis.findByAnamCausacesarea", query = "SELECT a FROM Anamnesis a WHERE a.anamCausacesarea = :anamCausacesarea"),
    @NamedQuery(name = "Anamnesis.findByAnamPartoprem", query = "SELECT a FROM Anamnesis a WHERE a.anamPartoprem = :anamPartoprem"),
    @NamedQuery(name = "Anamnesis.findByAnamEmbmultiple", query = "SELECT a FROM Anamnesis a WHERE a.anamEmbmultiple = :anamEmbmultiple"),
    @NamedQuery(name = "Anamnesis.findByAnamMortinato", query = "SELECT a FROM Anamnesis a WHERE a.anamMortinato = :anamMortinato"),
    @NamedQuery(name = "Anamnesis.findByAnamMortineonato", query = "SELECT a FROM Anamnesis a WHERE a.anamMortineonato = :anamMortineonato"),
    @NamedQuery(name = "Anamnesis.findByAnamImc", query = "SELECT a FROM Anamnesis a WHERE a.anamImc = :anamImc"),
    @NamedQuery(name = "Anamnesis.findByAnamDu", query = "SELECT a FROM Anamnesis a WHERE a.anamDu = :anamDu"),
    @NamedQuery(name = "Anamnesis.findByAnamPresentacion", query = "SELECT a FROM Anamnesis a WHERE a.anamPresentacion = :anamPresentacion"),
    @NamedQuery(name = "Anamnesis.findByAnamPosicion", query = "SELECT a FROM Anamnesis a WHERE a.anamPosicion = :anamPosicion"),
    @NamedQuery(name = "Anamnesis.findByAnamConsistencia", query = "SELECT a FROM Anamnesis a WHERE a.anamConsistencia = :anamConsistencia"),
    @NamedQuery(name = "Anamnesis.findByAnamBorramiento", query = "SELECT a FROM Anamnesis a WHERE a.anamBorramiento = :anamBorramiento"),
    @NamedQuery(name = "Anamnesis.findByAnamPlano", query = "SELECT a FROM Anamnesis a WHERE a.anamPlano = :anamPlano"),
    @NamedQuery(name = "Anamnesis.findByAnamMembranas", query = "SELECT a FROM Anamnesis a WHERE a.anamMembranas = :anamMembranas"),
    @NamedQuery(name = "Anamnesis.findByAnamBishop", query = "SELECT a FROM Anamnesis a WHERE a.anamBishop = :anamBishop"),
    @NamedQuery(name = "Anamnesis.findByAnamMotivoconsulta", query = "SELECT a FROM Anamnesis a WHERE a.anamMotivoconsulta = :anamMotivoconsulta"),
    @NamedQuery(name = "Anamnesis.findByAnamMotivoultima", query = "SELECT a FROM Anamnesis a WHERE a.anamMotivoultima = :anamMotivoultima"),
    @NamedQuery(name = "Anamnesis.findByAnamFur", query = "SELECT a FROM Anamnesis a WHERE a.anamFur = :anamFur"),
    @NamedQuery(name = "Anamnesis.findByAnamFurop", query = "SELECT a FROM Anamnesis a WHERE a.anamFurop = :anamFurop"),
    @NamedQuery(name = "Anamnesis.findByAnamFpp", query = "SELECT a FROM Anamnesis a WHERE a.anamFpp = :anamFpp"),
    @NamedQuery(name = "Anamnesis.findByAnamEcoprecoz", query = "SELECT a FROM Anamnesis a WHERE a.anamEcoprecoz = :anamEcoprecoz"),
    @NamedQuery(name = "Anamnesis.findByAnamUltimoemb", query = "SELECT a FROM Anamnesis a WHERE a.anamUltimoemb = :anamUltimoemb"),
    @NamedQuery(name = "Anamnesis.findByAnamUltimocontrol", query = "SELECT a FROM Anamnesis a WHERE a.anamUltimocontrol = :anamUltimocontrol"),
    @NamedQuery(name = "Anamnesis.findByAnamEg", query = "SELECT a FROM Anamnesis a WHERE a.anamEg = :anamEg"),
    @NamedQuery(name = "Anamnesis.findByAnamIngresopor", query = "SELECT a FROM Anamnesis a WHERE a.anamIngresopor = :anamIngresopor"),
    @NamedQuery(name = "Anamnesis.findByAnamGestas", query = "SELECT a FROM Anamnesis a WHERE a.anamGestas = :anamGestas"),
    @NamedQuery(name = "Anamnesis.findByAnamPartos", query = "SELECT a FROM Anamnesis a WHERE a.anamPartos = :anamPartos"),
    @NamedQuery(name = "Anamnesis.findByAnamVaginales", query = "SELECT a FROM Anamnesis a WHERE a.anamVaginales = :anamVaginales"),
    @NamedQuery(name = "Anamnesis.findByAnamCesareas", query = "SELECT a FROM Anamnesis a WHERE a.anamCesareas = :anamCesareas"),
    @NamedQuery(name = "Anamnesis.findByAnamAbortos", query = "SELECT a FROM Anamnesis a WHERE a.anamAbortos = :anamAbortos"),
    @NamedQuery(name = "Anamnesis.findByAnamMenorpeso", query = "SELECT a FROM Anamnesis a WHERE a.anamMenorpeso = :anamMenorpeso"),
    @NamedQuery(name = "Anamnesis.findByAnamMayorpeso", query = "SELECT a FROM Anamnesis a WHERE a.anamMayorpeso = :anamMayorpeso"),
    @NamedQuery(name = "Anamnesis.findByAnamPulso", query = "SELECT a FROM Anamnesis a WHERE a.anamPulso = :anamPulso"),
    @NamedQuery(name = "Anamnesis.findByAnamPa", query = "SELECT a FROM Anamnesis a WHERE a.anamPa = :anamPa"),
    @NamedQuery(name = "Anamnesis.findByAnamAu", query = "SELECT a FROM Anamnesis a WHERE a.anamAu = :anamAu"),
    @NamedQuery(name = "Anamnesis.findByAnamLcf", query = "SELECT a FROM Anamnesis a WHERE a.anamLcf = :anamLcf"),
    @NamedQuery(name = "Anamnesis.findByAnamDilatacion", query = "SELECT a FROM Anamnesis a WHERE a.anamDilatacion = :anamDilatacion"),
    @NamedQuery(name = "Anamnesis.findByAnamTemperatura", query = "SELECT a FROM Anamnesis a WHERE a.anamTemperatura = :anamTemperatura"),
    @NamedQuery(name = "Anamnesis.findByAnamPeso", query = "SELECT a FROM Anamnesis a WHERE a.anamPeso = :anamPeso"),
    @NamedQuery(name = "Anamnesis.findByAnamTalla", query = "SELECT a FROM Anamnesis a WHERE a.anamTalla = :anamTalla"),
    @NamedQuery(name = "Anamnesis.findByAnamDiagnostico", query = "SELECT a FROM Anamnesis a WHERE a.anamDiagnostico = :anamDiagnostico"),
    @NamedQuery(name = "Anamnesis.findByAnamIndicaciones", query = "SELECT a FROM Anamnesis a WHERE a.anamIndicaciones = :anamIndicaciones")})
public class Anamnesis implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_anamnesis")
    private Integer idAnamnesis;
    @Size(max = 2147483647)
    @Column(name = "anam_descripcion")
    private String anamDescripcion;
    @Size(max = 2147483647)
    @Column(name = "anam_causacesarea")
    private String anamCausacesarea;
    @Size(max = 20)
    @Column(name = "anam_partoprem")
    private String anamPartoprem;
    @Size(max = 20)
    @Column(name = "anam_embmultiple")
    private String anamEmbmultiple;
    @Size(max = 20)
    @Column(name = "anam_mortinato")
    private String anamMortinato;
    @Size(max = 20)
    @Column(name = "anam_mortineonato")
    private String anamMortineonato;
    @Size(max = 10)
    @Column(name = "anam_imc")
    private String anamImc;
    @Size(max = 20)
    @Column(name = "anam_du")
    private String anamDu;
    @Size(max = 20)
    @Column(name = "anam_presentacion")
    private String anamPresentacion;
    @Size(max = 20)
    @Column(name = "anam_posicion")
    private String anamPosicion;
    @Size(max = 20)
    @Column(name = "anam_consistencia")
    private String anamConsistencia;
    @Size(max = 20)
    @Column(name = "anam_borramiento")
    private String anamBorramiento;
    @Size(max = 20)
    @Column(name = "anam_plano")
    private String anamPlano;
    @Size(max = 20)
    @Column(name = "anam_membranas")
    private String anamMembranas;
    @Size(max = 20)
    @Column(name = "anam_bishop")
    private String anamBishop;
    @Size(max = 2147483647)
    @Column(name = "anam_motivoconsulta")
    private String anamMotivoconsulta;
    @Size(max = 2147483647)
    @Column(name = "anam_motivoultima")
    private String anamMotivoultima;
    @Column(name = "anam_fur")
    @Temporal(TemporalType.DATE)
    private Date anamFur;
    @Column(name = "anam_furop")
    @Temporal(TemporalType.DATE)
    private Date anamFurop;
    @Column(name = "anam_fpp")
    @Temporal(TemporalType.DATE)
    private Date anamFpp;
    @Column(name = "anam_ecoprecoz")
    @Temporal(TemporalType.DATE)
    private Date anamEcoprecoz;
    @Column(name = "anam_ultimoemb")
    @Temporal(TemporalType.DATE)
    private Date anamUltimoemb;
    @Column(name = "anam_ultimocontrol")
    @Temporal(TemporalType.DATE)
    private Date anamUltimocontrol;
    @Column(name = "anam_eg")
    private Integer anamEg;
    @Column(name = "anam_ingresopor")
    private Integer anamIngresopor;
    @Column(name = "anam_gestas")
    private Integer anamGestas;
    @Column(name = "anam_partos")
    private Integer anamPartos;
    @Column(name = "anam_vaginales")
    private Integer anamVaginales;
    @Column(name = "anam_cesareas")
    private Integer anamCesareas;
    @Column(name = "anam_abortos")
    private Integer anamAbortos;
    @Column(name = "anam_menorpeso")
    private Integer anamMenorpeso;
    @Column(name = "anam_mayorpeso")
    private Integer anamMayorpeso;
    @Column(name = "anam_pulso")
    private Integer anamPulso;
    @Column(name = "anam_pa")
    private Integer anamPa;
    @Column(name = "anam_au")
    private Integer anamAu;
    @Column(name = "anam_lcf")
    private Integer anamLcf;
    @Column(name = "anam_dilatacion")
    private Integer anamDilatacion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "anam_temperatura")
    private Double anamTemperatura;
    @Column(name = "anam_peso")
    private Double anamPeso;
    @Column(name = "anam_talla")
    private Double anamTalla;
    @Size(max = 2147483647)
    @Column(name = "anam_diagnostico")
    private String anamDiagnostico;
    @Size(max = 2147483647)
    @Column(name = "anam_indicaciones")
    private String anamIndicaciones;
    @JoinColumn(name = "id_paciente", referencedColumnName = "id_paciente")
    @ManyToOne
    private Paciente idPaciente;

    public Anamnesis() {
    }

    public Anamnesis(Integer idAnamnesis) {
        this.idAnamnesis = idAnamnesis;
    }

    public Integer getIdAnamnesis() {
        return idAnamnesis;
    }

    public void setIdAnamnesis(Integer idAnamnesis) {
        this.idAnamnesis = idAnamnesis;
    }

    public String getAnamDescripcion() {
        return anamDescripcion;
    }

    public void setAnamDescripcion(String anamDescripcion) {
        this.anamDescripcion = anamDescripcion;
    }

    public String getAnamCausacesarea() {
        return anamCausacesarea;
    }

    public void setAnamCausacesarea(String anamCausacesarea) {
        this.anamCausacesarea = anamCausacesarea;
    }

    public String getAnamPartoprem() {
        return anamPartoprem;
    }

    public void setAnamPartoprem(String anamPartoprem) {
        this.anamPartoprem = anamPartoprem;
    }

    public String getAnamEmbmultiple() {
        return anamEmbmultiple;
    }

    public void setAnamEmbmultiple(String anamEmbmultiple) {
        this.anamEmbmultiple = anamEmbmultiple;
    }

    public String getAnamMortinato() {
        return anamMortinato;
    }

    public void setAnamMortinato(String anamMortinato) {
        this.anamMortinato = anamMortinato;
    }

    public String getAnamMortineonato() {
        return anamMortineonato;
    }

    public void setAnamMortineonato(String anamMortineonato) {
        this.anamMortineonato = anamMortineonato;
    }

    public String getAnamImc() {
        return anamImc;
    }

    public void setAnamImc(String anamImc) {
        this.anamImc = anamImc;
    }

    public String getAnamDu() {
        return anamDu;
    }

    public void setAnamDu(String anamDu) {
        this.anamDu = anamDu;
    }

    public String getAnamPresentacion() {
        return anamPresentacion;
    }

    public void setAnamPresentacion(String anamPresentacion) {
        this.anamPresentacion = anamPresentacion;
    }

    public String getAnamPosicion() {
        return anamPosicion;
    }

    public void setAnamPosicion(String anamPosicion) {
        this.anamPosicion = anamPosicion;
    }

    public String getAnamConsistencia() {
        return anamConsistencia;
    }

    public void setAnamConsistencia(String anamConsistencia) {
        this.anamConsistencia = anamConsistencia;
    }

    public String getAnamBorramiento() {
        return anamBorramiento;
    }

    public void setAnamBorramiento(String anamBorramiento) {
        this.anamBorramiento = anamBorramiento;
    }

    public String getAnamPlano() {
        return anamPlano;
    }

    public void setAnamPlano(String anamPlano) {
        this.anamPlano = anamPlano;
    }

    public String getAnamMembranas() {
        return anamMembranas;
    }

    public void setAnamMembranas(String anamMembranas) {
        this.anamMembranas = anamMembranas;
    }

    public String getAnamBishop() {
        return anamBishop;
    }

    public void setAnamBishop(String anamBishop) {
        this.anamBishop = anamBishop;
    }

    public String getAnamMotivoconsulta() {
        return anamMotivoconsulta;
    }

    public void setAnamMotivoconsulta(String anamMotivoconsulta) {
        this.anamMotivoconsulta = anamMotivoconsulta;
    }

    public String getAnamMotivoultima() {
        return anamMotivoultima;
    }

    public void setAnamMotivoultima(String anamMotivoultima) {
        this.anamMotivoultima = anamMotivoultima;
    }

    public Date getAnamFur() {
        return anamFur;
    }

    public void setAnamFur(Date anamFur) {
        this.anamFur = anamFur;
    }

    public Date getAnamFurop() {
        return anamFurop;
    }

    public void setAnamFurop(Date anamFurop) {
        this.anamFurop = anamFurop;
    }

    public Date getAnamFpp() {
        return anamFpp;
    }

    public void setAnamFpp(Date anamFpp) {
        this.anamFpp = anamFpp;
    }

    public Date getAnamEcoprecoz() {
        return anamEcoprecoz;
    }

    public void setAnamEcoprecoz(Date anamEcoprecoz) {
        this.anamEcoprecoz = anamEcoprecoz;
    }

    public Date getAnamUltimoemb() {
        return anamUltimoemb;
    }

    public void setAnamUltimoemb(Date anamUltimoemb) {
        this.anamUltimoemb = anamUltimoemb;
    }

    public Date getAnamUltimocontrol() {
        return anamUltimocontrol;
    }

    public void setAnamUltimocontrol(Date anamUltimocontrol) {
        this.anamUltimocontrol = anamUltimocontrol;
    }

    public Integer getAnamEg() {
        return anamEg;
    }

    public void setAnamEg(Integer anamEg) {
        this.anamEg = anamEg;
    }

    public Integer getAnamIngresopor() {
        return anamIngresopor;
    }

    public void setAnamIngresopor(Integer anamIngresopor) {
        this.anamIngresopor = anamIngresopor;
    }

    public Integer getAnamGestas() {
        return anamGestas;
    }

    public void setAnamGestas(Integer anamGestas) {
        this.anamGestas = anamGestas;
    }

    public Integer getAnamPartos() {
        return anamPartos;
    }

    public void setAnamPartos(Integer anamPartos) {
        this.anamPartos = anamPartos;
    }

    public Integer getAnamVaginales() {
        return anamVaginales;
    }

    public void setAnamVaginales(Integer anamVaginales) {
        this.anamVaginales = anamVaginales;
    }

    public Integer getAnamCesareas() {
        return anamCesareas;
    }

    public void setAnamCesareas(Integer anamCesareas) {
        this.anamCesareas = anamCesareas;
    }

    public Integer getAnamAbortos() {
        return anamAbortos;
    }

    public void setAnamAbortos(Integer anamAbortos) {
        this.anamAbortos = anamAbortos;
    }

    public Integer getAnamMenorpeso() {
        return anamMenorpeso;
    }

    public void setAnamMenorpeso(Integer anamMenorpeso) {
        this.anamMenorpeso = anamMenorpeso;
    }

    public Integer getAnamMayorpeso() {
        return anamMayorpeso;
    }

    public void setAnamMayorpeso(Integer anamMayorpeso) {
        this.anamMayorpeso = anamMayorpeso;
    }

    public Integer getAnamPulso() {
        return anamPulso;
    }

    public void setAnamPulso(Integer anamPulso) {
        this.anamPulso = anamPulso;
    }

    public Integer getAnamPa() {
        return anamPa;
    }

    public void setAnamPa(Integer anamPa) {
        this.anamPa = anamPa;
    }

    public Integer getAnamAu() {
        return anamAu;
    }

    public void setAnamAu(Integer anamAu) {
        this.anamAu = anamAu;
    }

    public Integer getAnamLcf() {
        return anamLcf;
    }

    public void setAnamLcf(Integer anamLcf) {
        this.anamLcf = anamLcf;
    }

    public Integer getAnamDilatacion() {
        return anamDilatacion;
    }

    public void setAnamDilatacion(Integer anamDilatacion) {
        this.anamDilatacion = anamDilatacion;
    }

    public Double getAnamTemperatura() {
        return anamTemperatura;
    }

    public void setAnamTemperatura(Double anamTemperatura) {
        this.anamTemperatura = anamTemperatura;
    }

    public Double getAnamPeso() {
        return anamPeso;
    }

    public void setAnamPeso(Double anamPeso) {
        this.anamPeso = anamPeso;
    }

    public Double getAnamTalla() {
        return anamTalla;
    }

    public void setAnamTalla(Double anamTalla) {
        this.anamTalla = anamTalla;
    }

    public String getAnamDiagnostico() {
        return anamDiagnostico;
    }

    public void setAnamDiagnostico(String anamDiagnostico) {
        this.anamDiagnostico = anamDiagnostico;
    }

    public String getAnamIndicaciones() {
        return anamIndicaciones;
    }

    public void setAnamIndicaciones(String anamIndicaciones) {
        this.anamIndicaciones = anamIndicaciones;
    }

    public Paciente getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Paciente idPaciente) {
        this.idPaciente = idPaciente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAnamnesis != null ? idAnamnesis.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Anamnesis)) {
            return false;
        }
        Anamnesis other = (Anamnesis) object;
        if ((this.idAnamnesis == null && other.idAnamnesis != null) || (this.idAnamnesis != null && !this.idAnamnesis.equals(other.idAnamnesis))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.RCE.www.entities.Anamnesis[ idAnamnesis=" + idAnamnesis + " ]";
    }
    
}
