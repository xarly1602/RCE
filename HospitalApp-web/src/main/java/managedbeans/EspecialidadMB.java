/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import cl.RCE.www.subespecialidad.SubespecialidadNegocioLocal;
import cl.RCE.www.entities.Especialidad;
import cl.RCE.www.entities.Subespecialidad;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import cl.RCE.www.sessionbeans.EspecialidadFacadeLocal;
import cl.RCE.www.sessionbeans.SubespecialidadFacadeLocal;

/**
 *
 * @author DevelUser
 */
@ManagedBean
@SessionScoped
public class EspecialidadMB {

    @EJB
    private SubespecialidadFacadeLocal subespecialidadFacade;
    @EJB
    private SubespecialidadNegocioLocal subespecialidadNegocio;
    @EJB
    private EspecialidadFacadeLocal especialidadFacade;

    private String espeNombre;
    private int espeId;

    private Especialidad especialidad;
    private List<Subespecialidad> listaSubespecialidades;

    /**
     * Creates a new instance of EspecialidadMB
     */
    public EspecialidadMB() {
    }

    @PostConstruct
    public void init() {
        especialidad = new Especialidad();
    }

    public void nuevaEspecialidad(ActionEvent actionEvent) {
        if (Integer.toString(espeId).length() != 5) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Largo codigo inválido", "El código debe tener largo 5"));
        } else if (especialidadFacade.find(espeId) != null) {
            if(especialidadFacade.find(espeId).getEspeActivo())
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención.", "El código ingresado ya existe."));
            else{
                especialidad = especialidadFacade.find(espeId);
                especialidad.setEspeActivo(Boolean.TRUE);
                especialidad.setEspeNombre(espeNombre);
                especialidadFacade.edit(especialidad);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Acción realizada.", "Especialidad: " + espeNombre + " agregada con éxito."));
                return;
            }
        }
        if (espeNombre.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención.", "Debe ingresar un nombre de especialidad."));
        } else if (Integer.toString(espeId).length() == 5 && !espeNombre.isEmpty()) {
            especialidad = new Especialidad();
            especialidad.setIdEspecialidad(espeId);
            especialidad.setEspeNombre(espeNombre);
            especialidad.setEspeActivo(Boolean.TRUE);
            especialidadFacade.create(especialidad);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Acción realizada.", "Especialidad: " + espeNombre + " agregada con éxito."));
            this.resetData();
        }
    }

    public void eliminar() {
        listaSubespecialidades = subespecialidadNegocio.busquedaSubespecialidadIdEspecialidad(especialidad.getIdEspecialidad());
        for (Subespecialidad subespecialidad : listaSubespecialidades) {
            subespecialidad.setSubespeActivo(Boolean.FALSE);
            subespecialidadFacade.edit(subespecialidad);
        }
        especialidad.setEspeActivo(Boolean.FALSE);
        especialidadFacade.edit(especialidad);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Especialidad eliminada.", "Se han borrado también todas las subespecialidades asociadas."));
        this.resetData();
    }

    public void editar(ActionEvent actionEvent) {
        try {
            System.out.println("Editar: " + especialidad.getEspeNombre());
            if(especialidad.getEspeNombre().isEmpty()){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Atención.", "Debe indicar un nombre de especialidad."));
            }
            especialidadFacade.edit(especialidad);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Acción realizada.", "Especialidad: " + espeNombre + " editada con éxito."));
        } catch (Exception e) {
        }
        this.resetData();
    }

    public void resetData(){
        espeNombre = "";
        espeId = 0;
    }
    
    public String getEspeNombre() {
        return espeNombre;
    }

    public void setEspeNombre(String espeNombre) {
        this.espeNombre = espeNombre;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public int getEspeId() {
        return espeId;
    }

    public void setEspeId(int espeId) {
        this.espeId = espeId;
    }

}
