/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nverse.sac.dao.impl;

import br.com.nverse.sac.dao.interfaces.FilialDao;
import br.com.nverse.sac.model.Filial;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jmarcos
 */
@Repository
public class FilialDaoImpl extends GenericDaoImpl<Filial> implements FilialDao {

    private SessionFactory sessionFactory;
   

    @Autowired
    public FilialDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);

    }

    @Override
    protected Class getEntityClass() {
        return Filial.class;
    }
}
