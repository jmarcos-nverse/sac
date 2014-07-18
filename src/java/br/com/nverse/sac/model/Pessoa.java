
package br.com.nverse.sac.model;

import javax.persistence.MappedSuperclass;

/**
 *
 * @author JMarcos_Nverse
 */
@MappedSuperclass
public class Pessoa extends Entidade {
    
    
    private String cpfcnpj;
    private String nome;
    private String razaoSocial;


    public String getCpfcnpj() {
        return cpfcnpj;
    }

    public void setCpfcnpj(String cpfcnpj) {
        this.cpfcnpj = cpfcnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }
    
    
}
