/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nverse.sac.controllers;

import br.com.nverse.sac.dao.interfaces.ClienteDao;
import br.com.nverse.sac.dao.interfaces.DetalheContatoDao;
import br.com.nverse.sac.dao.interfaces.ProtocoloDao;
import br.com.nverse.sac.model.Cliente;
import br.com.nverse.sac.model.DetalheContato;
import br.com.nverse.sac.model.Protocolo;
import br.com.nverse.sac.model.enums.StatusProtocolo;
import br.com.nverse.sac.model.enums.TipoContato;
import br.com.nverse.sac.util.Utils;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.event.ValueChangeEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author JMarcos_Nverse
 */
@Controller(value = "protocoloCtrl")
@Scope("view")
public class ProtocoloCtrl extends GenericCtrl<Protocolo> {

    @Autowired
    MenuCtrl menuCtrl;
    @Autowired
    private ProtocoloDao protocoloDao;
    @Autowired
    private ClienteDao clienteDao;
    @Autowired
    private DetalheContatoDao detalheContatoDao;
    private TreeNode rootDetCont;
    private TreeNode selectedDetContNode;
    private String nomePequisaCliente;
    private String codigoPequisaCliente;
    private String ufPequisaCliente;
    private String sitContratoPesquisaCliente;
    private String placaPequisaCliente;
    private List<Cliente> listaClientes;
    private Protocolo protocolo;
    private List<Protocolo> listaProtocolo;
    private String numPesqProtocolo;
    private String contrClientPesqProtocolo;
    private String fantasiaPesqProtocolo;

    public ProtocoloCtrl() {
//        this.state = ViewState.PESQUISAR;
    }

    @PostConstruct
    public void preenchaDadosProtocolo() {

        this.state = menuCtrl.buscarDefaultState("protocolo.sac");

        if (state == null) {
            this.state = ViewState.ADICIONAR;
        }
        protocolo = new Protocolo();
        protocolo.setPessoaIdentificada(true);
        protocolo.setPessoaPne(false);

        protocolo.setDataContato(new Date());
        protocolo.setNumProtocolo(geraNovoNumero());
//        listaProtocolo = obtenhaAlgunsProtocolos();

    }

    @Override
    public void prepararAdicionar() {
        listaClientes = new LinkedList<>();
        this.state = ViewState.ADICIONAR;
    }

    @Override
    public void prepararPesquisar() {
        super.prepararPesquisar();
        this.listaProtocolo = new LinkedList<>();
        menuCtrl.addDefaultStateProtocoloPesquisar();
    }

    @Override
    public void salvar(Protocolo entity) {
    }

    @Override
    public void salvarEntidade() {
        try {
            if (verificaDadosPreenchidos()) {
                protocoloDao.salvar(protocolo);
                mostraMsgInfo("Sucesso", "Protocolo salvo com sucesso!");
                prepararPesquisar();
            }

        } catch (Exception e) {
            mostraMsgErro("Falha!", "Problemas para salvar o protocolo.");
            getLogger().log(Level.WARNING, "Problemas ao salvar Protocolo", e);
        }
    }

    @Override
    public void atualizar(Protocolo entity) {
        
    }

    @Override
    public void remover(Protocolo entity) {
        try {
            protocoloDao.remover(entity);
            listaProtocolo = new LinkedList<>();
        } catch (Exception ex) {
            mostraMsgErro("Falha", "Falha ao tentar excluir o Protocolo.");
            Logger.getLogger(ProtocoloCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void visualizarProtocolo(Protocolo prot){
        protocolo = protocoloDao.obterPorId(prot.getId());
        state = ViewState.EDITAR;
    }

    @Override
    public List<Protocolo> listar() {
        return new LinkedList<>();
    }

    public void inserirDetCont(DetalheContato node) {
        if (Utils.isNullOrEmpty(protocolo.getDetalheContato())) {
            protocolo.setDetalheContato(new LinkedList<DetalheContato>());
        }

        if (Utils.isNotNull(node)) {
            protocolo.getDetalheContato().add(node);
        } else {
            protocolo.getDetalheContato().add((DetalheContato) selectedDetContNode.getData());
        }


    }

    private Long geraNovoNumero() {

        return protocoloDao.obtenhaUltimoNumero() + 1;

    }

    public Protocolo getProtocolo() {
        if (protocolo == null) {
            protocolo = new Protocolo();
        }
        return protocolo;
    }

    public void setProtocolo(Protocolo protocolo) {
        this.protocolo = protocolo;
    }

    public List<TipoContato> getListaTiposContato() {
        List<TipoContato> lista = new LinkedList<>();
        lista.addAll(Arrays.asList(TipoContato.values()));
        return lista;
    }

    public List<String> getListaOrigemContato() {
        List<String> lista = new LinkedList<>();

        lista.add("Celular");
        lista.add("Fixo");
        lista.add("Público");

        return lista;
    }

    public List<StatusProtocolo> getListaStatusProtocolo() {
        List<StatusProtocolo> lista = new LinkedList<>();
        lista.addAll(Arrays.asList(StatusProtocolo.values()));
        return lista;
    }

    public String getNomePequisaCliente() {
        return nomePequisaCliente;
    }

    public void setNomePequisaCliente(String nomePequisaCliente) {
        this.nomePequisaCliente = nomePequisaCliente;
    }

    public String getCodigoPequisaCliente() {
        return codigoPequisaCliente;
    }

    public void setCodigoPequisaCliente(String codigoPequisaCliente) {
        this.codigoPequisaCliente = codigoPequisaCliente;
    }

    public String getUfPequisaCliente() {
        return ufPequisaCliente;
    }

    public void setUfPequisaCliente(String ufPequisaCliente) {
        this.ufPequisaCliente = ufPequisaCliente;
    }

    public String getSitContratoPesquisaCliente() {
        return sitContratoPesquisaCliente;
    }

    public void setSitContratoPesquisaCliente(String sitContratoPesquisaCliente) {
        this.sitContratoPesquisaCliente = sitContratoPesquisaCliente;
    }

    public String getPlacaPequisaCliente() {
        return placaPequisaCliente;
    }

    public void setPlacaPequisaCliente(String placaPequisaCliente) {
        this.placaPequisaCliente = placaPequisaCliente;
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public void listaClientesPorCampos() {
        listaClientes =
                clienteDao.listarPorNomes(codigoPequisaCliente, nomePequisaCliente,
                placaPequisaCliente, ufPequisaCliente, sitContratoPesquisaCliente);
    }

    public void pesquisaPorNomeRazao(ValueChangeEvent e) {
        listaClientes = clienteDao.listarPorNomes("", nomePequisaCliente, "", "", "");
    }

    public void pesquisaPorCodigo(ValueChangeEvent e) {
        listaClientes = clienteDao.listarPorNomes(codigoPequisaCliente, "", "", "", "");
    }

    public void selecionarCliente(Cliente cli) {

        protocolo.setCliente(cli);
    }

    public TreeNode getRootDetCont() {
        if (rootDetCont == null) {
            rootDetCont = montarArvore();
        }
        return rootDetCont;
    }

    public void setRootDetCont(TreeNode rootDetCont) {
        this.rootDetCont = rootDetCont;
    }

    private TreeNode montarArvore() {

        TreeNode raiz = new DefaultTreeNode("Raiz", null);
        raiz.setExpanded(true);
        preenchaNodeComFilhos(raiz, null);
//        TreeNode no = new DefaultTreeNode("", raiz);
        return raiz;
    }

    public void removeDetalheCont(DetalheContato det) {
        protocolo.getDetalheContato().remove(det);
    }

    private void preenchaNodeComFilhos(TreeNode node, DetalheContato pai) {

        List<DetalheContato> lista = detalheContatoDao.acheFilhosDe(pai);
        for (DetalheContato d : lista) {

            TreeNode filho = new DefaultTreeNode(d, node);
            filho.setExpanded(true);
            preenchaNodeComFilhos(filho, d);

        }
    }

    public TreeNode getSelectedDetContNode() {
        return selectedDetContNode;
    }

    public void setSelectedDetContNode(TreeNode selectedDetContNode) {
        this.selectedDetContNode = selectedDetContNode;
    }

    public List<Protocolo> getListaProtocolo() {
        return listaProtocolo;
    }

    public void setListaProtocolo(List<Protocolo> listaProtocolo) {
        this.listaProtocolo = listaProtocolo;
    }

    public String getNumPesqProtocolo() {
        return numPesqProtocolo;
    }

    public void setNumPesqProtocolo(String numPesqProtocolo) {
        this.numPesqProtocolo = numPesqProtocolo;
    }

    public String getContrClientPesqProtocolo() {
        return contrClientPesqProtocolo;
    }

    public void setContrClientPesqProtocolo(String contrClientPesqProtocolo) {
        this.contrClientPesqProtocolo = contrClientPesqProtocolo;
    }

    public String getFantasiaPesqProtocolo() {
        return fantasiaPesqProtocolo;
    }

    public void setFantasiaPesqProtocolo(String fantasiaPesqProtocolo) {
        this.fantasiaPesqProtocolo = fantasiaPesqProtocolo;
    }

    public void listeProtocolosPorNomes() {
        listaProtocolo = protocoloDao.listarPorNomes(numPesqProtocolo, contrClientPesqProtocolo, fantasiaPesqProtocolo);
    }

    private List<Protocolo> obtenhaAlgunsProtocolos() {
        return protocoloDao.listar(50);
    }

    @Override
    public Protocolo obterPorId(Long id) {
        return new Protocolo();
    }

    private boolean verificaDadosPreenchidos() {
        boolean valido = true;
        if (protocolo.getCliente() == null) {
            mostraMsgErro("Cliente inválido!", "Selecione um cliente.");
            valido = false;
        }
        if (Utils.isNullOrEmpty(protocolo.getDetalheContato())) {
            mostraMsgWarn("Dados Inválidos", "Selecione ao menos um Detalhe de Contato.");
            valido = false;

        }
        if (Utils.isNullOrEmpty(protocolo.getNumeroOrigem())) {
            mostraMsgWarn("Dados Inválidos", "Adicione o número de origem.");
            valido = false;
        }
        if (protocolo.getStatus() == null) {
            mostraMsgWarn("Dados Inválidos", "Selecione o Status.");
            valido = false;
        }
        if (protocolo.getOrigemContato() == null) {
            mostraMsgWarn("Dados Inválidos", "Selecione a origem.");
            valido = false;
        }
        return valido;
    }
}