/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.nverse.sac.model.report;

/**
 *
 * @author JMarcos
 */
public class EntidadeRelatorioClientesValor {
    
    private String nomeFantasia;
    private String razaoSocial;
    private String UF;
    private double valorPorOnibus;
    private double valorTotal;
    private String contrato;

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

    public double getValorPorOnibus() {
        return valorPorOnibus;
    }

    public void setValorPorOnibus(double valorPorOnibus) {
        this.valorPorOnibus = valorPorOnibus;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }
    
    
    
    
}
