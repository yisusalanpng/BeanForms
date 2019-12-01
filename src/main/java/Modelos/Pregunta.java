/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alan
 */
public class Pregunta {

    private int pregunta_id;
    private List<Opcion> opciones;
    private String pregunta;
    private Boolean multiple;
    private int opcion_elegida_id;

    public Pregunta(String pregunta, List<Opcion> opciones) {
        this.opciones = opciones;
        this.pregunta = pregunta;
        this.multiple = true;
    }

    public Pregunta(String pregunta) {
        this.pregunta = pregunta;
        opciones = new ArrayList<Opcion>();
        this.multiple = true;

    }

    public Pregunta(String pregunta, int pregunta_id) {
        this.pregunta = pregunta;
        this.pregunta_id = pregunta_id;
        opciones = new ArrayList<Opcion>();
        this.multiple = true;

    }

    public List<Opcion> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<Opcion> opciones) {
        this.opciones = opciones;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public Boolean getMultiple() {
        return multiple;
    }

    public void setMultiple(Boolean multiple) {
        this.multiple = multiple;
    }

    public int getPregunta_id() {
        return pregunta_id;
    }

    public void setPregunta_id(int pregunta_id) {
        this.pregunta_id = pregunta_id;
    }

    public int getOpcion_elegida_id() {
        return opcion_elegida_id;
    }

    public void setOpcion_elegida_id(int opcion_elegida_id) {
        this.opcion_elegida_id = opcion_elegida_id;
    }

}
