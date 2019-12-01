/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import BaseDeDatos.BaseDeDatos;
import Modelos.Form;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author alan
 */
public class ObtenerFormularios implements Serializable{

    private static final long serialVersionUID = 1L;
    private List<Form> forms;
    private Form formSeleccionado;
    @EJB
    private BaseDeDatos conexionBD;

    @PostConstruct
    public void init() {
        forms = conexionBD.obtenerForms();
    }

    public Form getFormSeleccionado() {
        return formSeleccionado;
    }

    public void setFormSeleccionado(Form formSeleccionado) {
        this.formSeleccionado = formSeleccionado;
    }

    public List<Form> getForms() {
        return forms;
    }

    public void setForms(List<Form> forms) {
        this.forms = forms;
    }
    
     public void crearForm() {
    }
}
