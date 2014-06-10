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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Xarly1602
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
    private List<Persona> listaPersonaProf;
    private Paciente paciente;
    
    /**
     * Constructor de la clase.
     */
    public busquedaMB() {
    }

    /**
     * Postconstructor: 
     * Inicializar algunas variables y lista de personas.
     */
    @PostConstruct
    public void init() {
        nombres = "";
        apePaterno = "";
        apeMaterno = "";
        rut = "";
        numeroFicha = "";
        fechaNacimiento = null;
        etapa = 0;
        listaPersonas = personaFacade.findAll();
        listaPersonaProf = personaFacade.findAll();
        for (int i = listaPersonas.size() - 1; i >= 0; i--) {
            if (listaPersonas.get(i).getPersTipopersona() != 1) {
                listaPersonas.remove(i);
            }
        }
        
        for (int i = listaPersonaProf.size() - 1; i >= 0; i--) {
            if (listaPersonaProf.get(i).getPersTipopersona() == 1) {
                listaPersonaProf.remove(i);
            }
        }
    }

    /**
     * Autocompletar ruts para la búsqueda de pacientes
     * Función para realizar la lista que se muestra mientras se va escribiendo
     * un rut en el campo de búsqueda correspondiente.
     * @param query String correspondiente al inicio del rut que se busca.
     * @return Lista con los resultados encontrados.
     */
    public List<String> completarRut(String query){
        List<String> listaFiltrada = new ArrayList<String>();
        for (Persona persona : listaPersonas) {
            if (persona.getPersRut().toString().startsWith(query) && !listaFiltrada.contains(persona.getPersRut().toString())) {
                listaFiltrada.add(persona.getPersRut().toString());
            }
        }
        return listaFiltrada;  }
    
    /**
     * Autocompletar ruts para la búsqueda de pacientes
     * Función para realizar la lista que se muestra mientras se va escribiendo
     * un rut en el campo de búsqueda correspondiente.
     * @param query String correspondiente al inicio del rut que se busca.
     * @return Lista con los resultados encontrados.
     */
    public List<String> completarRutMujer(String query){
        List<String> listaFiltrada = new ArrayList<String>();
        for (Persona persona : listaPersonas) {
            if (persona.getPersRut().toString().startsWith(query) && !listaFiltrada.contains(persona.getPersRut().toString()) && persona.getIdGenero().getIdGenero() == 2) {
                listaFiltrada.add(persona.getPersRut().toString());
            }
        }
        return listaFiltrada;
    }
    /**
     * Autocompletar ruts para la búsqueda de profesionales
     * Función para realizar la lista que se muestra mientras se va escribiendo
     * un rut en el campo de búsqueda correspondiente.
     * @param query String correspondiente al inicio del rut que se busca.
     * @return Lista con los resultados encontrados.
     */
    public List<String> completarRutProf(String query){
        List<String> listaFiltrada = new ArrayList<String>();
        for (Persona persona : listaPersonaProf) {
            if (persona.getPersRut().toString().startsWith(query) && !listaFiltrada.contains(persona.getPersRut().toString())) {
                listaFiltrada.add(persona.getPersRut().toString());
            }
        }
        return listaFiltrada;
    }
    
    /**
     * Autocompletar nombres para la búsqueda
     * Función para realizar la lista que se muestra mientras se va escribiendo
     * un nombre en el campo de búsqueda correspondiente.
     * @param query String correspondiente al inicio del nombre buscado.
     * @return Lista de resultados encontrados.
     */
    public List<String> completarNombre(String query){
        List<String> listaFiltrada = new ArrayList<String>();
        for (Persona persona : listaPersonas) {
            if (persona.getPersNombres().startsWith(query) && !listaFiltrada.contains(persona.getPersNombres())) {
                listaFiltrada.add(persona.getPersNombres());
            }
        }
        return listaFiltrada;
    }
    
    /**
     * Autocompletar apellido paterno para la búsqueda
     * Función para realizar la lista que se muestra mientras se va escribiendo
     * un apellido en el campo de búsqueda correspondiente al apellido paterno.
     * @param query String correspondiente al inicio del apellido buscado.
     * @return Lista de resultados encontrados.
     */
    public List<String> completarApePaterno(String query){
        List<String> listaFiltrada = new ArrayList<String>();
        for (Persona persona : listaPersonas) {
            if (persona.getPersApepaterno().startsWith(query) && !listaFiltrada.contains(persona.getPersApepaterno())) {
                listaFiltrada.add(persona.getPersApepaterno());
            }
        }
        return listaFiltrada;
    }
    
    /**
     * Autocompletar apellido para la búsqueda
     * Función para realizar la lista que se muestra mientras se va escribiendo
     * un apellido en el campo de búsqueda correspondiente al apellido materno.
     * @param query String correspondiente al inicio del apellido buscado.
     * @return Lista de resultados encontrados.
     */
    public List<String> completarApeMaterno(String query){
        List<String> listaFiltrada = new ArrayList<String>();
        for (Persona persona : listaPersonas) {
            if (persona.getPersApematerno().startsWith(query) && !listaFiltrada.contains(persona.getPersApematerno())) {
                listaFiltrada.add(persona.getPersApematerno());
            }
        }
        return listaFiltrada;
    }
    
    /**
     * Función que realiza la búsqueda de los campos indicados.
     * La búsqueda se realiza según todos los campos completados, en primer 
     * lugar se busca por el primer valor no nulo o vacío y luego se filtra ese
     * resultado según los demás campos rellenados.
     */
    public void buscar() {
        if (fechaNacimiento != null) {
            listaPersonas = personaNegocio.busquedaPersonaFechaNacimiento(fechaNacimiento, 1);
            etapa = 1;
        } else if (!nombres.isEmpty()) {
            System.out.println("BuscaNombres");
            // listaPersonas = personaNegocio.busquedaPersonaNombre(nombres, 1);
            for (int i = listaPersonas.size() - 1; i >= 0; i--) {
                if (!listaPersonas.get(i).getPersNombres().startsWith(nombres)) {
                    listaPersonas.remove(i);
                }
            }
            etapa = 2;
        } else if (!apePaterno.isEmpty()) {
            System.out.println("BuscaApellido");
            // listaPersonas = personaNegocio.busquedaPersonaApellidoPaterno(apePaterno, 1);
            for (int i = listaPersonas.size() - 1; i >= 0; i--) {
                if (!listaPersonas.get(i).getPersApepaterno().startsWith(apePaterno)) {
                    listaPersonas.remove(i);
                }
            }
            etapa = 3;
        } else if (!apeMaterno.isEmpty()) {
            System.out.println("BuscaMaterno");
            // listaPersonas = personaNegocio.busquedaPersonaApellidoPaterno(apeMaterno, 1);
            for (int i = listaPersonas.size() - 1; i >= 0; i--) {
                if (!listaPersonas.get(i).getPersApematerno().startsWith(apeMaterno)) {
                    listaPersonas.remove(i);
                }
            }
            etapa = 4;
        } else if (!numeroFicha.isEmpty()) {
            paciente = pacienteNegocio.busquedaPacienteNumeroFicha(numeroFicha);
            for (int i = listaPersonas.size() - 1; i >= 0; i--) {
                if (listaPersonas.get(i).getIdPersona() != paciente.getIdPersona().getIdPersona()) {
                    listaPersonas.remove(i);
                }
            }
            etapa = 5;
        } else if (sexo != 0) {
            listaPersonas = personaNegocio.busquedaPersonaSexo(sexo, 1);
            etapa = 6;
        } else if (!rut.isEmpty()) {
            System.out.println("busca Rut");
            rut = rut.toUpperCase();
            rut = rut.replace(".", "");
            try {
                int rutTemp = Integer.parseInt(rut);
                listaPersonas = personaNegocio.busquedaPersonaRut(rutTemp, 1);
                etapa = 7;
            } catch (NumberFormatException e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Rut invalido", "Ingrese un rut válido"));
            }
        }
        switch (etapa) {
            case 1:
                if (!nombres.isEmpty()) {
                    System.out.println("1");
                    for (int i = listaPersonas.size() - 1; i >= 0; i--) {
                        if (!listaPersonas.get(i).getPersNombres().startsWith(nombres)) {
                            listaPersonas.remove(i);
                        }
                    }
                }
            case 2:
                if (!apePaterno.isEmpty()) {
                    System.out.println("2");
                    for (int i = listaPersonas.size() - 1; i >= 0; i--) {
                        if (!listaPersonas.get(i).getPersApepaterno().startsWith(apePaterno)) {
                            listaPersonas.remove(i);
                        }
                    }
                }
            case 3:
                if (!apeMaterno.isEmpty()) {
                    System.out.println("3");
                    for (int i = listaPersonas.size() - 1; i >= 0; i--) {
                        if (!listaPersonas.get(i).getPersApematerno().startsWith(apeMaterno)) {
                            listaPersonas.remove(i);
                        }
                    }
                }
            case 4:
                if (!numeroFicha.isEmpty()) {
                    paciente = pacienteNegocio.busquedaPacienteNumeroFicha(numeroFicha);
                    for (int i = listaPersonas.size() - 1; i >= 0; i--) {
                        if (listaPersonas.get(i).getIdPersona() != paciente.getIdPersona().getIdPersona()) {
                            listaPersonas.remove(i);
                        }
                    }
                }
            case 5:
                if (sexo != 0) {
                    for (int i = listaPersonas.size() - 1; i >= 0; i--) {
                        if (listaPersonas.get(i).getIdGenero().getIdGenero() != sexo) {
                            listaPersonas.remove(i);
                        }
                    }
                }
            case 6:
                if (!rut.isEmpty()) {
                    rut = rut.toUpperCase();
                    rut = rut.replace(".", "");
                    try {
                        int rutTemp = Integer.parseInt(rut);
                        for (int i = listaPersonas.size() - 1; i >= 0; i--) {
                        if (listaPersonas.get(i).getPersRut() != rutTemp) {
                            listaPersonas.remove(i);
                        }
                    }
                    } catch (NumberFormatException e) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Rut invalido", "Ingrese un rut válido"));
                    }
                }
            default:
                break;
        }
        for (Persona persona : listaPersonas) {
            System.out.println(persona.getPersNombres());
        }
    }

    //Getters y Setters
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

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

}
