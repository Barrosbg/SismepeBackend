package br.gov.pe.sismepe.resources;

import br.gov.pe.sismepe.dto.jms.BoletimSaudeDTO;
import br.gov.pe.sismepe.services.BoletimSaudeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ApiIgnore
@RestController
@RequestMapping(value = "/boletim-saude")
@CrossOrigin
@Api(value = "boletim-saude")
public class BoletimSaudeResource {
    @Autowired
    BoletimSaudeService service;

    @ApiOperation(value = "")
    @RequestMapping(value = "/filtro", method = RequestMethod.GET)
    ResponseEntity<Page<BoletimSaudeDTO>> filtro(
            @RequestParam(required = false) Integer matricula,
            @RequestParam(required = false) Integer numero,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "24") Integer linesPerPage) {
        return ResponseEntity.ok().body(service.findByMatriculaAndNumero(matricula, numero, page, linesPerPage));
    }
}
