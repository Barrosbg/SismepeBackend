package br.gov.pe.sismepe.resources;

import br.gov.pe.sismepe.domain.Parecer;
import br.gov.pe.sismepe.repositories.ParecerRepository;
import br.gov.pe.sismepe.services.exceptions.ObjectNotFoundException;
import springfox.documentation.annotations.ApiIgnore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@ApiIgnore
@RestController
@RequestMapping(value = "/parecer")
public class ParecerResource {
    @Autowired
    private ParecerRepository repo;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Parecer>> findAll() {
        List<Parecer> pareceres = repo.findAll();

        return ResponseEntity.ok().body(pareceres);
    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity<Parecer> find(@PathVariable Long id) {
        Parecer parecer = repo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Parecer não encontrado: " + id));

        return ResponseEntity.ok().body(parecer);
    }

    @RequestMapping(value = "/filtro", method = RequestMethod.GET)
    public ResponseEntity<?> filtro(
            @RequestParam(value = "parecer", defaultValue = "") String parecer) {
        List<Parecer> pareceres = repo.findByParecerContaining(parecer);

        return ResponseEntity.ok().body(pareceres);
    }
}
