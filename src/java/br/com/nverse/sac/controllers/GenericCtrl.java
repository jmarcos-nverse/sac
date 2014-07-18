package br.com.nverse.sac.controllers;

import java.io.Serializable;
import java.util.List;
import br.com.nverse.sac.model.Entidade;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

/**
 *
 * @author JMarcos_Nverse
 */
public abstract class GenericCtrl<T extends Entidade> implements Serializable {

    protected static final long serialVersionUID = 1L;
    /*  VIEWSTATE   */
    protected ViewState state;

    public boolean isPesquisarState() {
        return state.equals(ViewState.PESQUISAR);
    }
    
    public boolean isVisualizarState() {
        return state.equals(ViewState.VISUALIZAR);
    }

    public boolean isEditarState() {
        return state.equals(ViewState.EDITAR);
    }

    public boolean isAdicionarState() {
        return state.equals(ViewState.ADICIONAR);
    }

    public abstract void prepararAdicionar();

    public void prepararEditar() {
        state = ViewState.EDITAR;
    }

    public void prepararPesquisar() {
        state = ViewState.PESQUISAR;

    }
    
    public void prepararVisualizar() {
        state = ViewState.VISUALIZAR;

    }

    public ViewState getState() {
        return state;
    }

    public Logger getLogger() {
        return Logger.getLogger("[SAC]");
    }

    public void mostraMsgErro(String titulo, String erro) {
        mostraMsg(FacesMessage.SEVERITY_ERROR, titulo, erro);
    }
    
    public void mostraMsgWarn(String titulo, String warn) {
        mostraMsg(FacesMessage.SEVERITY_WARN, titulo, warn);
    }
    
    public void mostraMsgInfo(String titulo, String info) {
        mostraMsg(FacesMessage.SEVERITY_INFO, titulo, info);
    }
    
    public void mostraMsg(Severity severity, String titulo, String msg){
        FacesContext.getCurrentInstance().addMessage("sac",
                new FacesMessage(severity, titulo, msg));
    }

    /* CRUD */
    public abstract void salvar(T entity);

    public abstract void salvarEntidade();

    public abstract void atualizar(T entity);

    public abstract void remover(T entity);

    public abstract T obterPorId(Long id);

    public abstract List<T> listar();
}
