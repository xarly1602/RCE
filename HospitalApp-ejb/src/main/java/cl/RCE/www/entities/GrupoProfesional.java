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
@Table(name = "grupo_profesional")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GrupoProfesional.findAll", query = "SELECT g FROM GrupoProfesional g"),
    @NamedQuery(name = "GrupoProfesional.findByIdGrupoprofesional", query = "SELECT g FROM GrupoProfesional g WHERE g.idGrupoprofesional = :idGrupoprofesional"),
    @NamedQuery(name = "GrupoProfesional.findByGrupoNombre", query = "SELECT g FROM GrupoProfesional g WHERE g.grupoNombre = :grupoNombre")})
public class GrupoProfesional implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_grupoprofesional")
    private Integer idGrupoprofesional;
    @Size(max = 50)
    @Column(name = "grupo_nombre")
    private String grupoNombre;
    @OneToMany(mappedBy = "idGrupoprofesional")
    private Collection<Profesional> profesionalCollection;

    public GrupoProfesional() {
    }

    public GrupoProfesional(Integer idGrupoprofesional) {
        this.idGrupoprofesional = idGrupoprofesional;
    }

    public Integer getIdGrupoprofesional() {
        return idGrupoprofesional;
    }

    public void setIdGrupoprofesional(Integer idGrupoprofesional) {
        this.idGrupoprofesional = idGrupoprofesional;
    }

    public String getGrupoNombre() {
        return grupoNombre;
    }

    public void setGrupoNombre(String grupoNombre) {
        this.grupoNombre = grupoNombre;
    }

    @XmlTransient
    public Collection<Profesional> getProfesionalCollection() {
        return profesionalCollection;
    }

    public void setProfesionalCollection(Collection<Profesional> profesionalCollection) {
        this.profesionalCollection = profesionalCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGrupoprofesional != null ? idGrupoprofesional.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrupoProfesional)) {
            return false;
        }
        GrupoProfesional other = (GrupoProfesional) object;
        if ((this.idGrupoprofesional == null && other.idGrupoprofesional != null) || (this.idGrupoprofesional != null && !this.idGrupoprofesional.equals(other.idGrupoprofesional))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.RCE.www.entities.GrupoProfesional[ idGrupoprofesional=" + idGrupoprofesional + " ]";
    }
    
}
