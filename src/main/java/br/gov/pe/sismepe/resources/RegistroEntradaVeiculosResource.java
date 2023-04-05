package br.gov.pe.sismepe.resources;

import java.net.URI;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.gov.pe.sismepe.domain.CadastroDeCarros;
import br.gov.pe.sismepe.domain.RegistroEntradaVeiculos;
import br.gov.pe.sismepe.dto.estacionamento.RegistroEntradaVeiculoDTO;
import br.gov.pe.sismepe.repositories.CadastroDeCarrosRepository;
import br.gov.pe.sismepe.repositories.RegistroEntradaVeiculosRepository;
import br.gov.pe.sismepe.services.RegistroEntradaVeiculoService;
import br.gov.pe.sismepe.util.Utils;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping("/registros")
public class RegistroEntradaVeiculosResource {

	@Autowired
	private RegistroEntradaVeiculosRepository repo;

	@Autowired
	private RegistroEntradaVeiculoService service;

	@Autowired
	private CadastroDeCarrosRepository cadastroDeCarrosRepository;

	@GetMapping
	public ResponseEntity<List<RegistroEntradaVeiculos>> buscartodos() {
		List<RegistroEntradaVeiculos> veliculos = repo.findAll();
		return ResponseEntity.ok().body(veliculos);
	}

	@GetMapping("/situacaoEntrada")
	public ResponseEntity<List<RegistroEntradaVeiculos>> buscarPorSituacaoEntrada() {
		List<RegistroEntradaVeiculos> registros = service.buscarPorSituacao();
		return ResponseEntity.ok().body(registros);
	}

	@PutMapping("/registrosaida/{id}")

	public ResponseEntity<?> registrarSaida(@PathVariable Long id, @RequestBody RegistroEntradaVeiculoDTO registro) {
		RegistroEntradaVeiculos cadRegistro = service.registrarSaidaVeiculo(id, registro);
		return ResponseEntity.ok(cadRegistro);
	}

	@GetMapping(value = "/{placa}")
	public ResponseEntity<?> buscarPorPlacaVeicolosComEntrada(@PathVariable String placa) {

		List<CadastroDeCarros> registro = cadastroDeCarrosRepository.findByPlacaContaining(placa);
		return registro != null ? ResponseEntity.ok().body(registro)
				: ResponseEntity.noContent().build();

	}
	
	@GetMapping("/TotalEstacionados")
	
	public ResponseEntity<?> totalVeiculosEstacionados(){
		Date dataInicio = Utils.trim(new Date(), 0, 0, 0, 0);
		Date dataFim = Utils.trim(new Date(), 23, 59, 59, 59);
		long totalVeiculos = repo.countTotalVeiculosEstacionados(dataInicio,dataFim);
//		long totalVeiculos = repo.countRegistroEntradaVeiculos(situacaoEntrada);
//		System.out.println(repo.countTotalVeiculosEstacionados(dataInicio,dataFim));
		return ResponseEntity.ok(totalVeiculos);
	}

	@PostMapping
	public ResponseEntity<Object> salvar(@RequestBody RegistroEntradaVeiculos registro) {

		RegistroEntradaVeiculos cadRegistro = repo.save(registro);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cadRegistro.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

}
