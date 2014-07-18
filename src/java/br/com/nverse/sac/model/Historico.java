
package br.com.nverse.sac.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author JMarcos_Nverse
 */
@Entity
@Table(name = "historico")
public class Historico extends Entidade{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    
    private String solicitante;
    private String atendente;
    
    @Lob
    private String descricao;
    
    @ManyToOne
    private Cliente alvo;

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    public String getAtendente() {
        return atendente;
    }

    public void setAtendente(String atendente) {
        this.atendente = atendente;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Cliente getAlvo() {
        return alvo;
    }

    public void setAlvo(Cliente alvo) {
        this.alvo = alvo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
    
}
