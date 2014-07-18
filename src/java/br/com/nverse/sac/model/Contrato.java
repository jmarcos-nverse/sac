/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nverse.sac.model;

import br.com.nverse.sac.model.enums.DiaPagamento;
import br.com.nverse.sac.model.enums.SituacaoContrato;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;



/**
 *
 * @author JMarcos_Nverse
 */
@Entity
@Table(name = "contrato")
public class Contrato extends Entidade implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String numeroContrato;
    
    private double valorPorVeiculo;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataInicio;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataFim;
    
    @ManyToOne
    private Cliente cliente;
    
    private DiaPagamento parcela;
    
    private String atendente;
    
    private SituacaoContrato situacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getValorPorVeiculo() {
        return valorPorVeiculo;
    }

    public void setValorPorVeiculo(double valorPorVeiculo) {
        this.valorPorVeiculo = valorPorVeiculo;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public DiaPagamento getParcela() {
        return parcela;
    }

    public void setParcela(DiaPagamento parcela) {
        this.parcela = parcela;
    }

    public String getAtendente() {
        return atendente;
    }

    public void setAtendente(String atendente) {
        this.atendente = atendente;
    }

    public String getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public SituacaoContrato getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoContrato situacao) {
        this.situacao = situacao;
    }
    
    
    
}
