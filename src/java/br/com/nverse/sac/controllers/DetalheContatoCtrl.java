package br.com.nverse.sac.controllers;

import br.com.nverse.sac.dao.interfaces.DetalheContatoDao;
import br.com.nverse.sac.model.DetalheContato;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author jmarcos
 */
@Controller(value = "detalheContatoCtrl")
@Scope(value = "view")
public class DetalheContatoCtrl extends GenericCtrl<DetalheContato> {

    @Autowired
    private DetalheContatoDao detalheContatoDao;
    private DetalheContato detalheContato;
    List<DetalheContato> listaDetalhesContato;
    private TreeNode root;
    private TreeNode selectedNode;
    private List<String> listaDetalhe;
    private String paiSelec;

    public DetalheContatoCtrl() {
    }

    @PostConstruct
    public void preparaView() {
        prepararPesquisar();
        prepararAdicionar();
    }

    @Override
    public void prepararAdicionar() {
        detalheContato = new DetalheContato();
        getLogger().log(Level.WARNING, "Tentando salvar DetalheContato.");
        listaDetalhe = new LinkedList<>();
        listaDetalhesContato = listar();
        for (DetalheContato det : listaDetalhesContato) {
            listaDetalhe.add(det.getCodEstruturado() + " - " + det.getTitulo());
        }
        detalheContato.setCodEstruturado(obtenhaCod(detalheContato.getPai()));
    }

    @Override
    public void prepararEditar() {

        getLogger().log(Level.WARNING, "Tentando salvar DetalheContato.");
        listaDetalhe = new LinkedList<>();
        listaDetalhesContato = listar();
        listaDetalhesContato.remove(detalheContato);
        if (detalheContato.getPai() != null) {
            paiSelec = detalheContato.getPai().getCodEstruturado() + " - " + detalheContato.getPai().getTitulo();
        } else {
            paiSelec = null;
        }
        for (DetalheContato det : listaDetalhesContato) {
            listaDetalhe.add(det.getCodEstruturado() + " - " + det.getTitulo());
        }
    }

    @Override
    public void salvar(DetalheContato entity) {
    }

    @Override
    public void salvarEntidade() {
        try {

            detalheContatoDao.salvar(detalheContato);
            mostraMsgInfo("Sucesso", "Detalhe de Contato adicionado com sucesso!");
            prepararAddNovo();
        } catch (Exception e) {
            FacesContext.getCurrentInstance()
                    .addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Problemas ao salvar detalhe",
                    "Problemas ao salvar detalhe, verifique os dados inseridos."));

            getLogger().log(Level.WARNING, "Problemas ao salvar DetalheContato.", e);
        }
    }

    @Override
    public void atualizar(DetalheContato entity) {
        try {
            if (entity != null) {
                detalheContato = entity;
                prepararEditar();
                return;
            } else if (selectedNode != null) {
                detalheContato = (DetalheContato) selectedNode.getData();
                prepararEditar();
            } else {
                throw new IllegalStateException("Entidade e Selected Node Null.");
            }
        } catch (IllegalStateException ex) {

            Logger.getLogger(DetalheContatoCtrl.class.getName())
                    .log(Level.SEVERE, "Problemas ao Editar Detalhe de Contato.", ex);


        } catch (Exception ex) {
            Logger.getLogger(DetalheContatoCtrl.class.getName())
                    .log(Level.SEVERE, "Problemas ao editar Detalhe de Contato.", ex);
            mostraMsgErro("Problemas.", "Problemas ao editar Detalhe de Contato.");
        }
    }

    @Override
    public void remover(DetalheContato entity) {
        try {
            if (entity != null) {

                detalheContatoDao.remover(entity);

            } else if (selectedNode != null) {
                detalheContatoDao.remover((DetalheContato) selectedNode.getData());
            } else {
                throw new IllegalStateException("Entidade e Selected Node Null.");
            }

            mostraMsgInfo("Sucesso!", "Sucesso ao exluir Detalhe de Contato");

            excluiDaArvore(root, selectedNode);


        } catch (IllegalStateException ex) {

            Logger.getLogger(DetalheContatoCtrl.class.getName())
                    .log(Level.SEVERE, "Problemas ao excluir Detalhe de Contato.", ex);


        } catch (Exception ex) {
            Logger.getLogger(DetalheContatoCtrl.class.getName())
                    .log(Level.SEVERE, "Problemas ao excluir Detalhe de Contato.", ex);
            mostraMsgErro("Problemas.", "Problemas ao excluir Detalhe de Contato.");
        }
    }

    @Override
    public DetalheContato obterPorId(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.3qw[]78+h
    }

    @Override
    public List<DetalheContato> listar() {
        return detalheContatoDao.listar();
    }

    public TreeNode getRoot() {
        if (root == null) {
            root = montarArvore();
        }

        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    private TreeNode montarArvore() {

        TreeNode raiz = new DefaultTreeNode("Raiz", null);
        raiz.setExpanded(true);
        preenchaNodeComFilhos(raiz, null);
//        TreeNode no = new DefaultTreeNode("", raiz);
        return raiz;
    }

    private void preenchaNodeComFilhos(TreeNode node, DetalheContato pai) {

        List<DetalheContato> lista = detalheContatoDao.acheFilhosDe(pai);
        for (DetalheContato d : lista) {

            TreeNode filho = new DefaultTreeNode(d, node);
            filho.setExpanded(true);
            preenchaNodeComFilhos(filho, d);

        }
    }

    public void selecionaComoPai(DetalheContato entity) {
        try {
            if (entity != null) {
                paiSelec = entity.getTitulo();
                detalheContato.setPai(entity);
                return;
            } else if (selectedNode != null) {
                DetalheContato det = (DetalheContato) selectedNode.getData();
                paiSelec = det.getCodEstruturado() + " - " + det.getTitulo();
                detalheContato.setPai((DetalheContato) selectedNode.getData());
            } else {
                throw new IllegalStateException("Entidade e Selected Node Null.");
            }
            String codEstr = detalheContato.getPai().getCodEstruturado() + "." + obtenhaCod(detalheContato.getPai());

            detalheContato.setCodEstruturado(codEstr);


        } catch (IllegalStateException ex) {

            Logger.getLogger(DetalheContatoCtrl.class.getName())
                    .log(Level.SEVERE, "Problemas ao selecionar Detalhe de Contato.", ex);


        } catch (Exception ex) {
            Logger.getLogger(DetalheContatoCtrl.class.getName())
                    .log(Level.SEVERE, "Problemas ao selecionar Detalhe de Contato.", ex);
            mostraMsgErro("Problemas.", "Problemas ao selecionar Detalhe de Contato.");
        }

    }

    public DetalheContato getDetalheContato() {
        return detalheContato;
    }

    public void setDetalheContato(DetalheContato detalheContato) {
        this.detalheContato = detalheContato;
    }

    public List<String> getListaDetalhe() {
        return listaDetalhe;
    }

    public void setListaDetalhe(List<String> listaDetalhe) {
        this.listaDetalhe = listaDetalhe;
    }

    public String getPaiSelec() {
        return paiSelec;
    }

    public void setPaiSelec(String paiSelec) {
        this.paiSelec = paiSelec;
    }

    public List<DetalheContato> getListaDetalhesContato() {
        return listaDetalhesContato;
    }

    public void setListaDetalhesContato(List<DetalheContato> listaDetalhesContato) {
        this.listaDetalhesContato = listaDetalhesContato;
    }

    private String obtenhaCod(DetalheContato det) {
        return (detalheContatoDao.obtenhaUltimoCodigo(det) + 1) + "";
    }

    private boolean excluiDaArvore(TreeNode pai, TreeNode excluir) {
        if (pai.getChildren().contains(excluir)) {
            pai.getChildren().remove(excluir);
            return true;
        } else {
            for (TreeNode det : pai.getChildren()) {
                if (excluiDaArvore(det, excluir)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void prepararAddNovo() {
        root = montarArvore();
        detalheContato = new DetalheContato();
        detalheContato.setCodEstruturado(obtenhaCod(null));
    }
    
    public String obtenhaindiceNo(DetalheContato no){
        if(listaDetalhesContato.indexOf(no) %2 ==0){
            return "linhaPar";
        }
        return "linhaImpar";
    }
}
