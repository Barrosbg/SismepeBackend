package br.gov.pe.sismepe.report.service;

import java.io.OutputStream;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.util.ResourceUtils;

import br.gov.pe.sismepe.domain.ExameTransfusional;
import br.gov.pe.sismepe.report.JasperReportBase;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;



public class ResultadoExamesExternosReportSevice extends JasperReportBase {
	public void toOutputStream(String XmlFilepath, HashMap params, OutputStream os) throws Exception {
        String jrXmlFile = XmlFilepath;
        URL Url = ResourceUtils.getURL("classpath:imagens/logo_e_texto_ds.jpg");
        params.put("LOGO", Url);

        JasperPrint print = compileAndPrintParamOnly(jrXmlFile,  params);

         
        if (params != null) {
        	System.out.println(params);
//            params.put("titulo", "RESULTADO EXAMES EXTERNOS");
            jrXmlFile = "/jasper/ResultadoExamesExternos.jrxml";
            print = compileAndPrintParamOnly(jrXmlFile, params);
  
        }

        JasperExportManager.exportReportToPdfStream(print, os);
    }


}
