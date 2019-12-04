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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.PrimeFaces;

/**
 *
 * @author alan
 */
@Named
public class ObtenerFormularios implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<Form> forms;

    @Inject
    Usuario usuario;
    @EJB
    private BaseDeDatos conexionBD;
    @EJB
    private FormActual formActual;
    ExternalContext context;
    FacesContext contextFaces;

    @PostConstruct
    public void init() {
        forms = conexionBD.obtenerForms();
        context = FacesContext.getCurrentInstance().getExternalContext();
        contextFaces = FacesContext.getCurrentInstance();
    }

    public List<Form> getForms() {
        return forms;
    }

    public void setForms(List<Form> forms) {
        this.forms = forms;
    }

    public void contestarForm(int id, String titulo) {
        formActual.setFormActual(new Form(id, titulo));

        if (!usuario.isSesionIniciada()) {
            try {
                context.redirect("registroEncuesta.xhtml");
            } catch (Exception ex) {
            }
        } else {
            try {
                context.redirect("contestar_form.xhtml");
            } catch (Exception ex) {
            }
        }

    }

    public void like(int form) {
        if (conexionBD.like(form)) {
            try {
                context.redirect("index.xhtml");
            } catch (Exception ex) {
            }
        }
    }
      public void dislike(int form) {
        if (conexionBD.dislike(form)) {
            try {
                context.redirect("index.xhtml");
            } catch (Exception ex) {
            }
        }
    }
}
