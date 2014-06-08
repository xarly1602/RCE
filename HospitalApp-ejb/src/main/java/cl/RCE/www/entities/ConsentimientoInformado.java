/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.RCE.www.entities;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DevelUser
 */
@Entity
@Table(name = "consentimiento_informado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConsentimientoInformado.findAll", query = "SELECT c FROM ConsentimientoInformado c"),
    @NamedQuery(name = "ConsentimientoInformado.findByIdConsentimiento", query = "SELECT c FROM ConsentimientoInformado c WHERE c.idConsentimiento = :idConsentimiento"),
    @NamedQuery(name = "ConsentimientoInformado.findByConsentEstado", query = "SELECT c FROM ConsentimientoInformado c WHERE c.consentEstado = :consentEstado"),
    @NamedQuery(name = "ConsentimientoInformado.findByConsentNombreresponsable", query = "SELECT c FROM ConsentimientoInformado c WHERE c.consentNombreresponsable = :consentNombreresponsable"),
    @NamedQuery(name = "ConsentimientoInformado.findByConsentRutresponsable", query = "SELECT c FROM ConsentimientoInformado c WHERE c.consentRutresponsable = :consentRutresponsable"),
    @NamedQuery(name = "ConsentimientoInformado.findByConsentTipo", query = "SELECT c FROM ConsentimientoInformado c WHERE c.consentTipo = :consentTipo"),
    @NamedQuery(name = "ConsentimientoInformado.findByConsentTexto", query = "SELECT c FROM ConsentimientoInformado c WHERE c.consentTexto = :consentTexto"),
    @NamedQuery(name = "ConsentimientoInformado.findByConsentExamen", query = "SELECT c FROM ConsentimientoInformado c WHERE c.consentExamen = :consentExamen")})
public class ConsentimientoInformado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_consentimiento")
    private Integer idConsentimiento;
    @Size(max = 20)
    @Column(name = "consent_estado")
    private String consentEstado;
    @Size(max = 50)
    @Column(name = "consent_nombreresponsable")
    private String consentNombreresponsable;
    @Size(max = 15)
    @Column(name = "consent_rutresponsable")
    private String consentRutresponsable;
    @Size(max = 20)
    @Column(name = "consent_tipo")
    private String consentTipo;
    @Size(max = 2147483647)
    @Column(name = "consent_texto")
    private String consentTexto;
    @Size(max = 2147483647)
    @Column(name = "consent_examen")
    private String consentExamen;
    @JoinColumn(name = "id_profesional", referencedColumnName = "id_profesional")
    @ManyToOne
    private Profesional idProfesional;
    @JoinColumn(name = "id_paciente", referencedColumnName = "id_paciente")
    @ManyToOne
    private Paciente idPaciente;

    public ConsentimientoInformado() {
    }

    public ConsentimientoInformado(Integer idConsentimiento) {
        this.idConsentimiento = idConsentimiento;
    }

    public Integer getIdConsentimiento() {
        return idConsentimiento;
    }

    public void setIdConsentimiento(Integer idConsentimiento) {
        this.idConsentimiento = idConsentimiento;
    }

    public String getConsentEstado() {
        return consentEstado;
    }

    public void setConsentEstado(String consentEstado) {
        this.consentEstado = consentEstado;
    }

    public String getConsentNombreresponsable() {
        return consentNombreresponsable;
    }

    public void setConsentNombreresponsable(String consentNombreresponsable) {
        this.consentNombreresponsable = consentNombreresponsable;
    }

    public String getConsentRutresponsable() {
        return consentRutresponsable;
    }

    public void setConsentRutresponsable(String consentRutresponsable) {
        this.consentRutresponsable = consentRutresponsable;
    }

    public String getConsentTipo() {
        return consentTipo;
    }

    public void setConsentTipo(String consentTipo) {
        this.consentTipo = consentTipo;
    }

    public String getConsentTexto() {
        return consentTexto;
    }

    public void setConsentTexto(String consentTexto) {
        this.consentTexto = consentTexto;
    }

    public String getConsentExamen() {
        return consentExamen;
    }

    public void setConsentExamen(String consentExamen) {
        this.consentExamen = consentExamen;
    }

    public Profesional getIdProfesional() {
        return idProfesional;
    }

    public void setIdProfesional(Profesional idProfesional) {
        this.idProfesional = idProfesional;
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
        hash += (idConsentimiento != null ? idConsentimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsentimientoInformado)) {
            return false;
        }
        ConsentimientoInformado other = (ConsentimientoInformado) object;
        if ((this.idConsentimiento == null && other.idConsentimiento != null) || (this.idConsentimiento != null && !this.idConsentimiento.equals(other.idConsentimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.RCE.www.entities.ConsentimientoInformado[ idConsentimiento=" + idConsentimiento + " ]";
    }
    
}
