package br.com.nverse.sac.model;

import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author jmarcos
 */

@MappedSuperclass
public class Empresa extends Pessoa {

    @OneToOne(fetch = FetchType.EAGER)
    @Cascade(CascadeType.ALL)
    private Contato contato;
 
//    @OneToMany
//    private List<Veiculo> veiculos;
//
//    public List<Veiculo> getVeiculos() {
//        if(veiculos == null){
//            veiculos = new LinkedList<Veiculo>();
//        }
//        return veiculos;
//    }
//
//    public void setVeiculos(List<Veiculo> veiculos) {
//        this.veiculos = veiculos;
//    }

    public Contato getContato() {
        if(contato == null){
            contato = new Contato();
        }
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

}
