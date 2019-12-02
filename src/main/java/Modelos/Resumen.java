/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.util.List;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author alan
 */
public class Resumen {

    private int pregunta_id;
    private String pregunta;
    private List<Estadistica> estadistica;
    private PieChartModel model;

    public int getPregunta_id() {
        return pregunta_id;
    }

    public void setPregunta_id(int pregunta_id) {
        this.pregunta_id = pregunta_id;
    }

    public List<Estadistica> getEstadistica() {
        return estadistica;
    }

    public void setEstadistica(List<Estadistica> estadistica) {
        this.estadistica = estadistica;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public PieChartModel getModel() {
        return model;
    }

    public void setModel(PieChartModel model) {
        this.model = model;
    }

}
