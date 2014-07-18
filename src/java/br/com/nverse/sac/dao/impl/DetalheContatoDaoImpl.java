/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nverse.sac.dao.impl;

import br.com.nverse.sac.dao.interfaces.DetalheContatoDao;
import br.com.nverse.sac.model.DetalheContato;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jmarcos
 */
@Repository
@Transactional(readOnly = false)
public class DetalheContatoDaoImpl extends GenericDaoImpl<DetalheContato> implements DetalheContatoDao {

    @Autowired
    public DetalheContatoDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    protected Class getEntityClass() {
        return DetalheContato.class;
    }

    @Override
    public List<DetalheContato> acheFilhosDe(DetalheContato pai) {
        Session currentSession = getSessionFactory().getCurrentSession();

        Criteria c = currentSession.createCriteria(getEntityClass());
        List<DetalheContato> dets = c.list();

        for (Iterator<DetalheContato> it = dets.iterator(); it.hasNext();) {
            DetalheContato d = it.next();

            if (d.getPai() == pai) {
                continue;
            }
            if (d.getPai() != null && pai != null) {
                if (d.getPai().getId().equals(pai.getId())) {
                    continue;
                }
            }

            it.remove();
        }

        return dets;
    }

    @Override
    public int obtenhaUltimoCodigo(DetalheContato pai) {

        List<DetalheContato> dets = acheFilhosDe(pai);
        int size = dets.size();

        return size;

    }
}
