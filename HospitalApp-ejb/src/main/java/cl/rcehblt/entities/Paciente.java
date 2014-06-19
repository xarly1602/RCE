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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DevelUser
 */
@Entity
@Table(name = "paciente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paciente.findAll", query = "SELECT p FROM Paciente p"),
    @NamedQuery(name = "Paciente.findByIdPaciente", query = "SELECT p FROM Paciente p WHERE p.idPaciente = :idPaciente"),
    @NamedQuery(name = "Paciente.findByPaciNficha", query = "SELECT p FROM Paciente p WHERE p.paciNficha = :paciNficha"),
    @NamedQuery(name = "Paciente.findByPaciFfallecimiento", query = "SELECT p FROM Paciente p WHERE p.paciFfallecimiento = :paciFfallecimiento"),
    @NamedQuery(name = "Paciente.findByPaciFallecido", query = "SELECT p FROM Paciente p WHERE p.paciFallecido = :paciFallecido"),
    @NamedQuery(name = "Paciente.findByIdPersona", query = "SELECT p FROM Paciente p WHERE p.idPersona.idPersona = :idPersona"),
    @NamedQuery(name = "Paciente.findByPaciOtraprevision", query = "SELECT p FROM Paciente p WHERE p.paciOtraprevision = :paciOtraprevision")})
public class Paciente implements Serializable {
    @OneToMany(mappedBy = "idPaciente")
    private Collection<ConsentimientoInformado> consentimientoInformadoCollection;
    @OneToMany(mappedBy = "idPaciente")
    private Collection<Anamnesis> anamnesisCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_paciente")
    private Integer idPaciente;
    @Size(max = 20)
    @Column(name = "paci_nficha")
    private String paciNficha;
    @Column(name = "paci_ffallecimiento")
    @Temporal(TemporalType.DATE)
    private Date paciFfallecimiento;
    @Column(name = "paci_fallecido")
    private Boolean paciFallecido;
    @Size(max = 50)
    @Column(name = "paci_otraprevision")
    private String paciOtraprevision;
    @JoinColumn(name = "id_tipoprevision", referencedColumnName = "id_tipoprevision")
    @ManyToOne
    private TipoPrevision idTipoprevision;
    @JoinColumn(name = "id_serviciosalud", referencedColumnName = "id_serviciosalud")
    @ManyToOne
    private ServicioSalud idServiciosalud;
    @JoinColumn(name = "id_sector", referencedColumnName = "id_sector")
    @ManyToOne
    private Sector idSector;
    @JoinColumn(name = "id_prevision", referencedColumnName = "id_prevision")
    @ManyToOne
    private Prevision idPrevision;
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne
    private Persona idPersona;
    @JoinColumn(name = "id_leyessociales", referencedColumnName = "id_leyessociales")
    @ManyToOne
    private LeyesSociales idLeyessociales;
    @JoinColumn(name = "id_establecimiento", referencedColumnName = "id_establecimiento")
    @ManyToOne
    private Establecimiento idEstablecimiento;
    @JoinColumn(name = "id_consultorio", referencedColumnName = "id_consultorio")
    @ManyToOne
    private Consultorio idConsultorio;

    public Paciente() {
    }

    public Paciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getPaciNficha() {
        return paciNficha;
    }

    public void setPaciNficha(String paciNficha) {
        this.paciNficha = paciNficha;
    }

    public Date getPaciFfallecimiento() {
        return paciFfallecimiento;
    }

    public void setPaciFfallecimiento(Date paciFfallecimiento) {
        this.paciFfallecimiento = paciFfallecimiento;
    }

    public Boolean getPaciFallecido() {
        return paciFallecido;
    }

    public void setPaciFallecido(Boolean paciFallecido) {
        this.paciFallecido = paciFallecido;
    }

    public String getPaciOtraprevision() {
        return paciOtraprevision;
    }

    public void setPaciOtraprevision(String paciOtraprevision) {
        this.paciOtraprevision = paciOtraprevision;
    }

    public TipoPrevision getIdTipoprevision() {
        return idTipoprevision;
    }

    public void setIdTipoprevision(TipoPrevision idTipoprevision) {
        this.idTipoprevision = idTipoprevision;
    }

    public ServicioSalud getIdServiciosalud() {
        return idServiciosalud;
    }

    public void setIdServiciosalud(ServicioSalud idServiciosalud) {
        this.idServiciosalud = idServiciosalud;
    }

    public Sector getIdSector() {
        return idSector;
    }

    public void setIdSector(Sector idSector) {
        this.idSector = idSector;
    }

    public Prevision getIdPrevision() {
        return idPrevision;
    }

    public void setIdPrevision(Prevision idPrevision) {
        this.idPrevision = idPrevision;
    }

    public Persona getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Persona idPersona) {
        this.idPersona = idPersona;
    }

    public LeyesSociales getIdLeyessociales() {
        return idLeyessociales;
    }

    public void setIdLeyessociales(LeyesSociales idLeyessociales) {
        this.idLeyessociales = idLeyessociales;
    }

    public Establecimiento getIdEstablecimiento() {
        return idEstablecimiento;
    }

    public void setIdEstablecimiento(Establecimiento idEstablecimiento) {
        this.idEstablecimiento = idEstablecimiento;
    }

    public Consultorio getIdConsultorio() {
        return idConsultorio;
    }

    public void setIdConsultorio(Consultorio idConsultorio) {
        this.idConsultorio = idConsultorio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPaciente != null ? idPaciente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paciente)) {
            return false;
        }
        Paciente other = (Paciente) object;
        if ((this.idPaciente == null && other.idPaciente != null) || (this.idPaciente != null && !this.idPaciente.equals(other.idPaciente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.RCE.www.entities.Paciente[ idPaciente=" + idPaciente + " ]";
    }

    @XmlTransient
    public Collection<ConsentimientoInformado> getConsentimientoInformadoCollection() {
        return consentimientoInformadoCollection;
    }

    public void setConsentimientoInformadoCollection(Collection<ConsentimientoInformado> consentimientoInformadoCollection) {
        this.consentimientoInformadoCollection = consentimientoInformadoCollection;
    }

    @XmlTransient
    public Collection<Anamnesis> getAnamnesisCollection() {
        return anamnesisCollection;
    }

    public void setAnamnesisCollection(Collection<Anamnesis> anamnesisCollection) {
        this.anamnesisCollection = anamnesisCollection;
    }

}
