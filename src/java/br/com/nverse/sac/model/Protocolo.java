
package br.com.nverse.sac.model;

import br.com.nverse.sac.model.enums.StatusProtocolo;
import br.com.nverse.sac.model.enums.TipoContato;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author jmarcos
 */
@Entity
@Table(name = "protocolo")
public class Protocolo extends Entidade implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Long numProtocolo;
    
    @OneToOne
    private Cliente cliente;
        
    private StatusProtocolo status;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataContato;
    
    @ManyToMany
    private List<DetalheContato> detalheContato;
    private String origemContato;
    
    private boolean pessoaPne;
    private boolean pessoaIdentificada;
    
    private String pessoaContato;
    
    private TipoContato tipoContato;
    
    private String numeroOrigem;
    
    @Lob
    private String descricao;

    public Long getNumProtocolo() {
        return numProtocolo;
    }

    public void setNumProtocolo(Long numProtocolo) {
        this.numProtocolo = numProtocolo;
    }
    

    public StatusProtocolo getStatus() {
        return status;
    }

    public void setStatus(StatusProtocolo status) {
        this.status = status;
    }

    public Date getDataContato() {
        return dataContato;
    }

    public void setDataContato(Date dataContato) {
        this.dataContato = dataContato;
    }

    public List<DetalheContato> getDetalheContato() {
        return detalheContato;
    }

    public void setDetalheContato(List<DetalheContato> detalheContato) {
        this.detalheContato = detalheContato;
    }

    public String getOrigemContato() {
        return origemContato;
    }

    public void setOrigemContato(String origemContato) {
        this.origemContato = origemContato;
    }

    public String getPessoaContato() {
        return pessoaContato;
    }

    public void setPessoaContato(String pessoaContato) {
        this.pessoaContato = pessoaContato;
    }

    public TipoContato getTipoContato() {
        return tipoContato;
    }

    public void setTipoContato(TipoContato tipoContato) {
        this.tipoContato = tipoContato;
    }

    public String getNumeroOrigem() {
        return numeroOrigem;
    }

    public void setNumeroOrigem(String numeroOrigem) {
        this.numeroOrigem = numeroOrigem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public boolean isPessoaPne() {
        return pessoaPne;
    }

    public void setPessoaPne(boolean pessoaPne) {
        this.pessoaPne = pessoaPne;
    }
    
    
    public boolean isPessoaIdentificada() {
        return pessoaIdentificada;
    }

    public void setPessoaIdentificada(boolean pessoaIdentificada) {
        this.pessoaIdentificada = pessoaIdentificada;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}
