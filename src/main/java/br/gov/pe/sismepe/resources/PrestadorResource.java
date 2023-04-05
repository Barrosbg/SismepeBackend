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

import br.gov.pe.sismepe.domain.Prestador;
import br.gov.pe.sismepe.dto.PrestadorDTO;
import br.gov.pe.sismepe.services.PrestadorService;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping(value = "/prestador")
public class PrestadorResource {

	@Autowired
	private PrestadorService service;
	
	
	/**
	 * Pesquisar um médico pelo Id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Long id){
		Prestador obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	/**
	 * Pesquisar todos os médico
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<PrestadorDTO>> findAll(){
		List<Prestador> list = service.findAll();
		List<PrestadorDTO> listDTO = list.stream().map(obj -> new PrestadorDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	/**
	 * Pesquisar todos os médico paginado
	 */
//	@RequestMapping(value = "/filtro", method = RequestMethod.GET)
//	public ResponseEntity<Page<PrestadorDTO>> findPage(
//			@RequestParam(value = "nome", defaultValue = "") String nome,
//			@RequestParam(value = "esp", required = false) Long idEspecialidade,
//			@RequestParam(value = "numeroConselho", required = false) String numeroConselho,
//			@RequestParam(value = "page", defaultValue = "0") Integer page,
//			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
//			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
//			@RequestParam(value = "direction", defaultValue = "ASC") String directions){
//		Page<Prestador> list = service.findByNomeAndEspecialidade(nome, idEspecialidade, numeroConselho, page, linesPerPage, orderBy, directions);
//		Page<PrestadorDTO> listDTO = list.map(obj -> new PrestadorDTO(obj));
//		return ResponseEntity.ok().body(listDTO);
//	}

	/**
	 * Pesquisar todos os médico paginado
	 */
	@RequestMapping(value = "/filtro", method = RequestMethod.GET)
	public ResponseEntity<Page<PrestadorDTO>> findPage(
			@RequestParam(value = "nome", defaultValue = "") String nome,
			@RequestParam(value = "esp", required = false) Long idEspecialidade,
			@RequestParam(value = "numeroConselho", required = false) String numeroConselho,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String directions){
		Page<Prestador> list = service.findPrestador(nome, idEspecialidade, numeroConselho, page, linesPerPage, orderBy, directions);
		Page<PrestadorDTO> listDTO = list.map(obj -> new PrestadorDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}
	
}
