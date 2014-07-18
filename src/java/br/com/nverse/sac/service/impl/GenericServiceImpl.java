/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nverse.sac.service.impl;

import br.com.nverse.sac.controllers.ViewState;
import br.com.nverse.sac.model.Entidade;
import br.com.nverse.sac.service.interfaces.GenericService;

/**
 *
 * @author jmarcos
 */
public abstract class GenericServiceImpl<T extends Entidade> implements GenericService<T>{
    
    public ViewState state;
    
    @Override
    public void setStateAdicionar() {
        this.state = ViewState.ADICIONAR;
    }

    @Override
    public void setStatePesquisar() {
        this.state = ViewState.PESQUISAR;
    }

    @Override
    public void setStateEditar() {
        this.state = ViewState.EDITAR;
    }

    @Override
    public boolean isStateAdicionar() {
        return state == ViewState.ADICIONAR;
    }

    @Override
    public boolean isStatePesquisar() {
        return state == ViewState.PESQUISAR;
    }

    @Override
    public boolean isStateEditar() {
        return state == ViewState.EDITAR;
    }
    
}
