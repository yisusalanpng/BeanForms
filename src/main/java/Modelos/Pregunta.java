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

    private List<Opcion> opciones;
    private String pregunta;
    private Boolean multiple;

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

}
