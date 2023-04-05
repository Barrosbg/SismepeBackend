package br.gov.pe.sismepe.resources;

import br.gov.pe.sismepe.domain.Conselho;
import br.gov.pe.sismepe.repositories.ConselhoRepository;
import br.gov.pe.sismepe.services.ConselhoService;
import br.gov.pe.sismepe.services.exceptions.ObjectNotFoundException;
import springfox.documentation.annotations.ApiIgnore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@ApiIgnore
@RestController
@RequestMapping(value = "/conselho")
public class ConselhoResource {
    @Autowired
    private ConselhoRepository repo;

    @Autowired
    private ConselhoService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Conselho>> findAll() {
        List<Conselho> conselhos = repo.findAll();

        return ResponseEntity.ok().body(conselhos);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Conselho> find(@PathVariable Long id) {
        Conselho conselho = repo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Conselho não encontrado: " + id));

        return ResponseEntity.ok().body(conselho);
    }

    @RequestMapping(value = "/filtro", method = RequestMethod.GET)
    public ResponseEntity<Page<Conselho>> filtro(
            @RequestParam(value = "descricao", defaultValue = "") String descricao,
            @RequestParam(value = "sigla", defaultValue = "") String sigla,
            @RequestParam(value = "uf", defaultValue = "") String uf,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "24") Integer linesPerPage,
            @RequestParam(defaultValue = "sigla") String orderBy,
            @RequestParam(defaultValue = "ASC") String direction) {
        Page<Conselho> conselhos = service.findByDescricaoOrSiglaOrUf(descricao, sigla, uf, page, linesPerPage, orderBy, direction);

        return ResponseEntity.ok().body(conselhos);
    }
}
