/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nverse.sac.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author JMarcos_Nverse
 */
@Entity
@Table(name = "agregado")
public class Agregado extends Empresa{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    private Cliente cliente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Agregado: Nome: " + getNome() + " - Razão Social: " + getRazaoSocial();
    }

    
    
    
}
