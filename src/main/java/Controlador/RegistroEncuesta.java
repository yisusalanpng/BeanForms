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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author alan
 */
@Named
public class RegistroEncuesta implements Serializable {

    ExternalContext context;
    @EJB
    private BaseDeDatos conexionBD;
    @Inject
    Usuario usuario;
    @EJB
    private FormActual formActual;

    @PostConstruct
    public void init() {
        context = FacesContext.getCurrentInstance().getExternalContext();
        if (formActual.getFormActual() == null) {
            try {
                context.redirect("index.xhtml");
            } catch (Exception ex) {

            }
        }

    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String registrar() {
        Random random = new Random();

        String datos = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String codigoGenerado = "";
        int x;

        for (int i = 0; i < 20; i++) {
            x = random.nextInt(datos.length() - 1);
            codigoGenerado += datos.charAt(x);

        }
        usuario.setNickname(codigoGenerado);
        try {
            context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect("contestar_form.xhtml");
            return "registrar";
        } catch (Exception ex) {
            System.out.println(ex);
            return "dead";
        }

    }
}
