package br.com.nverse.sac.dao.impl;

import br.com.nverse.sac.dao.interfaces.GenericDao;
import java.util.List;
import br.com.nverse.sac.model.Entidade;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.jboss.logging.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class GenericDaoImpl<T extends Entidade> extends HibernateDaoSupport implements GenericDao<T> {

    protected SessionFactory sessionFactory;

    public GenericDaoImpl(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Override
    public void salvar(T entidade)  throws Exception{
        try {
                Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
            session.setFlushMode(FlushMode.COMMIT);
            Transaction tx = session.getTransaction();
            if (!tx.isActive()) {
                tx.begin();
            }
            session.saveOrUpdate(entidade);

            tx.commit();
//            session.close();
        } catch (HibernateException he) {
            logger.warn("Bugou", he);
            throw he;
        }
    }

    @Override
    public void salvarTodos(List<T> lista)  throws Exception{
        try {
            Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
            session.setFlushMode(FlushMode.COMMIT);
            Transaction tx = session.getTransaction();
            if (!tx.isActive()) {
                tx.begin();
            }
            for (T t : lista) {
                getHibernateTemplate().saveOrUpdate(t);
            }
            
            tx.commit();
//            session.close();

        } catch (HibernateException he) {
            logger.warn("Bugou", he);
            throw he;
        }



    }

    @Override
    public void atualizar(T entidade)  throws Exception{
         try {
            Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
            session.setFlushMode(FlushMode.COMMIT);
            Transaction tx = session.getTransaction();
            if (!tx.isActive()) {
                tx.begin();
            }
            session.update(entidade);

            tx.commit();
            session.close();
        } catch (HibernateException he) {
            logger.warn("Bugou", he);
            throw he;
        }
    }

    @Override
    public void remover(T entidade) throws Exception {
        try {
            Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
            session.setFlushMode(FlushMode.COMMIT);
            Transaction tx = session.getTransaction();
            if (!tx.isActive()) {
                tx.begin();
            }

            session.delete(entidade);

            tx.commit();
            session.close();
            
        } catch (HibernateException he) {
            logger.warn("Bugou", he);
            throw he;
        }

    }

    @Override
    public T obterPorId(Long id) {
        Criteria criteria = getSession().createCriteria(getEntityClass());

        criteria.add(Restrictions.eq("id", id));
        T result = (T) criteria.uniqueResult();
        
        Hibernate.initialize(result);

        return result;
    }

    @Override
    public T obterPor(String propriedade, String valor) {
        Criteria criteria = getSession().createCriteria(getEntityClass());

        criteria.add(Restrictions.eq(propriedade, valor));
        try {
            return (T) criteria.uniqueResult();
        } catch (HibernateException he) {
            Logger.getLogger(getEntityClass()).warn("Não existe um objeto "
                    + " com o identificador " + valor + ", ou existem vários ./n"
                    + "Tentando obter uma lista e pegar o primeiro.", he);
            try {
                return (T) criteria.list().get(0);
            } catch (HibernateException e) {
                Logger.getLogger(getEntityClass()).warn("Problema na obtenção do objeto, " + valor + ".", e);
                return null;
            }
        } catch (Exception e) {
            Logger.getLogger(getEntityClass()).warn("Problema na obtenção do objeto, " + valor + ".", e);
            return null;
        }
    }

    @Override
    public List<T> listarPor(String propriedade, String valor) {
        Criteria criteria = getSession().createCriteria(getEntityClass());

        criteria.add(Restrictions.like(propriedade, valor, MatchMode.ANYWHERE));
        try {
            List<T> lista = criteria.list();
            return lista;
        } catch (HibernateException he) {
            Logger.getLogger(getEntityClass()).warn("Não existe um objeto "
                    + " com o identificador " + valor + ".", he);
            return new ArrayList<>();
        } catch (Exception e) {
            Logger.getLogger(getEntityClass()).warn("Problema na obtenção do objeto, " + valor + ".", e);
            return new ArrayList<>();
        }
    }

    @Override
    public List<T> listar() {
        Criteria criteria = getSession().createCriteria(getEntityClass());

        return criteria.list();
    }

    protected abstract Class getEntityClass();
}
