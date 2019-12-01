/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelos.Usuario;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author alan
 */
public class MenuToolbar {

    private MenuModel model;
    private String titulo;

    @EJB
    private Usuario usuario;

    @PostConstruct
    public void init() {
        resetMenu();

    }

    public void resetMenu() {
        model = new DefaultMenuModel();

        if (usuario.isSesionIniciada()) {
            titulo = "Bienvenido, " + usuario.getNickname();
            DefaultMenuItem cerrarSesion = new DefaultMenuItem("Cerrar sesión");
            cerrarSesion.setCommand("#{menuToolbar.cerrarSesion()}");
            model.getElements().add(cerrarSesion);

        } else {

            titulo = "Menú";
            DefaultMenuItem login = new DefaultMenuItem("Iniciar sesión");
            login.setOutcome("login");
            model.getElements().add(login);

            DefaultMenuItem registro = new DefaultMenuItem("Registro");
            registro.setOutcome("registro");
            model.getElements().add(registro);

        }
        DefaultMenuItem Encuestas = new DefaultMenuItem("Encuestas");
        Encuestas.setOutcome("index");
        model.getElements().add(Encuestas);

        DefaultMenuItem escanearEncuesta = new DefaultMenuItem("Código encuesta");
        escanearEncuesta.setOutcome("index");
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
        System.out.println(usuario.isSesionIniciada());
        resetMenu();
        
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
       try{
        context.redirect("index.xhtml");
       }catch(Exception ex){
           System.out.println(ex);
       }
    }

}
