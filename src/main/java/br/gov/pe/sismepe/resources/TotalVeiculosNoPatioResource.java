package br.gov.pe.sismepe.resources;

import java.net.URI;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.gov.pe.sismepe.domain.TotalVeiculosNoPatio;
import br.gov.pe.sismepe.dto.estacionamento.totalVeiculosNoPatioDTO;
import br.gov.pe.sismepe.repositories.TotalVeiculosNoPatioRepository;
import br.gov.pe.sismepe.services.TotalVeiculosNoPatioService;
import br.gov.pe.sismepe.util.Utils;
import springfox.documentation.annotations.ApiIgnore;


@ApiIgnore
@RestController
@RequestMapping("/totalVeiculosNoPatio")
public class TotalVeiculosNoPatioResource {
	
	@Autowired
	private TotalVeiculosNoPatioRepository totalVeiculosNoPatioRepository;
	
	@Autowired
	private TotalVeiculosNoPatioService totalVeiculos;
	@GetMapping
	public ResponseEntity<?> buscar() {
		Date dataInicio = Utils.trim(new Date(), 0, 0, 0, 0);
		Date dataFim = Utils.trim(new Date(), 23, 59, 59, 59);

		TotalVeiculosNoPatio total = totalVeiculosNoPatioRepository.findByDataEntradaBetween(dataInicio, dataFim);
		return total !=null ?  ResponseEntity.ok().body(total) : ResponseEntity.noContent().build();
	}


	@PostMapping
	public ResponseEntity<Object> salvarTotal(@Valid @RequestBody totalVeiculosNoPatioDTO registro) throws ParseException {
		
		TotalVeiculosNoPatio cadRegistro = totalVeiculos.salvarTotal(registro);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cadRegistro.getId())
				.toUri();
		System.out.println(uri);
		return ResponseEntity.created(uri).build();
       
	}
	
	
	
}
