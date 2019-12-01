/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import Modelos.Form;
import javax.ejb.Stateless;

/**
 *
 * @author alan
 */
@Stateless
public class FormActual {
    Form formActual;

  

    public Form getFormActual() {
        return formActual;
    }

    public void setFormActual(Form formActual) {
        this.formActual = formActual;
    }
    
}
