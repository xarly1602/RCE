/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cl.RCE.www.entities;

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
@Table(name = "persona")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p"),
    @NamedQuery(name = "Persona.findByIdPersona", query = "SELECT p FROM Persona p WHERE p.idPersona = :idPersona"),
    @NamedQuery(name = "Persona.findByPersRut", query = "SELECT p FROM Persona p WHERE p.persRut = :persRut"),
    @NamedQuery(name = "Persona.findByPersDv", query = "SELECT p FROM Persona p WHERE p.persDv = :persDv"),
    @NamedQuery(name = "Persona.findByPersNombres", query = "SELECT p FROM Persona p WHERE p.persNombres = :persNombres"),
    @NamedQuery(name = "Persona.findByPersApepaterno", query = "SELECT p FROM Persona p WHERE p.persApepaterno = :persApepaterno"),
    @NamedQuery(name = "Persona.findByPersApematerno", query = "SELECT p FROM Persona p WHERE p.persApematerno = :persApematerno"),
    @NamedQuery(name = "Persona.findByPersEmail", query = "SELECT p FROM Persona p WHERE p.persEmail = :persEmail"),
    @NamedQuery(name = "Persona.findByPersTelefono", query = "SELECT p FROM Persona p WHERE p.persTelefono = :persTelefono"),
    @NamedQuery(name = "Persona.findByPersCelular", query = "SELECT p FROM Persona p WHERE p.persCelular = :persCelular"),
    @NamedQuery(name = "Persona.findByPersTelcontacto", query = "SELECT p FROM Persona p WHERE p.persTelcontacto = :persTelcontacto"),
    @NamedQuery(name = "Persona.findByPersDireccion", query = "SELECT p FROM Persona p WHERE p.persDireccion = :persDireccion"),
    @NamedQuery(name = "Persona.findByPersNdepartamento", query = "SELECT p FROM Persona p WHERE p.persNdepartamento = :persNdepartamento"),
    @NamedQuery(name = "Persona.findByPersOcupacion", query = "SELECT p FROM Persona p WHERE p.persOcupacion = :persOcupacion"),
    @NamedQuery(name = "Persona.findByPersActividad", query = "SELECT p FROM Persona p WHERE p.persActividad = :persActividad"),
    @NamedQuery(name = "Persona.findByPersFnacimiento", query = "SELECT p FROM Persona p WHERE p.persFnacimiento = :persFnacimiento"),
    @NamedQuery(name = "Persona.findByPersNacionalidad", query = "SELECT p FROM Persona p WHERE p.persNacionalidad = :persNacionalidad"),
    @NamedQuery(name = "Persona.findByPersPasaporte", query = "SELECT p FROM Persona p WHERE p.persPasaporte = :persPasaporte"),
    @NamedQuery(name = "Persona.findByPersTipopersona", query = "SELECT p FROM Persona p WHERE p.persTipopersona = :persTipopersona")})
public class Persona implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_persona")
    private Integer idPersona;
    @Column(name = "pers_rut")
    private Integer persRut;
    @Size(max = 1)
    @Column(name = "pers_dv")
    private String persDv;
    @Size(max = 50)
    @Column(name = "pers_nombres")
    private String persNombres;
    @Size(max = 30)
    @Column(name = "pers_apepaterno")
    private String persApepaterno;
    @Size(max = 30)
    @Column(name = "pers_apematerno")
    private String persApematerno;
    @Size(max = 50)
    @Column(name = "pers_email")
    private String persEmail;
    @Size(max = 10)
    @Column(name = "pers_telefono")
    private String persTelefono;
    @Size(max = 15)
    @Column(name = "pers_celular")
    private String persCelular;
    @Size(max = 15)
    @Column(name = "pers_telcontacto")
    private String persTelcontacto;
    @Size(max = 100)
    @Column(name = "pers_direccion")
    private String persDireccion;
    @Size(max = 20)
    @Column(name = "pers_ndepartamento")
    private String persNdepartamento;
    @Size(max = 64)
    @Column(name = "pers_ocupacion")
    private String persOcupacion;
    @Size(max = 64)
    @Column(name = "pers_actividad")
    private String persActividad;
    @Column(name = "pers_fnacimiento")
    @Temporal(TemporalType.DATE)
    private Date persFnacimiento;
    @Size(max = 128)
    @Column(name = "pers_nacionalidad")
    private String persNacionalidad;
    @Column(name = "pers_pasaporte")
    private Integer persPasaporte;
    @Column(name = "pers_tipopersona")
    private Integer persTipopersona;
    @OneToMany(mappedBy = "idPersona")
    private Collection<Paciente> pacienteCollection;
    @OneToMany(mappedBy = "idPersona")
    private Collection<Profesional> profesionalCollection;
    @JoinColumn(name = "id_religion", referencedColumnName = "id_religion")
    @ManyToOne
    private Religion idReligion;
    @JoinColumn(name = "id_pueblooriginario", referencedColumnName = "id_pueblooriginario")
    @ManyToOne
    private PuebloOriginario idPueblooriginario;
    @JoinColumn(name = "id_genero", referencedColumnName = "id_genero")
    @ManyToOne
    private Genero idGenero;
    @JoinColumn(name = "id_estadoconyugal", referencedColumnName = "id_estadoconyugal")
    @ManyToOne
    private EstadoConyugal idEstadoconyugal;
    @JoinColumn(name = "id_educacion", referencedColumnName = "id_educacion")
    @ManyToOne
    private Educacion idEducacion;
    @JoinColumn(name = "id_comuna", referencedColumnName = "id_comuna")
    @ManyToOne
    private Comuna idComuna;

    public Persona() {
    }

    public Persona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public Integer getPersRut() {
        return persRut;
    }

    public void setPersRut(Integer persRut) {
        this.persRut = persRut;
    }

    public String getPersDv() {
        return persDv;
    }

    public void setPersDv(String persDv) {
        this.persDv = persDv;
    }

    public String getPersNombres() {
        return persNombres;
    }

    public void setPersNombres(String persNombres) {
        this.persNombres = persNombres;
    }

    public String getPersApepaterno() {
        return persApepaterno;
    }

    public void setPersApepaterno(String persApepaterno) {
        this.persApepaterno = persApepaterno;
    }

    public String getPersApematerno() {
        return persApematerno;
    }

    public void setPersApematerno(String persApematerno) {
        this.persApematerno = persApematerno;
    }

    public String getPersEmail() {
        return persEmail;
    }

    public void setPersEmail(String persEmail) {
        this.persEmail = persEmail;
    }

    public String getPersTelefono() {
        return persTelefono;
    }

    public void setPersTelefono(String persTelefono) {
        this.persTelefono = persTelefono;
    }

    public String getPersCelular() {
        return persCelular;
    }

    public void setPersCelular(String persCelular) {
        this.persCelular = persCelular;
    }

    public String getPersTelcontacto() {
        return persTelcontacto;
    }

    public void setPersTelcontacto(String persTelcontacto) {
        this.persTelcontacto = persTelcontacto;
    }

    public String getPersDireccion() {
        return persDireccion;
    }

    public void setPersDireccion(String persDireccion) {
        this.persDireccion = persDireccion;
    }

    public String getPersNdepartamento() {
        return persNdepartamento;
    }

    public void setPersNdepartamento(String persNdepartamento) {
        this.persNdepartamento = persNdepartamento;
    }

    public String getPersOcupacion() {
        return persOcupacion;
    }

    public void setPersOcupacion(String persOcupacion) {
        this.persOcupacion = persOcupacion;
    }

    public String getPersActividad() {
        return persActividad;
    }

    public void setPersActividad(String persActividad) {
        this.persActividad = persActividad;
    }

    public Date getPersFnacimiento() {
        return persFnacimiento;
    }

    public void setPersFnacimiento(Date persFnacimiento) {
        this.persFnacimiento = persFnacimiento;
    }

    public String getPersNacionalidad() {
        return persNacionalidad;
    }

    public void setPersNacionalidad(String persNacionalidad) {
        this.persNacionalidad = persNacionalidad;
    }

    public Integer getPersPasaporte() {
        return persPasaporte;
    }

    public void setPersPasaporte(Integer persPasaporte) {
        this.persPasaporte = persPasaporte;
    }

    public Integer getPersTipopersona() {
        return persTipopersona;
    }

    public void setPersTipopersona(Integer persTipopersona) {
        this.persTipopersona = persTipopersona;
    }

    @XmlTransient
    public Collection<Paciente> getPacienteCollection() {
        return pacienteCollection;
    }

    public void setPacienteCollection(Collection<Paciente> pacienteCollection) {
        this.pacienteCollection = pacienteCollection;
    }

    @XmlTransient
    public Collection<Profesional> getProfesionalCollection() {
        return profesionalCollection;
    }

    public void setProfesionalCollection(Collection<Profesional> profesionalCollection) {
        this.profesionalCollection = profesionalCollection;
    }

    public Religion getIdReligion() {
        return idReligion;
    }

    public void setIdReligion(Religion idReligion) {
        this.idReligion = idReligion;
    }

    public PuebloOriginario getIdPueblooriginario() {
        return idPueblooriginario;
    }

    public void setIdPueblooriginario(PuebloOriginario idPueblooriginario) {
        this.idPueblooriginario = idPueblooriginario;
    }

    public Genero getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(Genero idGenero) {
        this.idGenero = idGenero;
    }

    public EstadoConyugal getIdEstadoconyugal() {
        return idEstadoconyugal;
    }

    public void setIdEstadoconyugal(EstadoConyugal idEstadoconyugal) {
        this.idEstadoconyugal = idEstadoconyugal;
    }

    public Educacion getIdEducacion() {
        return idEducacion;
    }

    public void setIdEducacion(Educacion idEducacion) {
        this.idEducacion = idEducacion;
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
        hash += (idPersona != null ? idPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.idPersona == null && other.idPersona != null) || (this.idPersona != null && !this.idPersona.equals(other.idPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.RCE.www.entities.Persona[ idPersona=" + idPersona + " ]";
    }
    
}
