package br.gov.pe.sismepe.report.service;

import java.io.OutputStream;
import java.net.URL;
import java.util.HashMap;

import org.springframework.util.ResourceUtils;

import br.gov.pe.sismepe.report.JasperReportBase;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

public class FichaCadastroTransfusionalReportService extends JasperReportBase{
	
	public void toOutputStream(String XmlFilepath, HashMap params, OutputStream os) throws Exception {
        String jrXmlFile = XmlFilepath;
//        URL jmsUrl = ResourceUtils.getURL("classpath:imagens/JMS.jpeg");
//        params.put("JMS", jmsUrl);

        JasperPrint print = compileAndPrintParamOnly(jrXmlFile, params);

//        if (params != null) {
////            params.put("titulo", "FICHA DE INSPEÇÃO PARA QUADRO DE ACESSO JMS");
//            jrXmlFile = "/jasper/transfusional.jrxml";///se liga ao Ireport
//            print = compileAndPrintParamOnly(jrXmlFile, params);
//        }

        JasperExportManager.exportReportToPdfStream(print, os);
    }
}
