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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DevelUser
 */
@Entity
@Table(name = "log")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Log.findAll", query = "SELECT l FROM Log l"),
    @NamedQuery(name = "Log.findByIdLog", query = "SELECT l FROM Log l WHERE l.idLog = :idLog"),
    @NamedQuery(name = "Log.findByLogUsername", query = "SELECT l FROM Log l WHERE l.logUsername = :logUsername"),
    @NamedQuery(name = "Log.findByLogFaccion", query = "SELECT l FROM Log l WHERE l.logFaccion = :logFaccion"),
    @NamedQuery(name = "Log.findByLogAccion", query = "SELECT l FROM Log l WHERE l.logAccion = :logAccion"),
    @NamedQuery(name = "Log.findByLogNombretabla", query = "SELECT l FROM Log l WHERE l.logNombretabla = :logNombretabla"),
    @NamedQuery(name = "Log.findByLogIp", query = "SELECT l FROM Log l WHERE l.logIp = :logIp")})
public class Log implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_log")
    private Integer idLog;
    @Size(max = 50)
    @Column(name = "log_username")
    private String logUsername;
    @Column(name = "log_faccion")
    @Temporal(TemporalType.DATE)
    private Date logFaccion;
    @Size(max = 100)
    @Column(name = "log_accion")
    private String logAccion;
    @Size(max = 256)
    @Column(name = "log_nombretabla")
    private String logNombretabla;
    @Size(max = 15)
    @Column(name = "log_ip")
    private String logIp;
    @OneToMany(mappedBy = "idLog")
    private Collection<DetalleLog> detalleLogCollection;

    public Log() {
    }

    public Log(Integer idLog) {
        this.idLog = idLog;
    }

    public Integer getIdLog() {
        return idLog;
    }

    public void setIdLog(Integer idLog) {
        this.idLog = idLog;
    }

    public String getLogUsername() {
        return logUsername;
    }

    public void setLogUsername(String logUsername) {
        this.logUsername = logUsername;
    }

    public Date getLogFaccion() {
        return logFaccion;
    }

    public void setLogFaccion(Date logFaccion) {
        this.logFaccion = logFaccion;
    }

    public String getLogAccion() {
        return logAccion;
    }

    public void setLogAccion(String logAccion) {
        this.logAccion = logAccion;
    }

    public String getLogNombretabla() {
        return logNombretabla;
    }

    public void setLogNombretabla(String logNombretabla) {
        this.logNombretabla = logNombretabla;
    }

    public String getLogIp() {
        return logIp;
    }

    public void setLogIp(String logIp) {
        this.logIp = logIp;
    }

    @XmlTransient
    public Collection<DetalleLog> getDetalleLogCollection() {
        return detalleLogCollection;
    }

    public void setDetalleLogCollection(Collection<DetalleLog> detalleLogCollection) {
        this.detalleLogCollection = detalleLogCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLog != null ? idLog.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Log)) {
            return false;
        }
        Log other = (Log) object;
        if ((this.idLog == null && other.idLog != null) || (this.idLog != null && !this.idLog.equals(other.idLog))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.RCE.www.entities.Log[ idLog=" + idLog + " ]";
    }
    
}
