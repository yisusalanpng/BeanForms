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
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author alan
 */
@Named
public class MisEncuestas implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private BaseDeDatos conexionBD;
    @Inject
    Usuario usuario;
    @EJB
    private FormActual formActual;
    private List<Form> forms;
    ExternalContext context;
    FacesContext contextFaces;

    @PostConstruct
    public void init() {
        if (usuario.isAdministrador()) {
            forms = conexionBD.obtenerMisForms(true);
        } else {
            forms = conexionBD.obtenerMisForms(false);

        }
        context = FacesContext.getCurrentInstance().getExternalContext();
        contextFaces = FacesContext.getCurrentInstance();

        if (!usuario.isSesionIniciada()) {

            try {
                context.redirect("index.xhtml");
            } catch (Exception ex) {
            }

        }
    }

    public List<Form> getForms() {
        return forms;
    }

    public void setForms(List<Form> forms) {
        this.forms = forms;
    }

    public void verRespuestas(int id, String titulo) {
        formActual.setFormActual(new Form(id, titulo));
        context = FacesContext.getCurrentInstance().getExternalContext();

        try {

            context.redirect("ver_respuestas.xhtml");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void eliminarForm(int id) {
        conexionBD.borrarForm(id);
        if (usuario.isAdministrador()) {
            forms = conexionBD.obtenerMisForms(true);
        } else {
            forms = conexionBD.obtenerMisForms(false);

        }
        context = FacesContext.getCurrentInstance().getExternalContext();

        try {

            context.redirect("mis_encuestas.xhtml");
        } catch (Exception ex) {
            System.out.println(ex);
        }
        addMessage("Form eliminado", "Hasta la vista!");
    }

    public void deshabilitarForm(int id) {
        context = FacesContext.getCurrentInstance().getExternalContext();

        if (conexionBD.deshabilitarForm(id)) {
            try {

                context.redirect("mis_encuestas.xhtml");
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }

    }
     public void habilitarForm(int id) {
        context = FacesContext.getCurrentInstance().getExternalContext();

        if (conexionBD.habilitarForm(id)) {
            try {

                context.redirect("mis_encuestas.xhtml");
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }

    }

    public void editarForm(int id, String titulo, Date expiracion, Boolean privado, String creador) {
        formActual.setFormActual(new Form(id, titulo, expiracion, privado, creador));
        context = FacesContext.getCurrentInstance().getExternalContext();

        try {

            context.redirect("editar_form.xhtml");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public FormActual getFormActual() {
        return formActual;
    }

    public void setFormActual(FormActual formActual) {
        this.formActual = formActual;
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
