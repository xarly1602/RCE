/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import cl.rcehblt.entities.Especialidad;
import cl.rcehblt.entities.Subespecialidad;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import cl.rcehblt.sessionbeans.SubespecialidadFacadeLocal;

/**
 *
 * @author DevelUser
 */
@ManagedBean
@SessionScoped
public class SubespecialidadMB {

    @EJB
    private SubespecialidadFacadeLocal subespecialidadFacade;
    private List<Subespecialidad> listaSubespecialidad;
    private final List<String> subespecialidades;
    private Subespecialidad subespecialidad;
    private String subespeNombre;
    private int subespecialidadId;
    private int especialidadId;
    private Subespecialidad subespecialidadSeleccionada;
    private int especialidadSeleccionadaId;
    private int subespecialidadSeleccionadaId;

    /**
     * Constructor de la clase.
     */
    public SubespecialidadMB() {
        subespecialidades = new ArrayList<String>();
    }

    /**
     * Postconstructor:
     * Inicializar variables.
     */
    @PostConstruct
    public void init() {
        subespecialidad = new Subespecialidad();
        subespecialidadSeleccionada = new Subespecialidad();
        listaSubespecialidad = subespecialidadFacade.findAll();
        for (int i = 0; i < listaSubespecialidad.size(); i++) {
            if (!listaSubespecialidad.get(i).getSubespeActivo()) {
                listaSubespecialidad.remove(i);
            }
        }
        for (Subespecialidad subespecialidad1 : listaSubespecialidad) {
            subespecialidades.add(subespecialidad1.getSubespeNombre());
        }
    }

    /**
     * Agregar una nueva subespecialidad al sistema.
     * Se genera una nueva subespecialidad con los datos ingresados en la vista
     * correspondiente.
     * @param actionEvent Evento en la página xhtml que acciona la función.
     */
    public void nuevaSubespecialidad(ActionEvent actionEvent) {
        if (Integer.toString(subespecialidadId).length() != 5) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Largo codigo inválido", "El código debe tener largo 5"));
        }
        if (subespeNombre.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Nombre no indicado", "Debe ingresar un nombre"));
        }
        if (especialidadId == 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Especialidad inválida", "Debe seleccionar una especialidad"));
        } else if (especialidadId != 0 && !subespeNombre.isEmpty() && Integer.toString(subespecialidadId).length() == 5) {
            subespecialidad = subespecialidadFacade.find(subespecialidadId);
            if (subespecialidad == null) {
                subespecialidad = new Subespecialidad();
                subespecialidad.setSubespeActivo(Boolean.TRUE);
                subespecialidad.setSubespeNombre(subespeNombre);
                subespecialidad.setIdSubespecialidad(subespecialidadId);
                subespecialidad.setIdEspecialidad(new Especialidad(especialidadId));
                try {
                    subespecialidadFacade.create(subespecialidad);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Subespecialidad " + subespeNombre + " agregada exitosamente"));
                } catch (Exception e) {
                    subespecialidadFacade.edit(subespecialidad);
                }
            } else {
                if (subespecialidad.getSubespeActivo()) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("El código ingresado ya está registrado."));
                } else {
                    subespecialidad.setSubespeActivo(Boolean.TRUE);
                    subespecialidad.setSubespeNombre(subespeNombre);
                    subespecialidad.setIdEspecialidad(new Especialidad(especialidadId));
                    subespecialidadFacade.edit(subespecialidad);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Subespecialidad " + subespeNombre + " agregada exitosamente"));
                }
            }

            this.resetData();
        }
    }

    /**
     * Setear parámetros de acuerdo a la subespecialidad seleccionada.
     */
    public void seleccionEditar() {
        try {
            subespeNombre = subespecialidad.getSubespeNombre();
            especialidadId = subespecialidad.getIdEspecialidad().getIdEspecialidad();
        } catch (Exception e) {
        }
    }

    /**
     * Eliminar la subespecialidad seleccionada.
     */
    public void eliminar() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Subespecialidad " + subespeNombre + " eliminada exitosamente"));
        subespecialidad.setSubespeActivo(Boolean.FALSE);
        subespecialidadFacade.edit(subespecialidad);
        this.resetData();
    }

    /**
     * Editar subespecialidad.
     * Se actualiza la especialidad seleccionada.
     * @param actionEvent Evento en la página xhtml que acciona la función.
     */
    public void editar(ActionEvent actionEvent) {
        if (subespecialidadSeleccionada.getSubespeNombre().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Nombre no indicado", "Debe ingresar un nombre"));
        }
        if (subespecialidadSeleccionadaId == 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Especialidad inválida", "Debe seleccionar una especialidad"));
        } else if (!subespecialidadSeleccionada.getSubespeNombre().isEmpty() && subespecialidadSeleccionadaId != 0) {
            subespecialidadSeleccionada.setIdEspecialidad(new Especialidad(especialidadSeleccionadaId));
            subespecialidadSeleccionada.setIdSubespecialidad(subespecialidadSeleccionadaId);
            subespecialidadFacade.edit(subespecialidadSeleccionada);
            System.out.println("EDITADA");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Subespecialidad " + subespeNombre + " editada exitosamente"));
        }
        this.resetData();
    }

    /**
     * Reiniciar variables utilizadas.
     */
    private void resetData() {
        subespeNombre = "";
        especialidadId = 0;
        subespecialidadId = 0;
    }

    // Getters y Setters.
    public int getSubespecialidadId() {
        return subespecialidadId;
    }

    public void setSubespecialidadId(int subespecialidadId) {
        this.subespecialidadId = subespecialidadId;
    }

    public Subespecialidad getSubespecialidad() {
        return subespecialidad;
    }

    public void setSubespecialidad(Subespecialidad subespecialidad) {
        this.subespecialidad = subespecialidad;
        System.out.println("Espec: " + subespecialidad.getSubespeNombre());
        try {
            subespecialidadId = subespecialidad.getIdSubespecialidad();
            subespeNombre = subespecialidad.getSubespeNombre();
            especialidadId = subespecialidad.getIdEspecialidad().getIdEspecialidad();
        } catch (Exception e) {
        }
    }

    public String getSubespeNombre() {
        return subespeNombre;
    }

    public void setSubespeNombre(String subespeNombre) {
        System.out.println(subespeNombre);
        this.subespeNombre = subespeNombre;

    }

    public int getEspecialidadId() {
        return especialidadId;
    }

    public void setEspecialidadId(int especialidadId) {
        this.especialidadId = especialidadId;
    }

    public Subespecialidad getSubespecialidadSeleccionada() {
        return subespecialidadSeleccionada;
    }

    public void setSubespecialidadSeleccionada(Subespecialidad subespecialidadSeleccionada) {
        System.out.println("Seleccionada: " + subespecialidadSeleccionada);
        this.subespecialidadSeleccionada = subespecialidadSeleccionada;
        this.subespecialidadSeleccionadaId = subespecialidadSeleccionada.getIdSubespecialidad();
        System.out.println("--->" + subespecialidadSeleccionadaId);
        this.especialidadSeleccionadaId = subespecialidadSeleccionada.getIdEspecialidad().getIdEspecialidad();
    }

    public int getEspecialidadSeleccionadaId() {
        return especialidadSeleccionadaId;
    }

    public void setEspecialidadSeleccionadaId(int especialidadSeleccionadaId) {
        this.especialidadSeleccionadaId = especialidadSeleccionadaId;
    }

    public int getSubespecialidadSeleccionadaId() {
        return subespecialidadSeleccionadaId;
    }

    public void setSubespecialidadSeleccionadaId(int subespecialidadSeleccionadaId) {
        this.subespecialidadSeleccionadaId = subespecialidadSeleccionadaId;
    }

}
