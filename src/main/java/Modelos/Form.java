/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private Boolean habilitado;

    private int likes;
    private int dislikes;
    private List<Pregunta> preguntas;

    public Form(int forms_id, String titulo, String expiracion, String codigo, String creador_fk, Boolean privado) {
        this.forms_id = forms_id;
        this.titulo = titulo;
        try {
            this.expiracion = new SimpleDateFormat("yyyy-MM-dd").parse(expiracion);
        } catch (ParseException ex) {
        }
        this.codigo = codigo;
        this.creador_fk = creador_fk;
        this.privado = privado;

    }

    public Form(int forms_id, String titulo, String expiracion, String codigo, String creador_fk, Boolean privado, int likes, int dislikes) {
        this.forms_id = forms_id;
        this.titulo = titulo;
        try {
            this.expiracion = new SimpleDateFormat("yyyy-MM-dd").parse(expiracion);
        } catch (ParseException ex) {
        }
        this.codigo = codigo;
        this.creador_fk = creador_fk;
        this.privado = privado;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    public Form(int forms_id, String titulo, String expiracion, String codigo, String creador_fk, Boolean privado, int likes, int dislikes,boolean habilitado) {
        this.forms_id = forms_id;
        this.titulo = titulo;
        try {
            this.expiracion = new SimpleDateFormat("yyyy-MM-dd").parse(expiracion);
        } catch (ParseException ex) {
        }
        this.codigo = codigo;
        this.creador_fk = creador_fk;
        this.privado = privado;
        this.likes = likes;
        this.dislikes = dislikes;
        this.habilitado=habilitado;
    }

    public Form() {
    }

    public Form(int forms_id) {
        this.forms_id = forms_id;

    }

    public Form(int forms_id, String titulo) {
        this.forms_id = forms_id;
        this.titulo = titulo;
    }

    public Form(int forms_id, String titulo, Date expiracion, Boolean privado) {
        this.forms_id = forms_id;
        this.titulo = titulo;
        this.expiracion = expiracion;
        this.privado = privado;
    }

    public Form(int forms_id, String titulo, Date expiracion, Boolean privado, int likes, int dislikes) {
        this.forms_id = forms_id;
        this.titulo = titulo;
        this.expiracion = expiracion;
        this.privado = privado;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    public Form(int forms_id, String titulo, Date expiracion, Boolean privado, String creador) {
        this.forms_id = forms_id;
        this.titulo = titulo;
        this.expiracion = expiracion;
        this.privado = privado;
        this.creador_fk = creador;
    }

    public Form(int forms_id, String titulo, Date expiracion, Boolean privado, String creador, int likes, int dislikes) {
        this.forms_id = forms_id;
        this.titulo = titulo;
        this.expiracion = expiracion;
        this.privado = privado;
        this.creador_fk = creador;
        this.likes = likes;
        this.dislikes = dislikes;
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

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

}
