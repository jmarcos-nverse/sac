/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nverse.sac.dao.impl;

import br.com.nverse.sac.dao.interfaces.ClienteDao;
import br.com.nverse.sac.dao.interfaces.ContatoDao;
import br.com.nverse.sac.dao.interfaces.EnderecoDao;
import br.com.nverse.sac.dao.interfaces.RepresentanteDao;
import br.com.nverse.sac.model.Cliente;
import br.com.nverse.sac.model.Representante;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jmarcos
 */
@Repository
public class RepresentanteDaoImpl extends GenericDaoImpl<Representante> implements RepresentanteDao {

   private SessionFactory sessionFactory;
   @Autowired
   private ContatoDao contatoDao;

    @Autowired
    public RepresentanteDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);

    }

//    @Override
//    public void salvarTodos(List<Representante> lista) throws Exception {
//        for
//    }

    

    @Override
    protected Class getEntityClass() {
        return Representante.class;
    }
}
