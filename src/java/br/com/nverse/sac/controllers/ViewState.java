/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nverse.sac.controllers;

/**
 *
 * @author jmarcos
 */
public enum ViewState {
    
    PESQUISAR("pesquisar"),
    VISUALIZAR("visualizar"),
    ADICIONAR("adicionar"),
    EDITAR("editar");

    private String tipoState;
    private ViewState(String tipo) {
        tipoState = tipo;
    }
    
    public String getTipo(){
        return tipoState;
    }
    
    
}
