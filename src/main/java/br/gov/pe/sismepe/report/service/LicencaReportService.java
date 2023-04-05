package br.gov.pe.sismepe.report.service;

import br.gov.pe.sismepe.dto.jms.*;
import br.gov.pe.sismepe.report.JasperReportBase;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.util.ResourceUtils;

import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class LicencaReportService extends JasperReportBase {
    public void toOutputStream(String XmlFilepath, Collection dataSource, HashMap params, OutputStream os) throws Exception {
        String jrXmlFile = XmlFilepath;
        URL jmsUrl = ResourceUtils.getURL("classpath:imagens/JMS.jpeg");
        params.put("JMS", jmsUrl);

        JasperPrint print = compileAndPrint(jrXmlFile, dataSource, params);

        if (dataSource.isEmpty()) {
            params.put("titulo", "RELATÓRIO DE LICENÇA/DISPENSA");
            jrXmlFile = "/jasper/relSemDados.jrxml";
            print = compileAndPrintParamOnly(jrXmlFile, params);
        }

        JasperExportManager.exportReportToPdfStream(print, os);
    }
}
