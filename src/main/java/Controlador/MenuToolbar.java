/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelos.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author alan
 */
@Named
public class MenuToolbar implements Serializable {

    private MenuModel model;
    private String titulo;

    @Inject
    Usuario usuario;

    @PostConstruct
    public void init() {
        resetMenu();

    }

    public void resetMenu() {
        model = new DefaultMenuModel();

        if (usuario.isSesionIniciada()) {
            titulo = "Bienvenido, " + usuario.getNickname();
            DefaultMenuItem crearEncuesta = new DefaultMenuItem("Nueva encuesta");
            crearEncuesta.setOutcome("crear_encuesta");
            crearEncuesta.setIcon("pi pi-plus-circle");
            model.getElements().add(crearEncuesta);

            DefaultMenuItem misEncuestas = new DefaultMenuItem("Mis encuestas");
            misEncuestas.setIcon("pi pi-file");
            misEncuestas.setCommand("#{menuToolbar.misEncuestas()}");
            model.getElements().add(misEncuestas);

            DefaultMenuItem cerrarSesion = new DefaultMenuItem("Cerrar sesión");
            cerrarSesion.setIcon("pi pi-power-off");
            cerrarSesion.setCommand("#{menuToolbar.cerrarSesion()}");
            model.getElements().add(cerrarSesion);
            if (usuario.isAdministrador()) {
                DefaultMenuItem usuarios = new DefaultMenuItem("Usuarios");
                usuarios.setIcon("pi pi-users");
                usuarios.setCommand("#{menuToolbar.verUsuarios()}");
                model.getElements().add(usuarios);
            }
        } else {

            titulo = "Menú";
            DefaultMenuItem login = new DefaultMenuItem("Iniciar sesión");
            login.setOutcome("login");
            login.setIcon("pi pi-user");
            model.getElements().add(login);

            DefaultMenuItem registro = new DefaultMenuItem("Registro");
            registro.setOutcome("registro");
            registro.setIcon("pi pi-user-plus");
            model.getElements().add(registro);

        }

        DefaultMenuItem escanearEncuesta = new DefaultMenuItem("Código encuesta");
        escanearEncuesta.setOutcome("escanear_codigo");
        escanearEncuesta.setIcon("pi pi-key");
        model.getElements().add(escanearEncuesta);

    }

    public MenuModel getModel() {
        return model;
    }

    public void save() {
        addMessage("Success", "Data saved");
    }

    public void update() {
        addMessage("Success", "Data updated");
    }

    public void delete() {
        addMessage("Success", "Data deleted");
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void cerrarSesion() {
        System.out.println("yea");
        usuario.setSesionIniciada(false);
        usuario.setNickname("");
        usuario.setNombre("");
        usuario.setApellido("");
        usuario.setAdministrador(false);
        System.out.println(usuario.isSesionIniciada());
        resetMenu();

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        try {
            context.redirect("index.xhtml");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void misEncuestas() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        try {
            context.redirect("mis_encuestas.xhtml");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void verUsuarios() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        try {
            context.redirect("ver_usuarios.xhtml");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
