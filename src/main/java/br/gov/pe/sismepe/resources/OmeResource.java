package br.gov.pe.sismepe.resources;

import br.gov.pe.sismepe.domain.Ome;
import br.gov.pe.sismepe.services.OmeService;
import springfox.documentation.annotations.ApiIgnore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ApiIgnore
@RestController
@RequestMapping("/ome")
public class OmeResource {
    @Autowired
    OmeService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Ome>> findAll() {
        List<Ome> omes = service.findAll();

        return ResponseEntity.ok().body(omes);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Ome> find(@PathVariable Long id) {
        Ome ome = service.findById(id);

        return ResponseEntity.ok().body(ome);
    }

    @RequestMapping(value = "/filtro", method = RequestMethod.GET)
    public ResponseEntity<Page<Ome>> filtro (@RequestParam(required = false) String descricao,
                                             @RequestParam(required = false) String abreviacao,
                                             @RequestParam(defaultValue = "0") Integer page,
                                             @RequestParam(defaultValue = "24") Integer linesPerPage,
                                             @RequestParam(defaultValue = "id") String orderBy,
                                             @RequestParam(defaultValue = "ASC") String direction) {
        Page<Ome> omes = service.filtro(descricao, abreviacao, page, linesPerPage, orderBy, direction);

        return ResponseEntity.ok().body(omes);
    }
}
