/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nverse.sac.dao.impl;

import br.com.nverse.sac.dao.interfaces.ProtocoloDao;
import br.com.nverse.sac.model.Cliente;
import br.com.nverse.sac.model.Protocolo;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jmarcos
 */
@Repository
public class ProtocoloDaoImpl extends GenericDaoImpl<Protocolo> implements ProtocoloDao {

    @Autowired
    public ProtocoloDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    protected Class getEntityClass() {
        return Protocolo.class;
    }

    @Override
    public long obtenhaUltimoNumero() {
        try {
            Criteria c = getSessionFactory().getCurrentSession().createCriteria(getEntityClass());

            c.setProjection(Projections.max("numProtocolo"));

            return (Long) c.uniqueResult();
        } catch (Exception he) {
            logger.warn("Problemas ao obter número.", he);
            return 0001;
        }
    }

    @Override
    public List<Protocolo> listarPorNomes(String numPesqProtocolo, String contrClientPesqProtocolo, String fantasiaPesqProtocolo) {
        Criteria c = getSessionFactory().getCurrentSession().createCriteria(getEntityClass());
        try {
            if (!(numPesqProtocolo == null) && !numPesqProtocolo.isEmpty() && !numPesqProtocolo.equals("")) {

                Long numProt = Long.parseLong(numPesqProtocolo);
                c.add(Restrictions.eq("numProtocolo", numProt));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        c.createAlias("cliente", "cliente");

        if (!(contrClientPesqProtocolo == null) && !contrClientPesqProtocolo.isEmpty() && !numPesqProtocolo.equals("")) {
            try {

                c.createAlias("cliente.contratos", "contrato");
                c.add(Restrictions.like("contrato.numeroContrato", contrClientPesqProtocolo));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (!(fantasiaPesqProtocolo == null) || !fantasiaPesqProtocolo.isEmpty() || !numPesqProtocolo.equals("")) {
            c.add(Restrictions.ilike("cliente.nome", fantasiaPesqProtocolo, MatchMode.ANYWHERE));
        }
        c.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        List<Protocolo> list = c.list();

        return list;

    }


    @Override
    public List<Protocolo> listar(int i) {
        Criteria c = getSessionFactory().getCurrentSession().createCriteria(getEntityClass());

        c.setMaxResults(i);
        return c.list();

    }

    @Override
    public boolean clientePossuiLigacoes(Cliente cliente) {

        Criteria c = getSessionFactory().getCurrentSession().createCriteria(getEntityClass());

        c.add(Restrictions.eq("cliente", cliente));
        c.setProjection(Projections.count("numProtocolo"));

        int i = (Integer) c.uniqueResult();

        return i > 0;

    }
}
