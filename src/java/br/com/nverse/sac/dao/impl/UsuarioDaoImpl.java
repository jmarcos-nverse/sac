/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.nverse.sac.dao.impl;

import br.com.nverse.sac.dao.interfaces.UsuarioDao;
import br.com.nverse.sac.model.security.Usuario;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author JMarcos
 */
@Repository
public class UsuarioDaoImpl extends GenericDaoImpl<Usuario> implements UsuarioDao{

    @Autowired
    public UsuarioDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    protected Class getEntityClass() {
        return Usuario.class;
    }
    
}
