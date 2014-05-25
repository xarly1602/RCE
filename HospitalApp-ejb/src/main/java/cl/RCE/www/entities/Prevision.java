/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.RCE.www.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DevelUser
 */
@Entity
@Table(name = "prevision")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prevision.findAll", query = "SELECT p FROM Prevision p"),
    @NamedQuery(name = "Prevision.findByIdPrevision", query = "SELECT p FROM Prevision p WHERE p.idPrevision = :idPrevision"),
    @NamedQuery(name = "Prevision.findByPreviNombre", query = "SELECT p FROM Prevision p WHERE p.previNombre = :previNombre")})
public class Prevision implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_prevision")
    private Integer idPrevision;
    @Size(max = 50)
    @Column(name = "previ_nombre")
    private String previNombre;
    @OneToMany(mappedBy = "idPrevision")
    private Collection<Paciente> pacienteCollection;
    @OneToMany(mappedBy = "idPrevision")
    private Collection<TipoPrevision> tipoPrevisionCollection;

    public Prevision() {
    }

    public Prevision(Integer idPrevision) {
        this.idPrevision = idPrevision;
    }

    public Integer getIdPrevision() {
        return idPrevision;
    }

    public void setIdPrevision(Integer idPrevision) {
        this.idPrevision = idPrevision;
    }

    public String getPreviNombre() {
        return previNombre;
    }

    public void setPreviNombre(String previNombre) {
        this.previNombre = previNombre;
    }

    @XmlTransient
    public Collection<Paciente> getPacienteCollection() {
        return pacienteCollection;
    }

    public void setPacienteCollection(Collection<Paciente> pacienteCollection) {
        this.pacienteCollection = pacienteCollection;
    }

    @XmlTransient
    public Collection<TipoPrevision> getTipoPrevisionCollection() {
        return tipoPrevisionCollection;
    }

    public void setTipoPrevisionCollection(Collection<TipoPrevision> tipoPrevisionCollection) {
        this.tipoPrevisionCollection = tipoPrevisionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPrevision != null ? idPrevision.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prevision)) {
            return false;
        }
        Prevision other = (Prevision) object;
        if ((this.idPrevision == null && other.idPrevision != null) || (this.idPrevision != null && !this.idPrevision.equals(other.idPrevision))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.RCE.www.entities.Prevision[ idPrevision=" + idPrevision + " ]";
    }
    
}
