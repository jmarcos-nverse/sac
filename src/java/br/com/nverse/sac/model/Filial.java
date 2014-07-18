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
import javax.persistence.Table;

/**
 *
 * @author JMarcos_Nverse
 */
@Entity
@Table(name = "filial")
public class Filial extends Empresa implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
     @ManyToOne
    private Cliente matriz;

    public Cliente getMatriz() {
        return matriz;
    }

    public void setMatriz(Cliente matriz) {
        this.matriz = matriz;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}
