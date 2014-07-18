/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.nverse.sac.model.report;

import java.util.Date;
import java.util.List;

/**
 *
 * @author JMarcos
 */
public class EntRelatorioANTT {
    
    
    private Date inicioRelatorio;
    private Date finalRelatorio;
    
    private String cliente;
    
    private int total;
    
    private int tipoManifInfo;
    private int tipoManifDuvi;
    private int tipoManifRecl;
    private int tipoManifSusp;
    private int tipoManifCanc;
    private int tipoManifElog;
    
    private int origManifFixo;
    private int origManifPubl;
    private int origManifMove;
    
    private String motivInfo1;
    private String motivInfo2;
    private String motivInfo3;
    private String motivInfo4;
    private String motivInfo5;
    
    private int info1;
    private int info2;
    private int info3;
    private int info4;
    private int info5;
    
    private String motivDuvi1;
    private String motivDuvi2;
    private String motivDuvi3;
    private String motivDuvi4;
    private String motivDuvi5;
    
    private int duvi1;
    private int duvi2;
    private int duvi3;
    private int duvi4;
    private int duvi5;
    
    private String motivRecl1;
    private String motivRecl2;
    private String motivRecl3;
    private String motivRecl4;
    private String motivRecl5;
    
    private int recl1;
    private int recl2;
    private int recl3;
    private int recl4;
    private int recl5;
    
    private int envioRegistroCorres;
    private int envioRegistroEletr;
    private int acessoGravCorres;
    private int acessoGravEletr;
    private int envioHistoricoCorres;
    private int envioHistoricoEletr;
    private int comprovResolCorres;
    private int comprovResolEletr;
    private int comprovCancelCorres;
    private int comprovCancelEletr;
    
    private int reclResol1dia;
    private int reclResol2dia;
    private int reclResol3dia;
    private int reclResol4dia;
    private int reclResol5dia;
    
    private int pne;

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    public Date getInicioRelatorio() {
        return inicioRelatorio;
    }

    public void setInicioRelatorio(Date inicioRelatorio) {
        this.inicioRelatorio = inicioRelatorio;
    }

    public Date getFinalRelatorio() {
        return finalRelatorio;
    }

    public void setFinalRelatorio(Date finalRelatorio) {
        this.finalRelatorio = finalRelatorio;
    }

    public int getTipoManifInfo() {
        return tipoManifInfo;
    }

    public void setTipoManifInfo(int tipoManifInfo) {
        this.tipoManifInfo = tipoManifInfo;
    }

    public int getTipoManifDuvi() {
        return tipoManifDuvi;
    }

    public void setTipoManifDuvi(int tipoManifDuvi) {
        this.tipoManifDuvi = tipoManifDuvi;
    }

    public int getTipoManifRecl() {
        return tipoManifRecl;
    }

    public void setTipoManifRecl(int tipoManifRecl) {
        this.tipoManifRecl = tipoManifRecl;
    }

    public int getTipoManifSusp() {
        return tipoManifSusp;
    }

    public void setTipoManifSusp(int tipoManifSusp) {
        this.tipoManifSusp = tipoManifSusp;
    }

    public int getTipoManifCanc() {
        return tipoManifCanc;
    }

    public void setTipoManifCanc(int tipoManifCanc) {
        this.tipoManifCanc = tipoManifCanc;
    }

    public int getTipoManifElog() {
        return tipoManifElog;
    }

    public void setTipoManifElog(int tipoManifElog) {
        this.tipoManifElog = tipoManifElog;
    }

    public int getOrigManifFixo() {
        return origManifFixo;
    }

    public void setOrigManifFixo(int origManifFixo) {
        this.origManifFixo = origManifFixo;
    }

    public int getOrigManifPubl() {
        return origManifPubl;
    }

    public void setOrigManifPubl(int origManifPubl) {
        this.origManifPubl = origManifPubl;
    }

    public int getOrigManifMove() {
        return origManifMove;
    }

    public void setOrigManifMove(int origManifMove) {
        this.origManifMove = origManifMove;
    }

    public String getMotivInfo1() {
        return motivInfo1;
    }

    public void setMotivInfo1(String motivInfo1) {
        this.motivInfo1 = motivInfo1;
    }

    public String getMotivInfo2() {
        return motivInfo2;
    }

    public void setMotivInfo2(String motivInfo2) {
        this.motivInfo2 = motivInfo2;
    }

    public String getMotivInfo3() {
        return motivInfo3;
    }

    public void setMotivInfo3(String motivInfo3) {
        this.motivInfo3 = motivInfo3;
    }

    public String getMotivInfo4() {
        return motivInfo4;
    }

    public void setMotivInfo4(String motivInfo4) {
        this.motivInfo4 = motivInfo4;
    }

    public String getMotivInfo5() {
        return motivInfo5;
    }

    public void setMotivInfo5(String motivInfo5) {
        this.motivInfo5 = motivInfo5;
    }

    public int getInfo1() {
        return info1;
    }

    public void setInfo1(int info1) {
        this.info1 = info1;
    }

    public int getInfo2() {
        return info2;
    }

    public void setInfo2(int info2) {
        this.info2 = info2;
    }

    public int getInfo3() {
        return info3;
    }

    public void setInfo3(int info3) {
        this.info3 = info3;
    }

    public int getInfo4() {
        return info4;
    }

    public void setInfo4(int info4) {
        this.info4 = info4;
    }

    public int getInfo5() {
        return info5;
    }

    public void setInfo5(int info5) {
        this.info5 = info5;
    }

    public String getMotivDuvi1() {
        return motivDuvi1;
    }

    public void setMotivDuvi1(String motivDuvi1) {
        this.motivDuvi1 = motivDuvi1;
    }

    public String getMotivDuvi2() {
        return motivDuvi2;
    }

    public void setMotivDuvi2(String motivDuvi2) {
        this.motivDuvi2 = motivDuvi2;
    }

    public String getMotivDuvi3() {
        return motivDuvi3;
    }

    public void setMotivDuvi3(String motivDuvi3) {
        this.motivDuvi3 = motivDuvi3;
    }

    public String getMotivDuvi4() {
        return motivDuvi4;
    }

    public void setMotivDuvi4(String motivDuvi4) {
        this.motivDuvi4 = motivDuvi4;
    }

    public String getMotivDuvi5() {
        return motivDuvi5;
    }

    public void setMotivDuvi5(String motivDuvi5) {
        this.motivDuvi5 = motivDuvi5;
    }

    public int getDuvi1() {
        return duvi1;
    }

    public void setDuvi1(int duvi1) {
        this.duvi1 = duvi1;
    }

    public int getDuvi2() {
        return duvi2;
    }

    public void setDuvi2(int duvi2) {
        this.duvi2 = duvi2;
    }

    public int getDuvi3() {
        return duvi3;
    }

    public void setDuvi3(int duvi3) {
        this.duvi3 = duvi3;
    }

    public int getDuvi4() {
        return duvi4;
    }

    public void setDuvi4(int duvi4) {
        this.duvi4 = duvi4;
    }

    public int getDuvi5() {
        return duvi5;
    }

    public void setDuvi5(int duvi5) {
        this.duvi5 = duvi5;
    }

    public String getMotivRecl1() {
        return motivRecl1;
    }

    public void setMotivRecl1(String motivRecl1) {
        this.motivRecl1 = motivRecl1;
    }

    public String getMotivRecl2() {
        return motivRecl2;
    }

    public void setMotivRecl2(String motivRecl2) {
        this.motivRecl2 = motivRecl2;
    }

    public String getMotivRecl3() {
        return motivRecl3;
    }

    public void setMotivRecl3(String motivRecl3) {
        this.motivRecl3 = motivRecl3;
    }

    public String getMotivRecl4() {
        return motivRecl4;
    }

    public void setMotivRecl4(String motivRecl4) {
        this.motivRecl4 = motivRecl4;
    }

    public String getMotivRecl5() {
        return motivRecl5;
    }

    public void setMotivRecl5(String motivRecl5) {
        this.motivRecl5 = motivRecl5;
    }

    public int getRecl1() {
        return recl1;
    }

    public void setRecl1(int recl1) {
        this.recl1 = recl1;
    }

    public int getRecl2() {
        return recl2;
    }

    public void setRecl2(int recl2) {
        this.recl2 = recl2;
    }

    public int getRecl3() {
        return recl3;
    }

    public void setRecl3(int recl3) {
        this.recl3 = recl3;
    }

    public int getRecl4() {
        return recl4;
    }

    public void setRecl4(int recl4) {
        this.recl4 = recl4;
    }

    public int getRecl5() {
        return recl5;
    }

    public void setRecl5(int recl5) {
        this.recl5 = recl5;
    }

    public int getEnvioRegistroCorres() {
        return envioRegistroCorres;
    }

    public void setEnvioRegistroCorres(int envioRegistroCorres) {
        this.envioRegistroCorres = envioRegistroCorres;
    }

    public int getEnvioRegistroEletr() {
        return envioRegistroEletr;
    }

    public void setEnvioRegistroEletr(int envioRegistroEletr) {
        this.envioRegistroEletr = envioRegistroEletr;
    }

    public int getAcessoGravCorres() {
        return acessoGravCorres;
    }

    public void setAcessoGravCorres(int acessoGravCorres) {
        this.acessoGravCorres = acessoGravCorres;
    }

    public int getAcessoGravEletr() {
        return acessoGravEletr;
    }

    public void setAcessoGravEletr(int acessoGravEletr) {
        this.acessoGravEletr = acessoGravEletr;
    }

    public int getEnvioHistoricoCorres() {
        return envioHistoricoCorres;
    }

    public void setEnvioHistoricoCorres(int envioHistoricoCorres) {
        this.envioHistoricoCorres = envioHistoricoCorres;
    }

    public int getEnvioHistoricoEletr() {
        return envioHistoricoEletr;
    }

    public void setEnvioHistoricoEletr(int envioHistoricoEletr) {
        this.envioHistoricoEletr = envioHistoricoEletr;
    }

    public int getComprovResolCorres() {
        return comprovResolCorres;
    }

    public void setComprovResolCorres(int comprovResolCorres) {
        this.comprovResolCorres = comprovResolCorres;
    }

    public int getComprovResolEletr() {
        return comprovResolEletr;
    }

    public void setComprovResolEletr(int comprovResolEletr) {
        this.comprovResolEletr = comprovResolEletr;
    }

    public int getComprovCancelCorres() {
        return comprovCancelCorres;
    }

    public void setComprovCancelCorres(int comprovCancelCorres) {
        this.comprovCancelCorres = comprovCancelCorres;
    }

    public int getComprovCancelEletr() {
        return comprovCancelEletr;
    }

    public void setComprovCancelEletr(int comprovCancelEletr) {
        this.comprovCancelEletr = comprovCancelEletr;
    }

    public int getReclResol1dia() {
        return reclResol1dia;
    }

    public void setReclResol1dia(int reclResol1dia) {
        this.reclResol1dia = reclResol1dia;
    }

    public int getReclResol2dia() {
        return reclResol2dia;
    }

    public void setReclResol2dia(int reclResol2dia) {
        this.reclResol2dia = reclResol2dia;
    }

    public int getReclResol3dia() {
        return reclResol3dia;
    }

    public void setReclResol3dia(int reclResol3dia) {
        this.reclResol3dia = reclResol3dia;
    }

    public int getReclResol4dia() {
        return reclResol4dia;
    }

    public void setReclResol4dia(int reclResol4dia) {
        this.reclResol4dia = reclResol4dia;
    }

    public int getReclResol5dia() {
        return reclResol5dia;
    }

    public void setReclResol5dia(int reclResol5dia) {
        this.reclResol5dia = reclResol5dia;
    }

    public int getPne() {
        return pne;
    }

    public void setPne(int pne) {
        this.pne = pne;
    }
    
    
    
    
    
}
