/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nverse.sac.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author jmarcos
 */
@Entity
@Table(name = "detalheContato")
public class DetalheContato extends Entidade implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String codEstruturado;
    
    @ManyToOne
    private DetalheContato pai;
    
    @OneToMany(mappedBy = "pai", fetch = FetchType.LAZY)
    private List<DetalheContato> filhos;
    
    private String titulo;
    
    private String descricaoAutomatica;
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodEstruturado() {
        return codEstruturado;
    }

    public void setCodEstruturado(String codEstruturado) {
        this.codEstruturado = codEstruturado;
    }

    public DetalheContato getPai() {
        return pai;
    }

    public void setPai(DetalheContato pai) {
        this.pai = pai;
    }

    public List<DetalheContato> getFilhos() {
        return filhos;
    }

    public void setFilhos(List<DetalheContato> filhos) {
        this.filhos = filhos;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricaoAutomatica() {
        return descricaoAutomatica;
    }

    public void setDescricaoAutomatica(String descricaoAutomatica) {
        this.descricaoAutomatica = descricaoAutomatica;
    }
    
    
    
    
}
