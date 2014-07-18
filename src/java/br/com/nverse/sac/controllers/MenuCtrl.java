/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nverse.sac.controllers;

import com.sun.el.MethodExpressionImpl;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.faces.context.FacesContext;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuItem;
import org.primefaces.model.menu.MenuModel;
import org.primefaces.model.menu.Submenu;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author JMarcos_Nverse
 */
@Controller(value = "menuCtrl")
@Scope("session")
public class MenuCtrl implements Serializable {

    private List<DefaultState> viewStates;

    public MenuModel getMenu() {

        try {



            DefaultMenuModel menu = new DefaultMenuModel();

            DefaultMenuItem inicio = new DefaultMenuItem("Início", "ui-icon-home");
            inicio.setUrl("/index.sac");
            inicio.setUpdate("@form");

            menu.addElement(inicio);
            
            DefaultSubMenu cliente = new DefaultSubMenu("Clientes", "ui-icon-person");
            
            DefaultMenuItem addCliente = new DefaultMenuItem("Adicionar Cliente", "ui-icon-pencil");
            addCliente.setAjax(false);
            addCliente.setUpdate("@all");
            addCliente.setCommand("#{menuCtrl.addDefaultStateClienteAdicionar()}");
            
            DefaultMenuItem pesqCliente = new DefaultMenuItem("Pesquisar Cliente", "ui-icon-search");
            pesqCliente.setAjax(false);
            pesqCliente.setUpdate("@all");
            pesqCliente.setCommand("#{menuCtrl.addDefaultStateClientePesquisar()}");
            
            cliente.addElement(addCliente);
            cliente.addElement(pesqCliente);
            
            DefaultSubMenu subCadastro = new DefaultSubMenu("Cadastros", "ui-icon-pencil");

            
            menu.addElement(cliente);


//            menu.addMenuItem(addCliente);

            DefaultMenuItem addDetalhe = new DefaultMenuItem("Detalhes de Contato", "ui-icon-document-b");
            addDetalhe.setUrl("/detalhecontato.sac");
            addDetalhe.setAjax(false);
            addDetalhe.setUpdate("@form");

            DefaultMenuItem addRecado = new DefaultMenuItem("Adicionar Recado", "ui-icon-comment");
            addRecado.setUrl("/recado.sac");
            addRecado.setAjax(false);
            addRecado.setUpdate("@form");

            
            subCadastro.getElements().add(addDetalhe);
            subCadastro.getElements().add(addRecado);

//            menu.addMenuItem(addDetalhe);

            menu.addElement(subCadastro);

            Submenu subProtocolo = new DefaultSubMenu("Protocolo", "ui-icon-document");

            DefaultMenuItem addProtocolo = new DefaultMenuItem("Adicionar Protocolo", "ui-icon-pencil");
            addProtocolo.setAjax(false);
            addProtocolo.setUpdate("@form");
//             MethodExpression addProtExpr =
//                    obtenhaMethodExpression("#{menuCtrl.addDefaultStateProtocoloAdicionar()}");
            addProtocolo.setCommand("#{menuCtrl.addDefaultStateProtocoloAdicionar()}");
//            addProtocolo.setUrl("/protocolo.sac");
//            addDefaultState("protocolo.sac", ViewState.ADICIONAR);

            DefaultMenuItem pesqProtocolo = new DefaultMenuItem("Pesquisar Protocolo", "ui-icon-search");
            pesqProtocolo.setAjax(false);
            pesqProtocolo.setUpdate("@form");

//            pesqProtocolo.setUrl("/protocolo.sac");
//            MethodExpression pesqProtExpr =
//                    obtenhaMethodExpression("#{menuCtrl.addDefaultStateProtocoloPesquisar()}");
            pesqProtocolo.setCommand("#{menuCtrl.addDefaultStateProtocoloPesquisar()}");
//            addDefaultState("protocolo.sac", ViewState.PESQUISAR);

            subProtocolo.getElements().add(addProtocolo);
            subProtocolo.getElements().add(pesqProtocolo);

            menu.addElement(subProtocolo);
//            menu.addMenuItem(addProtocolo);


            menu.generateUniqueIds();
            return menu;

        } catch (Exception e) {
            Logger.getLogger("SAC").log(Level.WARNING, "Problemas ao criar o menu", e);
            return null;
        }
    }

    private MethodExpression obtenhaMethodExpression(String expressao) {
        ExpressionFactory ef = ExpressionFactory.newInstance();
        ELContext ec = FacesContext.getCurrentInstance().getELContext();

        return ef.createMethodExpression(ec,
                expressao,
                null, new Class[]{});
    }

    private void addDefaultState(String view, ViewState state) {
        if (viewStates == null) {
            viewStates = new LinkedList<>();
        }
        viewStates.add(new DefaultState(view, state));
    }

    public ViewState buscarDefaultState(String view) {

        try {
            for (Iterator<DefaultState> it = viewStates.iterator(); it.hasNext();) {
                DefaultState ds = it.next();

                if (ds.getView().equalsIgnoreCase(view)) {
                    it.remove();
                    return ds.getState();
                }
            }

        } catch (Exception e) {
        }
        return null;
    }

    /* DEFAULT STATES PARA AS PÁGINAS */
    public String addDefaultStateProtocoloPesquisar() {
        addDefaultState("protocolo.sac", ViewState.PESQUISAR);
        return "irParaProtocolo";
    }

    public String addDefaultStateProtocoloAdicionar() {
        addDefaultState("protocolo.sac", ViewState.ADICIONAR);
        return "irParaProtocolo";
    }

    public String addDefaultStateClientePesquisar() {
        addDefaultState("cliente.sac", ViewState.PESQUISAR);
        return "irParaCliente";
    }

    public String addDefaultStateClienteAdicionar() {
        addDefaultState("cliente.sac", ViewState.ADICIONAR);
        return "irParaCliente";
    }
}

class DefaultState {

    private String view;
    private ViewState state;

    public DefaultState(String view, ViewState state) {
        this.view = view;
        this.state = state;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public ViewState getState() {
        return state;
    }

    public void setState(ViewState state) {
        this.state = state;
    }
}
