package br.gov.pe.sismepe.report;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.InputStream;
import java.util.Collection;
import java.util.Map;

public abstract class JasperReportBase {
    public JasperPrint compileAndPrint(String jrXmlFile, Collection dataSource, Map parameters) throws Exception {
        InputStream stream = this.getClass().getResourceAsStream(jrXmlFile);
        JasperReport report = JasperCompileManager.compileReport(stream);

        JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(dataSource);

        JasperPrint print = JasperFillManager.fillReport(report, parameters, source);

        return print;
    }

    public byte[] toPdfBytes(String jrXmlFile, Collection dataSource, Map parameters) throws Exception {
        JasperPrint print = compileAndPrint(jrXmlFile, dataSource, parameters);
        return JasperExportManager.exportReportToPdf(print);
    }

    public JasperPrint compileAndPrintParamOnly(String jrXmlFile, Map parameters) throws Exception {
        InputStream stream = this.getClass().getResourceAsStream(jrXmlFile);
        JasperReport report = JasperCompileManager.compileReport(stream);

        JasperPrint print = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());

        return print;
    }
}
