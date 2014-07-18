/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nverse.sac.service.interfaces;

import br.com.nverse.sac.model.Entidade;

/**
 *
 * @author jmarcos
 */
public interface GenericService<T extends Entidade> {

    public void setStateAdicionar();

    public void setStatePesquisar();

    public void setStateEditar();

    public boolean isStateAdicionar();

    public boolean isStatePesquisar();

    public boolean isStateEditar();
}
