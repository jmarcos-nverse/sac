/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nverse.sac.controllers;

import br.com.nverse.sac.model.Veiculo;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author jmarcos
 */
@Controller(value = "veiculoCtrl")
@Scope("view")
public class VeiculoCtrl extends GenericCtrl<Veiculo>{

    private Veiculo veiculo;
    
    public VeiculoCtrl(){
        prepararAdicionar();
    }
    
    @Override
    public void prepararAdicionar() {
        veiculo = new Veiculo();
        this.state = ViewState.ADICIONAR;
    }

    @Override
    public void salvar(Veiculo entity) {}

    @Override
    public void salvarEntidade() {}

    @Override
    public void atualizar(Veiculo entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void remover(Veiculo entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Veiculo obterPorId(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List<Veiculo> listar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
    
}
