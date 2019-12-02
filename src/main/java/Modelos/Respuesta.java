/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

/**
 *
 * @author alan
 */
public class Respuesta {

    private String persona;
    private int pregunta_fk;
    private int opcion_fk;
    private String pregunta;
    private String opcion;

    public Respuesta(String nickname, int pregunta_fk, int opcion_fk, String pregunta, String opcion) {
        this.persona = nickname;
        this.pregunta_fk = pregunta_fk;
        this.opcion_fk = opcion_fk;
        this.pregunta = pregunta;
        this.opcion = opcion;
    }

    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    

    public int getPregunta_fk() {
        return pregunta_fk;
    }

    public void setPregunta_fk(int pregunta_fk) {
        this.pregunta_fk = pregunta_fk;
    }

    public int getOpcion_fk() {
        return opcion_fk;
    }

    public void setOpcion_fk(int opcion_fk) {
        this.opcion_fk = opcion_fk;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }
    
}
