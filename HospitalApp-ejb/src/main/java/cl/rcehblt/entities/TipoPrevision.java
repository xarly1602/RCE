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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "tipo_prevision")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoPrevision.findAll", query = "SELECT t FROM TipoPrevision t"),
    @NamedQuery(name = "TipoPrevision.findByIdTipoprevision", query = "SELECT t FROM TipoPrevision t WHERE t.idTipoprevision = :idTipoprevision"),
    @NamedQuery(name = "TipoPrevision.findByIdPrevision", query = "SELECT t FROM TipoPrevision t WHERE t.idPrevision.idPrevision = :idPrevision"),
    @NamedQuery(name = "TipoPrevision.findByClasNombre", query = "SELECT t FROM TipoPrevision t WHERE t.clasNombre = :clasNombre")})
public class TipoPrevision implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipoprevision")
    private Integer idTipoprevision;
    @Size(max = 20)
    @Column(name = "clas_nombre")
    private String clasNombre;
    @OneToMany(mappedBy = "idTipoprevision")
    private Collection<Paciente> pacienteCollection;
    @JoinColumn(name = "id_prevision", referencedColumnName = "id_prevision")
    @ManyToOne
    private Prevision idPrevision;

    public TipoPrevision() {
    }

    public TipoPrevision(Integer idTipoprevision) {
        this.idTipoprevision = idTipoprevision;
    }

    public Integer getIdTipoprevision() {
        return idTipoprevision;
    }

    public void setIdTipoprevision(Integer idTipoprevision) {
        this.idTipoprevision = idTipoprevision;
    }

    public String getClasNombre() {
        return clasNombre;
    }

    public void setClasNombre(String clasNombre) {
        this.clasNombre = clasNombre;
    }

    @XmlTransient
    public Collection<Paciente> getPacienteCollection() {
        return pacienteCollection;
    }

    public void setPacienteCollection(Collection<Paciente> pacienteCollection) {
        this.pacienteCollection = pacienteCollection;
    }

    public Prevision getIdPrevision() {
        return idPrevision;
    }

    public void setIdPrevision(Prevision idPrevision) {
        this.idPrevision = idPrevision;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoprevision != null ? idTipoprevision.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoPrevision)) {
            return false;
        }
        TipoPrevision other = (TipoPrevision) object;
        if ((this.idTipoprevision == null && other.idTipoprevision != null) || (this.idTipoprevision != null && !this.idTipoprevision.equals(other.idTipoprevision))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.RCE.www.entities.TipoPrevision[ idTipoprevision=" + idTipoprevision + " ]";
    }
    
}
