/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nverse.sac.service.impl;

import br.com.nverse.sac.controllers.ViewState;
import br.com.nverse.sac.model.Protocolo;
import br.com.nverse.sac.service.interfaces.ProtocoloService;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

/**
 *
 * @author jmarcos
 */
@Service
public class ProtocoloServiceImpl extends GenericServiceImpl<Protocolo> implements ProtocoloService{

    @PostConstruct
    public void setInitialState(){
        this.state = ViewState.ADICIONAR;
    }
    
}
