/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import BaseDeDatos.BaseDeDatos;
import Modelos.Estadistica;
import Modelos.Form;
import Modelos.Respuesta;
import Modelos.Resumen;
import Modelos.Usuario;
import Servicios.FormActual;
import java.io.Serializable;
import java.util.ArrayList;
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
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author alan
 */
@Named
public class RespuestaEncuesta implements Serializable{

    @EJB
    private BaseDeDatos conexionBD;
    @Inject
    Usuario usuario;
    @EJB
    private FormActual formActual;
    ExternalContext context;
    FacesContext contextFaces;
    private List<Respuesta> respuestas;
    private List<Resumen> resumen;
    private String titulo;

    public List<Resumen> getResumen() {
        return resumen;
    }

    public void setResumen(List<Resumen> resumen) {
        this.resumen = resumen;
    }

    @PostConstruct
    public void init() {
        int id = formActual.getFormActual().getForms_id();
        titulo = formActual.getFormActual().getTitulo();
        context = FacesContext.getCurrentInstance().getExternalContext();
        contextFaces = FacesContext.getCurrentInstance();
        Form temp = conexionBD.obtenerForm(formActual.getFormActual().getForms_id());
        formActual.setFormActual(temp);
        respuestas = conexionBD.obtenerRespuestas(id);
        resumen = new ArrayList();
        for (int i = 0; i < formActual.getFormActual().getPreguntas().size(); i++) {
            Resumen tempResumen = new Resumen();
            List<Estadistica> estadistica = new ArrayList<>();

            tempResumen.setPregunta_id(formActual.getFormActual().getPreguntas().get(i).getPregunta_id());
            tempResumen.setPregunta(formActual.getFormActual().getPreguntas().get(i).getPregunta());

            for (int j = 0; j < formActual.getFormActual().getPreguntas().get(i).getOpciones().size(); j++) {
                int cont = 0;

                for (int k = 0; k < respuestas.size(); k++) {
                    if (respuestas.get(k).getOpcion_fk() == formActual.getFormActual().getPreguntas().get(i).getOpciones().get(j).getOpcion_id()) {
                        cont++;
                    }
                }
                estadistica.add(new Estadistica(formActual.getFormActual().getPreguntas().get(i).getOpciones().get(j).getOpcion_id(), cont, formActual.getFormActual().getPreguntas().get(i).getOpciones().get(j).getOpcion()));
                tempResumen.setEstadistica(estadistica);
            }
            resumen.add(tempResumen);

        }
        for (int i = 0; i < resumen.size(); i++) {
            PieChartModel model = new PieChartModel();
            model.setTitle(resumen.get(i).getPregunta());
            model.setLegendPosition("w");
            model.setShowDataLabels(true);
            model.setDiameter(150);
            for (int j = 0; j < resumen.get(i).getEstadistica().size(); j++) {
                //double num = ((resumen.get(i).getEstadistica().get(j).getPorcentaje() * 100) / resumen.get(i).getEstadistica().size());
                //resumen.get(i).getEstadistica().get(j).setPorcentaje(num );
                model.set(resumen.get(i).getEstadistica().get(j).getOpcion(), resumen.get(i).getEstadistica().get(j).getPorcentaje());
            }
            resumen.get(i).setModel(model);
        }

    }

    public FormActual getFormActual() {
        return formActual;
    }

    public void setFormActual(FormActual formActual) {
        this.formActual = formActual;
    }

    public List<Respuesta> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<Respuesta> respuestas) {
        this.respuestas = respuestas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

}
