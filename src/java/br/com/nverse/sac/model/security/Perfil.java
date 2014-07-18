/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nverse.sac.model.security;

import br.com.nverse.sac.model.Entidade;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author jmarcos
 */
@Entity
public class Perfil extends Entidade implements Serializable{
    
    @Id
    private Long id;
    
    private String nomePerfil;
    
    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "perfil_id"),
            inverseJoinColumns = @JoinColumn(name = "permissao_id"))
    private List<Permissao> permissoes;
    
    public List<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<Permissao> permissoes) {
        this.permissoes = permissoes;
    }
    
    public Permissao getPermissao(String operacao){
        for (Permissao permissao : permissoes) {
            if(permissao.getOperacao().equals(operacao)){
                return permissao;
            }
        }
        return null;
    }

    public String getNomePerfil() {
        return nomePerfil;
    }

    public void setNomePerfil(String nomePerfil) {
        this.nomePerfil = nomePerfil;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
