/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nverse.sac.model.security;

import br.com.nverse.sac.model.Entidade;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author jmarcos
 */
@Entity
@Table(name = "permissao")
public class Permissao extends Entidade{
    @Id
    private Long id;
    
    private String operacao;
    
    private boolean permiteEditar;
    private boolean permiteVisualizar;
    private boolean permiteAdicionar;
    private boolean permiteRemover;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public boolean isPermiteEditar() {
        return permiteEditar;
    }

    public void setPermiteEditar(boolean permiteEditar) {
        this.permiteEditar = permiteEditar;
    }

    public boolean isPermiteVisualizar() {
        return permiteVisualizar;
    }

    public void setPermiteVisualizar(boolean permiteVisualizar) {
        this.permiteVisualizar = permiteVisualizar;
    }

    public boolean isPermiteAdicionar() {
        return permiteAdicionar;
    }

    public void setPermiteAdicionar(boolean permiteAdicionar) {
        this.permiteAdicionar = permiteAdicionar;
    }

    public boolean isPermiteRemover() {
        return permiteRemover;
    }

    public void setPermiteRemover(boolean permiteRemover) {
        this.permiteRemover = permiteRemover;
    }
    
    
    
}
