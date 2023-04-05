package br.gov.pe.sismepe.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.pe.sismepe.domain.CepLogradouro;
import br.gov.pe.sismepe.repositories.CepLogradouroRepository;
import br.gov.pe.sismepe.services.CepLogradouroService;

@RestController
@RequestMapping(value="/logradouro")
public class CepLogradouroResource {
	
	@Autowired
	private CepLogradouroRepository cepRepo;

	@Autowired
	private CepLogradouroService cepService;
	
	@GetMapping("/{cep}")
	public ResponseEntity<List<CepLogradouro>> buscarPorCep(@PathVariable Long cep){
		 List<CepLogradouro> log = cepService.buscarCep(cep);
		
       return !log.isEmpty() ? ResponseEntity.ok(log) : ResponseEntity.noContent().build();
	}
	
	
}
