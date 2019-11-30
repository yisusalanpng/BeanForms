/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import BaseDeDatos.BaseDeDatos;
import Modelos.Usuario;
import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author alan
 */
public class RegistrarUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Creates a new instance of RegistrarUsuario
     */
    private Usuario usuario;
    @EJB
    private BaseDeDatos conexionBD;
    @PostConstruct
    public void init() {
        usuario = new Usuario();
    }



    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void registrar() throws IOException {
        boolean success= conexionBD.registro(this.usuario);
        if(success){
            ExternalContext context= FacesContext.getCurrentInstance().getExternalContext();
		context.redirect("index.xhtml");
        }
       
    }
}
