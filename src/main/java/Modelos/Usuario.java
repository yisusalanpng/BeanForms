/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.io.Serializable;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Stateful;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author alan
 */
@Named
@SessionScoped
public class Usuario implements Serializable{

    private String nickname;
    private String nombre;
    private String apellido;
    private boolean administrador;
    private boolean sesionIniciada;
    private boolean activo;

    public Usuario() {
        sesionIniciada=false;
    }

  

    public Usuario(String nickname, String nombre, String apellido, boolean administrador) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido;
        this.administrador = administrador;
    }

    public Usuario(String nickname, String nombre, String apellido, boolean administrador,boolean activo) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido;
        this.administrador = administrador;
        this.activo=activo;
    }

    public boolean isSesionIniciada() {
        return sesionIniciada;
    }

    public void setSesionIniciada(boolean sesionIniciada) {
        this.sesionIniciada = sesionIniciada;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

}
