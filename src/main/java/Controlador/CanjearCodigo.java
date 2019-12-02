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
public class CanjearCodigo implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    Usuario usuario;
    @EJB
    private BaseDeDatos conexionBD;

    @EJB
    private FormActual formActual;
    ExternalContext context;
    FacesContext contextFaces;
    private String codigo;

    @PostConstruct
    public void init() {
        context = FacesContext.getCurrentInstance().getExternalContext();
        contextFaces = FacesContext.getCurrentInstance();

    }

    public void buscarForm() {
            context = FacesContext.getCurrentInstance().getExternalContext();
            contextFaces = FacesContext.getCurrentInstance();
            Form temp = conexionBD.canjearCodigo(codigo);
            if (temp.getForms_id() == 0||codigo.equals("0")) {
                contextFaces.addMessage(null, new FacesMessage("No se ha encontrado el formulario"));
            } else {
                formActual.setFormActual(temp);
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
        
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

}
