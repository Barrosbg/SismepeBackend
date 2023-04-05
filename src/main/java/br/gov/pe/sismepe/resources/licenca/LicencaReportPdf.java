package br.gov.pe.sismepe.resources.licenca;

import br.gov.pe.sismepe.dto.jms.*;
import br.gov.pe.sismepe.report.service.LicencaReportService;
import br.gov.pe.sismepe.services.LicencaService;
import springfox.documentation.annotations.ApiIgnore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@ApiIgnore
@RestController
@RequestMapping("/licenca/relatorio")
@CrossOrigin
public class LicencaReportPdf {
    @Autowired
    LicencaService service;

    @RequestMapping(value = "/licenca-ome/pdf", method = RequestMethod.GET)
    void licencaOmePdf(@RequestParam (required = false)  Long cdOme,
                       @RequestParam(required = false) Integer tipoParecer,
                       @RequestParam(defaultValue = "true") boolean pm,
                       @RequestParam(defaultValue = "true") boolean bm,
                       @RequestParam(defaultValue = "0") Integer qtdDias,
                       @RequestParam(required = false) java.sql.Date dataIni,
                       @RequestParam(required = false) java.sql.Date dataFim,
                       @RequestParam(required = false) String opIni,
                       @RequestParam(required = false) String opFim,
                       @RequestParam(required = false) Integer matricula,
                       @RequestParam(required = false) boolean disp,
                       @RequestParam(required = false) boolean lic,
                       HttpServletResponse response) throws Exception {
        LicencasPorOmeReportDTO l = service.gerarRelatorioLicencasPorOmeDTO(cdOme, tipoParecer, pm, bm,
                qtdDias, true, dataIni, dataFim, opIni, opFim, matricula, disp, lic);
        LicencaReportService lrs = new LicencaReportService();

        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("TITULO", "RELATÓRIO DE LICENÇA / DISPENSA POR OME");
        parameters.put("totalPms", l.getTotalPms());
        parameters.put("totalBms", l.getTotalBms());

        response.setContentType("application/pdf");
        response.addHeader("Content-Disposition", "inline; filename=licenca-por-ome.pdf;");

        lrs.toOutputStream("/jasper/relLicencasPorOME.jrxml", l.getLicencas(), parameters, response.getOutputStream());
    }

    @RequestMapping(value = "/licenca-data/pdf", method = RequestMethod.GET)
    void licencaPorDataPdf(@RequestParam (required = false) Long cdOme,
                           @RequestParam(required = false) Integer tipoParecer,
                           @RequestParam(defaultValue = "true") boolean pm,
                           @RequestParam(defaultValue = "true") boolean bm,
                           @RequestParam(defaultValue = "0") Integer qtdDias,
                           @RequestParam(required = false) java.sql.Date dataIni,
                           @RequestParam(required = false) java.sql.Date dataFim,
                           @RequestParam(required = false) String opIni,
                           @RequestParam(required = false) String opFim,
                           @RequestParam(required = false) Integer matricula,
                           @RequestParam(required = false) boolean disp,
                           @RequestParam(required = false) boolean lic,
                           HttpServletResponse response) throws Exception {
        LicencasPorOmeReportDTO l = service.gerarRelatorioLicencasPorOmeDTO(cdOme, tipoParecer, pm, bm,
                qtdDias, true, dataIni, dataFim, opIni, opFim, matricula, disp, lic);
        LicencaReportService lrs = new LicencaReportService();

        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("TITULO", "RELATÓRIO DE LICENÇA / DISPENSA POR DATA");
        parameters.put("totalPms", l.getTotalPms());
        parameters.put("totalBms", l.getTotalBms());

        response.setContentType("application/pdf");
        response.addHeader("Content-Disposition", "inline; filename=licenca-data.pdf;");

        lrs.toOutputStream("/jasper/relLicencasPorOME.jrxml", l.getLicencas(), parameters, response.getOutputStream());
    }
    
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('JMS_MEDICO_HOMOLOGA') or hasAuthority('JMS_GESTOR_GERAL')")
    @RequestMapping(value = "/gerencial/parecer/pdf", method = RequestMethod.GET)
    void relatorioGerencialPorParecerPdf(@RequestParam(required = false) java.sql.Date dataIni,
                                         @RequestParam(required = false) java.sql.Date dataFim,
                                         @RequestParam(defaultValue = "0") Integer tipo,
                                         @RequestParam(defaultValue = "") Integer matricula,
                                         @RequestParam(defaultValue = "") String cid,
                                         HttpServletResponse response) throws Exception {
        RelatorioGerencialLicencaDTO rg = service.getRelatorio(tipo, dataIni, dataFim, matricula, cid);
        LicencaReportService lrs = new LicencaReportService();

        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("DESCRICAO", rg.getLabelColunaDescricao());
        parameters.put("LABEL_COL1", rg.getLabelCol1());
        parameters.put("LABEL_COL2", rg.getLabelCol2());

        parameters.put("totalLic", rg.getTotalLicencas());

        parameters.put("EST_FEMININO", rg.getFeminino());
        parameters.put("EST_MASCULINO", rg.getMasculino());

        parameters.put("abaixoDe19", rg.getabaixoDe19());
        parameters.put("entre19e30", rg.getEntre19e30());
        parameters.put("entre31e40", rg.getentre31e40());
        parameters.put("entre41e50", rg.getEntre41e50());
        parameters.put("acimaDe50", rg.getAcimaDe50());

        response.setContentType("application/pdf");
        response.addHeader("Content-Disposition", "inline; filename=licenca-data.pdf;");

        String xmlPath = "";

        if (tipo == 5 || tipo == 6) {
            xmlPath = "/jasper/relGerencialJMSDuasColunas.jrxml";
        } else {
            xmlPath = "/jasper/relGerencialJMS.jrxml";
        }

        lrs.toOutputStream(xmlPath, rg.getLista().stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()),
                parameters, response.getOutputStream());
    }

    @RequestMapping(value = "/militar-por-prestador/pdf", method = RequestMethod.GET)
    void militaresAtendidosPorPrestadorPdf(
            @RequestParam(required = false) String prestador,
            @RequestParam(required = false) String conselho,
            @RequestParam(required = false) java.sql.Date dataIni,
            @RequestParam(required = false) java.sql.Date dataFim,
            HttpServletResponse response) throws Exception {
        List<LicencaMilitarPorPrestadorDTO> lista = service.findMilitaresAtendidosPorPrestador(conselho, prestador,
                dataIni, dataFim);

        LicencaReportService lrs = new LicencaReportService();

        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("totalLic", lista.stream().count());

        response.setContentType("application/pdf");
        response.addHeader("Content-Disposition", "inline; filename=licenca-militar-prestador.pdf;");

        lrs.toOutputStream("/jasper/relLicencaMilitarPorPrestadorJMS.jrxml", lista, parameters, response.getOutputStream());
    }

    @RequestMapping(value = "/dias-ininterruptos/pdf", method = RequestMethod.GET)
    void licencasDiasIninterruptos(HttpServletResponse response) throws Exception {
        List<LicencasDiasIninterruptosDTO> lista =
                service.licencasDiasIninterruptos(0, Integer.MAX_VALUE).getContent();

        LicencaReportService lrs = new LicencaReportService();

        HashMap<String, Object> parameters = new HashMap<>();

        response.setContentType("application/pdf");
        response.addHeader("Content-Disposition", "inline; filename=licenca-dias-ininterruptos.pdf;");

        lrs.toOutputStream("/jasper/relLicencaPorDiasIninterruptos.jrxml", lista, parameters, response.getOutputStream());
    }

    @RequestMapping(value = "/militares-afastados-por-ome/pdf", method = RequestMethod.GET)
    void militaresAfastadosPorOmePdf(HttpServletResponse response) throws Exception {
        RelatorioGerencialLicencaDTO rg = new RelatorioGerencialLicencaDTO();
        List<GenericItemRelatorioGerencialDTO> lista = service.militaresAfastadosPorOme(0, Integer.MAX_VALUE).getContent();
        rg.setLabelColunaDescricao("Unidade Militar");
        rg.setLabelCol1("Militares homologados");
        rg.setLabelCol2("Militares lançados");

        rg.setLista(lista);

        LicencaReportService lrs = new LicencaReportService();

        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("DESCRICAO", rg.getLabelColunaDescricao());
        parameters.put("LABEL_COL1", rg.getLabelCol1());
        parameters.put("LABEL_COL2", rg.getLabelCol2());
        parameters.put("totalLic", lista.stream().count());

        response.setContentType("application/pdf");
        response.addHeader("Content-Disposition", "inline; filename=licenca-data.pdf;");

        String xmlPath = "/jasper/relGerencialJMSSemEstatistica.jrxml";

        lrs.toOutputStream(xmlPath, rg.getLista(), parameters, response.getOutputStream());
    }

    @RequestMapping(value = "/militares-afastados-por-cid/pdf", method = RequestMethod.GET)
    void militaresAfastadosPorOme(@RequestParam(required = false) String cid,
                                  HttpServletResponse response) throws Exception {
        List<MilitarAfastadoCidDTO> lista = service.militaresAfastadosPorCid(cid, 0, Integer.MAX_VALUE).getContent();

        LicencaReportService lrs = new LicencaReportService();

        HashMap<String, Object> parameters = new HashMap<>();

        response.setContentType("application/pdf");
        response.addHeader("Content-Disposition", "inline; filename=licenca-militares-afastados-cid.pdf;");

        lrs.toOutputStream("/jasper/relMilitarAfastadoPorCid.jrxml", lista, parameters, response.getOutputStream());
    }

    @RequestMapping(value = "/licencas/pdf", method = RequestMethod.GET)
    void relatorioLicencasPdf(@RequestParam(required = false) java.sql.Date dataIni,
                              @RequestParam(required = false) java.sql.Date dataFim,
                              HttpServletResponse response) throws Exception {
        List<LicencaParecerDTO> lista = service.relatorioLicencas(dataIni, dataFim, 0, Integer.MAX_VALUE).getContent();

        LicencaReportService lrs = new LicencaReportService();

        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("totalLic", lista.size());

        response.setContentType("application/pdf");
        response.addHeader("Content-Disposition", "inline; filename=relatorio-licencas.pdf;");

        lrs.toOutputStream("/jasper/relLicencas.jrxml", lista, parameters, response.getOutputStream());
    }
}
