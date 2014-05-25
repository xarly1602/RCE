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
@Table(name = "leyes_sociales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LeyesSociales.findAll", query = "SELECT l FROM LeyesSociales l"),
    @NamedQuery(name = "LeyesSociales.findByIdLeyessociales", query = "SELECT l FROM LeyesSociales l WHERE l.idLeyessociales = :idLeyessociales"),
    @NamedQuery(name = "LeyesSociales.findByLeyesNombre", query = "SELECT l FROM LeyesSociales l WHERE l.leyesNombre = :leyesNombre")})
public class LeyesSociales implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_leyessociales")
    private Integer idLeyessociales;
    @Size(max = 256)
    @Column(name = "leyes_nombre")
    private String leyesNombre;
    @OneToMany(mappedBy = "idLeyessociales")
    private Collection<Paciente> pacienteCollection;

    public LeyesSociales() {
    }

    public LeyesSociales(Integer idLeyessociales) {
        this.idLeyessociales = idLeyessociales;
    }

    public Integer getIdLeyessociales() {
        return idLeyessociales;
    }

    public void setIdLeyessociales(Integer idLeyessociales) {
        this.idLeyessociales = idLeyessociales;
    }

    public String getLeyesNombre() {
        return leyesNombre;
    }

    public void setLeyesNombre(String leyesNombre) {
        this.leyesNombre = leyesNombre;
    }

    @XmlTransient
    public Collection<Paciente> getPacienteCollection() {
        return pacienteCollection;
    }

    public void setPacienteCollection(Collection<Paciente> pacienteCollection) {
        this.pacienteCollection = pacienteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLeyessociales != null ? idLeyessociales.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LeyesSociales)) {
            return false;
        }
        LeyesSociales other = (LeyesSociales) object;
        if ((this.idLeyessociales == null && other.idLeyessociales != null) || (this.idLeyessociales != null && !this.idLeyessociales.equals(other.idLeyessociales))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.RCE.www.entities.LeyesSociales[ idLeyessociales=" + idLeyessociales + " ]";
    }
    
}
