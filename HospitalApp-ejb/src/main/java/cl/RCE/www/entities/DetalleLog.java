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
@Table(name = "detalle_log")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleLog.findAll", query = "SELECT d FROM DetalleLog d"),
    @NamedQuery(name = "DetalleLog.findByIdDetallelog", query = "SELECT d FROM DetalleLog d WHERE d.idDetallelog = :idDetallelog"),
    @NamedQuery(name = "DetalleLog.findByDetaCampo", query = "SELECT d FROM DetalleLog d WHERE d.detaCampo = :detaCampo"),
    @NamedQuery(name = "DetalleLog.findByDetaValor", query = "SELECT d FROM DetalleLog d WHERE d.detaValor = :detaValor")})
public class DetalleLog implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_detallelog")
    private Integer idDetallelog;
    @Size(max = 256)
    @Column(name = "deta_campo")
    private String detaCampo;
    @Size(max = 5012)
    @Column(name = "deta_valor")
    private String detaValor;
    @JoinColumn(name = "id_log", referencedColumnName = "id_log")
    @ManyToOne
    private Log idLog;

    public DetalleLog() {
    }

    public DetalleLog(Integer idDetallelog) {
        this.idDetallelog = idDetallelog;
    }

    public Integer getIdDetallelog() {
        return idDetallelog;
    }

    public void setIdDetallelog(Integer idDetallelog) {
        this.idDetallelog = idDetallelog;
    }

    public String getDetaCampo() {
        return detaCampo;
    }

    public void setDetaCampo(String detaCampo) {
        this.detaCampo = detaCampo;
    }

    public String getDetaValor() {
        return detaValor;
    }

    public void setDetaValor(String detaValor) {
        this.detaValor = detaValor;
    }

    public Log getIdLog() {
        return idLog;
    }

    public void setIdLog(Log idLog) {
        this.idLog = idLog;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetallelog != null ? idDetallelog.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleLog)) {
            return false;
        }
        DetalleLog other = (DetalleLog) object;
        if ((this.idDetallelog == null && other.idDetallelog != null) || (this.idDetallelog != null && !this.idDetallelog.equals(other.idDetallelog))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.RCE.www.entities.DetalleLog[ idDetallelog=" + idDetallelog + " ]";
    }
    
}
