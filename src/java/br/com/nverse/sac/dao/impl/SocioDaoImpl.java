/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nverse.sac.dao.impl;

import br.com.nverse.sac.dao.interfaces.ContatoDao;
import br.com.nverse.sac.dao.interfaces.RepresentanteDao;
import br.com.nverse.sac.dao.interfaces.SocioDao;
import br.com.nverse.sac.model.Representante;
import br.com.nverse.sac.model.Socio;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jmarcos
 */
@Repository
public class SocioDaoImpl extends GenericDaoImpl<Socio> implements SocioDao {

    @Autowired
    private ContatoDao contatoDao;
    
    private SessionFactory sessionFactory;
   

    @Autowired
    public SocioDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);

    }

    @Override
    public void salvarTodos(List<Socio> lista) {
        for (Socio socio : lista) {
            getHibernateTemplate().saveOrUpdate(socio.getContato());
            getHibernateTemplate().saveOrUpdate(socio);
        }
    }
    
    

    @Override
    protected Class getEntityClass() {
        return Socio.class;
    }
}
