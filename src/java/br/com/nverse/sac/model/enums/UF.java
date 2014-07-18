
package br.com.nverse.sac.model.enums;

/**
 *
 * @author jmarcos
 */
public enum UF {

    AC("AC", "Acre"), // Acre
    AL("AL", "Alagoas"), // Alagoas
    AP("AP", "Amap·"), // Amap√°
    AM("AM", "Amazonas"), // Amazonas
    BA("BA", "Bahia"), // Bahia
    CE("CE", "Cear·"), // Cear√°
    DF("DF", "Distrito Federal"), // Distrito Federal
    ES("ES", "EspÌrito Santo"), // Esp√≠rito Santo
    GO("GO", "Goi·s"), // Goi√°s
    MA("MA", "Maranh„o"), // Maranh√£o
    MT("MT", "Mato Grosso"), // Mato Grosso
    MS("MS", "Mato Grosso do Sul"), // Mato Grosso do Sul
    MG("MG", "Minas Gerais"), // Minas Gerais
    PA("PA", "Par·"), // Par√°
    PB("PB", "ParaÌba"), // Para√≠ba
    PR("PR", "Paran·"), // Paran√°
    PE("PR", "Pernambuco"), // Pernambuco
    PI("PI", "PiauÌ"), // Piau√≠
    RR("RR", "Roraima"), // Roraima
    RO("RO", "RondÙnia"), // Rond√¥nia
    RJ("RJ", "Rio de Janeiro"), // Rio de Janeiro
    RN("RN", "Rio Grande do Norte"), // Rio Grande do Norte
    RS("RS", "Rio Grande do Sul"), // Rio Grande do Sul
    SC("SC", "Santa Catarina"), // Santa Catarina
    SP("SP", "S„o Paulo"), // S√£o Paulo
    SE("SE", "Sergipe"), // Sergipe
    TO("TO", "Tocantins"); // Tocantins
    
    private String sigla;
    private String nome;
    
    private UF(String sigla, String nome){
        this.sigla = sigla;
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public String getNome() {
        return nome;
    }
    
    
}
