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
public class Opcion {

    private int opcion_id;
    private String opcion;

    public Opcion(String opcion) {
        this.opcion = opcion;
    }

    public Opcion(int opcion_id, String opcion) {
        this.opcion_id = opcion_id;
        this.opcion = opcion;

    }

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    public int getOpcion_id() {
        return opcion_id;
    }

    public void setOpcion_id(int opcion_id) {
        this.opcion_id = opcion_id;
    }

 

   

}
