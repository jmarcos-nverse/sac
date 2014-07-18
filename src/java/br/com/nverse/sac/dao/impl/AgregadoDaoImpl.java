/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nverse.sac.dao.impl;

import br.com.nverse.sac.dao.interfaces.AgregadoDao;
import br.com.nverse.sac.dao.interfaces.ContatoDao;
import br.com.nverse.sac.model.Agregado;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jmarcos
 */
@Repository
public class AgregadoDaoImpl extends GenericDaoImpl<Agregado> implements AgregadoDao{
    
    @Autowired
    private ContatoDao contatoDao;
    
    private SessionFactory sessionFactory;

    @Autowired
    public AgregadoDaoImpl(SessionFactory sessionFactory){
        super(sessionFactory);
    }

    @Override
    public void salvarTodos(List<Agregado> lista) {
        
        for (Agregado agregado : lista) {
            getHibernateTemplate().saveOrUpdate(agregado.getContato());
            getHibernateTemplate().saveOrUpdate(agregado);
        }
    }
    
    @Override
    protected Class getEntityClass() {
        return Agregado.class;
    }
    
}
