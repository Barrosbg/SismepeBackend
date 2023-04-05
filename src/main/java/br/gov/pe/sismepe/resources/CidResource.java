package br.gov.pe.sismepe.resources;

import br.gov.pe.sismepe.domain.Cid;
import br.gov.pe.sismepe.repositories.CidRepository;
import br.gov.pe.sismepe.services.CidService;
import springfox.documentation.annotations.ApiIgnore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ApiIgnore
@RestController
@RequestMapping(value = "/cid")
public class CidResource {
    @Autowired
    private CidService service;

    @RequestMapping(value = "/filtro", method = RequestMethod.GET)
    public ResponseEntity<Page<Cid>> findPage(
            @RequestParam(required = false, defaultValue = "") String id,
            @RequestParam(required = false, defaultValue = "") String descricao,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "24") Integer linesPerPage,
            @RequestParam(defaultValue = "id") String orderBy,
            @RequestParam(defaultValue = "ASC") String direction) {
        Page<Cid> cids = service.findCidByIdLikeOrDescricaoContaining(id, descricao, page, linesPerPage, orderBy, direction);

        return ResponseEntity.ok().body(cids);
    }
}
