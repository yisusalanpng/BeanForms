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
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author alan
 */
public class EditarEncuesta {

    private Form formNuevo;
    @EJB
    private BaseDeDatos conexionBD;
    @EJB
    Usuario usuario;
    @EJB
    private FormActual formActual;
    private String titulo;

    @PostConstruct
    public void init() {
        int id = formActual.getFormActual().getForms_id();
        titulo = formActual.getFormActual().getTitulo();
        System.out.println(formActual.getFormActual());

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

        if (!usuario.isSesionIniciada()) {

            try {
                context.redirect("index.xhtml");
            } catch (Exception ex) {
            }

        }
        List<Pregunta> preguntas = new ArrayList<>();
        preguntas = conexionBD.obtenerPreguntas(id);

        formNuevo = formActual.getFormActual();
        formNuevo.setPreguntas(preguntas);
    }

    public Form getFormNuevo() {
        return formNuevo;
    }

    public void setFormNuevo(Form formNuevo) {
        this.formNuevo = formNuevo;
    }

    public void agregarOpcion(int index) {
        List<Pregunta> preguntas = formNuevo.getPreguntas();
        preguntas.get(index).getOpciones().add(new Opcion(""));

        formNuevo.setPreguntas(preguntas);

    }

    public void quitarOpcion(int index) {
        List<Pregunta> preguntas = formNuevo.getPreguntas();
        List<Opcion> opciones = new ArrayList<>();
        opciones = preguntas.get(index).getOpciones();
        if (opciones.size() > 0) {
            opciones.remove(opciones.size() - 1);

        }
        preguntas.get(index).setOpciones(opciones);
        formNuevo.setPreguntas(preguntas);

    }

    public void agregarPregunta() {
        List<Pregunta> preguntas = formNuevo.getPreguntas();
        List<Opcion> opciones = new ArrayList<>();
        opciones.add(new Opcion(""));
        preguntas.add(new Pregunta("", opciones));
        if (opciones.size() > 0) {
            opciones.remove(opciones.size() - 1);

        }
        formNuevo.setPreguntas(preguntas);
        for (int i = 0; i < formNuevo.getPreguntas().size(); i++) {
            System.out.println(formNuevo.getPreguntas().get(i).getPregunta());
        }

    }

    public void quitarPregunta(int index) {
        List<Pregunta> preguntas = formNuevo.getPreguntas();
        if (preguntas.size() > 0) {
            preguntas.remove(preguntas.size() - 1);

        }
        formNuevo.setPreguntas(preguntas);
        

    }

    public void cambioMultiple(int index) {
        if (!formNuevo.getPreguntas().get(index).getMultiple()) {
            List<Opcion> opciones = new ArrayList<>();
            opciones.add(new Opcion("Verdadero"));
            opciones.add(new Opcion("Falso"));

            formNuevo.getPreguntas().get(index).setOpciones(opciones);

        } else {
            List<Opcion> opciones = new ArrayList<>();
            opciones.add(new Opcion(""));
            formNuevo.getPreguntas().get(index).setOpciones(opciones);

        }
    }

    public void crearEncuesta() {
        FacesContext contextFaces = FacesContext.getCurrentInstance();
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

        for (int i = 0; i < formNuevo.getPreguntas().size(); i++) {
            if (formNuevo.getPreguntas().get(i).getPregunta().equals("")) {
                contextFaces.addMessage(null, new FacesMessage("Llena todos los campos"));

                return;
            }
            for (int j = 0; j < formNuevo.getPreguntas().get(i).getOpciones().size(); j++) {
                if (formNuevo.getPreguntas().get(i).getOpciones().get(j).getOpcion().equals("")) {
                    contextFaces.addMessage(null, new FacesMessage("Llena todos los campos"));

                    return;
                }
            }
        }
        if (formNuevo.getPrivado()) {
            formNuevo.setCodigo(generarCodigo());
            System.out.println(formNuevo.getCodigo());
        }
        if (conexionBD.editarEncuesta(formNuevo, formActual.getFormActual().getForms_id(),formActual.getFormActual().getCreador_fk())) {
            try {
                context.redirect("index.xhtml");
            } catch (Exception ex) {
            }

        }

    }

    public String generarCodigo() {
        Random random = new Random();

        String datos = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String codigoGenerado = "";
        int x;

        for (int i = 0; i < 5; i++) {
            x = random.nextInt(datos.length() - 1);
            codigoGenerado += datos.charAt(x);

        }
        return codigoGenerado;
    }
}
