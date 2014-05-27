/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbeans;

import cl.RCE.www.entities.Paciente;
import cl.RCE.www.entities.Persona;
import cl.RCE.www.paciente.PacienteNegocioLocal;
import cl.RCE.www.persona.PersonaNegocioLocal;
import cl.RCE.www.sessionbeans.PacienteFacadeLocal;
import cl.RCE.www.sessionbeans.PersonaFacadeLocal;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author DevelUser
 */
@ManagedBean
@RequestScoped
public class busquedaMB {
    @EJB
    private PacienteFacadeLocal pacienteFacade;
    @EJB
    private PacienteNegocioLocal pacienteNegocio;
    @EJB
    private PersonaNegocioLocal personaNegocio;
    @EJB
    private PersonaFacadeLocal personaFacade;

    private String nombres;
    private String apePaterno;
    private String apeMaterno;
    private String rut;
    private String numeroFicha;
    private int sexo;
    private int etapa;
    private Date fechaNacimiento;
    
    private List<Persona> listaPersonas;
    private List<Paciente> listaPaciente;
    
    public busquedaMB() {
        nombres = "";
        apePaterno = "";
        apeMaterno = "";
        rut = "";
        numeroFicha = "";
        fechaNacimiento = null;
        etapa = 0;
    }

    public void buscar(){
        if (!rut.isEmpty()){
            System.out.println("busca Rut");
            rut = rut.toUpperCase();
            rut = rut.replace(".", "");
            try{
                int rutTemp = Integer.parseInt(rut);
                listaPersonas = personaNegocio.busquedaPersonaRut(rutTemp, 1);
                etapa = 1;
            }
            catch (NumberFormatException e){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Rut invalido", "Ingrese un rut válido"));
            }
        }
        else if (!nombres.isEmpty()){
            System.out.println("BuscaNombres");
            listaPersonas = personaNegocio.busquedaPersonaNombre(nombres, 1);
            etapa = 2;
        }
        else if (!apePaterno.isEmpty()){
            System.out.println("BuscaApellido");
            listaPersonas = personaNegocio.busquedaPersonaApellidoPaterno(apePaterno, 1);
            etapa = 3;
        }
        else if (!apeMaterno.isEmpty()){
            System.out.println("BuscaMaterno");
            listaPersonas = personaNegocio.busquedaPersonaApellidoPaterno(apeMaterno, 1);
            etapa = 4;
        }
        else if (!numeroFicha.isEmpty()){
            etapa = 5;
        }
        else if (sexo != 0){
            etapa = 6;
        }
        else if (fechaNacimiento != null){
            etapa ++;
        }
        switch (etapa){
            case 1:
                if (!nombres.isEmpty()) {
                System.out.println("1");
                    for (int i = listaPersonas.size()-1; i >= 0; i--) {
                        if (!listaPersonas.get(i).getPersNombres().startsWith(nombres)) {
                            listaPersonas.remove(i);
                        }
                    }
                }
            case 2: 
                if (!apePaterno.isEmpty()) {
                System.out.println("2");
                    for (int i = listaPersonas.size()-1; i >= 0; i--) {
                        if (!listaPersonas.get(i).getPersApepaterno().startsWith(apePaterno)) {
                            listaPersonas.remove(i);
                        }
                    }
                }
            case 3:
                if (!apeMaterno.isEmpty()) {
                System.out.println("3");
                    for (int i = listaPersonas.size()-1; i >= 0; i--) {
                        if (!listaPersonas.get(i).getPersApematerno().startsWith(apeMaterno)) {
                            listaPersonas.remove(i);
                        }
                    }
                }
            case 4:
                if (!numeroFicha.isEmpty()) {
                System.out.println("4");
                    System.out.println("holi");
                }
            case 5:
                if (sexo != 0) {
                System.out.println("5");
                    System.out.println("Holi");
                }
            case 6:
                if (fechaNacimiento != null) {
                System.out.println("6");
                    System.out.println("holi");
                }
            default:
                break;
        }
        for (Persona persona : listaPersonas) {
            System.out.println(persona.getPersNombres());
        }
    }
    
    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApePaterno() {
        return apePaterno;
    }

    public void setApePaterno(String apePaterno) {
        this.apePaterno = apePaterno;
    }

    public String getApeMaterno() {
        return apeMaterno;
    }

    public void setApeMaterno(String apeMaterno) {
        this.apeMaterno = apeMaterno;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNumeroFicha() {
        return numeroFicha;
    }

    public void setNumeroFicha(String numeroFicha) {
        this.numeroFicha = numeroFicha;
    }

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public List<Persona> getListaPersonas() {
        return listaPersonas;
    }

    public void setListaPersonas(List<Persona> listaPersonas) {
        this.listaPersonas = listaPersonas;
    }

    public List<Paciente> getListaPaciente() {
        return listaPaciente;
    }

    public void setListaPaciente(List<Paciente> listaPaciente) {
        this.listaPaciente = listaPaciente;
    }
    
}
