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
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author alan
 */
public class IniciarSesion implements Serializable{

    private static final long serialVersionUID = 1L;

    @EJB
    Usuario usuario;
    @EJB
    private BaseDeDatos conexionBD;
    private String nickname;
    private String pass;
    ExternalContext context;
    FacesContext contextFaces;

    @PostConstruct
    public void init() {
        context = FacesContext.getCurrentInstance().getExternalContext();
        contextFaces = FacesContext.getCurrentInstance();

        if (usuario.isSesionIniciada()) {

            try {
                context.redirect("index.xhtml");
            } catch (Exception ex) {
            }

        }
    }

    public void iniciarSesion() throws IOException {
        this.usuario = conexionBD.iniciarSesion(nickname, pass);
    
        if (this.usuario != null) {
            this.usuario.setSesionIniciada(true);

            context.redirect("index.xhtml");

        } else {
            contextFaces.addMessage(null, new FacesMessage("Credenciales erroneas"));

        }
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

}
