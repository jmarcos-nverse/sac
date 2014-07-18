/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nverse.sac.dao.impl;

import br.com.nverse.sac.dao.interfaces.ClienteDao;
import br.com.nverse.sac.model.Cliente;
import br.com.nverse.sac.model.Contato;
import br.com.nverse.sac.model.Endereco;
import br.com.nverse.sac.model.enums.SituacaoContrato;
import br.com.nverse.sac.model.enums.UF;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
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
public class ClienteDaoImpl extends GenericDaoImpl<Cliente> implements ClienteDao {

    private SessionFactory sessionFactory;
    private String detalheErro;

    @Autowired
    public ClienteDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);

    }

    @Override
    public void salvar(Cliente cliente) throws Exception {

//        try {
//            Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
//            session.setFlushMode(FlushMode.COMMIT);
//            Transaction tx = session.getTransaction();
//            if (!tx.isActive()) {
//                tx.begin();
//            }
//
//            if (contatoValido(cliente.getContato())) {
//                session.save(cliente.getContato());
//            } else {
//                throw new SacException("Contato inválido. " + detalheErro);
//            }
//
//            if (enderecoValido(cliente.getEnderecoPrimario())) {
//                session.save(cliente.getEnderecoPrimario());
//            } else {
//                tx.rollback();
//                throw new SacException("Endereço Primário deve ser válido. " + detalheErro);
//
//            }
//
//            if (enderecoValido(cliente.getEnderecoSecundario())) {
//                session.save(cliente.getEnderecoSecundario());
//            }
//            if (clienteValido(cliente)) {
//                session.save(cliente);
//            } else {
//                tx.rollback();
//                throw new SacException("Cliente inválido. " + detalheErro);
//            }
//            tx.commit();
//            session.close();
//
//        } catch (Exception he) {
//            logger.warn("Bugou", he);
//            throw he;
//        }
        super.salvar(cliente);

    }

    @Override
    public List<Cliente> listarPorNomes(String nome) {
        Criteria c = getSession().createCriteria(Cliente.class);

        c.add(Restrictions.or(
                Restrictions.ilike("nome", nome, MatchMode.ANYWHERE),
                Restrictions.ilike("razaoSocial", nome, MatchMode.ANYWHERE)));

        return c.list();

    }

    @Override
    public List<Cliente> listarPorNomes(String codigoPequisa, String nomePequisa,
            String placaPequisa, String ufPequisa, String situacao) {

        Criteria c = getSession().createCriteria(Cliente.class);

        Disjunction d = Restrictions.disjunction();

        if (codigoPequisa != null && !codigoPequisa.equals("")) {
            d.add(Restrictions.ilike("codigo", codigoPequisa, MatchMode.ANYWHERE));
        }
        if (nomePequisa != null && !nomePequisa.equals("")) {
            d.add(Restrictions.ilike("nome", nomePequisa, MatchMode.ANYWHERE));
            d.add(Restrictions.ilike("razaoSocial", nomePequisa, MatchMode.ANYWHERE));
        }
        if (ufPequisa != null && !ufPequisa.equals("") && !ufPequisa.equals("---")) {
            c.createAlias("enderecoPrimario", "endp");
            c.createAlias("enderecoSecundario", "ends");
            d.add(Restrictions.eq("endp.uf", ufPequisa));
            d.add(Restrictions.eq("ends.uf", ufPequisa));
        }
        if (placaPequisa != null && !placaPequisa.equals("")) {
            c.createAlias("veiculos", "vei");
            d.add(Restrictions.ilike("vei.placa", placaPequisa, MatchMode.ANYWHERE));
        }

        if (situacao != null && !situacao.equals("Todos")) {
            c.createAlias("contratos", "con");
            for (SituacaoContrato s : SituacaoContrato.values()) {
                if (s.getValor().equals(situacao)) {
                    d.add(Restrictions.eq("con.situacao", s));
                }
            }

        }
        c.setFetchMode("contratos", FetchMode.SELECT);

        c.add(d);

        List<Cliente> clis = c.list();

        for (Cliente cliente : clis) {
            Hibernate.initialize(cliente.getContratos());
        }

        return clis;
    }

    @Override
    public Cliente obterPorId(Long id) {
        Criteria criteria = getSession().createCriteria(getEntityClass());

        criteria.add(Restrictions.eq("id", id));
        Cliente result = (Cliente) criteria.uniqueResult();

        Hibernate.initialize(result);
        Hibernate.initialize(result.getAgregados());
        Hibernate.initialize(result.getFiliais());
        Hibernate.initialize(result.getRepresentantes());
        Hibernate.initialize(result.getSocios());
        Hibernate.initialize(result.getVeiculos());

        return result;
    }

    @Override
    protected Class getEntityClass() {
        return Cliente.class;
    }

    @Override
    public String obterUltimoCodigo() {

        Criteria crit = getSession().createCriteria(Cliente.class);
        crit.setProjection(Projections.max("codigo"));
        String result = (String) crit.uniqueResult();
        String max = "";
        if (result == null) {
            max = "00001";
        } else {
            max = Integer.parseInt(result) + 1 + "";
        }

        int j = 5 - max.length();

        for (int i = 0; i < j; i++) {
            max = "0".concat(max);
        }
        return max;
    }

    private boolean enderecoValido(Endereco endereco) {

        if (endereco.getLogradouro() == null || endereco.getLogradouro().trim().isEmpty()) {
            detalheErro = "Endereço inválido, o campo logradouro está vazio.";
            return false;
        }

        if (endereco.getBairro() == null || endereco.getBairro().trim().isEmpty()) {
            detalheErro = "Endereço inválido, o campo bairro está vazio.";
            return false;
        }

        if (endereco.getCidade() == null || endereco.getCidade().trim().isEmpty()) {
            detalheErro = "Endereço inválido, o campo cidade está vazio.";
            return false;
        }

        if (endereco.getUf() == null || endereco.getUf().equals("---")) {
            detalheErro = "Endereço inválido, o campo UF está vazio.";
            return false;
        }

        return true;

    }

    private boolean contatoValido(Contato contato) {

        if (contato.getTelefone1() == null
                || contato.getTelefone1().replace("-", "")
                .replace("(", "").replace(")", "").isEmpty()) {

            detalheErro = "Telefone 1 deve ser preenchido.";
            return false;

        }

        return true;

    }

    private boolean clienteValido(Cliente cliente) {

        if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()) {
            detalheErro = "Nome Fantasia deve ser preenchido.";
            return false;
        }

        if (cliente.getCpfcnpj() == null || cliente.getCpfcnpj()
                .trim().replace(".", "").replace("-", "").isEmpty()) {
            detalheErro = "CNPJ deve ser preenchido.";
            return false;
        }

        if (cliente.getRazaoSocial() == null || cliente.getRazaoSocial().trim().isEmpty()) {
            detalheErro = "Razão Social deve ser preenchida.";
            return false;
        }

        if (cliente.getCodigo() == null || cliente.getCodigo().trim().isEmpty()) {
            detalheErro = "Código deve ser preenchido.";
            return false;
        }

        if (!getSession().createCriteria(Cliente.class)
                .add(Restrictions.eq("codigo", cliente.getCodigo()))
                .list().isEmpty()) {
            detalheErro = "Código já existe";
            return false;
        }

        return true;
    }

    @Override
    public int obtenhaQuantidadePorUF(UF estado) {

        Criteria c = getSession().createCriteria(Cliente.class);

        c.createAlias("enderecoPrimario", "endp");
        c.createAlias("enderecoSecundario", "ends");
        Disjunction d = Restrictions.disjunction();
        d.add(Restrictions.eq("endp.uf", estado.getSigla()));
        d.add(Restrictions.eq("ends.uf", estado.getSigla()));
        c.setProjection(Projections.count("codigo"));
        c.add(d);
        
        return (int)c.uniqueResult();

    }

}
