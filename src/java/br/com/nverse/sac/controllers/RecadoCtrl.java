/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nverse.sac.controllers;

import br.com.nverse.sac.dao.interfaces.RecadoDao;
import br.com.nverse.sac.model.Recado;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author jmarcos
 */
@Controller(value = "recadoCtrl")
@Scope("view")
public class RecadoCtrl extends GenericCtrl<Recado>{
    
    @Autowired
    RecadoDao recadoDao;
    
    Recado recado;
    List<Recado> listaRecados;

    public RecadoCtrl() {
    }
    
    
    
    
    @PostConstruct
    public void preparaTela(){
        listaRecados = recadoDao.listar();
        prepararAdicionar();
    }

    @Override
    public void prepararAdicionar() {
        this.state = ViewState.ADICIONAR;
        recado = new Recado();
    }

    @Override
    public void salvar(Recado entity) {
        try {
            recadoDao.salvar(entity);
            mostraMsgInfo("Sucesso!", "Recado salvo com sucesso.");
            recado = new Recado();
            listaRecados = listar();
        } catch (Exception ex) {
            Logger.getLogger(RecadoCtrl.class.getName()).log(Level.SEVERE, "Problema ao salvar Recado", ex);
            mostraMsgErro("Erro", "Problema ao salvar Recado");
        }
    }

    @Override
    public void salvarEntidade() {
        try {
            recadoDao.salvar(recado);
            mostraMsgInfo("Sucesso!", "Recado salvo com sucesso.");
            recado = new Recado();
            listaRecados = listar();
        } catch (Exception ex) {
            Logger.getLogger(RecadoCtrl.class.getName()).log(Level.SEVERE, "Problema ao salvar Recado", ex);
            mostraMsgErro("Erro", "Problema ao salvar Recado");
        }
    }

    @Override
    public void atualizar(Recado entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void remover(Recado entity) {
        try {
            recadoDao.remover(entity);
            mostraMsgInfo("Sucesso!", "Recado removido com sucesso.");
        } catch (Exception ex) {
            Logger.getLogger(RecadoCtrl.class.getName()).log(Level.SEVERE, "Problema ao remover Recado", ex);
            mostraMsgErro("Erro", "Problema ao remover Recado");
        }
    }

    @Override
    public Recado obterPorId(Long id) {
        return recadoDao.obterPorId(id);
    }

    @Override
    public List<Recado> listar() {
        return recadoDao.listar();
    }

    
    public void visualizarRecado(Recado rec){
        recado = rec;
        this.state = ViewState.VISUALIZAR;
    }
    
    public void editarRecado(Recado rec){
        recado = rec;
        this.state = ViewState.EDITAR;
    }
    
    
    
    public Recado getRecado() {
        return recado;
    }

    public void setRecado(Recado recado) {
        this.recado = recado;
    }

    public List<Recado> getListaRecados() {
        return listaRecados;
    }

    public void setListaRecados(List<Recado> listaRecados) {
        this.listaRecados = listaRecados;
    }
    
    
    public List<Recado> getListaRecadosvalidos(){
        return recadoDao.getListaRecadosValidosNaData(new Date());
    }
    
    
    public String obtenhaDataParaExibicao(Date data){
        
        String string = "";
        
        if(data.getDate() < 10){
            string = string.concat("0");
        }
        string = string.concat(data.getDate() + "/");
        
        if(data.getMonth()< 9){
            string = string.concat("0");
            string = string.concat(data.getMonth() + 1 + "/");
        }
        
        string = string.concat(data.getYear() + 1900 + "");
        return string;
    }
}
