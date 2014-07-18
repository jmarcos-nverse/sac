

package br.com.nverse.sac.controllers;

import br.com.nverse.sac.dao.interfaces.ClienteDao;
import br.com.nverse.sac.dao.interfaces.ProtocoloDao;
import br.com.nverse.sac.model.Cliente;
import br.com.nverse.sac.model.enums.UF;
import br.com.nverse.sac.model.report.EntRelatorioANTT;
import br.com.nverse.sac.model.report.EntidadeRelatorioClientesValor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.model.chart.DonutChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author JMarcos
 */
@Controller(value = "relatorioCtrl")
@Scope("view")
public class RelatorioCtrl {

    @Autowired
    ClienteDao clienteDao;
    @Autowired
    ProtocoloDao protocoloDao;

    public DonutChartModel obtenhaRelatorioClientesPorEstado() {
        DonutChartModel torta = new DonutChartModel();
        Map<String, Number> circle = new LinkedHashMap<String, Number>();
        for (UF estado : UF.values()) {

            int clis = clienteDao.obtenhaQuantidadePorUF(estado);

            circle.put(estado.getSigla() + " - " + clis, clis);

        }
        torta.addCircle(circle);
        return torta;
    }

    public void gerarRelatorio(String arquivo, String type, List lista) {

        JRDataSource jrds = new JRBeanCollectionDataSource(lista);

        gerarRelatorioWeb(jrds, null, arquivo, type);
    }

    private void gerarRelatorioWeb(JRDataSource jrds, Map<String, Object> parametros, String arquivo, String type) {
        ServletOutputStream servletOutputStream;
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        arquivo = context.getExternalContext().getRealPath(arquivo);

        try {
            servletOutputStream = response.getOutputStream();
            JasperRunManager.runReportToPdfStream(new FileInputStream(new File(arquivo)), servletOutputStream, parametros, jrds);
            response.setContentType(type);

            servletOutputStream.flush();
            servletOutputStream.close();
            context.renderResponse();
            context.responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gereRelatorioClientesValor() {

        String arquivo = "/relatorios/relatorioclientesvalor.jasper";

        String type = "application/pdf";

        List<EntidadeRelatorioClientesValor> lista = new LinkedList<EntidadeRelatorioClientesValor>();

        for (Cliente cliente : clienteDao.listar()) {
            EntidadeRelatorioClientesValor ent = new EntidadeRelatorioClientesValor();

            ent.setContrato(cliente.getContratos().get(0).getNumeroContrato());
            ent.setNomeFantasia(cliente.getNome());
            ent.setRazaoSocial(cliente.getRazaoSocial());
            ent.setUF(cliente.getEnderecoPrimario().getUf());
            ent.setValorPorOnibus(cliente.getContratos().get(0).getValorPorVeiculo());
            ent.setValorTotal(cliente.getVeiculos().size() * ent.getValorPorOnibus());
            lista.add(ent);
        }
        Collections.sort(lista, new Comparator<EntidadeRelatorioClientesValor>() {

            @Override
            public int compare(EntidadeRelatorioClientesValor t, EntidadeRelatorioClientesValor t1) {
                return t.getUF().compareTo(t1.getUF());
            }
        });
//        gerarRelatorio(arquivo, type, lista);
        gereRelatorioDownload(lista, arquivo, "relatorioTeste");

    }

    private void gereRelatorioDownload(List lista, String jasper, String nomeArquivo) {
        JRDataSource jrds = new JRBeanCollectionDataSource(lista);

        FacesContext context = FacesContext.getCurrentInstance();
        jasper = context.getExternalContext().getRealPath(jasper);

        try {
            JasperPrint jPrint = JasperFillManager.fillReport(new FileInputStream(new File(jasper)), null, jrds);
            byte[] reportToPdf = JasperExportManager.exportReportToPdf(jPrint);

            ExternalContext externalContext = context.getExternalContext();
            HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

            // Initialize response.
            response.reset(); // Some JSF component library or some Filter might have set some headers in the buffer beforehand. We want to get rid of them, else it may collide.
            response.setContentType("application/pdf"); // Check http://www.iana.org/assignments/media-types for all types. Use if necessary ServletContext#getMimeType() for auto-detection based on filename.
            response.setHeader("Content-disposition", "attachment; filename=\"" + nomeArquivo + ".pdf\""); // The Save As popup magic is done here. You can give it any filename you want, this only won't work in MSIE, it will use current request URL as filename instead.

            // Write file to response.
            OutputStream output = response.getOutputStream();
            output.write(reportToPdf);
            output.close();

            // Inform JSF to not take the response in hands.
            context.responseComplete();

//            JasperRunManager.runReportToPdfFile(arquivo, "RelatorioTesteZueira.pdf", null, jrds);
            //JasperRunManager.runReportToPdfStream(new FileInputStream(new File(arquivo)), servletOutputStream, parametros, jrds);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gereRelatorioANTT() {
        try {
            String arquivo = "/relatorios/RelatorioANTT.jasper";

            String type = "application/pdf";

            List<EntRelatorioANTT> list = new LinkedList<>();

            List<Cliente> clientes = new LinkedList<>();

            clientes = clienteDao.listar();
            FacesContext context = FacesContext.getCurrentInstance();
            String jasper = context.getExternalContext().getRealPath(arquivo);
            ExternalContext externalContext = context.getExternalContext();
            HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
            List<byte[]> reports = new LinkedList<>();
            List<File> files = new LinkedList<>();

            OutputStream output = response.getOutputStream();
            
            for (Cliente cliente : clientes) {

                if (!protocoloDao.clientePossuiLigacoes(cliente)) {
                    EntRelatorioANTT ent = new EntRelatorioANTT();
                    ent.setCliente(cliente.getNome());
                    list.add(ent);

                    JRDataSource jrds = new JRBeanCollectionDataSource(list);

                    //gereRelatorioDownload(list, arquivo, cliente.getNome());
                    JasperPrint jPrint = JasperFillManager.fillReport(new FileInputStream(new File(jasper)), null, jrds);
                    //reports.add(JasperExportManager.exportReportToPdf(jPrint));
                    File clienteRel = new File("/"+cliente.getNome()+".pdf");
                    FileOutputStream fOutput = new FileOutputStream(clienteRel);
                    fOutput.write(JasperExportManager.exportReportToPdf(jPrint));
                    files.add(clienteRel);
//                    fOutput.flush();
//                    files.add(clienteRel);
                    // Initialize response.
                    list = new LinkedList<>();

                }

            }

//            response.reset(); // Some JSF component library or some Filter might have set some headers in the buffer beforehand. We want to get rid of them, else it may collide.
//            response.setContentType("application/pdf"); // Check http://www.iana.org/assignments/media-types for all types. Use if necessary ServletContext#getMimeType() for auto-detection based on filename.
//            response.setHeader("Content-disposition", "attachment; filename=\"relatorioz.pdf\""); // The Save As popup magic is done here. You can give it any filename you want, this only won't work in MSIE, it will use current request URL as filename instead.
//
            
            

            output.close();

            // Inform JSF to not take the response in hands.
            context.responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
