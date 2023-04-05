package br.gov.pe.sismepe.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.gov.pe.sismepe.domain.Paciente;
import br.gov.pe.sismepe.dto.PacienteDTO;
import br.gov.pe.sismepe.services.PacienteService;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping(value = "/paciente")
public class PacienteResource {

	@Autowired
	private PacienteService service;
	
	/**
	 * Pesquisar um paciente pelo Id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Long id){
		Paciente obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	/**
	 * Pesquisar todos os pacientes
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<PacienteDTO>> findAll(){
		List<Paciente> list = service.findAll();
		List<PacienteDTO> listDTO = list.stream().map(obj -> new PacienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	/**
	 * Pesquisar todos os pacientes paginado e com filtro
	 */
	@RequestMapping(value = "/filtro", method = RequestMethod.GET)
	public ResponseEntity<Page<PacienteDTO>> findPage(
			@RequestParam(value = "paciente", defaultValue = "") String nome, 
			@RequestParam(value = "matricula", defaultValue = "") Integer matricula, 
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "pessoa.nome") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String directions){
		Page<Paciente> list = service.findByPessoaNomeMatricula(nome, matricula, page, linesPerPage, orderBy, directions);
		Page<PacienteDTO> listDTO = list.map(obj -> new PacienteDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}
	
}
