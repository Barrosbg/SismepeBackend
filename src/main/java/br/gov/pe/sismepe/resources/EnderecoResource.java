package br.gov.pe.sismepe.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.pe.sismepe.domain.Paciente;
import br.gov.pe.sismepe.dto.EnderecoDTO;
import br.gov.pe.sismepe.services.PacienteService;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping(value = "/endereco")
public class EnderecoResource {
	
	//@Autowired
	//private EnderecoService service;
	
	@Autowired
	private PacienteService pacienteService;
	
	@CrossOrigin(origins = "*")
	@PatchMapping("/alterar")
	public ResponseEntity<?> alterar(@RequestBody EnderecoDTO enderecoDto) {
		Paciente paciente = pacienteService.find(enderecoDto.getCdPessoa());
		
//		Endereco end = service.alterar(enderecoDto.toEndereco());
//		
//		Paciente paciente = pacienteService.find(enderecoDto.getCdPessoa());
//		paciente.getPessoa().setEndereco(end);
//		
//		pacienteService.save(paciente);
//		
		return ResponseEntity.ok().body(paciente);
	}
}
