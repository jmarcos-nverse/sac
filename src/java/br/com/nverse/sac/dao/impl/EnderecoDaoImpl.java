/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nverse.sac.dao.impl;

import br.com.nverse.sac.dao.interfaces.EnderecoDao;
import br.com.nverse.sac.model.Endereco;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author JMarcos_Nverse
 */
@Repository
public class EnderecoDaoImpl extends GenericDaoImpl<Endereco> implements EnderecoDao{

    @Autowired
    public EnderecoDaoImpl(SessionFactory sessionFactory){
        super(sessionFactory);
    }
    
    @Override
    protected Class getEntityClass() {
        return Endereco.class;
    }
    
}
