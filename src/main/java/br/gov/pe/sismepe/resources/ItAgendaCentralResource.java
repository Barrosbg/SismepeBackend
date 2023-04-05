package br.gov.pe.sismepe.resources;

import java.net.URI;
import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import br.gov.pe.sismepe.domain.ItAgendaCentral;
import br.gov.pe.sismepe.dto.EstatisticaAtendimentoDTO;
import br.gov.pe.sismepe.dto.ItAgendaCentralDTO;
import br.gov.pe.sismepe.dto.ItAgendaCentralDisponivelDTO;
import br.gov.pe.sismepe.services.ItAgendaCentralService;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping(value = "/it-agenda-central")
public class ItAgendaCentralResource {

	@Autowired
	private ItAgendaCentralService service;
	
	/**
	 * Pesquisar um item de agendamento pelo Id
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> buscar(@PathVariable Long id){
		ItAgendaCentral obj = service.find(id);		
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping("/filtro")
	public ResponseEntity<Page<ItAgendaCentralDTO>> buscarTodos(
		@RequestParam(value = "esp", defaultValue = "") Long especialidadeId, 
		@RequestParam(value = "prestador", defaultValue = "") Long prestadorId,
		@RequestParam(value = "paciente", defaultValue = "") Long pacienteId,
		@RequestParam(value = "data", defaultValue = "") String dataAgendamento, 
		@RequestParam(value = "tipo", defaultValue = "A") String tipoAgenda, 
		@RequestParam(value = "situacao", defaultValue = "") String situacaoAgendamento, 
		@RequestParam(value = "situacaoAtend", defaultValue = "") String situacaoAtendimento, 
		@RequestParam(value = "page", defaultValue = "0") Integer page, 
		@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
		@RequestParam(value = "orderBy", defaultValue = "id") String orderBy, 
		@RequestParam(value = "direction", defaultValue = "ASC") String directions) throws ParseException{
		
		Page<ItAgendaCentral> list = service.findAllByFiltro(especialidadeId, prestadorId, pacienteId, dataAgendamento, 
				tipoAgenda, situacaoAgendamento, situacaoAtendimento, page, linesPerPage, orderBy, directions);
		Page<ItAgendaCentralDTO> listDTO = list.map(obj -> new ItAgendaCentralDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}
	
	@GetMapping("/disp")
	public ResponseEntity<Page<ItAgendaCentralDisponivelDTO>> buscarDisp(
		@RequestParam(value = "esp", defaultValue = "") Long especialidadeId, 
		@RequestParam(value = "prestador", defaultValue = "") Long prestadorId,
		@RequestParam(value = "page", defaultValue = "0") Integer page, 
		@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
		@RequestParam(value = "orderBy", defaultValue = "id") String orderBy, 
		@RequestParam(value = "direction", defaultValue = "ASC") String directions){
		Page<ItAgendaCentralDisponivelDTO> listDTO = service.buscarItensLivres(especialidadeId, prestadorId, page, linesPerPage, orderBy, directions);
		return ResponseEntity.ok().body(listDTO);
	}
	
	@PostMapping
	public ResponseEntity<Void> inserir(@Valid @RequestBody ItAgendaCentralDTO objDTO){
		ItAgendaCentral obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizar(@Valid @RequestBody ItAgendaCentralDTO objDTO, @PathVariable Long id) {
		ItAgendaCentral obj = service.fromDTO(objDTO);
		obj.setId(id);
		service.update(obj,objDTO.getSituacaoAtendimento());
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/estatisticas/atendimentos")
	public ResponseEntity<List<EstatisticaAtendimentoDTO>> filtrarEstatisticasDeAtendimentos(
		@RequestParam(value = "prestador", defaultValue = "") Long prestadorId,
		@RequestParam(value = "paciente", defaultValue = "") Long pacienteId,
		@RequestParam(value = "periodo", defaultValue = "semanal") String periodo){
		
		List<EstatisticaAtendimentoDTO> list = service.pesquisarEstatisticasDeAtendimento(prestadorId, pacienteId, periodo);		
		return ResponseEntity.ok().body(list);
	}
	
}
