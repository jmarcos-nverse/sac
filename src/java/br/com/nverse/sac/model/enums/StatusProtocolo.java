/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nverse.sac.model.enums;

/**
 *
 * @author JMarcos_Nverse
 */
public enum StatusProtocolo {
    
    NOVO("Novo"),
    EMANALISE("Em Análise"),
    AGRESPOSTA("Aguardando Resposta"),
    ENCERRADO("Encerrado");

    
    private String desc;
    
    private StatusProtocolo(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
    
}
