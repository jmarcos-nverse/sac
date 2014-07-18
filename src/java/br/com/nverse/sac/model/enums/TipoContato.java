/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nverse.sac.model.enums;

/**
 *
 * @author JMarcos_Nverse
 */
public enum TipoContato {
    CANCELAMENTO("Cancelamento"),
    DUVIDA("Duvida"),
    ELOGIO("Elogio"),
    INFORMACAO("Informa��o"),
    RECLAMACAO("Reclama��o"),
    SUSPENS�O("Suspens�o"),
    SUGESTAO("Sugest�o");
    
    private String desc;
    
    TipoContato(String desc){
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
    
}
