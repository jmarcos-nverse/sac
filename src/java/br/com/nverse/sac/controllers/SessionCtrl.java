/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nverse.sac.controllers;

import br.com.nverse.sac.model.security.Usuario;
import java.io.Serializable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author jmarcos
 */
@Controller(value = "sessionCtrl")
@Scope("session")
public class SessionCtrl implements Serializable{
    
    private Usuario usuariologado;

    
    
    
    
    
    
    public Usuario getUsuariologado() {
        return usuariologado;
    }

    public void setUsuariologado(Usuario usuariologado) {
        this.usuariologado = usuariologado;
    }
    
    
}
