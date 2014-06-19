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
@Table(name = "establecimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Establecimiento.findAll", query = "SELECT e FROM Establecimiento e"),
    @NamedQuery(name = "Establecimiento.findByIdEstablecimiento", query = "SELECT e FROM Establecimiento e WHERE e.idEstablecimiento = :idEstablecimiento"),
    @NamedQuery(name = "Establecimiento.findByEstCodigo", query = "SELECT e FROM Establecimiento e WHERE e.estCodigo = :estCodigo"),
    @NamedQuery(name = "Establecimiento.findByEstNombre", query = "SELECT e FROM Establecimiento e WHERE e.estNombre = :estNombre"),
    @NamedQuery(name = "Establecimiento.findByEstDireccion", query = "SELECT e FROM Establecimiento e WHERE e.estDireccion = :estDireccion"),
    @NamedQuery(name = "Establecimiento.findByEstTelefono", query = "SELECT e FROM Establecimiento e WHERE e.estTelefono = :estTelefono"),
    @NamedQuery(name = "Establecimiento.findByEstNombredepto", query = "SELECT e FROM Establecimiento e WHERE e.estNombredepto = :estNombredepto")})
public class Establecimiento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_establecimiento")
    private Integer idEstablecimiento;
    @Size(max = 10)
    @Column(name = "est_codigo")
    private String estCodigo;
    @Size(max = 128)
    @Column(name = "est_nombre")
    private String estNombre;
    @Size(max = 256)
    @Column(name = "est_direccion")
    private String estDireccion;
    @Size(max = 15)
    @Column(name = "est_telefono")
    private String estTelefono;
    @Size(max = 20)
    @Column(name = "est_nombredepto")
    private String estNombredepto;
    @OneToMany(mappedBy = "idEstablecimiento")
    private Collection<Paciente> pacienteCollection;
    @JoinColumn(name = "id_serviciosalud", referencedColumnName = "id_serviciosalud")
    @ManyToOne
    private ServicioSalud idServiciosalud;
    @JoinColumn(name = "id_comuna", referencedColumnName = "id_comuna")
    @ManyToOne
    private Comuna idComuna;

    public Establecimiento() {
    }

    public Establecimiento(Integer idEstablecimiento) {
        this.idEstablecimiento = idEstablecimiento;
    }

    public Integer getIdEstablecimiento() {
        return idEstablecimiento;
    }

    public void setIdEstablecimiento(Integer idEstablecimiento) {
        this.idEstablecimiento = idEstablecimiento;
    }

    public String getEstCodigo() {
        return estCodigo;
    }

    public void setEstCodigo(String estCodigo) {
        this.estCodigo = estCodigo;
    }

    public String getEstNombre() {
        return estNombre;
    }

    public void setEstNombre(String estNombre) {
        this.estNombre = estNombre;
    }

    public String getEstDireccion() {
        return estDireccion;
    }

    public void setEstDireccion(String estDireccion) {
        this.estDireccion = estDireccion;
    }

    public String getEstTelefono() {
        return estTelefono;
    }

    public void setEstTelefono(String estTelefono) {
        this.estTelefono = estTelefono;
    }

    public String getEstNombredepto() {
        return estNombredepto;
    }

    public void setEstNombredepto(String estNombredepto) {
        this.estNombredepto = estNombredepto;
    }

    @XmlTransient
    public Collection<Paciente> getPacienteCollection() {
        return pacienteCollection;
    }

    public void setPacienteCollection(Collection<Paciente> pacienteCollection) {
        this.pacienteCollection = pacienteCollection;
    }

    public ServicioSalud getIdServiciosalud() {
        return idServiciosalud;
    }

    public void setIdServiciosalud(ServicioSalud idServiciosalud) {
        this.idServiciosalud = idServiciosalud;
    }

    public Comuna getIdComuna() {
        return idComuna;
    }

    public void setIdComuna(Comuna idComuna) {
        this.idComuna = idComuna;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstablecimiento != null ? idEstablecimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Establecimiento)) {
            return false;
        }
        Establecimiento other = (Establecimiento) object;
        if ((this.idEstablecimiento == null && other.idEstablecimiento != null) || (this.idEstablecimiento != null && !this.idEstablecimiento.equals(other.idEstablecimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.RCE.www.entities.Establecimiento[ idEstablecimiento=" + idEstablecimiento + " ]";
    }
    
}
