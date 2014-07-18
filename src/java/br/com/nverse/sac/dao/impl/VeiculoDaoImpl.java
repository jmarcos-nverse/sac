/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nverse.sac.dao.impl;

import br.com.nverse.sac.dao.interfaces.VeiculoDao;
import br.com.nverse.sac.model.Veiculo;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jmarcos
 */
@Repository
public class VeiculoDaoImpl extends GenericDaoImpl<Veiculo> implements VeiculoDao {

    private SessionFactory sessionFactory;

    @Autowired
    public VeiculoDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);

    }

    

    @Override
    protected Class getEntityClass() {
        return Veiculo.class;
    }
}
