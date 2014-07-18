
package br.com.nverse.sac.dao.impl;

import br.com.nverse.sac.dao.interfaces.HistoricoDao;
import br.com.nverse.sac.model.Historico;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HistoricoDaoImpl extends GenericDaoImpl<Historico> implements HistoricoDao {

    
    @Autowired
    public HistoricoDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
    
    @Override
    protected Class getEntityClass() {
        return Historico.class;
    }

    

}
