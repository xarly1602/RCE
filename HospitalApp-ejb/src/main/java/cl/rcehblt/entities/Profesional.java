/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.rcehblt.entities;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DevelUser
 */
@Entity
@Table(name = "profesional")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Profesional.findAll", query = "SELECT p FROM Profesional p"),
    @NamedQuery(name = "Profesional.findByIdProfesional", query = "SELECT p FROM Profesional p WHERE p.idProfesional = :idProfesional"),
    @NamedQuery(name = "Profesional.findByProfActivo", query = "SELECT p FROM Profesional p WHERE p.profActivo = :profActivo"),
    @NamedQuery(name = "Profesional.findByProfFechadesde", query = "SELECT p FROM Profesional p WHERE p.profFechadesde = :profFechadesde"),
    @NamedQuery(name = "Profesional.findByProfeFechahasta", query = "SELECT p FROM Profesional p WHERE p.profeFechahasta = :profeFechahasta"),
    @NamedQuery(name = "Profesional.findByProfeSubespecialidad", query = "SELECT p FROM Profesional p WHERE p.idSubespecialidad.idSubespecialidad = :profeSubespecialidad"),
    @NamedQuery(name = "Profesional.findByProfeEspecialidad", query = "SELECT p FROM Profesional p WHERE p.idSubespecialidad.idEspecialidad.idEspecialidad = :profeEspecialidad"),
    @NamedQuery(name = "Profesional.findByIdPersona", query = "SELECT u FROM Profesional u WHERE u.idPersona.idPersona = :idPersona")    
})
public class Profesional implements Serializable {
    @OneToMany(mappedBy = "idProfesional")
    private Collection<ConsentimientoInformado> consentimientoInformadoCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_profesional")
    private Integer idProfesional;
    @Column(name = "prof_activo")
    private Boolean profActivo;
    @Column(name = "prof_fechadesde")
    @Temporal(TemporalType.DATE)
    private Date profFechadesde;
    @Column(name = "profe_fechahasta")
    @Temporal(TemporalType.DATE)
    private Date profeFechahasta;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario idUsuario;
    @JoinColumn(name = "id_subespecialidad", referencedColumnName = "id_subespecialidad")
    @ManyToOne
    private Subespecialidad idSubespecialidad;
    @OneToMany(mappedBy = "proIdProfesional")
    private Collection<Profesional> profesionalCollection;
    @JoinColumn(name = "pro_id_profesional", referencedColumnName = "id_profesional")
    @ManyToOne
    private Profesional proIdProfesional;
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne
    private Persona idPersona;
    @JoinColumn(name = "id_local", referencedColumnName = "id_local")
    @ManyToOne
    private Local idLocal;
    @JoinColumn(name = "id_grupoprofesional", referencedColumnName = "id_grupoprofesional")
    @ManyToOne
    private GrupoProfesional idGrupoprofesional;
    @JoinColumn(name = "id_cargo", referencedColumnName = "id_cargo")
    @ManyToOne
    private Cargo idCargo;

    public Profesional() {
    }

    public Profesional(Integer idProfesional) {
        this.idProfesional = idProfesional;
    }

    public Integer getIdProfesional() {
        return idProfesional;
    }

    public void setIdProfesional(Integer idProfesional) {
        this.idProfesional = idProfesional;
    }

    public Boolean getProfActivo() {
        return profActivo;
    }

    public void setProfActivo(Boolean profActivo) {
        this.profActivo = profActivo;
    }

    public Date getProfFechadesde() {
        return profFechadesde;
    }

    public void setProfFechadesde(Date profFechadesde) {
        this.profFechadesde = profFechadesde;
    }

    public Date getProfeFechahasta() {
        return profeFechahasta;
    }

    public void setProfeFechahasta(Date profeFechahasta) {
        this.profeFechahasta = profeFechahasta;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Subespecialidad getIdSubespecialidad() {
        return idSubespecialidad;
    }

    public void setIdSubespecialidad(Subespecialidad idSubespecialidad) {
        this.idSubespecialidad = idSubespecialidad;
    }

    @XmlTransient
    public Collection<Profesional> getProfesionalCollection() {
        return profesionalCollection;
    }

    public void setProfesionalCollection(Collection<Profesional> profesionalCollection) {
        this.profesionalCollection = profesionalCollection;
    }

    public Profesional getProIdProfesional() {
        return proIdProfesional;
    }

    public void setProIdProfesional(Profesional proIdProfesional) {
        this.proIdProfesional = proIdProfesional;
    }

    public Persona getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Persona idPersona) {
        this.idPersona = idPersona;
    }

    public Local getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(Local idLocal) {
        this.idLocal = idLocal;
    }

    public GrupoProfesional getIdGrupoprofesional() {
        return idGrupoprofesional;
    }

    public void setIdGrupoprofesional(GrupoProfesional idGrupoprofesional) {
        this.idGrupoprofesional = idGrupoprofesional;
    }

    public Cargo getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Cargo idCargo) {
        this.idCargo = idCargo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProfesional != null ? idProfesional.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profesional)) {
            return false;
        }
        Profesional other = (Profesional) object;
        if ((this.idProfesional == null && other.idProfesional != null) || (this.idProfesional != null && !this.idProfesional.equals(other.idProfesional))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Profesional{" + "idProfesional=" + idProfesional + ", profActivo=" + profActivo + ", profFechadesde=" + profFechadesde + ", profeFechahasta=" + profeFechahasta + ", idUsuario=" + idUsuario + ", idSubespecialidad=" + idSubespecialidad + ", proIdProfesional=" + proIdProfesional + ", idPersona=" + idPersona + ", idLocal=" + idLocal + ", idGrupoprofesional=" + idGrupoprofesional + ", idCargo=" + idCargo + '}';
    }

    @XmlTransient
    public Collection<ConsentimientoInformado> getConsentimientoInformadoCollection() {
        return consentimientoInformadoCollection;
    }

    public void setConsentimientoInformadoCollection(Collection<ConsentimientoInformado> consentimientoInformadoCollection) {
        this.consentimientoInformadoCollection = consentimientoInformadoCollection;
    }

   

   
    
}
