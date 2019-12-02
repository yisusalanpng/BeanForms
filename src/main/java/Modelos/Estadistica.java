/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author alan
 */
public class Estadistica {

    private int opcion_fk;
    private int porcentaje;
    private String opcion;
    private PieChartModel model;

    public Estadistica(int opcion_fk, int porcentaje, String opcion) {
        this.opcion_fk = opcion_fk;
        this.porcentaje = porcentaje;
        this.opcion = opcion;
    }

    public int getOpcion_fk() {
        return opcion_fk;
    }

    public void setOpcion_fk(int opcion_fk) {
        this.opcion_fk = opcion_fk;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

}
