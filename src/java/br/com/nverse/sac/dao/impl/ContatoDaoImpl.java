/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nverse.sac.dao.impl;

import br.com.nverse.sac.dao.interfaces.ContatoDao;
import br.com.nverse.sac.model.Contato;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author JMarcos_Nverse
 */
@Repository
public class ContatoDaoImpl extends GenericDaoImpl<Contato> implements ContatoDao{

    
    @Autowired
    public ContatoDaoImpl(SessionFactory sessionFactory){
        super(sessionFactory);
    }
    
    @Override
    protected Class getEntityClass() {
        return Contato.class;
    }
    
}
