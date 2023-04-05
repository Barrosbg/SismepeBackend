package br.gov.pe.sismepe.report.service;

import java.io.OutputStream;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;

import org.springframework.util.ResourceUtils;
import br.gov.pe.sismepe.report.JasperReportBase;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

public class CadastroDeCarrosReportService extends JasperReportBase {

	public void toOutputStream(String XmlFilepath, Collection dataSource, HashMap params, OutputStream os) throws Exception {
        String jrXmlFile = XmlFilepath;
        URL carrosUrl = ResourceUtils.getURL("classpath:imagens/logo_sismepe.png");
        params.put("LOGO", carrosUrl);

        JasperPrint print = compileAndPrint(jrXmlFile, dataSource, params);

        if (dataSource.isEmpty()) {
            params.put("titulo", "FICHA DE CADASTRO POR SETOR");
            jrXmlFile = "/jasper/FichaEstacionamento.jrxml";
            print = compileAndPrintParamOnly(jrXmlFile, params);
        }

        JasperExportManager.exportReportToPdfStream(print, os);
    }


	
}
