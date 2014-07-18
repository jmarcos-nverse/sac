/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nverse.sac.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author jmarcos
 */
@Entity
public class Recado extends Entidade implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String responsavel;
    @Lob
    private String mensagem;
    
    @Temporal(TemporalType.DATE)
    private Date dataInicio;
    
    @Temporal(TemporalType.DATE)
    private Date dataFim;

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getMensagem() {
        return mensagem;
    }
    
    public String getMensagemParaExibicao() {
        if(mensagem.length() > 60){
            List<String> lista = Arrays.asList(mensagem.split(""));
            String exib = "";
            for(String s : lista.subList(0, 60)){
                exib = exib.concat(s);
            }
            exib = exib.concat("...");
            return exib;
        }
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
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
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}
