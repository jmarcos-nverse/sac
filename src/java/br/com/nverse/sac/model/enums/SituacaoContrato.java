
package br.com.nverse.sac.model.enums;

/**
 *
 * @author jmarcos
 */
public enum SituacaoContrato {
    
    ATIVO_EMDIA("Ativo em Dia"),
    ATIVO_PENDENCIA("Ativo com Pend�ncias"),
    INATIVO_DEBITO("Inativo por D�bitos"),
    INATIVO("Inativo");
    
    private String valor;
    
    private SituacaoContrato(String valor){
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
    
}
