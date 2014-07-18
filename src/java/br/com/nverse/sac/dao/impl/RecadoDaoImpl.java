/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nverse.sac.dao.impl;

import br.com.nverse.sac.dao.interfaces.RecadoDao;
import br.com.nverse.sac.model.Recado;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jmarcos
 */
@Repository
public class RecadoDaoImpl extends GenericDaoImpl<Recado> implements RecadoDao{

    @Autowired
    public RecadoDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
    
    @Override
    protected Class getEntityClass() {
        return Recado.class;
    }

    @Override
    public List<Recado> getListaRecadosValidosNaData(Date hoje) {
        Criteria c = getSession().createCriteria(getEntityClass());
       
         List<Recado> list = c.list();
        
        for (Iterator<Recado> it = list.iterator(); it.hasNext();) {
            Recado recado = it.next();        
            if(hoje.after(recado.getDataFim())){
                it.remove();
                continue;
            }
            if(hoje.before(recado.getDataInicio())){
                it.remove();
                continue;
            }
            
        }
        return list;
    }
    
}
