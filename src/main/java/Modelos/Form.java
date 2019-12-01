/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author alan
 */
public class Form implements Serializable {

    private int forms_id;
    private String titulo;
    private Date expiracion;
    private String codigo;
    private String creador_fk;
    private Boolean privado;
    private List<Pregunta> preguntas;

    public Form(int forms_id, String titulo, String expiracion, String codigo, String creador_fk, Boolean privado) {
        this.forms_id = forms_id;
        this.titulo = titulo;
        this.expiracion = new Date();
        this.codigo = codigo;
        this.creador_fk = creador_fk;
        this.privado = privado;
    }

    public Form() {
    }

    public int getForms_id() {
        return forms_id;
    }

    public void setForms_id(int forms_id) {
        this.forms_id = forms_id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getExpiracion() {
        return expiracion;
    }

    public void setExpiracion(Date expiracion) {
        this.expiracion = expiracion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCreador_fk() {
        return creador_fk;
    }

    public void setCreador_fk(String creador_fk) {
        this.creador_fk = creador_fk;
    }

    public Boolean getPrivado() {
        return privado;
    }

    public void setPrivado(Boolean privado) {
        this.privado = privado;
    }

    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

}
