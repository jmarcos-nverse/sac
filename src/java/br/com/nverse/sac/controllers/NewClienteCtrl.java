/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nverse.sac.controllers;

import br.com.nverse.sac.dao.interfaces.AgregadoDao;
import br.com.nverse.sac.dao.interfaces.ClienteDao;
import br.com.nverse.sac.dao.interfaces.ContratoDao;
import br.com.nverse.sac.dao.interfaces.FilialDao;
import br.com.nverse.sac.dao.interfaces.HistoricoDao;
import br.com.nverse.sac.dao.interfaces.RepresentanteDao;
import br.com.nverse.sac.dao.interfaces.SocioDao;
import br.com.nverse.sac.dao.interfaces.VeiculoDao;
import br.com.nverse.sac.model.Agregado;
import br.com.nverse.sac.model.Cliente;
import br.com.nverse.sac.model.Contato;
import br.com.nverse.sac.model.Contrato;
import br.com.nverse.sac.model.Historico;
import br.com.nverse.sac.model.Representante;
import br.com.nverse.sac.model.Veiculo;
import br.com.nverse.sac.model.enums.DiaPagamento;
import br.com.nverse.sac.model.enums.SituacaoContrato;
import br.com.nverse.sac.model.enums.UF;
import br.com.nverse.sac.util.exception.SacException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.event.ValueChangeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author jmarcos
 */
@Controller(value = "clienteCtrl")
@Scope("view")
public class NewClienteCtrl extends GenericCtrl<Cliente> {

    @Autowired
    private MenuCtrl menuCtrl;
    @Autowired
    private ClienteDao clienteDao;
    @Autowired
    private ContratoDao contratoDao;
    @Autowired
    private AgregadoDao agregadoDao;
    @Autowired
    private VeiculoDao veiculoDao;
    @Autowired
    private RepresentanteDao representanteDao;
    @Autowired
    private HistoricoDao historicoDao;

    Cliente cliente;
    String dataCadastro;
    Contrato contrato;
    String situacaoContrato;
    String parcela;

    Representante representante;
    Veiculo veiculo;
    Historico historiaAtual;
    Agregado agregadoAtual;
    private String atendente;
    private String solicitante;
    private String sitContratoPesquisa;
    private String descHistorico;
    private String nomePequisa;
    private String codigoPequisa;
    private String ufPequisa;
    private String placaPequisa;
    private List<Cliente> listaClientes;

    public NewClienteCtrl() {
    }

    @PostConstruct
    public void preparaTela() {
        this.state = menuCtrl.buscarDefaultState("cliente.sac");

        if (state == null) {
            this.state = ViewState.PESQUISAR;
        }

        if (isAdicionarState()) {
            prepararAdicionar();
        } else {
            prepararPesquisar();
        }

    }

    @Override
    public void prepararAdicionar() {
        cliente = new Cliente();
        cliente.setDataCadastro(new Date());
        cliente.setCodigo(gereCodigoNovo());
        inicializarVariaveis();
        state = ViewState.ADICIONAR;
    }

    private String gereCodigoNovo() {

        return clienteDao.obterUltimoCodigo();

    }

    @Override
    public void salvar(Cliente entity) {
        try {
            clienteDao.salvar(cliente);
        } catch (Exception ex) {
            Logger.getLogger(NewClienteCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void salvarEntidade() {
        try {
            if (dataCadastro == null || dataCadastro.isEmpty()) {
                cliente.setDataCadastro(new Date());
            } else {
                cliente.setDataCadastro(new Date(dataCadastro));
            }
            if (state.equals(ViewState.EDITAR)) {
                historiaAtual.setAtendente(atendente);
                historiaAtual.setSolicitante(solicitante);
                historiaAtual.setDescricao(descHistorico);
                adicionaHistoriaAtual();

                salveColecoesCliente();
                clienteDao.atualizar(cliente);
            } else {

                clienteDao.salvar(cliente);

                if (cliente.getContratos() != null && !cliente.getContratos().isEmpty()) {
                    adicionaContratoAtual();

                }
                salveColecoesCliente();
            }
            listaClientes = new LinkedList<>();
            prepararPesquisar();
        } catch (SacException e) {
            mostraMsgErro("Problemas ao cadastrar Cliente", e.getMessage());

            Logger.getLogger("SAC").
                    logp(Level.SEVERE, this.getClass().getSimpleName(),
                            "salvarEntidade()", "Bugou: " + e.getMessage(), e);
        } catch (Exception e) {
            Logger.getLogger("SAC").
                    logp(Level.SEVERE, this.getClass().getSimpleName(),
                            "salvarEntidade()", "Bugou", e);
        }
    }

    private void salveColecoesCliente() throws SacException {

        try {

            if (cliente.getRepresentantes() != null && !cliente.getRepresentantes().isEmpty()) {
                representanteDao.salvarTodos(cliente.getRepresentantes());
            } else {
                throw new SacException("Cliente deve possuir ao menos um representante legal.");
            }
            //SALVANDO AGREGADOS
            if (cliente.getAgregados() != null && !cliente.getAgregados().isEmpty()) {
                agregadoDao.salvarTodos(cliente.getAgregados());
            }

            if (cliente.getVeiculos() != null && !cliente.getVeiculos().isEmpty()) {
                veiculoDao.salvarTodos(cliente.getVeiculos());
            }

            if (cliente.getHistoria() != null && !cliente.getHistoria().isEmpty()) {
                historicoDao.salvarTodos(cliente.getHistoria());
            }

            contratoDao.salvarTodos(cliente.getContratos());

        } catch (SacException e) {
            throw e;
        } catch (Exception e) {

            Logger.getLogger("SAC").
                    logp(Level.SEVERE, this.getClass().getSimpleName(),
                            "salveColecoesCliente()", "Bugou", e);
            throw new SacException("Problemas ao gravar os dados de cliente.");
        }

    }

    @Override
    public void atualizar(Cliente entity) {
        try {
            clienteDao.atualizar(entity);
        } catch (Exception ex) {
            Logger.getLogger(NewClienteCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void remover(Cliente entity) {
        try {
            clienteDao.remover(entity);
        } catch (Exception ex) {
            Logger.getLogger(NewClienteCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Cliente obterPorId(Long id) {
        return clienteDao.obterPorId(id);
    }

    @Override
    public List<Cliente> listar() {
        return clienteDao.listar();
    }

    private void inicializarVariaveis() {

        Date data = this.cliente.getDataCadastro();
        String mes = data.getMonth() < 10 ? "0" + data.getMonth() : data.getMonth() + "";
        int ano = data.getYear() + 1900;
        String dia = data.getDate() < 10 ? "0" + data.getDate() : data.getDate() + "";
        dataCadastro = dia + "/" + mes + "/" + ano;

        agregadoAtual = new Agregado();
        veiculo = new Veiculo();
        representante = new Representante();
        contrato = new Contrato();
        historiaAtual = new Historico();
    }

    public List<String> getUfs() {
        List<String> ufs = new LinkedList<String>();
        for (UF u : UF.values()) {
            ufs.add(u.getSigla());
        }
        return ufs;
    }

    public void visualizarCliente(Cliente cliente) {
        super.state = ViewState.VISUALIZAR;
        this.cliente = clienteDao.obterPorId(cliente.getId());

        contrato = cliente.getContratos().get(0);
        situacaoContrato = contrato.getSituacao().getValor();
        inicializarVariaveis();

    }

    public void editarCliente(Cliente cliente) {
        super.state = ViewState.EDITAR;
        this.cliente = clienteDao.obterPorId(cliente.getId());

        contrato = cliente.getContratos().get(0);
        situacaoContrato = contrato.getSituacao().getValor();
        inicializarVariaveis();

    }

    public void adicionaRepresentanteAtual() {
        if (representante.getNome() == null || representante.getNome().trim().isEmpty()) {
            mostraMsgWarn("Problema ao inserir Representante:", "Nome não pode estar vazio.");
            return;
        }
        if (contatoVazio(representante.getContato())) {
            representante.setContato(null);
        }

        if (cliente.getRepresentantes() == null) {
            cliente.setRepresentantes(new LinkedList<Representante>());
        } else if (cliente.getRepresentantes().contains(representante)) {
            removerRepresentante(representante);
        }
        representante.setRepresenta(cliente);
        addDescHistorico(representante.toString());
        cliente.getRepresentantes().add(representante);
        representante = new Representante();
    }

    public void removerRepresentante(Representante rep) {
        removeDescHistorico(rep.toString());
        cliente.getRepresentantes().remove(rep);
        try {
            representanteDao.remover(rep);
        } catch (Exception ex) {
            Logger.getLogger(NewClienteCtrl.class.getName()).log(Level.SEVERE, "Problemas ao excluir Representante.", ex);
            mostraMsgErro("Falha.", "Problemas ao excluir Representante");
        }
    }

    public void limpaRepresentanteAtual() {
        this.representante = new Representante();
    }

    public void editarRepresentante(Representante rep) {
        representante = rep;
    }
    

    public Contrato getContrato() {
        if (cliente.getContratos() == null || cliente.getContratos().isEmpty()) {
            cliente.setContratos(new LinkedList<Contrato>());
            cliente.getContratos().add(0, new Contrato());
        }
        contrato = cliente.getContratos().get(0);
        return contrato;
    }

    public void setContrato(Contrato cont) {
        this.contrato = cont;
    }

    public void limpaContratoAtual() {
        this.contrato = new Contrato();
    }

    public void adicionaContratoAtual() {
        //cliente.getContratos().add(0, contrato);
        try {
            Contrato contratoNew = new Contrato();

            contrato.setCliente(cliente);
            for (DiaPagamento d : DiaPagamento.values()) {
                if (d.getValor().equals(parcela)) {
                    contrato.setParcela(d);
                }
            }
            for (SituacaoContrato s : SituacaoContrato.values()) {
                if (s.getValor().equals(situacaoContrato)) {
                    contrato.setSituacao(s);
                }
            }

            contratoNew.setAtendente(contrato.getAtendente());
            contratoNew.setNumeroContrato(contrato.getNumeroContrato());
            contratoNew.setCliente(contrato.getCliente());
            contratoNew.setDataInicio(contrato.getDataInicio());
            contratoNew.setDataFim(contrato.getDataFim());
            contratoNew.setParcela(contrato.getParcela());
            contratoNew.setSituacao(contrato.getSituacao());
            contratoNew.setValorPorVeiculo(contrato.getValorPorVeiculo());
            contrato = contratoNew;
            cliente.getContratos().add(0, contratoNew);
        } catch (Exception e) {
            mostraMsgWarn("", "Verifique os dados de contrato.");
        }
    }

    public void editarContrato(Contrato con) {
        contrato = con;
    }

    public void limpaVeiculoAtual() {
        this.veiculo = new Veiculo();
    }

    public void adicionaVeiculoAtual() {
        if (veiculo.getPlaca() == null || veiculo.getPlaca().trim().isEmpty()) {
            mostraMsgWarn("Problema ao inserir Veículo:", "Placa não pode ser vazia.");
            return;
        }

        if (cliente.getVeiculos() == null) {
            cliente.setVeiculos(new LinkedList<Veiculo>());
        } else if (cliente.getVeiculos().contains(veiculo)) {
            removerVeiculo(veiculo);
        }

        veiculo.setDono(cliente);
        veiculo.setPlaca(veiculo.getPlaca().toUpperCase());

        addDescHistorico(veiculo.toString());

        cliente.getVeiculos().add(veiculo);
        veiculo = new Veiculo();
    }

    public void removerVeiculo(Veiculo vei) {
        removeDescHistorico(vei.toString());
        cliente.getVeiculos().remove(vei);
        try {
            veiculoDao.remover(vei);
        } catch (Exception ex) {
            Logger.getLogger(NewClienteCtrl.class.getName()).log(Level.SEVERE, "Problemas ao excluir veículo.", ex);
            mostraMsgErro("Falha.", "Problemas ao excluir veículo");
        }
    }
    
    public Agregado getAgregadoAtual() {

        return agregadoAtual;
    }

    public void setAgregadoAtual(Agregado agregadoAtual) {
        this.agregadoAtual = agregadoAtual;
    }

    public void adicionaAgregadoAtual() {
        if (agregadoAtual.getNome() == null || agregadoAtual.getNome().trim().isEmpty()) {
            mostraMsgWarn("Problema ao inserir Agregado:", "Nome Fantasia não pode estar vazio.");
            return;
        }
        if (agregadoAtual.getRazaoSocial() == null || agregadoAtual.getRazaoSocial().trim().isEmpty()) {
            mostraMsgWarn("Problema ao inserir Agregado:", "Razão Social não pode estar vazio.");
            return;
        }
        if (agregadoAtual.getCpfcnpj() == null || agregadoAtual.getCpfcnpj().trim().isEmpty()) {
            mostraMsgWarn("Problema ao inserir Agregado:", "CNPJ não pode estar vazio.");
            return;
        }
        if (contatoVazio(agregadoAtual.getContato())) {
            agregadoAtual.setContato(null);
        }

        if (cliente.getAgregados() == null) {
            cliente.setAgregados(new LinkedList<Agregado>());
        } else if (cliente.getAgregados().contains(agregadoAtual)) {

            cliente.getAgregados().remove(agregadoAtual);
        }
        agregadoAtual.setCliente(cliente);
        cliente.getAgregados().add(agregadoAtual);
        agregadoAtual = new Agregado();
    }

    public void editarAgregado(Agregado agr) {
        agregadoAtual = agr;
    }

    public void removerAgregado(Agregado agr) {
        cliente.getAgregados().remove(agr);
    }

    public void limpaAgregadoAtual() {
        agregadoAtual = new Agregado();
    }


    public void adicionaHistoriaAtual() {
        if (cliente.getHistoria() == null) {
            cliente.setHistoria(new LinkedList<Historico>());
        }

        historiaAtual.setAlvo(cliente);
        cliente.getHistoria().add(historiaAtual);
    }

    public void editarHistoria(Historico his) {
        historiaAtual = his;
    }

    public void removerHistoria(Historico his) {
        cliente.getHistoria().remove(his);
    }

    public Historico getHistoriaAtual() {
        return historiaAtual;
    }

    public void setHistoriaAtual(Historico historiaAtual) {
        this.historiaAtual = historiaAtual;
    }

    public void limpaHistoriaAtual() {
        this.historiaAtual = new Historico();
    }

    public String getNomePequisa() {
        return nomePequisa;
    }

    public void setNomePequisa(String nomePequisa) {
        this.nomePequisa = nomePequisa;
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public void listaPorNome() {
        listaClientes = clienteDao.listarPorNomes(nomePequisa);

    }

    public void listaPorCampos() {
        listaClientes = new LinkedList<>();
        listaClientes
                = clienteDao.listarPorNomes(codigoPequisa, nomePequisa,
                        placaPequisa, ufPequisa, sitContratoPesquisa);
    }

    public void pesquisaPorNomeRazao(ValueChangeEvent e) {
        listaClientes = new LinkedList<>();
        listaClientes = clienteDao.listarPorNomes("", nomePequisa, "", "", "");
    }

    public void pesquisaPorCodigo(ValueChangeEvent e) {
        listaClientes = new LinkedList<>();
        listaClientes = clienteDao.listarPorNomes(codigoPequisa, "", "", "", "");
    }

    public String getCodigoPequisa() {
        return codigoPequisa;
    }

    public void setCodigoPequisa(String codigoPequisa) {
        this.codigoPequisa = codigoPequisa;
    }

    public String getUfPequisa() {
        return ufPequisa;
    }

    public void setUfPequisa(String ufPequisa) {
        this.ufPequisa = ufPequisa;
    }

    public String getPlacaPequisa() {
        return placaPequisa;
    }

    public void setPlacaPequisa(String placaPequisa) {
        this.placaPequisa = placaPequisa;
    }

    private boolean contatoVazio(Contato contato) {

        return ((contato.getTelefone1() == null
                || contato.getTelefone1().replace("-", "").replace("(", "").replace(")", "").isEmpty())
                && (contato.getTelefone2() == null
                || contato.getTelefone2().replace("-", "").replace("(", "").replace(")", "").isEmpty())
                && (contato.getTelefone3() == null
                || contato.getTelefone3().replace("-", "").replace("(", "").replace(")", "").isEmpty())
                && (contato.getEmail() == null
                || contato.getEmail().isEmpty()));

    }

    public List<String> getDiasPagamento() {

        List<String> dias = new LinkedList<>();
        for (DiaPagamento d : DiaPagamento.values()) {
            dias.add(d.getValor());
        }

        return dias;
    }

    public String getValorTotal() {
        return cliente.getVeiculos() != null ? (contrato.getValorPorVeiculo() * cliente.getVeiculos().size()) + "" : "0";
    }

    public String getParcela() {
        if (isEditarState()) {
            parcela = cliente.getContratos().get(0).getParcela().getValor();
        }
        return parcela;
    }

    public void setParcela(String parcela) {
        this.parcela = parcela;
    }

    public List<String> getSitsContrato() {

        List<String> sits = new LinkedList<>();

        for (SituacaoContrato s : SituacaoContrato.values()) {
            sits.add(s.getValor());
        }

        return sits;

    }

    public String getSituacaoContrato() {
        return situacaoContrato;
    }

    public void setSituacaoContrato(String situacaoContrato) {
        this.situacaoContrato = situacaoContrato;
    }

    public String obtenhaIconeSituacao(Cliente cli) {
        switch (cli.getContratos().get(0).getSituacao()) {
            case ATIVO_EMDIA:
                return "/images/custom/ball-green-icon24.png";
            case ATIVO_PENDENCIA:
                return "/images/custom/ball-yallow-icon24.png";
            case INATIVO_DEBITO:
                return "/images/custom/ball-red-icon24.png";
            case INATIVO:
                return "/images/custom/ball-grey-icon24.png";
            default:
                return null;
        }
    }

    public void setDatFim(ValueChangeEvent e) {

        Date d = (Date) e.getNewValue();
        d.setYear(d.getYear() + 1);
        contrato.setDataFim(d);
    }

    public String getSitContratoPesquisa() {
        return sitContratoPesquisa;
    }

    public void setSitContratoPesquisa(String sitContratoPesquisa) {
        this.sitContratoPesquisa = sitContratoPesquisa;
    }

    public String getAtendente() {
        return atendente;
    }

    public void setAtendente(String atendente) {
        this.atendente = atendente;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    public String getDescHistorico() {
        if (descHistorico == null) {
            descHistorico = "";
        }
        return descHistorico;
    }

    public void setDescHistorico(String descHistorico) {
        this.descHistorico = descHistorico;
    }

    private void addDescHistorico(String toString) {
        descHistorico = getDescHistorico().concat("Adicionado/Editado " + toString + "\n");
    }

    private void removeDescHistorico(String toString) {
        descHistorico = getDescHistorico().concat("Removido " + toString + "\n");
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Representante getRepresentante() {
        return representante;
    }

    public void setRepresentante(Representante representante) {
        this.representante = representante;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

}
