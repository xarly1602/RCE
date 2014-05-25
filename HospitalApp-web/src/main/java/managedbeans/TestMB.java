/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author DevelUser
 */
@ManagedBean
@RequestScoped
public class TestMB {
    private String cadena;
    /**
     * Creates a new instance of TestMB
     */
    public TestMB() {
    }
    
    public void saludo(){
        System.out.println("Hola "+cadena);
    }

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }
}
