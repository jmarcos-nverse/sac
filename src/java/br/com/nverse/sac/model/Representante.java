/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nverse.sac.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author JMarcos_Nverse
 */
@Entity
@Table(name = "representante")
public class Representante extends Pessoa implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToOne
    @Cascade(CascadeType.ALL)
    private Contato contato;
    
    @ManyToOne
    private Cliente representa;

    public Contato getContato() {
        if (contato == null) {
            contato = new Contato();
        }
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getRepresenta() {
        return representa;
    }

    public void setRepresenta(Cliente representa) {
        this.representa = representa;
    }

    @Override
    public String toString() {
        return "Representente legal de Nome: " + getNome();
    }
    
    
    
}
