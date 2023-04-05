package br.gov.pe.sismepe.report.service;

import java.io.OutputStream;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;

import org.springframework.util.ResourceUtils;

import br.gov.pe.sismepe.report.JasperReportBase;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

public class FichaInspecaoJmsReportService extends JasperReportBase {
    
	public void toOutputStream(String XmlFilepath, Collection dataSource, HashMap params, OutputStream os) throws Exception {
        String jrXmlFile = XmlFilepath;
        URL jmsUrl = ResourceUtils.getURL("classpath:imagens/JMS.jpeg");
        params.put("JMS", jmsUrl);

        JasperPrint print = compileAndPrint(jrXmlFile, dataSource, params);

        if (dataSource.isEmpty()) {
            params.put("titulo", "FICHA DE INSPEÇÃO PARA QUADRO DE ACESSO JMS");
            jrXmlFile = "/jasper/relFichaInspecaoQuadroAcessoJMSretrato.jrxml";
            print = compileAndPrintParamOnly(jrXmlFile, params);
        }

        JasperExportManager.exportReportToPdfStream(print, os);
    }
}