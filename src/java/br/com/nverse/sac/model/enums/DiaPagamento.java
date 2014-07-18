
package br.com.nverse.sac.model.enums;

/**
 *
 * @author JMarcos_Nverse
 */
public enum DiaPagamento {
    
    DIA05("05"),
    DIA10("10"),
    DIA15("15"),
    DIA20("20"),
    DIA25("25"),
    DIA30("30"),;
    
    
    public String dia;
    DiaPagamento(String valor){
        dia = valor;
    }
    
    public String getValor(){
        return dia;
    }
}
