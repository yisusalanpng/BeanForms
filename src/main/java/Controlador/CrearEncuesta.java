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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;

/**
 *
 * @author alan
 */
public class CrearEncuesta implements Serializable {

    private Form formNuevo;
    @EJB
    private BaseDeDatos conexionBD;
    @EJB
    Usuario usuario;

    @PostConstruct
    public void init() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

        if (!usuario.isSesionIniciada()) {

            try {
                context.redirect("index.xhtml");
            } catch (Exception ex) {
            }

        }
        List<Pregunta> preguntas = new ArrayList<>();
        List<Opcion> opciones = new ArrayList<>();
        opciones.add(new Opcion(""));
        preguntas.add(new Pregunta("", opciones));

        /*
        opciones.add("Perros");
        opciones.add("Gatos");
        preguntas.add(new Pregunta("¿Cuál prefieres perros o gatos?", opciones));
        
        opciones = new ArrayList<>();
        opciones.add("Comida rápida");
        opciones.add("Ensaladas");
        opciones.add("Carnes");

        preguntas.add(new Pregunta("¿Comida favorita?",opciones));
        opciones = new ArrayList<>();
        opciones.add("Nublados");
        opciones.add("Soleados");

        preguntas.add(new Pregunta("¿Días nublados o soleados?",opciones));
         */
        formNuevo = new Form();
        formNuevo.setPreguntas(preguntas);
        formNuevo.setPrivado(false);
        formNuevo.setCodigo("0");
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

    public void agregarPregunta() {
        List<Pregunta> preguntas = formNuevo.getPreguntas();
        List<Opcion> opciones = new ArrayList<>();
        opciones.add(new Opcion(""));
        preguntas.add(new Pregunta("", opciones));

        formNuevo.setPreguntas(preguntas);
        for (int i = 0; i < formNuevo.getPreguntas().size(); i++) {
            System.out.println(formNuevo.getPreguntas().get(i).getPregunta());
        }
        generarCodigo();

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
        if (conexionBD.crearEncuesta(formNuevo)) {
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
