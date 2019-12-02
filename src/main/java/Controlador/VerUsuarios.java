/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import BaseDeDatos.BaseDeDatos;
import Modelos.Form;
import Modelos.Usuario;
import Servicios.FormActual;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author alan
 */
public class VerUsuarios {

    private static final long serialVersionUID = 1L;
    @EJB
    Usuario usuario;
    @EJB
    private BaseDeDatos conexionBD;
    private List<Usuario> usuarios;
    ExternalContext context;
    FacesContext contextFaces;

    @PostConstruct
    public void init() {
        context = FacesContext.getCurrentInstance().getExternalContext();
        contextFaces = FacesContext.getCurrentInstance();
        usuarios = conexionBD.obtenerUsuarios();

        if (!usuario.isSesionIniciada()) {

            try {
                context.redirect("index.xhtml");
            } catch (Exception ex) {
            }

            if (!usuario.isAdministrador()) {

                try {
                    context.redirect("index.xhtml");
                } catch (Exception ex) {
                }

            }
        }
    }

    public void eliminarUsuario(String nickname) {
        conexionBD.eliminarUsuario(nickname);
        usuarios = conexionBD.obtenerUsuarios();
        context = FacesContext.getCurrentInstance().getExternalContext();

        try {

            context.redirect("ver_usuarios.xhtml");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

}
