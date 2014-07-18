/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nverse.sac.model.enums;

/**
 *
 * @author JMarcos_Nverse
 */
public enum TipoTransportador {
    
    AUTORIZATARIA("Autorizatária"),
    PERMISSIONARIA("Permissionária"),
    AMBAS("Permissionária e Autorizatária");
    
    
    private String tipo;
    
     TipoTransportador(String tipo){
        this.tipo = tipo;
    } 
    
    public String getTipo(){
        return tipo;
    }
    
    
}
