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
public class ContestarEncuesta {

    private static final long serialVersionUID = 1L;

    @EJB
    private BaseDeDatos conexionBD;
    @EJB
    Usuario usuario;
    @EJB
    private FormActual formActual;
    private String header;

    @PostConstruct
    public void init() {
        header = formActual.getFormActual().getTitulo();
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        Form temp = conexionBD.obtenerForm(formActual.getFormActual().getForms_id());
        formActual.setFormActual(temp);
        /*   for (int i = 0; i < formActual.getFormActual().getPreguntas().size(); i++) {
            System.out.println(formActual.getFormActual().getPreguntas().get(i).getPregunta());

            for (int j = 0; j < formActual.getFormActual().getPreguntas().get(i).getOpciones().size(); j++) {
                System.out.println(formActual.getFormActual().getPreguntas().get(i).getOpciones().get(j).getOpcion());

            }

        }*/
    }

    public FormActual getFormActual() {
        return formActual;
    }

    public void setFormActual(FormActual formActual) {
        this.formActual = formActual;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void enviarRespuesta() {
        if (!usuario.isSesionIniciada()) {
            if (conexionBD.registro(usuario, usuario.getNickname())) {
                if (conexionBD.enviarRespuesta(formActual.getFormActual(), usuario.getNickname())) {
                    ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                    try {
                        context.redirect("index.xhtml");
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }

                }
            }
        } else {
            if (conexionBD.enviarRespuesta(formActual.getFormActual(), usuario.getNickname())) {
                ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
                try {
                    context.redirect("index.xhtml");
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        }
    }
}
