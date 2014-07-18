/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nverse.sac.model;

import br.com.nverse.sac.model.enums.TipoTransportador;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author jmarcos
 */
@Entity
@Table(name = "cliente")
public class Cliente extends Empresa implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String codigo;
    
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;
    
    private TipoTransportador tipo;
    
    @OneToOne(fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    private Endereco enderecoPrimario;
    @OneToOne(fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    private Endereco enderecoSecundario;
    @Lob
    private String observacoes;
    
    @OneToMany(mappedBy = "representa", fetch = FetchType.LAZY)
    @Cascade(CascadeType.DELETE)
    private List<Representante> representantes;
    
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    @Cascade(CascadeType.DELETE)
    private List<Socio> socios;
    
    @OneToMany(mappedBy = "matriz", fetch = FetchType.LAZY)
    @Cascade(CascadeType.DELETE)
    private List<Filial> filiais;
    
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    @Cascade(CascadeType.DELETE)
    private List<Agregado> agregados;
    
    @OneToMany(mappedBy = "dono", fetch = FetchType.LAZY)
    @JoinColumn(name = "dono_id")
    @Cascade(CascadeType.DELETE)
    private List<Veiculo> veiculos;
    
    @OneToMany(mappedBy = "alvo", fetch = FetchType.LAZY)
    @Cascade(CascadeType.DELETE)
    private List<Historico> historia;
    
    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
    @Cascade(CascadeType.DELETE)
    private List<Contrato> contratos;
    

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Endereco getEnderecoPrimario() {
        if(enderecoPrimario == null){
            enderecoPrimario = new Endereco();
        }
        return enderecoPrimario;
    }

    public void setEnderecoPrimario(Endereco enderecoPrimario) {
        this.enderecoPrimario = enderecoPrimario;
    }

    public Endereco getEnderecoSecundario() {
        if(enderecoSecundario == null){
            enderecoSecundario = new Endereco();
        }
        return enderecoSecundario;
    }

    public void setEnderecoSecundario(Endereco enderecoSecundario) {
        this.enderecoSecundario = enderecoSecundario;
    }
    
    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public TipoTransportador getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransportador tipo) {
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Representante> getRepresentantes() {
        return representantes;
    }

    public void setRepresentantes(List<Representante> representantes) {
        this.representantes = representantes;
    }

    public List<Socio> getSocios() {
        return socios;
    }

    public void setSocios(List<Socio> socios) {
        this.socios = socios;
    }

    public List<Filial> getFiliais() {
        return filiais;
    }

    public void setFiliais(List<Filial> filiais) {
        this.filiais = filiais;
    }

    public List<Agregado> getAgregados() {
        return agregados;
    }

    public void setAgregados(List<Agregado> agregados) {
        this.agregados = agregados;
    }

    public List<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public List<Historico> getHistoria() {
        return historia;
    }

    public void setHistoria(List<Historico> historia) {
        this.historia = historia;
    }

    public List<Contrato> getContratos() {
        return contratos;
    }

    public void setContratos(List<Contrato> contratos) {
        this.contratos = contratos;
    }

    
    
    
}
