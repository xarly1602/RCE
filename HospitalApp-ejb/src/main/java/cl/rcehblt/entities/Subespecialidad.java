/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.rcehblt.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DevelUser
 */
@Entity
@Table(name = "subespecialidad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Subespecialidad.findAll", query = "SELECT s FROM Subespecialidad s"),
    @NamedQuery(name = "Subespecialidad.findByIdSubespecialidad", query = "SELECT s FROM Subespecialidad s WHERE s.idSubespecialidad = :idSubespecialidad"),
    @NamedQuery(name = "Subespecialidad.findBySubespeNombre", query = "SELECT s FROM Subespecialidad s WHERE s.subespeNombre = :subespeNombre"),
    @NamedQuery(name = "Subespecialidad.findByIdEspecialidad", query = "SELECT s FROM Subespecialidad s WHERE s.idEspecialidad.idEspecialidad = :especialidadId"),
    @NamedQuery(name = "Subespecialidad.findBySubespeActivo", query = "SELECT s FROM Subespecialidad s WHERE s.subespeActivo = :subespeActivo")})
public class Subespecialidad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_subespecialidad")
    private Integer idSubespecialidad;
    @Size(max = 256)
    @Column(name = "subespe_nombre")
    private String subespeNombre;
    @Column(name = "subespe_activo")
    private Boolean subespeActivo;
    @JoinColumn(name = "id_especialidad", referencedColumnName = "id_especialidad")
    @ManyToOne
    private Especialidad idEspecialidad;
    @OneToMany(mappedBy = "idSubespecialidad")
    private Collection<Profesional> profesionalCollection;

    public Subespecialidad() {
    }

    public Subespecialidad(Integer idSubespecialidad) {
        this.idSubespecialidad = idSubespecialidad;
    }

    public Integer getIdSubespecialidad() {
        return idSubespecialidad;
    }

    public void setIdSubespecialidad(Integer idSubespecialidad) {
        this.idSubespecialidad = idSubespecialidad;
    }

    public String getSubespeNombre() {
        return subespeNombre;
    }

    public void setSubespeNombre(String subespeNombre) {
        this.subespeNombre = subespeNombre;
    }

    public Boolean getSubespeActivo() {
        return subespeActivo;
    }

    public void setSubespeActivo(Boolean subespeActivo) {
        this.subespeActivo = subespeActivo;
    }

    public Especialidad getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(Especialidad idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
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
        hash += (idSubespecialidad != null ? idSubespecialidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subespecialidad)) {
            return false;
        }
        Subespecialidad other = (Subespecialidad) object;
        if ((this.idSubespecialidad == null && other.idSubespecialidad != null) || (this.idSubespecialidad != null && !this.idSubespecialidad.equals(other.idSubespecialidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.RCE.www.entities.Subespecialidad[ idSubespecialidad=" + idSubespecialidad + " ]";
    }
    
}
