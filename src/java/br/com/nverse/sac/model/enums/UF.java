
package br.com.nverse.sac.model.enums;

/**
 *
 * @author jmarcos
 */
public enum UF {

    AC("AC", "Acre"), // Acre
    AL("AL", "Alagoas"), // Alagoas
    AP("AP", "Amap�"), // Amapá
    AM("AM", "Amazonas"), // Amazonas
    BA("BA", "Bahia"), // Bahia
    CE("CE", "Cear�"), // Ceará
    DF("DF", "Distrito Federal"), // Distrito Federal
    ES("ES", "Esp�rito Santo"), // Espírito Santo
    GO("GO", "Goi�s"), // Goiás
    MA("MA", "Maranh�o"), // Maranhão
    MT("MT", "Mato Grosso"), // Mato Grosso
    MS("MS", "Mato Grosso do Sul"), // Mato Grosso do Sul
    MG("MG", "Minas Gerais"), // Minas Gerais
    PA("PA", "Par�"), // Pará
    PB("PB", "Para�ba"), // Paraíba
    PR("PR", "Paran�"), // Paraná
    PE("PR", "Pernambuco"), // Pernambuco
    PI("PI", "Piau�"), // Piauí
    RR("RR", "Roraima"), // Roraima
    RO("RO", "Rond�nia"), // Rondônia
    RJ("RJ", "Rio de Janeiro"), // Rio de Janeiro
    RN("RN", "Rio Grande do Norte"), // Rio Grande do Norte
    RS("RS", "Rio Grande do Sul"), // Rio Grande do Sul
    SC("SC", "Santa Catarina"), // Santa Catarina
    SP("SP", "S�o Paulo"), // São Paulo
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
