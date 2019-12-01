/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import BaseDeDatos.BaseDeDatos;
import Modelos.Form;
import Modelos.Opcion;
import Modelos.Pregunta;
import Modelos.Usuario;
import Servicios.FormActual;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author alan
 */
public class ContestarEncuesta{
    private static final long serialVersionUID = 1L;

    private Form formContestado;
    @EJB
    private BaseDeDatos conexionBD;
    @EJB
    Usuario usuario;
    @EJB
    private FormActual formActual;

    @PostConstruct
    public void init() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

        
}

}