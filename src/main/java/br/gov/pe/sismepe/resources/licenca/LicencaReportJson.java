package br.gov.pe.sismepe.resources.licenca;

import br.gov.pe.sismepe.domain.Licenca;
import br.gov.pe.sismepe.dto.jms.*;
import br.gov.pe.sismepe.services.LicencaService;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Comparator;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@ApiIgnore
@RestController
@RequestMapping("/licenca/relatorio")
@CrossOrigin
public class LicencaReportJson {
    @Autowired
    LicencaService service;

    @RequestMapping(value = "/licenca-ome", method = RequestMethod.GET)
    ResponseEntity<?> licencaOme(
            @RequestParam Long cdOme,
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
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "24") Integer linesPerPage,
            @RequestParam(defaultValue = "id") String orderBy,
            @RequestParam(defaultValue = "ASC") String direction) {
        Page<Licenca> licencas = service.licencaPorOme(cdOme, tipoParecer, pm, bm, qtdDias, false, dataIni,
                dataFim, opIni, opFim, matricula, disp, lic, page, linesPerPage, orderBy, direction);

        return ResponseEntity.ok().body(licencas);
    }

    @RequestMapping(value = "/licenca-data", method = RequestMethod.GET)
    ResponseEntity<?> licencaPorData(@RequestParam (required = false) Long cdOme,
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
                                     @RequestParam(defaultValue = "0") Integer page,
                                     @RequestParam(defaultValue = "24") Integer linesPerPage,
                                     @RequestParam(defaultValue = "id") String orderBy,
                                     @RequestParam(defaultValue = "ASC") String direction) throws Exception {

        Page<Licenca> licencas = service.licencaPorOme(cdOme, tipoParecer, pm, bm, qtdDias, false, dataIni,
                dataFim, opIni, opFim, matricula, disp, lic, page, linesPerPage, orderBy, direction);

        return ResponseEntity.ok().body(licencas);
    }
    
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('JMS_MEDICO_HOMOLOGA') or hasAuthority('JMS_GESTOR_GERAL')")
    @RequestMapping(value = "/gerencial/parecer", method = RequestMethod.GET)
    ResponseEntity<?> relatorioGerencialPorParecer(@RequestParam(required = false) java.sql.Date dataIni,
                                                   @RequestParam(required = false) java.sql.Date dataFim,
                                                   @RequestParam(defaultValue = "0") Integer tipo,
                                                   @RequestParam(defaultValue = "") Integer matricula,
                                                   @RequestParam(defaultValue = "") String cid) {
        RelatorioGerencialLicencaDTO rg = service.getRelatorio(tipo, dataIni, dataFim, matricula, cid);

        return ResponseEntity.ok().body(rg.getLista().stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()));
    }

    @RequestMapping(value = "/militar-por-prestador", method = RequestMethod.GET)
    ResponseEntity<Page<LicencaMilitarPorPrestadorDTO>> militaresAtendidosPorPrestador(
            @RequestParam(required = false) String prestador,
            @RequestParam(required = false) String conselho,
            @RequestParam(required = false) java.sql.Date dataIni,
            @RequestParam(required = false) java.sql.Date dataFim,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "24") Integer linesPerPage) {
        return ResponseEntity.ok().body(service.findMilitaresAtendidosPorPrestador(conselho, prestador, dataIni,
                dataFim, page, linesPerPage));
    }

    @RequestMapping(value = "/dias-ininterruptos", method = RequestMethod.GET)
    ResponseEntity<Page<LicencasDiasIninterruptosDTO>> licencasDiasIninterruptos(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "24") Integer linesPerPage) {
        return ResponseEntity.ok().body(service.licencasDiasIninterruptos(page, linesPerPage));
    }

    @RequestMapping(value = "/militares-afastados-por-ome", method = RequestMethod.GET)
    ResponseEntity<Page<GenericItemRelatorioGerencialDTO>> militaresAfastadosPorOme(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "24") Integer linesPerPage) {
        return ResponseEntity.ok().body(service.militaresAfastadosPorOme(page, linesPerPage));
    }

    @RequestMapping(value = "/militares-afastados-por-cid", method = RequestMethod.GET)
    ResponseEntity<Page<MilitarAfastadoCidDTO>> militaresAfastadosPorOme(
            @RequestParam(required = false) String cid,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "24") Integer linesPerPage) {
        return ResponseEntity.ok().body(service.militaresAfastadosPorCid(cid, page, linesPerPage));
    }

    @RequestMapping(value = "/licencas", method = RequestMethod.GET)
    ResponseEntity<Page<LicencaParecerDTO>> relatorioLicencas(
            @RequestParam(required = false) java.sql.Date dataIni,
            @RequestParam(required = false) java.sql.Date dataFim,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "24") Integer linesPerPage) {
        return ResponseEntity.ok().body(service.relatorioLicencas(dataIni, dataFim, page, linesPerPage));
    }
}
