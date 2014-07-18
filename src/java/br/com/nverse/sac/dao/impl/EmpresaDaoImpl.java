/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nverse.sac.dao.impl;

import br.com.nverse.sac.dao.interfaces.EmpresaDao;
import br.com.nverse.sac.model.Empresa;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmpresaDaoImpl extends GenericDaoImpl<Empresa> implements EmpresaDao {

    @Autowired
    public EmpresaDaoImpl(SessionFactory sessionFactory){
        super(sessionFactory);
    }
    
    @Override
    protected Class getEntityClass() {
        return Empresa.class;
    }

   
    
}
