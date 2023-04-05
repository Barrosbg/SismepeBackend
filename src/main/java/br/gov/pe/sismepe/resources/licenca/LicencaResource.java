package br.gov.pe.sismepe.resources.licenca;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.pe.sismepe.domain.Licenca;
import br.gov.pe.sismepe.dto.LicencaDTO;
import br.gov.pe.sismepe.services.LicencaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping("/licenca")
@CrossOrigin
@Api(value = "licenca")
public class LicencaResource {
    @Autowired
    LicencaService service;

    @ApiOperation(value = "Busca licenças de um militar a partir da matrícula")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Licenças encontradas com sucesso", content = {@Content(schema = @Schema(implementation = Licenca.class))}),
    		@ApiResponse(responseCode = "404", description = "Licença não encontrada", content = @Content)})

    @GetMapping
    public ResponseEntity<?> find(@RequestParam Integer matricula, @RequestParam(required = false) Integer corporacao) {
        if (corporacao != null) {
            List<Licenca> licencas = service.findByMatriculaAndCorporacao(matricula, corporacao);

            return ResponseEntity.ok().body(licencas);
        }

        List<Licenca> licencas = service.findByMatricula(matricula);

        return ResponseEntity.ok().body(licencas);
    }

    @ApiOperation(value = "Busca licença a partir do id")
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Licenca> findById(@PathVariable Long id) {
        Licenca l = service.findById(id);

        return ResponseEntity.ok().body(l);
    }

    @ApiOperation(value = "Cria nova licença")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> save(@Valid @RequestBody LicencaDTO licencaDTO) {
        Licenca licensaInserida = service.save(licencaDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(licensaInserida);
    }

    @ApiOperation(value = "Altera propriedades de uma licença")
    @RequestMapping(method = RequestMethod.PATCH, value = "/{id}")
    ResponseEntity<?> update(@Valid @RequestBody LicencaDTO licencaDTO, @PathVariable Long id) {
        Licenca licenca = service.update(id, licencaDTO);
        return ResponseEntity.ok().body(licenca);
    }

    @ApiOperation(value = "Faz busca de licenças a partir de diferentes propriedades")
    @RequestMapping(value = "/filtro", method = RequestMethod.GET)
    ResponseEntity<Page<Licenca>> findPage(
            @RequestParam Integer matricula,
            @RequestParam(required = false) Integer corporacao,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "24") Integer linesPerPage,
            @RequestParam(defaultValue = "id") String orderBy,
            @RequestParam(defaultValue = "ASC") String direction) {
        Page<Licenca> licencas = service.findByMatriculaAndCorporacaoPage(matricula, corporacao, page, linesPerPage, orderBy, direction);
        return ResponseEntity.ok().body(licencas);
    }
}