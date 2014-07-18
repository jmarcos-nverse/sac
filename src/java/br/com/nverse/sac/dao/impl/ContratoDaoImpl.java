/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nverse.sac.dao.impl;

import br.com.nverse.sac.dao.interfaces.ContratoDao;
import br.com.nverse.sac.model.Contrato;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class ContratoDaoImpl extends GenericDaoImpl<Contrato> implements ContratoDao {

    
    @Autowired
    public ContratoDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
    
    @Override
    protected Class getEntityClass() {
        return Contrato.class;
    }

    

}
